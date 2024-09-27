import java.util.Scanner;

class ContaEnergia {
    public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    
    float kWh = scan.nextFloat();
    
    if(kWh < 0) {
    	System.out.print("-1");
    } else {
    
	    char tipo_c = scan.next().charAt(0);
	    
	    switch(tipo_c){
	        case 'R':
	            if(kWh <= 500){
	                float precoR = kWh * 0.40f;
	                System.out.printf("%.2f", precoR);
	            } else {
	                float precoR = kWh * 0.65f;
	                System.out.printf("%.2f", precoR);
	            }
	            break;
	        case 'I':
	            if(kWh <= 1000){
	                float precoI = kWh * 0.55f;
	                System.out.printf("%.2f", precoI);
	            } else {
	                float precoI = kWh * 0.60f;
	                System.out.printf("%.2f", precoI);
	            }
	            break;
	        case 'C':
	            if(kWh <= 5000){
	                float precoC = kWh * 0.55f;
	                System.out.printf("%.2f", precoC);
	            } else {
	                float precoC = kWh * 0.60f;
	                System.out.printf("%.2f", precoC);
	            }
	            break;
	        default:
	            System.out.print("-1");
	    }
    }
    scan.close();
    }
}