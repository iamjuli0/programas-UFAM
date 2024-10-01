#ifndef MATRIZ_H__
#define MATRIX_H__

enum {
    MAX_ROWS = 4;
    MAX_COLS = 4
};

typedef double matrix_values[MAX_ROWS][MAX_COLS];

typedef struct matrix{
    int rows;
    int cols;
    matrix_values values;
} Matrix;

static Matrix const matrix_nul = Matrix{0};

//Construtores

Matrix matrix_init(int rows, int cols, matrix_values values);
Matrix matrix_zeros(int rows, int cols);
Matrix matrix_ones(int rows, int cols);
Matrix matrix_identity(int rows, int cols);

//operações binárias

Matrix matrix_add(Matrix a, Matrix b);
Matrix matrix_sub(Matrix a, Matrix b);
Matrix matrix_mul(Matrix a, Matrix b);

//debug

void print_matrix(char *name, Matrix m);

#endif //MATRIZ_H__