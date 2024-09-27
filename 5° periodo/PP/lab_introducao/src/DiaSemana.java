import java.util.Scanner;

class DiaSemana {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int vetor[] = new int[100];
        int vetor_de_somas[] = new int[100];
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
        
        soma_cada_coluna(tabela, vetor_de_somas);
        int indiceMaior = maior_vetor(vetor_de_somas);
        int maior = vetor_de_somas[indiceMaior];
        
        for(int i = 0; i<7; i++){
            if(vetor_de_somas[i] == maior){
                System.out.println(i+1);
            }
        }
        
        
        
        scan.close();
    }
    
    public static void soma_cada_coluna(int vetor[][], int vetor2[]){
        int soma;
        
        for(int j = 0; j<vetor[0].length; j++){
            soma = 0;
            for (int i = 0; i<vetor.length; i++){
                soma = soma + vetor[i][j];
            }
            vetor2[j] = soma;
        }
    }
    
    public static int maior_vetor(int vetor[]){
        int maior = Integer.MIN_VALUE;
        int indiceMaior = -1;
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] > maior) {
                maior = vetor[i];
                indiceMaior = i;
            }
        }
        return indiceMaior;
    }
    
}