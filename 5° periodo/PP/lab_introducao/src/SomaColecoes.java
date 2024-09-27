import java.util.Scanner;

class SomaColecoes {
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
        int soma;
        
        for(int i = 0; i<quantidade; i++){
            soma = 0;
            for (int j = 0; j<vetor[0].length; j++){
                soma = soma + vetor[i][j];
            }
            System.out.println(soma);
        }
    }
    
}