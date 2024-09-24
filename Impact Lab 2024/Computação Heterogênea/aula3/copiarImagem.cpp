#include <iostream>
#include <fstream>

//using namespace std;

int main(){

    std::ifstream imagemOriginal;
    std::ofstream imagemCopia;

    imagemOriginal.open("imagemOriginal.ppm");
    imagemCopia.open("imagemCopia.ppm");

    //Copiar a informação da cabeça
    std::string type = "", widthNovo = "", heightNovo = "", RGB = "";
    imagemOriginal >> type;
    imagemOriginal >> widthNovo;
    imagemOriginal >> heightNovo;
    imagemOriginal >> RGB;

    //Copiar cabeça para imagemNova
    imagemCopia << type << std::endl;
    imagemCopia << widthNovo << " " << heightNovo << std::endl;
    imagemCopia << RGB << std::endl;

    //Lendo as strings
    std::string red = "", green = "", blue = "";

    //Valores
    //int intRed = 0, intGreen = 0, intBlue = 0;

    //Ler cada pixel
    while(!imagemOriginal.eof()){

        imagemOriginal >> red;
        imagemOriginal >> green;
        imagemOriginal >> blue;

        //Escrever na imagemNova
        imagemCopia << red << " " << green << " " << blue << " " << std::endl;

    }
    
    //Fecha as imagens
    imagemCopia.close();
    imagemCopia.close();

    //Impressão de resultados
    std::cout << type << std::endl;
    std::cout << widthNovo << std::endl;
    std::cout << heightNovo << std::endl;
    std::cout << RGB << std::endl;

    return 0;

}