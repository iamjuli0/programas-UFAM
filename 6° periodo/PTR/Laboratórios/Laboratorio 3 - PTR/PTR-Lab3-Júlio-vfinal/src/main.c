#include "sistema.h"
#include "io.h"
#include "utils.h"
#include "stats.h"

#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#define SIM_STEP 30  // Período de simulação em ms
#define OUTPUT_STEP 50.0 // Período de saída em ms
#define SIM_TIME 20.0 // Tempo total de simulação em segundos

Sistema sistema;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void *thread_simulacao(void *) { //uthread
    double t = 0.0;
    struct timespec tp_start, tp_stop;

    FILE *arquivo = fopen("saida.txt", "w");
    if (!arquivo) {
        perror("Erro ao abrir o arquivo");
        return NULL;
    }

    clock_gettime(CLOCK_MONOTONIC, &tp_start);
    while (t <= SIM_TIME) {
        clock_gettime(CLOCK_MONOTONIC, &tp_stop);
        t = timespec_diff(&tp_start, &tp_stop);

        pthread_mutex_lock(&mutex);
        atualizar_entrada(&sistema, t);
        atualizar_estado(&sistema, SIM_STEP / 1000.0); // Converte ms para s
        pthread_mutex_unlock(&mutex);

        struct timespec req = {.tv_sec = 0, .tv_nsec = OUTPUT_STEP*1000000};
        nanosleep(&req, NULL);
    }

    fclose(arquivo);
    return NULL;
}

void *thread_saida(void *) { //ythread
    double t = 0.0;
    struct timespec tp_start, tp_stop;

    FILE *arquivo = fopen("saida.txt", "a");
    if (!arquivo) {
        perror("Erro ao abrir o arquivo");
        return NULL;
    }

    clock_gettime(CLOCK_MONOTONIC, &tp_start);
    while (t <= SIM_TIME) {
        clock_gettime(CLOCK_MONOTONIC, &tp_stop);
        t = timespec_diff(&tp_start, &tp_stop);

        pthread_mutex_lock(&mutex);
        calcular_saida(&sistema);
        salvar_estado(arquivo, t, &sistema);
        pthread_mutex_unlock(&mutex);

        struct timespec req = {.tv_sec = 0, .tv_nsec = 50000000};
        nanosleep(&req, NULL);
    }

    fclose(arquivo);
    return NULL;
}

void gerar_graficos(const char *filename) {
    FILE *gnuplotPipe = popen("gnuplot -persistent", "w");
    if (gnuplotPipe == NULL) {
        fprintf(stderr, "Erro ao abrir o Gnuplot.\n");
        return;
    }

    fprintf(gnuplotPipe, "set title 'Gráfico com múltiplos eixos Y'\n"); // Título
    fprintf(gnuplotPipe, "set xlabel 'Tempo (t) [s]'\n");                // Rótulo do eixo X
    fprintf(gnuplotPipe, "set ylabel 'Valores'\n");                      // Rótulo do eixo Y
    fprintf(gnuplotPipe, "set grid\n");                                  // Grade
    fprintf(gnuplotPipe, "set key outside\n");                           // Legenda fora do gráfico

    //fprintf(gnuplotPipe, "set multiplot layout 2,1 title 'Gráficos Combinados'\n");

    fprintf(gnuplotPipe,
            "plot '%s' using 1:4 with lines title '|xc|', "
            "'%s' using 1:5 with lines title '|yc|', "
            "'%s' using 1:6 with lines title '|θ|'\n",
            filename, filename, filename);

    /*fprintf(gnuplotPipe,
            "plot '%s' using 4:5 with lines title 'Trajetória: |xc| x |yc|'\n",
            filename);*/

    //fprintf(gnuplotPipe, "unset multiplot\n");

    pclose(gnuplotPipe);

    printf("Gráfico gerado com sucesso para o arquivo '%s'.\n", filename);
}


int main() {
    pthread_t thread1, thread2;
    const char *arquivo = "saida.txt";
    double *tempos = NULL, *t_values = NULL, *j_values = NULL;
    Estatisticas stats_t, stats_j;
    size_t tamanho;

    inicializar_sistema(&sistema);

    pthread_create(&thread1, NULL, thread_simulacao, NULL); //uthread
    pthread_create(&thread2, NULL, thread_saida, NULL);     //yfthread

    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    gerar_graficos(arquivo);

    tamanho = ler_dados(arquivo, &tempos, &t_values, &j_values);
    if (tamanho == 0) {
        printf("Erro ao processar os dados.\n");
        return 1;
    }

    stats_t = calcular_estatisticas(t_values, tamanho - 1);
    stats_j = calcular_estatisticas(j_values, tamanho - 1);

    imprimir_tabela(stats_t, stats_j);

    free(tempos);
    free(t_values);
    free(j_values);

    return 0;
}
