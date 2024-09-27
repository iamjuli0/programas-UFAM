import java.util.Scanner;

class AproximacaoSeno{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        double graus = scan.nextDouble();
        double k = scan.nextDouble();
        double radianos = Math.toRadians(graus);
        int sinal = 1;
        int fator = 1;
        int i = 0;
        double soma = 0;
        
        while(i<k){
            
            soma = soma + sinal*Math.pow(radianos,fator)/fatorial(fator);
            System.out.printf("%.10f\n", soma);
            sinal = sinal * (-1);
            fator += 2;
            i++;
            
        }
       
        scan.close();
    }
    
    public static double fatorial(double n){
        
        if(n == 0){
            return 1;
        }
        
        return n * fatorial(n - 1);
    }
}