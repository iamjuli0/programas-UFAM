import java.util.Scanner;

class FolhaPagamento {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        double valor_hora = scan.nextFloat();
        int horas = scan.nextInt();
        
        double salario_bruto = valor_hora * horas;
        double ir = 0.11f * salario_bruto;
        double inss = 0.08f * salario_bruto;
        double descontos = ir + inss;
        double salario_liquido = salario_bruto - descontos;
        
        System.out.printf("Salario bruto: R$%.2f\n", salario_bruto);
        System.out.printf("IR: R$%.2f\n", ir);
        System.out.printf("INSS: R$%.2f\n", inss);
        System.out.printf("Total de descontos: R$%.2f\n", descontos);
        System.out.printf("Salario liquido: R$%.2f", salario_liquido);
        
        scan.close();
    }

}