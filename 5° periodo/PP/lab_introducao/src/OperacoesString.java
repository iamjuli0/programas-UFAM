import java.util.Scanner;

class OperacoesString {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String nome = scan.nextLine();
		int quantString = nome.length();
		char primeiro = nome.charAt(0);
		char ultimo = nome.charAt(quantString - 1);
		String maiuscula = nome.toUpperCase();
		String minuscula = nome.toLowerCase();
		String substituicao = nome.replace('a','e');
		int quantVogais = 0;
		
		System.out.println(quantString);
		System.out.println(primeiro);
		System.out.println(ultimo);
		System.out.println(maiuscula);
		System.out.println(minuscula);
		System.out.println(substituicao);
		
		for(int i = 0; i<nome.length(); i = i + 2) {
			System.out.print(nome.charAt(i));
		}
		System.out.println();
		
		for(int i = 0; i<nome.length(); i++) {
			if(nome.charAt(i) == 'a' || nome.charAt(i) == 'e' || nome.charAt(i) == 'i' || nome.charAt(i) == 'o' || nome.charAt(i) == 'u') {
				quantVogais++;
			}
			if(nome.charAt(i) == 'A' || nome.charAt(i) == 'E' || nome.charAt(i) == 'I' || nome.charAt(i) == 'O' || nome.charAt(i) == 'U') {
				quantVogais++;
			}
		}
		System.out.println(quantVogais);
		scan.close();
	}
}