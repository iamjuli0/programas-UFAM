import java.util.Scanner;

class TanqueCombustivel {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        float raio = scan.nextFloat();
        float altura_ar = scan.nextFloat();
        double pi = Math.PI;
        double volume_esfera = ((4.0)/(3.0)) * pi * Math.pow(raio, 3);
        double volume_calota = (pi/3) * Math.pow(altura_ar,2) * (3*raio-altura_ar);
        
        int opcao = scan.nextInt();
        
        switch(opcao){
            case 1:
                System.out.printf("%.4f", volume_calota);
                break;
            case 2:
                double volume_conbustivel = volume_esfera - volume_calota;
                System.out.printf("%.4f", volume_conbustivel);
                break;
            default:
                System.out.print("-1");
        }
        
        scan.close();
    }

}