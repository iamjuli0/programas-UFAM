import java.util.Scanner;

class HorasTrabalho {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int vetor[] = new int[100];
        int quantidade = 0;
        
        for(int i = 0; i < vetor.length; i++){
            vetor[i] = scan.nextInt();
            
            if(vetor[i] == -1){
                break;
            }
            quantidade++;
        
        }
        
        int linhas = 0;
        if(quantidade % 7 == 0){
            linhas = quantidade/7;
        } else {
            linhas = quantidade/7 + 1;
        }
        
        int tabela[][] = new int[linhas][7];
        int indice = 0;
        
        for(int i = 0; i<tabela.length; i++){
            for(int j = 0; j<tabela[0].length; j++){
                tabela[i][j] = vetor[indice];
                
                indice++;
            }
        }
        
        soma_linha(tabela);
        
        scan.close();
    }
    
    public static void soma_linha(int vetor[][]){
        int soma;
        
        for(int i = 0; i<vetor.length; i++){
            soma = 0;
            for (int j = 0; j<vetor[0].length; j++){
                soma = soma + vetor[i][j];
            }
            System.out.println(soma);
        }
    }  
    
}