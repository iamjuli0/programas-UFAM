import java.util.Scanner;

class FaltasTrabalho {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int faltas[] = new int[100];
        int i = 0;
        float segunda = 0;
        float terca = 0;
        float quarta = 0;
        float quinta = 0;
        float sexta = 0;
        float sabado = 0;
        
        
        while(i < faltas.length){
            faltas[i] = scan.nextInt();
            
            if(faltas[i] == 2){
                segunda++;
            }
            if(faltas[i] == 3){
                terca++;
            }
            if(faltas[i] == 4){
                quarta++;
            }
            if(faltas[i] == 5){
                quinta++;
            }
            if(faltas[i] == 6){
                sexta++;
            }
            if(faltas[i] == 7){
                sabado++;
            }
            
            if(faltas[i] == -1){
                break;
            }
            
            i++;
        }
        
        segunda = frequencia(segunda,i);
        terca = frequencia(terca,i);
        quarta = frequencia(quarta,i);
        quinta = frequencia(quinta,i);
        sexta = frequencia(sexta,i);
        sabado = frequencia(sabado,i);
        
        System.out.printf("%.1f %.1f %.1f %.1f %.1f %.1f", segunda, terca, quarta, quinta, sexta, sabado);
        
        scan.close();
    }
    
    public static float frequencia(float dia, int quantidade){
        
        float porcentagem = (dia/quantidade)*100;
        return porcentagem;
        
    }
    
}