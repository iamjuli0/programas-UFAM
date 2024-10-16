#include <iostream>
#include <thread>
#include <chrono>
#include <mutex>

using namespace std;

std::mutex cadeado;

void foo(int tid){
    std::cout << "Thread " << tid << " starting" << std::endl;

    for (int i = 0; i < 20; ++i){

        cadeado.lock();
        std::cout << "Hello from thread" << tid << " i=" << i << std::endl;
        cadeado.unlock();
    }

    std::cout << "Thread " << tid << " ending" << std::endl;
}

int main(){

    //Print the number of cores in the CPU that is running this program
    std::cout << std::thread::hardware_concurrency() << " concurrent threads supported." << std::endl;

    std::thread t0(foo, 0);
    std::thread t1(foo, 1);

    t0.join();
    t1.join();

    std::cout << "main() exiting" << std::endl;

    return 0;
}