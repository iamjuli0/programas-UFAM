import java.util.Scanner;

class IdadeUfam {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int ano_atual = scan.nextInt();
        int idade_ufam = ano_atual - 1909;
        
        System.out.printf("A UFAM tem %d anos de fundacao", idade_ufam);
        scan.close();
    }
}