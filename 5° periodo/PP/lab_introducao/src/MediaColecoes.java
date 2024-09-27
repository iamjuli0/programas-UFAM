import java.util.Scanner;
//ESSA FUNCAO SOMA_LINHA É HORRIVEL DE ITERACAOKKKKKKKKKKKKK MAS FUNCIONOU
class MediaColecoes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int colecoes[][] = new int[100][100]; 
        int quantidade_linha = 0;
        int contador = 0;
        
        first:
        for(int i = 0; i<colecoes.length; i++){
            second:
            for (int j = 0; j<colecoes[0].length; j++){
                colecoes[i][j] = scan.nextInt();
                
                if(colecoes[i][j] == -1){
                    colecoes[i][j] = 0;
                    if(contador == 1){
                        break first;
                    } else{
                        contador++;
                        break second;
                    }
                }
                contador = 0;
            }
            quantidade_linha++;
            
        }
        
        soma_linha(colecoes, quantidade_linha);
        
        scan.close();
    }
    
    public static void soma_linha(int vetor[][], int quantidade){
        double soma;
        double media;
        int divisao;
        
        for(int i = 0; i<quantidade; i++){
            soma = 0;
            media = 0;
            divisao = 0;
            for (int j = 0; j<vetor[0].length; j++){
                if(vetor[i][j] != 0){
                    divisao++;
                }
                
                soma = soma + vetor[i][j];
            }
            media = soma/divisao;
            System.out.printf("%.2f\n", media);
        }
    }
    
}