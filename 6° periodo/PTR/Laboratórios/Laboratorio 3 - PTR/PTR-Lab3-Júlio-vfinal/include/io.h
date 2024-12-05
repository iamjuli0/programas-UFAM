#ifndef IO_H
#define IO_H
#define M_PI 3.14159265358979323846

#include <stdio.h>
#include "sistema.h"

// Atualiza u(t) baseado no tempo
void atualizar_entrada(Sistema *sistema, double t);

// Salva o estado atual em um arquivo
void salvar_estado(FILE *arquivo, double t, Sistema *sistema);

#endif // IO_H
