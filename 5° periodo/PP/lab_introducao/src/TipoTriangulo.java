import java.util.Scanner;

class TipoTriangulo {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        float a = scan.nextFloat();
        float b = scan.nextFloat();
        float c = scan.nextFloat();
        
        if (a < 0 || b < 0 || c < 0 || triangulo(a,b,c) == false){
            System.out.println("invalido");
            System.exit(0);
        }
        
        if(a == b && b == c && a == c){
            System.out.println("equilatero");
        } else {
            if(a == b || b == c || a == c){
            System.out.println("isosceles");
        } else {
            System.out.println("escaleno");
        }
        scan.close();
            
        }
    }
    
    public static boolean triangulo(float lado1, float lado2, float lado3){
        
        if (lado1 < lado2 + lado3 && Math.abs(lado2 - lado3) < lado1){
            return true;
        }
        if (lado2 < lado1 + lado3 && Math.abs(lado1 - lado3) < lado2){
            return true;
        }
        if (lado3 < lado1 + lado2 && Math.abs(lado1 - lado2) < lado3 ){
            return true;
        } else {
            return false;
        }
    }
}