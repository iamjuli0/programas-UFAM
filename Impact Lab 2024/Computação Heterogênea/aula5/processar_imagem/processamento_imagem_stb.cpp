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

    //Escrever imagem de saída
    stbi_write_png("cachorro_copia.png", width, height, CHANNEL_NUM, img, CHANNEL_NUM * width);

    //Liberar imagem da memória
    stbi_image_free(img);


}