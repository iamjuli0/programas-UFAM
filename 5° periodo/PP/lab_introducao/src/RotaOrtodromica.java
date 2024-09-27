import java.util.Scanner;

class RotaOrtodromica {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        double raio_terra = 6371;
        double latitudep1 = scan.nextDouble();
        double longitudep1 = scan.nextDouble();
        double latitudep2 = scan.nextDouble();
        double longitudep2 = scan.nextDouble();
        
        double latitudep1_a = Math.toRadians(latitudep1);
        double longitudep1_a = Math.toRadians(longitudep1);
        double latitudep2_a = Math.toRadians(latitudep2);
        double longitudep2_a = Math.toRadians(longitudep2);
        
        double distancia = raio_terra * Math.acos(Math.sin(latitudep1_a) * Math.sin(latitudep2_a) + Math.cos(latitudep1_a) * Math.cos(latitudep2_a) * Math.cos(longitudep1_a - longitudep2_a));
        
        System.out.printf("A distancia entre os pontos (%f, %f) e (%f, %f) e de %.2f km", latitudep1, longitudep1, latitudep2, longitudep2, distancia);
        
        scan.close();
    }

}