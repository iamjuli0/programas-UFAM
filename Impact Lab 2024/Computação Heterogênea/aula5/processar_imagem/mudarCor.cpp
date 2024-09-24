#include <iostream>
#include <fstream>
#include <stdint.h>

#define STB_IMAGE_IMPLEMENTATION
#include "include/stb_image.h"

#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "include/stb_image_write.h"

#define CHANNEL_NUM 4

using namespace std;

int main(){

    int width, height, channels;

    //Abrir imagem
    unsigned char* img = stbi_load("cachorro.jpg", &width, &height, &channels, CHANNEL_NUM);

    //Mudança de cor pelo RGB
    for(int i = 0; i < width * height * 4; i += 4){
        img[i + 0] = 145;   //RED
        //img[i + 1] = 100; //GREEN
        img[i + 2] = 45; //BLUE
        //img[i + 3] = 100;   //ALPHA (opacidade)
    }

    /*Mudança de cor B&W
    for(int i = 0; i < width * height * 4; i += 4){
        // Obtém os valores de R, G, B
            unsigned char r = img[i];
            unsigned char g = img[i + 1];
            unsigned char b = img[i + 2];
            unsigned char a = img[i + 3]; // Canal alfa

            // Calcula o valor de cinza
            unsigned char gray = static_cast<unsigned char>(0.299 * r + 0.587 * g + 0.114 * b);

            // Define os valores R, G, B para o valor de cinza
            img[i] = gray;
            img[i + 1] = gray;
            img[i + 2] = gray;
            img[i + 3] = a; // Mantém o canal alfa inalterado
    }*/

    //Escrever imagem de saída
    stbi_write_png("cachorroColorido2.png", width, height, CHANNEL_NUM, img, CHANNEL_NUM * width);

    //Liberar imagem da memória
    stbi_image_free(img);


}