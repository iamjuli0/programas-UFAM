#include <stdio.h>
#include <string.h>

#include "buffer.h"

void buffer_init(Buffer *b){
    b->n = b->top = b->end = 0;
    memset(b->data, 0, MAX_BUFFER*sizeof(int));
}

void buffer_destroy(Buffer *b){
    b->n = b->top = b->end = 0;
    memset(b->data, 0, MAX_BUFFER*sizeof(int));
}

void buffer_add(Buffer *b, int data){
    if(b->n >= MAX_BUFFER){
        fputs("Buffer Full", stderr);
        return;
    }
    b->data(b->end) = data;
    b->end = (b->end + 1) % MAX_BUFFER;
    b->n++;
}

int buffer_rem(Buffer *b){
    if(b->n <=0){
        fputs("Buffer empty\n", stderr);
        return;
    }
    int data = b->data(b->top);
    b->top = (b->top + 1) % MAX_BUFFER;
    b->n--;
    return data;
}

int buffer_see(Buffer *b, int index){
    if(index < 0 || index >= b->n){
        fputs("Index out of range\n", stderr);
        return 0;

    }
    int i = (index + b->top) % MAX_BUFFER;
    return b->data[i];

}

int buffer_size(Buffer *b){
    return b->n;
}




