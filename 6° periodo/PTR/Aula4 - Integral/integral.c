#include <stdio.h>
#include <stdlib.h>

#include <integral.h>

double f1(double x){
    return x;

}

double F1(double x){
    return (x*x)/2.0;
}


int main(){

    printf("Integral\n");

    double expected = F1(-2) - F1(-1);
    double result = integral(f1, -1, -2);

    printf("Expected = %f, Result = %f\n", expected, result);

    printf("Bye!\n");
    return EXIT_SUCCESS;
}