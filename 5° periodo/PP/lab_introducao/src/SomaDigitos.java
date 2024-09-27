import java.util.Scanner;

class SomaDigitos {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int soma = 0;
        
        int digito = scan.nextInt();
        
        if(digito < 0){
            digito = digito * (-1);
        }
        
        while (digito > 0){
            soma = soma + digito % 10;
            digito = (digito / 10);
            
        }
        System.out.print(soma);
        scan.close();
        
    }

}