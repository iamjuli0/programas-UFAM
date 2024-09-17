#include <iostream>
#include <thread>

using namespace std;

void call_from_thread(const string& mensagem){
    cout << mensagem << endl;
}

int main(){

    thread t1(call_from_thread, "Programa A");
    t1.join();
    thread t2(call_from_thread, "Programa B");
    t2.join();
    thread t3(call_from_thread, "Programa C");
    t3.join();
    thread t4(call_from_thread, "Programa D");
    t4.join();
    
}