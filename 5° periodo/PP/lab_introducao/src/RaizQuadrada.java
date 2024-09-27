import java.util.Scanner;

class RaizQuadrada {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        float num = scan.nextFloat();
        double raiz = Math.sqrt(num);
        System.out.printf("%.4f", raiz);
        scan.close();
        
    }
}