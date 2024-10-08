#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "funcoes.h"

#define PI 3.14159265359
#define DT 0.01 // Passo de tempo (10 ms)
#define TEMPO_FINAL 20.0 // Simular por 20 segundos


int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Uso: %s <nome_do_arquivo>\n", argv[0]);
        return -1;
    }

    const char *nome_arquivo = argv[1];

    // Inicializar o estado do robô
    RoboEstado robo = {
        .x = {0.0, 0.0, 0.0}, // Estado inicial [x_c, y_c, theta]
        .u = {0.0, 0.0}       // Entrada inicial [v, omega]
    };

    // Simular e salvar em arquivo
    simular_e_salvar(&robo, nome_arquivo);

    return 0;
}
