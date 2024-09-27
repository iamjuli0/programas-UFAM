import java.util.Scanner;

class PorcentagemAcerto{
    public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    
    int questoes[][] = new int[2][100];
    float quantidade = 0;
    float iguais = 0;
    
    for(int i = 0; i<2; i++){
        for (int j = 0; j<questoes[0].length; j++){
            questoes[i][j] = scan.nextInt();
            if(questoes[i][j] == -1){
                break;
            }
            quantidade++;
        }
    }
    
    quantidade = quantidade/2;
    
    for (int j = 0; j<quantidade; j++){
        if(questoes[0][j] == questoes[1][j]){
           iguais++;
        }
    }
    float porcento = (iguais/quantidade)*100;
    System.out.printf("%.2f", porcento);
    scan.close();
    }

}