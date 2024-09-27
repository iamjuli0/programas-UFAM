import java.util.Scanner;

class AngryBirds {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        double gravidade = 9.8;
        double velocidade = scan.nextFloat();
        double angulo = scan.nextDouble();
        double distancia = scan.nextFloat();
        
        double alcance = (Math.pow(Math.abs(velocidade), 2) * Math.sin(Math.toRadians(2*angulo))) / gravidade;
        
        if(alcance <= distancia + 0.1 && alcance >= distancia - 0.1){
            System.out.println("1");
        } else {
            System.out.println("0");
        }
        
        scan.close();
    }

}