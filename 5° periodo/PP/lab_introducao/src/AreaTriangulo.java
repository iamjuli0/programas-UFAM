import java.util.Scanner;

class AreaTriangulo {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        float a = scan.nextFloat();
        float b = scan.nextFloat();
        float c = scan.nextFloat();
        float s = (a+b+c)/2;
        double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
        
        if(triangulo(a,b,c) == false){
            System.out.print("Triangulo invalido");
            System.exit(0);
            
        }
        
        System.out.printf("%.2f", area);
        scan.close();
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