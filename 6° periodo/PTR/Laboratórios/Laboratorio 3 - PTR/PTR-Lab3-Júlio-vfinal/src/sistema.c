#include "sistema.h"
#include <math.h>
#include <stdio.h>

// Inicializa o sistema
void inicializar_sistema(Sistema *sistema) {
    sistema->x[0] = 0.0; // x_c
    sistema->x[1] = 0.0; // y_c
    sistema->x[2] = 0.0; // theta
    sistema->u[0] = 0.0; // v
    sistema->u[1] = 0.0; // w
    sistema->D = 1.0;    // Diâmetro do robô
}

// Atualiza o estado do sistema
void atualizar_estado(Sistema *sistema, double dt) {
    double x_dot[3];
    x_dot[0] = sin(sistema->x[2]) * sistema->u[0]; // dx_c/dt
    x_dot[1] = cos(sistema->x[2]) * sistema->u[0]; // dy_c/dt
    x_dot[2] = sistema->u[1];                      // dtheta/dt

    for (int i = 0; i < 3; i++) {
        sistema->x[i] += dt * x_dot[i];
    }
}

// Calcula yf(t)
void calcular_saida(Sistema *sistema) {
    sistema->yf[0] = sistema->x[0] + (0.5 * sistema->D * cos(sistema->x[2]) * sistema->x[0]);
    sistema->yf[1] = sistema->x[1] + (0.5 * sistema->D * sin(sistema->x[2]) * sistema->x[1]);
    sistema->yf[2] = sistema->x[2] + (sistema->x[2]);
}
