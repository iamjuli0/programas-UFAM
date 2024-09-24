#ifndef COOP_H__
#define COOP_H__

#include <setjmp.h>

enum {
    MAX_TASKS = 5
}

typedef void coop_func(void *);

typedef struct {
    int id;
    void *context;
    coop_func *func;
} Coop_Task;


typedef struct {
    jmp_buf buf;
    int next;
    int size;
    Coop_Task tasks[MAX_TASKS];
} Coop;

void coop_init(Coop *c);
void coop_finish(Coop *c);
void coop_add_task(Coop *c, coop_func *func, void *context);
void coop_run(Coop *c);
void coop_yield(Coop *c);
void coop_debug(Coop *c);

#endif //COOP_H__