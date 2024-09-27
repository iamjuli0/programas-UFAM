import java.util.Scanner;

class Mediana {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int vetor[] = new int[100];
        int i = 0;
        int quantidade = 0;
        
        while(i < vetor.length){
            vetor[i] = scan.nextInt();
            
            if(vetor[i] == -1){
                break;
            }
            quantidade++;
            i++;
        }
        
        if(quantidade % 2 == 0){
            int mediana_pos1 = (quantidade/2) - 1;
            int mediana_pos2 = (quantidade/2);
            float mediana = (vetor[mediana_pos1] + vetor[mediana_pos2])/2f;
            
            System.out.println(mediana);
        } else{
            int mediana_pos = (quantidade/2);
            
            System.out.println(vetor[mediana_pos]);
        }
        
        scan.close();
    }
}