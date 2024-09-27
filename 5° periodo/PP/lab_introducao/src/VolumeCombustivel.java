import java.util.Scanner;

class VolumeCombustivel {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        float altura_tanque = scan.nextFloat();
        float nivel_combustivel = scan.nextFloat();
        float raio_bojos = scan.nextFloat();
        double pi = Math.PI;
        
        if(altura_tanque <= 0 || nivel_combustivel <= 0 || raio_bojos <= 0){
            System.out.println("-1.000");
            System.exit(0);
        }
        
        if(raio_bojos < nivel_combustivel){
            double volume_combustivel = ((2.0)/(3.0)) * pi * Math.pow(raio_bojos, 3) + pi * Math.pow(raio_bojos, 2) * (nivel_combustivel - raio_bojos);
            System.out.printf("%.3f", volume_combustivel);
        } else {
            double volume_calota = (pi/3) * Math.pow(nivel_combustivel,2) * (3*raio_bojos-nivel_combustivel);
            System.out.printf("%.3f", volume_calota);
        }
        
        scan.close();
    }
}