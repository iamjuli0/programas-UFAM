#include <iostream>
#include <fstream>
#include <stdint.h>

#define STB_IMAGE_IMPLEMENTATION
#include "include/stb_image.h"

#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "include/stb_image_write.h"

#define CHANNEL_NUM 3  

using namespace std;

int main(){

    //Declaração de variáveis
    int width, height, bpp;

    //Carregando a imagem e salvando

    unsigned char* original = stbi_load("siames.jpg", &width, &height, &bpp, CHANNEL_NUM);

    if(original == NULL){
        printf("Erro ao carregar a imagem!");
        exit(1);
    }

    cout << "Imagem carregada com largura de "<<width<<", altura de "<<height<<", e o número de canais igual a "<<CHANNEL_NUM<<endl;

    //Escreve imagens para PNG e JPG

    stbi_write_png("siames_copia.png", width, height, CHANNEL_NUM, original, width * CHANNEL_NUM);
    stbi_write_jpg("siames_copia.jpg", width, height, CHANNEL_NUM, original, 100);

    stbi_image_free(original);

    return 0;

}