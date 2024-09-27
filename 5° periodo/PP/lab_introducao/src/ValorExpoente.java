import java.util.Scanner;

class ValorExpoente {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int i = 0;
        double soma = 0;
        int numero = scan.nextInt();
        
        while(soma <= numero){
            
            i++;
            soma = soma + Math.pow(2,i);
            
        }
        System.out.printf("%d", i);
        scan.close();
    }
}
