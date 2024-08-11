import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class PRIOc {
	public static void start(String diretorio) {
		
		//Encontrando arquivo .txt usando o caminho do computador
		Path caminho = Paths.get(diretorio);
		try {
			
			//Declaração de variáveis
			byte[] texto = Files.readAllBytes(caminho);
			String entrada = new String(texto);
			String linhas[] = entrada.split("\n");
			ArrayList<Processo> processos = new ArrayList<>();
			ArrayList<Processo> fila = new ArrayList<>();
			String informacoes[];
			Processo processoAtual;
			double somaExecucao = 0, somaEspera = 0;
			int quantidade = 0;
			double tempoMedioExecucao = 0, tempoMedioEspera = 0;
			int tempo = 0, tempoMax = 0;
			
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
			
			//Buscando o tempo máximo do escalonador, baseando no tempo dos processos
			for(Processo processosArray : processos) {
				tempoMax += processosArray.duracao;
			}
			
			System.out.print("PRIOc:\nOrdem de finalização: ");
			while(tempo != tempoMax) {
				//Adicionando os processos na fila por tempo hábil
				for(int i = 0; i<processos.size(); i++) {
					if(0 <= processos.get(i).ingresso && processos.get(i).ingresso <= tempo && processos.get(i).fila == 0) {
						fila.add(processos.get(i));
						processos.get(i).fila = 1;
					}
				}
				
				//Reorganizando a fila em ordem de prioridade
				fila.sort((p1, p2) -> Integer.compare(p1.prioridade, p2.prioridade));
				
				//Inverte o vetor para fila de maior prioridade a menor
				Collections.reverse(fila);
				processoAtual = fila.get(0);
				
				//Calculando a soma de tempos de execução e espera
				somaExecucao += (processoAtual.duracao + tempo) - processoAtual.ingresso;
				somaEspera  += (tempo) - processoAtual.ingresso;
				
				//Calculando o tempo médio de execução e espera
				tempoMedioExecucao = somaExecucao/quantidade;
				tempoMedioEspera = somaEspera /quantidade;
				
				//Passagem de tempo
				tempo += processoAtual.duracao;
				
				//Printando o processo ao removê-lo da fila
				System.out.printf("%s ", processoAtual.processo);
				fila.remove(0);
			}
			
			//Imprimindo o tempo médio de execucao e tempo médio de espera
			System.out.println("\nTempo médio de execução: " + tempoMedioExecucao);
			System.out.println("Tempo médio de espera: " + tempoMedioEspera);
		
		//Tratamento de erros;
		} catch(Exception erro) {
			System.out.println("Erro ao ler arquivo");
		}
		
	}

}
