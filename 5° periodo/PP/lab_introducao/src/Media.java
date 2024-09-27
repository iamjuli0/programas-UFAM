import java.util.Scanner;

class Media {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        float soma = 0, num;
        
        for(int i = 0; i < 3; i++){
           num = scan.nextFloat();
           soma = soma + num;
            
        }
        float media = soma/3;
        System.out.printf("%.2f", media);
        scan.close();
        
    }
}