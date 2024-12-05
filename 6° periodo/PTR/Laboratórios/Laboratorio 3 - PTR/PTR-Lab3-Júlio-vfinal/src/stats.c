#include "stats.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <float.h>

Estatisticas calcular_estatisticas(double *dados, size_t tamanho) {
    Estatisticas stats = {0};
    if (tamanho == 0) return stats;

    double soma = 0.0, soma_quadrados = 0.0;
    stats.maximo = -DBL_MAX;
    stats.minimo = DBL_MAX;

    for (size_t i = 0; i < tamanho; i++) {
        soma += dados[i]*1000;
        if (dados[i]*1000 > stats.maximo) stats.maximo = dados[i]*1000;
        if (dados[i]*1000 < stats.minimo) stats.minimo = dados[i]*1000;
    }

    stats.media = soma / tamanho;
    for(size_t i = 0; i < tamanho; i++){
        soma_quadrados += pow(dados[i]*1000 - stats.media, 2);
    }
    
    stats.variancia = soma_quadrados / tamanho;
    stats.desvio_padrao = sqrt(stats.variancia);

    return stats;
}

size_t ler_dados(const char *filename, double **tempos, double **t_values, double **j_values) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        return 0;
    }

    size_t capacidade = 100, tamanho = 0;
    *tempos = (double *)malloc(capacidade * sizeof(double));
    *t_values = NULL; // Será alocado posteriormente
    *j_values = NULL;

    char linha[256];
    int ignorar_cabecalho = 1;

    while (fgets(linha, sizeof(linha), file)) {
        if (ignorar_cabecalho) {
            ignorar_cabecalho = 0; // Ignora a primeira linha (cabeçalho)
            continue;
        }

        double t, v, w, xc, yc, theta;
        if (sscanf(linha, "%lf %lf %lf %lf %lf %lf", &t, &v, &w, &xc, &yc, &theta) == 6) {
            if (tamanho == capacidade) {
                capacidade *= 2;
                *tempos = (double *)realloc(*tempos, capacidade * sizeof(double));
            }
            (*tempos)[tamanho++] = t;
        }
    }
    fclose(file);

    // Calcula J(k) = T(k+1) - T(k)
    if (tamanho > 1) {
        *t_values = (double *)malloc((tamanho - 1) * sizeof(double));
        for (size_t i = 0; i < tamanho - 1; i++) {
            (*t_values)[i] = (*tempos)[i + 1] - (*tempos)[i];
        }
    }

    if (tamanho > 1) {
        *j_values = (double *)malloc((tamanho - 1) * sizeof(double));
        for (size_t i = 0; i < tamanho - 1; i++) {
            (*j_values)[i] = ((*tempos)[i + 1] - (*tempos)[i]) - 0.050;
        }
    }

    return tamanho;
}

void imprimir_tabela(Estatisticas stats_t, Estatisticas stats_j) {
    printf("\nTabela Comparativa:\n");
    printf("         -------------------------------------------------------------------------\n");
    printf("        | Média(ms)    | Variância(ms)| Desvio      | Máximo(ms)   | Mínimo(ms)   |\n");
    printf("        |              |              | Padrão(ms)  |              |              |\n");
    printf("----------------------------------------------------------------------------------\n");
    printf("| T(k)  | %7.8f  | %9.8f | %8.8f | %7.8f | %7.8f |\n",
           stats_t.media, stats_t.variancia, stats_t.desvio_padrao,
           stats_t.maximo, stats_t.minimo);
    printf("| J(k)  | %7.9f  | %9.8f | %8.8f | %7.8f | %7.8f |\n",
           stats_j.media, stats_j.variancia, stats_j.desvio_padrao,
           stats_j.maximo, stats_j.minimo);
    printf("----------------------------------------------------------------------------------\n");
}

