#include <iostream>
#include <fstream>


int main(){

    //std::ifstream imagein;
    std::ofstream imageout;

    imageout.open("PPM_Images.ppm");

    if (imageout.is_open()){

        imageout << "P3" << std::endl;
        imageout << "250 250" << std::endl;
        imageout << "255" << std::endl;
    }

    //int red;
    //int green;
    //int blue;

    for(int w = 0; w < 250; w++){
        for(int h = 0; h < 250; h++){

        if(w <= 125){
            if(h <= 125){
                imageout << 0 << std::endl; //PRETO
                imageout << 0 << std::endl;
                imageout << 0 << std::endl;
            } else{
                imageout << 255 << std::endl; //BRANCO
                imageout << 255 << std::endl;
                imageout << 255 << std::endl;
            }
            
        } else{

            if(h <= 125){
                imageout << 255 << std::endl; //VERMELHO
                imageout << 0 << std::endl;
                imageout << 0 << std::endl;
            } else {
                imageout << 0 << std::endl; //AZUL
                imageout << 0 << std::endl;
                imageout << 255 << std::endl;
            }

        }

        }
    }
    imageout.close();
    return 0;
}
