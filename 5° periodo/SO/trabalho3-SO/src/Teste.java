
public class Teste {
	public static void main(String[] args) {
		
		//Declaração do caminho do arquivo .txt
		String diretorio = "C:\\\\Users\\\\julio\\\\OneDrive\\\\Área de Trabalho\\\\FCFS.txt";
		
		//Rodando cada processo com o caminho do diretorio e quantum, se necessário
		FIFO.start(diretorio);
		System.out.println("-------------------------------------");
		LRU.start(diretorio);
		System.out.println("-------------------------------------");
		SegundaChance.start(diretorio);
		System.out.println("-------------------------------------");
		
	}

}
