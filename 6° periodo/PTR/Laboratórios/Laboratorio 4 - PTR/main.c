#include <pthread.h>
#include "controle_robo.h"

int main() {
    pthread_t thread_sim_robo, thread_linearizacao, thread_controle;
    pthread_t thread_sim_ref, thread_geracao_ref, thread_interface;

    // Inicialização das variáveis globais
    estado_robo.x_c = 0.0;
    estado_robo.y_c = 0.0;
    estado_robo.theta = 0.0;
    u.v = 0.0;
    u.omega = 0.0;
    v.v = 0.0;
    v.omega = 0.0;
    x_ref = 0.0;
    y_ref = 0.0;
    y_mx = 0.0;
    y_my = 0.0;

    // Criação das threads
    pthread_create(&thread_sim_robo, NULL, sim_robo, NULL);
    pthread_create(&thread_linearizacao, NULL, malha_linearizacao, NULL);
    pthread_create(&thread_controle, NULL, malha_controle, NULL);
    pthread_create(&thread_sim_ref, NULL, simulacao_referencia, NULL);
    pthread_create(&thread_geracao_ref, NULL, geracao_referencias, NULL);
    pthread_create(&thread_interface, NULL, interface_usuario, NULL);

    // Espera pelas threads
    pthread_join(thread_sim_robo, NULL);
    pthread_join(thread_linearizacao, NULL);
    pthread_join(thread_controle, NULL);
    pthread_join(thread_sim_ref, NULL);
    pthread_join(thread_geracao_ref, NULL);
    pthread_join(thread_interface, NULL);

    return 0;
}
