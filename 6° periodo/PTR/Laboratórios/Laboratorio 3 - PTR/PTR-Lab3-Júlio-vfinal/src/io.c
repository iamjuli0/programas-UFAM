#include "io.h"
#include <stdio.h>

void atualizar_entrada(Sistema *sistema, double t) {
    if (t < 0) {
        sistema->u[0] = 0.0;
        sistema->u[1] = 0.0;
    } else if (t >= 0 && t < 10) {
        sistema->u[0] = 1.0; // v
        sistema->u[1] = 0.2 * M_PI; // w
    } else {
        sistema->u[0] = 1.0; // v
        sistema->u[1] = -0.2 * M_PI; // w
    }
}

void salvar_estado(FILE *arquivo, double t, Sistema *sistema) {
    static int cabecalho = 0;
    if (!cabecalho) {
        fprintf(arquivo, "t(s)\t|v|\t\t|w|\t\t|xc|\t|yc|\t|θ|\n");
        fprintf(arquivo, "-------------------------------------------------\n");
        cabecalho = 1;
    }

    fprintf(arquivo, "%.5f\t%.5f\t%.5f\t%.2f\t%.2f\t%.2f\n",
            t, sistema->u[0], sistema->u[1], sistema->yf[0], sistema->yf[1], sistema->yf[2]);
}
