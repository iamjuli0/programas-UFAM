import java.util.Scanner;

class AproximacaoPi{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        double k = scan.nextDouble();
        int sinal = 1;
        double fator = 2;
        int i = 1;
        double soma = 3;
        
        System.out.printf("%.6f\n", soma);
        
        while(i<k){
            
            soma = soma + sinal*4/(fator*(fator+1)*(fator+2));
            System.out.printf("%.6f\n", soma);
            sinal = sinal * (-1);
            fator += 2;
            i++;
            
        }
       
        scan.close();
    }
    
}