import java.util.Scanner;

class RaizDois {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int termos = scan.nextInt();

        for (int i = 1; i <= termos; i++) {
            double resultado = calcularRaizDois(i);
            System.out.printf("%.14f\n", resultado);
        }

        scan.close();
    }

    public static double calcularRaizDois(int quantidade) {
        double soma = 1.0;
        double ciclo = 1.0;

        for (int j = 0; j < quantidade; j++) {
            ciclo = 1.0 / (2.0 + ciclo);
            soma = 1.0 + ciclo;
        }
        return soma;
    }
}