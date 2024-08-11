import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
//import javax.swing.JOptionPane;

public class FCFS {
	public static void start(String diretorio) {
		
		//Encontrando arquivo .txt usando o caminho do computador
		Path caminho = Paths.get(diretorio);
		try {
			
			//Declaração de variáveis
			byte[] texto = Files.readAllBytes(caminho);
			String entrada = new String(texto);
			String linhas[] = entrada.split("\n");
			ArrayList<Processo> processos = new ArrayList<>();
			String informacoes[];
			double somaExecucao = 0, somaEspera = 0;
			int quantidade = 0;
			double tempoExecucao = 0, tempoEspera = 0;
			int tempo = 0;
			
			//Separando informações linha a linha sobre cada processo e adicionando no array
			for(int i = 0; i<linhas.length; i++) {
				informacoes = linhas[i].split(" ");
				
				String processo = informacoes[0];
				int ingresso = Integer.parseInt(informacoes[1]);
				int duracao = Integer.parseInt(informacoes[2]);
				int prioridade = Integer.parseInt(informacoes[3]);
				int tipo = Integer.parseInt(informacoes[4].substring(0, 1));
				
				processos.add(new Processo(processo, ingresso, duracao, prioridade, tipo, 0, 0));
				quantidade++;
			}
			
			processos.sort((p1, p2) -> Integer.compare(p1.ingresso, p2.ingresso));
			
			//Cálculo do tempo medio de execucao
			for(Processo processosArray : processos) {
				somaExecucao += (processosArray.duracao + tempo) - processosArray.ingresso;
				tempo += processosArray.duracao;
				tempoExecucao = somaExecucao/quantidade;
				
			}
			
			//Cálculo do tempo medio de espera
			tempo = 0;
			for(Processo processosArray : processos) {
				somaEspera += (tempo) - processosArray.ingresso;
				tempo += processosArray.duracao;
				tempoEspera = somaEspera/quantidade;
			}
			
			//Impressão dos tempos e fila
			System.out.print("FCFS:\nOrdem de finalização: ");
			for (Processo processosArray : processos) {
	            System.out.printf("%s ", processosArray.processo);
	        }
			System.out.println("\nTempo médio de execução: " + tempoExecucao);
			System.out.println("Tempo médio de espera: " + tempoEspera);
			
			//Impressão dos tempos e fila numa tela. Para utilizar basta, comentar a linha 60 a 65, e descomentar a linha 68 a 75
//			String saida = "Ordem de finalização:";
//			for (Processo processosArray : processos) {
//				saida += " " + processosArray.processo;
//	        }
//			saida += "\nTempo médio de execução: " + tempoExecucao;
//			saida += "\nTempo médio de espera: " + tempoEspera;
//			
//			JOptionPane.showMessageDialog(null, saida);
		
		//Tratamento de erros;
		} catch(Exception erro) {
			System.out.println("Erro ao ler arquivo");
		}
		
	}

}
