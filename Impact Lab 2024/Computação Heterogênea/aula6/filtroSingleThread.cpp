#include <iostream>
#include <stdint.h>
#include <chrono>

#define STB_IMAGE_IMPLEMENTATION
#include "include/stb_image.h"

#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "include/stb_image_write.h"

#define CHANNELS_NUM 4

using namespace std;

int main(){

    int width, height, channels;
    unsigned char* img = stbi_load("cachorroOculos.jpg", &width, &height, &channels, CHANNELS_NUM);

    auto start_single_thread = chrono::high_resolution_clock::now();

    for(int i = 0; i < width * height * CHANNELS_NUM; i += CHANNELS_NUM) { //RGB - Red, green and blue. Esse 0 a 3 são meus canais (Ao todo são 4 canais)
        img[i + 0] = 222; //RED
        //img[i + 1] = 84; //GREEN
        img[i + 2] = 227; //BLUE
        //img[i + 3] = 200; //ALFA, indica a opacidade, transparência 
    }

    auto stop_single_thread = chrono::high_resolution_clock::now();
    auto duration_single_thread = chrono::duration_cast<chrono::microseconds>(stop_single_thread - start_single_thread);


    stbi_write_png("cachorroOculosColorido.png", width, height, CHANNELS_NUM, img, width * CHANNELS_NUM);
    stbi_image_free(img);

    //Resultado do tempo no display
    cout << "Tempo de processamento mono-thread de uma imagem do cachorro: " << duration_single_thread.count() << " microsegundos" << endl;

    return 0;

}