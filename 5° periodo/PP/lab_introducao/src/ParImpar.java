import java.util.Scanner;

class ParImpar {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int vetor[] = new int[100];
        int i = 0;
        
        while(i < vetor.length){
            vetor[i] = scan.nextInt();
            
            if(vetor[i] == -1){
                break;
            }
            i++;
            
        }
        par_impar(vetor, i);
        scan.close();
        
    }
    public static void par_impar(int vetor[], int quantidade){
        for(int j = 0; j < quantidade; j++){
            
            if(vetor[j] % 2 == 0){
                System.out.println("PAR");
            } else {
                System.out.println("IMPAR");
            }
            
        }
        
        
    }
    
    
}