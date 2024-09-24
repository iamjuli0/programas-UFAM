#include <setjmp.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <time.h>
#include "coop.h"

void msleep(long ms){
    struct timespec req = {.tv_sec = 0, .tv_nsec = ms*1000000};
    nanosleep(&req, NULL);
}

jmp_buf buf;

void f1(void);
void f2(void);
void f3(void);

void f1(void *cntx){
    Coop *c = cntx;
    printf("f1() - start\n");
    msleep(300);
    printf("f1() - end\n");
    coop_yield(c);
}
void f2(void *cntx){
    Coop *c = cntx;
    printf("f2() - start\n");
    msleep(450);
    printf("f2() - end\n");
    coop_yield(c);
}
void f3(void *cntx){
    printf("f3() - start\n");
    msleep(200);
    printf("f3() - end\n");
    coop_yield(c);
}

Coop coop, *c = &coop;

int main(){

    printf("Cooperative Multi-Tasking!\n");
    
    coop_init(c);
    coop_add_task(c, f1, c);
    coop_add_task(c, f2, c);
    coop_add_task(c, f3, c);

    coop_run(c);

    coop_finish(c);
    
    printf("Bye!\n");
    return EXIT_SUCCESS;
}