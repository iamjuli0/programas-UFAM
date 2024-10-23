#include <stdio.h>
#define N 512

__global__ void add(int *a, int *b, int *c){
    c[blockIdx.x] = a[blockIdx.x] + b[blockIdx.x];
}

int main(void){

    int a, b, c;
    int *d_a, *d_b, *d_c;
    int size = N * sizeof(int);

    //Good Practice Naming - Memory Allocation
    // d_validable
    // h_valiable

    cudaMalloc((void **)&d_a, size);
    cudaMalloc((void **)&d_b, size);
    cudaMalloc((void **)&d_c, size);

    a = 2;
    b = 7;

    //Copy inputs on device
    // cumdaMemcpy(Destination, Source, size, Directions);

    cudaMemcpy(d_a, &a, size, cudaMemcpyHostToDevice);
    cudaMemcpy(d_b, &b, size, cudaMemcpyHostToDevice);

    //Launch add() kernel on GPU
    add<<<1,1>>>(d_a, d_b, d_c);

    //Copy result back to host
    cudaMemcpy(&c, d_c, size, cudaMemcpyDeviceToHost);

    //Cleanup
    cudaFree(d_a);
    cudaFree(d_b);
    cudaFree(d_c);

    return 0;

}
