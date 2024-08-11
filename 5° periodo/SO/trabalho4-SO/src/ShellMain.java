import java.util.Scanner;

public class ShellMain {
    private static Scanner scan;

	public static void main(String[] args) {
        try {
        	scan = new Scanner(System.in);
			ComandosCentral comandos = new ComandosCentral();

			while (true) {
			    System.out.print("shell-Júlio> ");
			    String entrada = scan.nextLine();
			    comandos.centralComandos(entrada);
			}
		} catch(Exception e) {
			System.out.println("Um erro ocorreu: " + e.getMessage());
		}
    }
}