#include <stdlib.h>
#include <stdio.h>

#include "buffer.h"

void print_buffer(Buffer *b){
    printf("Buffer: %d, %d, %d [", b->n, b->top, b->end);
    for(int i = 0; i < b->n; i++){
        printf("%d", buffer_size(b, i));

        if(i < (b->n - 1)){
            printf(", ");
        }
    }

    printf("] ");

}

int main(){

    printf("Circular Buffer!\n");

    Buffer buf
    Buffer *b = &buf;
    buffer_init(b);
    print_buffer(b);

    buffer_destroy(b);
    print_buffer(b);


}