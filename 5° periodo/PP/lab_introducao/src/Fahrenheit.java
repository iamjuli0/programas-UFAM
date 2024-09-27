import java.util.Scanner;

class Fahrenheit {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        float celsius = scan.nextFloat();
        float fahr = 1.8f * (celsius) + 32;
        
        System.out.printf("%.1f", fahr);
        scan.close();
        
    }
    
    
}