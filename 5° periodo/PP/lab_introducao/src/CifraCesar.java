import java.util.Scanner;

class CifraCesar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int deslocamento = scan.nextInt();
        String frase = scan.nextLine();
        String frase_minuscula = frase.toLowerCase();
        String frase_decodificada = "";
        
        char original, criptografado;
    
        for(int i = 0; i<frase_minuscula.length(); i++){
            if(frase_minuscula.charAt(i) >= 97 && frase_minuscula.charAt(i) <= 122){
                original = frase_minuscula.charAt(i);
                criptografado = (char) (original - 'a');          
                criptografado = (char) ((criptografado + deslocamento) % 26); 
                criptografado += 'A';
                frase_decodificada = frase_decodificada + criptografado;
            } else{
                frase_decodificada = frase_decodificada + frase_minuscula.charAt(i);
            }
        }
        String frase_final = frase_decodificada.replaceFirst(" ", "");
        System.out.println(frase_final);
        
        scan.close();    
    }
    
}