
public class teste {
	public static void main(String[] args) {
		
		//Declaração do caminho do arquivo .txt e quantum de cada processo
		String diretorio = "C:\\\\Users\\\\julio\\\\OneDrive\\\\Área de Trabalho\\\\RR-6.txt";
		int quantumRR = 2;
		int quantumSRTF = 2;
		int quantumPRIOp = 1;
		
		//Rodando cada processo com o caminho do diretorio e quantum, se necessário
		FCFS.start(diretorio);
		System.out.println("-------------------------------------");
		RR.start(diretorio, quantumRR);
		System.out.println("-------------------------------------");
		SJF.start(diretorio);
		System.out.println("-------------------------------------");
		SRTF.start(diretorio, quantumSRTF);
		System.out.println("-------------------------------------");
		PRIOc.start(diretorio);
		System.out.println("-------------------------------------");
		PRIOp.start(diretorio, quantumPRIOp);
		System.out.println("-------------------------------------");
	}

}
