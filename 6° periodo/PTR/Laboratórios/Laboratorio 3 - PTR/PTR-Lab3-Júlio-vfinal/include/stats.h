#ifndef STATS_H
#define STATS_H

#include <stddef.h>

// Estrutura para armazenar resultados estatísticos
typedef struct {
    double media;
    double variancia;
    double desvio_padrao;
    double maximo;
    double minimo;
} Estatisticas;

// Funções para cálculo de estatísticas
Estatisticas calcular_estatisticas(double *dados, size_t tamanho);

// Função para ler os dados de T(k) e calcular J(k) a partir de um arquivo
size_t ler_dados(const char *filename, double **tempos, double **t_values, double **j_values);

void imprimir_tabela(Estatisticas stats_t, Estatisticas stats_j);

#endif // STATS_H
