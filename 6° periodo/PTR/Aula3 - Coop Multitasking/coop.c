#include <coop.h>
#include <string.h>
#include <setjmp.h>

void coop_init(Coop *c){
    if(c){
        memset(c, 0, sizeof(*c));
    }
}
void coop_finish(Coop *c){
    if(c){
        memset(c, 0, sizeof(*c));
    }

}

void coop_add_task(Coop *c, coop_func *func, void *context){
    if(c->size < MAX_TASKS){
        c->tasks[c->size] = (Coop_Task){.func = func, .context = context, .id = c->size + 1};
        c->size++;
    }
}

void coop_run(Coop *c){
    c->next = setjmp(c->buf);
    if(c->next == 0) c->next = 1;
    if(c->next > c->size) {
        printf("Invalid next = %d\n", c->next);
        return;
    }

    if(c->tasks[c->next - 1].func != NULL){
        c->tasks[c->next - 1].func(c->tasks[c->next - 1].context);
    }


}

void coop_yield(Coop *c){
    c->next = (c->next + 1) % (c->size + 1);
    if(c->next == 0) c->next = 1;
    longjmp(c->buf, c->next);

}

void coop_debug(Coop *c){
    printf("Coop (%d, %d) [", c->next, c->size);
    for(int i = 0; i < c->size; i++){
        printf("%d", c->tasks[i].id);
    }
    printf("]\n");
}