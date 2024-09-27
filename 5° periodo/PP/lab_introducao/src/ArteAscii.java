import java.util.Scanner;

class ArteAscii{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int guarda = num;
        
        while(num != 0){
            for(int i = 0; i < num; i++){
                System.out.print("*");
                
            }
        num--;
        if(num != 0){
            System.out.print("\n");
            
        }
            
        }
        
        while(num <= guarda){
            for(int i = 0; i < num; i++){
                System.out.print("*");
                
            }
        num++;
        System.out.print("\n");
        
        }
        scan.close();
    }
}