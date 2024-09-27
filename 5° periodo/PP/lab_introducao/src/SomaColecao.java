import java.util.Scanner;

class SomaColecao {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int vetor[] = new int[100];
        int i = 0;
        int soma = 0;
        
        
        while(i < vetor.length){
            vetor[i] = scan.nextInt();
            
            if(vetor[i] == -1){
                break;
            }
            
            soma = soma + vetor[i];
            i++;
        }
        
        System.out.print(soma);
        scan.close();
    }
}
