#ifndef FUNCOES_H__
#define FUNCOES_H__

// Estrutura do robô
typedef struct {
    double x[3]; // [x_c, y_c, theta]
    double u[2]; // [v, omega]
} RoboEstado;

void atualizar_estado(RoboEstado *robo);
void definir_entrada(RoboEstado *robo, double t);
void calcular_saida(RoboEstado *robo, double y[3]);
void simular_e_salvar(RoboEstado *robo, const char *nome_arquivo);

#endif //FUNCOES_H__