import java.util.Scanner;

class Xadrez {
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    
    int linhas = scan.nextInt();
    
    for(int a = 0; a < (linhas/2); a++){
        for(int i = 0; i < linhas; i++){
            System.out.print("* ");
            
        }
        
        System.out.printf("\n");
        for(int i = 0; i < linhas; i++){
            System.out.print(" *");
            
        }
    System.out.printf("\n");
    }
    
    if(linhas % 2 != 0){
        for(int i = 0; i < linhas; i++){
            System.out.print("* ");
        }
    }
    scan.close();
    }    
}