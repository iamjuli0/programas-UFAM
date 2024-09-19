#ifndef BUFFER_H__
#define BUFFER_H__

enum{
    MAX_BUFFER = 10;
}

typedef struct buffer{
    int data[MAX_BUFFER];
    int n, top, end;
} Buffer;

void buffer_init(Buffer *b);
void buffer_destroy(Buffer *b);

void buffer_add(Buffer *b, int data);
int buffer_rem(Buffer *b);
int buffer_see(Buffer *b, int index);
int buffer_size(Buffer *b);

#endif //BUFFER_H__
