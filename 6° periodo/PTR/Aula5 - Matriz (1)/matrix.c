#include <stdio.h>
#include <stdlib.h>

#include <stdbool.h>
#include "matrix.h"

static inline bool test_range(int rows, int cols){
    return (rows >= 0 && rows <= MAX_ROWS && cols >= 0 && cols <= MAX_COLS);
}

Matrix matrix_init(int rows, int cols, matrix_values values){
    if(test_range(rows,cols)){
        Matrix m = matrix_nul;
        m.rows = rows;
        m.cols = cols;
        
        for(int i = 0; i < m.rows; i++){
            for(int j = 0; j < m.cols; j++){
                m.values[i][j] = values[i][j];
            }
        }
        return m;
    }
    fprintf(stderr, "Cannot initialize a matrix, number of rows or cols invalid.\n");
    return matrix_nul;

}

Matrix matrix_zeros(int rows, int cols){
    if(test_range(rows, cols)){
        Matrix m;
        m.rows = rows;
        m.cols = cols;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < rows; j++){
                m.values[i][j] = 0.0;
            }
        }
        return m;
    }
    fprintf(stderr, "Cannot initialize a zero matrix with a invalid number of rows or cols.\n");
}

Matrix matrix_ones(int rows, int cols){
    if(test_range(rows, cols)){
        Matrix m;
        m.rows = rows;
        m.cols = cols;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < rows; j++){
                m.values[i][j] = 1.0;
            }
        }
        return m;
    }
    fprintf(stderr, "Cannot initialize a one matrix with a invalid number of rows or cols.\n");
}

Matrix matrix_identity(int rows, int cols){
    if(test_range(rows, cols)){
        Matrix m;
        m.rows = rows;
        m.cols = cols;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(i == j){
                    m.values[i][j] = 1.0;
                } else{
                    m.values[i][j] = 0.0;
                }
            }
        }
        return m;
    }
    fprintf(stderr, "Cannot initialize a one matrix with a invalid number of rows or cols.\n");
}

Matrix matrix_add(Matrix a, Matrix b){
    if(test_range(a.rows, a.cols) && test_range(b.rows, b.cols)){
        if(a.rows == b.rows && a.cols == b.cols){
            Matrix m;
            m.rows = a.rows;
            m.cols = a.cols;
            for(int i = 0; i < m.rows; i++){
                for(int j = 0; j < m.cols; j++){
                    m.values[i][j] = a.values[i][j] + b.values[i][j];
                }
            }
            return m;
        }
        fprintf("Erro 1!");
        return matrix_nul;
    }
    fprintf("Erro 2!");
    return matrix_nul;
}

Matrix matrix_sub(Matrix a, Matrix b){
    if(test_range(a.rows, a.cols) && test_range(b.rows, b.cols)){
        if(a.rows == b.rows && a.cols == b.cols){
            Matrix m;
            m.rows = a.rows;
            m.cols = a.cols;
            for(int i = 0; i < m.rows; i++){
                for(int j = 0; j < m.cols; j++){
                    m.values[i][j] = a.values[i][j] - b.values[i][j];
                }
            }
            return m;
        }
        fprintf("Erro 1!");
        return matrix_nul;
    }
    fprintf("Erro 2!");
    return matrix_nul;
}

Matrix matrix_mul(Matrix a, Matrix b){
    return 0;
}

void print_matrix(char *name, Matrix m){
    printf("%s(%d,%d) = [%c]", );
}