import java.util.Scanner;

class DistanciaAviao {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int vetor_indice[] = new int[100];
        int tabela[][] = {{0,2,11,6,15,11,1}, {2,0,7,12,4,2,15}, {11,7,0,11,8,3,13}, {6,12,11,0,10,2,1}, {15,4,8,10,0,5,13}, {11,2,3,2,5,0,14}, {1,15,13,1,13,14,0}};
        int soma = 0;
        int quantidade = 0;
        
        
        for(int i = 0; i<vetor_indice.length; i++){
            vetor_indice[i] = scan.nextInt();
            if(vetor_indice[i] == -1){
                break;
            }
            
            vetor_indice[i] = (vetor_indice[i]/100) - 1;
            quantidade++;
        }
        
        for(int i = 0; i<(quantidade - 1); i++){
            soma = soma + tabela[vetor_indice[i]][vetor_indice[i+1]];
        }
        System.out.println(soma);
        
        scan.close();
    }
    
}