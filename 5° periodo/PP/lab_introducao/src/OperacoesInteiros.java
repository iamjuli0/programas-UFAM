import java.util.Scanner;

class OperacoesInteiros {
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
        
        for(int i = 0; i<quantidade_linha; i++){
            System.out.println(quantidade(colecoes, i));
            System.out.println(pares(colecoes, i));
            System.out.println(impares(colecoes, i));
            System.out.printf("%.0f\n", soma_total(colecoes, i));
            System.out.printf("%.2f\n", soma_total(colecoes, i)/quantidade(colecoes, i));
            System.out.println(maior(colecoes, i));
            System.out.println(menor(colecoes, i));
        }
        
        scan.close();
    }
    
    public static int quantidade(int vetor[][], int i){
        int qtd = 0;
        
        for(int j = 0; j < vetor[0].length; j++){
            if(vetor[i][j] == -1){
                break;
            }
            qtd++;
        }
        return qtd;
    }
    
    public static int pares(int vetor[][], int i){
        int pares = 0;
        
        for(int j = 0; j < vetor[0].length; j++){
            if(vetor[i][j] == -1){
                break;
            }
            
            if(vetor[i][j] % 2 == 0){
                pares++;
            }
            
        }
        return pares++;
    }
    
    public static int impares(int vetor[][], int i){
        int impares = 0;
        
        for(int j = 0; j < vetor[0].length; j++){
            if(vetor[i][j] == -1){
                break;
            }
            
            if(vetor[i][j] % 2 != 0){
                impares++;
            }
            
        }
        return impares++;
    }
    
    public static double soma_total(int vetor[][], int i){
        double soma = 0;
        
        for(int j = 0; j < vetor[0].length; j++){
            if(vetor[i][j] == -1){
                break;
            }
            soma = soma + vetor[i][j];
            
        }
        return soma;
    }
    
    public static int maior(int vetor[][], int i){
        int maior = Integer.MIN_VALUE;
        for (int j = 0; j < vetor[0].length; j++) {
            if (vetor[i][j] > maior) {
                maior = vetor[i][j];
            }
        }
        return maior;
    }
    
    public static int menor(int vetor[][], int i){
        int menor = Integer.MAX_VALUE;
        for (int j = 0; j < vetor[0].length; j++) {
            if(vetor[i][j] == -1){
                break;
            }
            if (vetor[i][j] < menor) {
                menor = vetor[i][j];
            }
        }
        return menor;
    }

}