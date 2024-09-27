import java.util.Scanner;

class DataExtenso{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        String meses[] = {"janeiro", "fevereiro", "marco", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
        String data = scan.nextLine();
        String dia = data.substring(0, 2);
        String mes = data.substring(2, 4);
        String ano = data.substring(4, 8);
        
        int num_dia = Integer.parseInt(dia);
        int num_mes = Integer.parseInt(mes);
        int num_ano = Integer.parseInt(ano);
        
        String nome_mes = meses[num_mes-1];
        
        System.out.printf("%d de %s de %d", num_dia, nome_mes, num_ano);
        
        scan.close();
    }
    
}