#include <stdio.h>

__global__ void myKernel(void){

}

int main(void){

    myKernel<<<1,1>>>();
    printf("Hello World!\n");
    return 0;
}
