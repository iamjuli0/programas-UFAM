import java.util.Scanner;

class Desconto {
    public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    
    float compra = scan.nextFloat();
    
    if(compra < 200){
        System.out.printf("%.2f", compra);
    } else {
        float descontado = compra * 0.95f;
        System.out.printf("%.2f", descontado);
    }
    scan.close();
    }
}