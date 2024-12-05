#ifndef SISTEMA_H
#define SISTEMA_H

#include <pthread.h>

// Definição do estado do sistema
typedef struct {
    double x[3];
    double u[2];
    double yf[3];
    double D;
} Sistema;

// Inicializa o sistema com valores iniciais
void inicializar_sistema(Sistema *sistema);

// Atualiza o estado x(t) usando o modelo do sistema
void atualizar_estado(Sistema *sistema, double dt);

// Calcula yf(t) (ponto frontal do robô)
void calcular_saida(Sistema *sistema);

#endif // SISTEMA_H
