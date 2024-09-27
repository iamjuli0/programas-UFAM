import java.util.Scanner;

class TimeFutebol {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int resultados[][] = new int[2][100];
        int quantidade = 0;
        int vitorias = 0;
        int empates = 0;
        int derrotas = 0;
    
        for(int i = 0; i<resultados.length; i++){
            for (int j = 0; j<resultados[0].length; j++){
            	resultados[i][j] = scan.nextInt();
                
                if(resultados[i][j] == -1){
                    break;
                }
                quantidade++;
            }
        }
        
        quantidade = quantidade/2;
        
        for (int j = 0; j<quantidade; j++){
            if(resultados[0][j] > resultados[1][j]){
                vitorias++;
            }
            
            if(resultados[0][j] == resultados[1][j]){
                empates++;
                
            }
            
            if(resultados[0][j] < resultados[1][j]){
                derrotas++;
            }
        }
        
        System.out.printf("%d %d %d", vitorias, empates, derrotas);
        scan.close();
    }
}