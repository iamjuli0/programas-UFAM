import java.util.Scanner;

class HorasTrabalho2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //System.out.println("Qual e a quantidade de funcionarios: ");
        //int n = scan.nextInt();
        int tabela[][] = new int[4][7];
        int parar = 1;
        for(int i = 0; i<tabela.length; i++){
            if(parar == 0){
                break;
            }
            for (int j = 0; j<tabela[0].length; j++){
                tabela[i][j] = scan.nextInt();
                if(tabela[i][j] == -1){
                    tabela[i][j] = 0;
                    parar = 0;
                    break;
                }
                if(j == 7){
                    break;
                }
            }
    
        }
        
        soma_linha(tabela);
        
        scan.close();
    }
    
    public static void soma_linha(int linha[][]){
        int soma;
        
        for(int i = 0; i<linha.length; i++){
            soma = 0;
            for (int j = 0; j<linha[0].length; j++){
                soma = soma + linha[i][j];
            }
            System.out.println(soma);
        }
    }  
    
}