#include "controle_robo.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

// Variáveis globais compartilhadas
EstadoRobo estado_robo;
EntradaControle u, v;
double x_ref, y_ref;
double y_mx, y_my;

// Mutexes para sincronização
pthread_mutex_t mutex_estado = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_u = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_v = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_ref = PTHREAD_MUTEX_INITIALIZER;

// Função para obter o tempo atual em nanosegundos
long get_time_ns() {
    struct timespec ts;
    clock_gettime(CLOCK_REALTIME, &ts);
    return ts.tv_sec * 1e9 + ts.tv_nsec;
}

// Função de espera até o próximo período
void wait_next_period(struct timespec *next_activation, long period_ns) {
    next_activation->tv_nsec += period_ns;
    while (next_activation->tv_nsec >= 1e9) {
        next_activation->tv_nsec -= 1e9;
        next_activation->tv_sec += 1;
    }
    clock_nanosleep(CLOCK_REALTIME, TIMER_ABSTIME, next_activation, NULL);
}

// Implementações das threads

void* sim_robo(void* arg) {
    struct timespec next_activation;
    clock_gettime(CLOCK_REALTIME, &next_activation);

    while (1) {
        pthread_mutex_lock(&mutex_estado);
        pthread_mutex_lock(&mutex_u);
        
        double dt = PERIOD_SIM_ROBO / 1e9;
        estado_robo.x_c += u.v * cos(estado_robo.theta) * dt;
        estado_robo.y_c += u.v * sin(estado_robo.theta) * dt;
        estado_robo.theta += u.omega * dt;
        
        pthread_mutex_unlock(&mutex_u);
        pthread_mutex_unlock(&mutex_estado);

        wait_next_period(&next_activation, PERIOD_SIM_ROBO);
    }
    return NULL;
}

void* malha_linearizacao(void* arg) {
    struct timespec next_activation;
    clock_gettime(CLOCK_REALTIME, &next_activation);

    while (1) {
        pthread_mutex_lock(&mutex_estado);
        pthread_mutex_lock(&mutex_v);
        pthread_mutex_lock(&mutex_u);
        
        double theta = estado_robo.theta;
        
        double L11 = cos(theta);
        double L12 = -sin(theta);
        double L21 = sin(theta);
        double L22 = cos(theta);

        u.v = L11 * v.v + L12 * v.omega;
        u.omega = L21 * v.v + L22 * v.omega;
        
        pthread_mutex_unlock(&mutex_u);
        pthread_mutex_unlock(&mutex_v);
        pthread_mutex_unlock(&mutex_estado);

        wait_next_period(&next_activation, PERIOD_LINEARIZACAO);
    }
    return NULL;
}

void* malha_controle(void* arg) {
    struct timespec next_activation;
    clock_gettime(CLOCK_REALTIME, &next_activation);

    while (1) {
        pthread_mutex_lock(&mutex_v);
        pthread_mutex_lock(&mutex_ref);

        double alpha1 = 2.0;
        double alpha2 = 3.0;

        v.v = alpha1 * (x_ref - y_mx);
        v.omega = alpha2 * (y_ref - y_my);
        
        pthread_mutex_unlock(&mutex_ref);
        pthread_mutex_unlock(&mutex_v);

        wait_next_period(&next_activation, PERIOD_CONTROLE);
    }
    return NULL;
}

void* simulacao_referencia(void* arg) {
    struct timespec next_activation;
    clock_gettime(CLOCK_REALTIME, &next_activation);

    while (1) {
        pthread_mutex_lock(&mutex_ref);

        double alpha1 = 2.0;
        double alpha2 = 3.0;

        y_mx += alpha1 * (x_ref - y_mx);
        y_my += alpha2 * (y_ref - y_my);
        
        pthread_mutex_unlock(&mutex_ref);

        wait_next_period(&next_activation, PERIOD_REF);
    }
    return NULL;
}

void* geracao_referencias(void* arg) {
    struct timespec next_activation;
    clock_gettime(CLOCK_REALTIME, &next_activation);

    double t = 0.0;

    while (1) {
        pthread_mutex_lock(&mutex_ref);

        x_ref = 5 / M_PI * cos(0.2 * M_PI * t);
        y_ref = (t <= 10) ? 5 / M_PI * sin(0.2 * M_PI * t) : 5 / M_PI * sin(0.2 * M_PI * (t - 10));
        
        pthread_mutex_unlock(&mutex_ref);

        t += PERIOD_GERACAO_REF / 1e9;

        wait_next_period(&next_activation, PERIOD_GERACAO_REF);
    }
    return NULL;
}

void* interface_usuario(void* arg) {
    struct timespec next_activation;
    clock_gettime(CLOCK_REALTIME, &next_activation);

    FILE *fp = fopen("dados.txt", "w");
    if (fp == NULL) {
        perror("Erro ao abrir arquivo");
        exit(EXIT_FAILURE);
    }

    while (1) {
        pthread_mutex_lock(&mutex_estado);
        fprintf(fp, "x: %f, y: %f, theta: %f\n", estado_robo.x_c, estado_robo.y_c, estado_robo.theta);
        pthread_mutex_unlock(&mutex_estado);

        wait_next_period(&next_activation, PERIOD_INTERFACE);
    }
    fclose(fp);
    return NULL;
}
