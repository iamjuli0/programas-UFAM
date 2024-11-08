// controle_robo.h
#ifndef CONTROLE_ROBO_H
#define CONTROLE_ROBO_H

#include <pthread.h>

// Definição dos períodos em nanosegundos
#define PERIOD_SIM_ROBO 30000000      // 30ms
#define PERIOD_LINEARIZACAO 40000000  // 40ms
#define PERIOD_CONTROLE 50000000      // 50ms
#define PERIOD_REF 50000000           // 50ms
#define PERIOD_GERACAO_REF 120000000  // 120ms
#define PERIOD_INTERFACE 100000000    // 100ms

// Estruturas de dados compartilhados
typedef struct {
    double x_c;
    double y_c;
    double theta;
} EstadoRobo;

typedef struct {
    double v;
    double omega;
} EntradaControle;

// Variáveis globais compartilhadas (declaradas como 'extern' para serem usadas em outros arquivos)
extern EstadoRobo estado_robo;
extern EntradaControle u, v;
extern double x_ref, y_ref;
extern double y_mx, y_my;

// Mutexes para sincronização
extern pthread_mutex_t mutex_estado;
extern pthread_mutex_t mutex_u;
extern pthread_mutex_t mutex_v;
extern pthread_mutex_t mutex_ref;

// Funções para inicialização e controle
void* sim_robo(void* arg);
void* malha_linearizacao(void* arg);
void* malha_controle(void* arg);
void* simulacao_referencia(void* arg);
void* geracao_referencias(void* arg);
void* interface_usuario(void* arg);

long get_time_ns();
void wait_next_period(struct timespec *next_activation, long period_ns);

#endif // CONTROLE_ROBO_H
