#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "funcoes.h"

#define PI 3.14159265359
#define DT 0.01 // Passo de tempo (10 ms)
#define TEMPO_FINAL 20.0 // Simular por 20 segundos

// Função para atualizar o estado (Método de Euler)
void atualizar_estado(RoboEstado *robo) {
    double x_c = robo->x[0];
    double y_c = robo->x[1];
    double theta = robo->x[2];

    double veloc = robo->u[0];      // Velocidade linear v
    double veloc_ang = robo->u[1];  // Velocidade angular omega

    // Equações diferenciais do sistema:
    double dx[3]; 
    dx[0] = veloc * sin(theta);  // dx_c/dt = v * sin(theta)
    dx[1] = veloc * cos(theta);  // dy_c/dt = v * cos(theta)
    dx[2] = veloc_ang;           // dtheta/dt = omega

    // Atualizar o estado usando o método de Euler
    robo->x[0] += dx[0] * DT;  // Atualiza x_c
    robo->x[1] += dx[1] * DT;  // Atualiza y_c
    robo->x[2] += dx[2] * DT;  // Atualiza theta
}

// Função para definir a entrada u(t)
void definir_entrada(RoboEstado *robo, double t) {
    if (t < 0) {
        robo->u[0] = 0;        // v = 0
        robo->u[1] = 0;        // omega = 0
    } else if (t >= 0 && t <= 10) {
        robo->u[0] = 1;        // v = 1
        robo->u[1] = 0.2 * PI; // omega = 0.2*pi
    } else {
        robo->u[0] = 1;        // v = 1
        robo->u[1] = -0.2 * PI; // omega = -0.2*pi
    }
}

// Função para calcular a saída y(t)
void calcular_saida(RoboEstado *robo, double y[3]) {
    // Matriz de saída y(t) = C*x(t)
    y[0] = robo->x[0]; // x_c
    y[1] = robo->x[1]; // y_c
    y[2] = robo->x[2]; // theta
}

// Função para simular e salvar os resultados em arquivo
void simular_e_salvar(RoboEstado *robo, const char *nome_arquivo) {
    FILE *arquivo = fopen(nome_arquivo, "w");
    if (arquivo == NULL) {
        printf("Erro ao abrir arquivo %s\n", nome_arquivo);
        return;
    }

    // Cabeçalho do arquivo
    fprintf(arquivo, "t\tv\tomega\tx_c\ty_c\ttheta\n");

    // Simulação do sistema
    for (double t = 0; t <= TEMPO_FINAL; t += DT) {
        definir_entrada(robo, t);
        atualizar_estado(robo);

        // Calcular a saída
        double y[3];
        calcular_saida(robo, y);

        // Gravar os dados no arquivo
        fprintf(arquivo, "%lf\t%lf\t%lf\t%lf\t%lf\t%lf\n", t, robo->u[0], robo->u[1], y[0], y[1], y[2]);
    }

    fclose(arquivo);
    printf("Simulação salva em %s\n", nome_arquivo);
}