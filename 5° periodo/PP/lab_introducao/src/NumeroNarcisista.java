import java.util.Scanner;

class NumeroNarcisista {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        int qtd;
        int num = scan.nextInt();
        double soma;
        
        qtd = quantidade(num);
        soma = soma_a_potencia(num, qtd);
        
        if(num == soma){
            System.out.print("SIM");
        } else{
            System.out.print("NAO");
        }
        
        scan.close();
    }
    
    public static int quantidade(int num){
        int qtd = 0;
        
        while (num > 0){
            num = (num / 10);
            qtd++;
            
        }
        return qtd;
    
    }
    
    public static double soma_a_potencia(int num, int qtd){
        double soma = 0;
        
        if(num < 0){
            num = num * (-1);
        }
        
        while (num > 0){
            soma = soma + Math.pow(num % 10,qtd);
            num = (num / 10);
            
        }
        return soma;
        
    }

}