#include <iostream>
#include <fstream>

//using namespace std;

int main(){

    std::ifstream imagemOriginal;
    std::ofstream imagemNova;

    imagemOriginal.open("PPM_Images3.ppm");
    //imagemNova.open("imagemNova.ppm");

    std::string type = "10", widthNovo = "", heightNovo = "", RGB = "";
    imagemOriginal >> type;
    imagemOriginal >> widthNovo;
    imagemOriginal >> heightNovo;
    imagemOriginal >> RGB;

    std::string red = "", green = "", blue = "";

    std::cout << type << std::endl;
    std::cout << widthNovo << std::endl;
    std::cout << heightNovo << std::endl;
    std::cout << RGB << std::endl;

}