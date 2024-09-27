import java.util.Scanner;

class NumeroNeperiano {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        double k = scan.nextInt();
        double soma = 0;
        double i = 0;
        
        while(i<k){
            
            soma = soma + 1/(fatorial(i));
            i++;
            
        }
        
        System.out.printf("%.6f", soma);
        scan.close();
    }
    
    public static double fatorial(double n){
        
        if(n == 0){
            return 1;
        }
        
        return n * fatorial(n - 1);
    }
    
}