#include <iostream>
#include <chrono>

using namespace std;

int main(){

    auto start_single_thread = chrono::high_resolution_clock::now();

    for(int i = 0; i < 1000000000; i++){

    }

    auto stop_single_thread = chrono::high_resolution_clock::now();
    auto duration_single_thread = chrono::duration_cast<chrono::milliseconds>(stop_single_thread - start_single_thread);

    //Resultado do tempo no display
    cout << "Tempo de processamento de uma imagem mono-thread: " << duration_single_thread.count() << "ms" << endl;

    auto start_multi_thread = chrono::high_resolution_clock::now();

    for(int i = 0; i < 1000; i++){

    }

    auto stop_multi_thread = chrono::high_resolution_clock::now();
    auto duration_multi_thread = chrono::duration_cast<chrono::milliseconds>(stop_multi_thread - start_multi_thread);

    cout << "Tempo de processamento de uma imagem multi-thread: " << duration_multi_thread.count() << "ms" << endl;

    return 0;

}