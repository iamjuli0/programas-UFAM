import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SRTF {
	public static void start(String diretorio, int quantum) {
		
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
			Processo temporario = null;
			double somaExecucao = 0, somaEspera = 0;
			int quantidade = 0;
			double tempoMedioExecucao = 0, tempoMedioEspera = 0;
			int tempo = 0, tempoMax = 0;
			
			int quantum_copia = quantum;
			
			//Separando informações linha a linha sobre cada processo e adicionando no array
			for(int i = 0; i<linhas.length; i++) {
				informacoes = linhas[i].split(" ");
				
				String processo = informacoes[0];
				int ingresso = Integer.parseInt(informacoes[1]);
				int duracao = Integer.parseInt(informacoes[2]);
				int prioridade = Integer.parseInt(informacoes[3]);
				int tipo = Integer.parseInt(informacoes[4].substring(0, 1));
				
				processos.add(new Processo(processo, ingresso, duracao, prioridade, tipo, 0, ingresso));
				quantidade++;
			}
			
			//Buscando o tempo máximo do escalonador, baseando no tempo dos processos
			for(Processo processosArray : processos) {
				tempoMax += processosArray.duracao;
			}
			
			System.out.print("SRTF:\nOrdem de finalização: ");
			while(tempo != tempoMax) {
				
				//Cópia do quantum para o algoritmo
				quantum_copia = quantum;
				
				//Adicionando os processos na fila por tempo hábil
				for(int i = 0; i<processos.size(); i++) {
					
					//Caso processo determinado já foi adicionado na fila, ou tem tempo indisponível, passa pro próximo
					if(processos.get(i).fila == 1 || processos.get(i).ingresso < 0) {
						continue;
					}
					
					//Caso não há um processo que sofreu o quantum a ser adicionado
					if(temporario == null) {
						if(processos.get(i).ingresso <= tempo) {
							fila.add(processos.get(i));
							processos.get(i).fila = 1;
						}
					
					//Caso há um processo que sofreu o quantum a ser adicionado
					} else {
						if(processos.get(i).ingresso <= tempo) {
							fila.add(processos.get(i));
							processos.get(i).fila = 1;
						}
						fila.add(temporario);
						temporario = null;
						
						if(processos.get(i).ingresso == tempo) {
							fila.add(processos.get(i));
							processos.get(i).fila = 1;
						}
					}
			
				}
				
				//Caso o processo que sofreu o quantum não está na fila, é adicionado
				if(temporario != null) {
					fila.add(temporario);
					temporario = null;
				}
				
				//Reorganizando a fila em ordem de duração
				fila.sort((p1, p2) -> Integer.compare(p1.duracao, p2.duracao));
				processoAtual = fila.get(0);
				
				//Caso o processo tenha tempo restante menor que quantum, quantum é substituído
				if(processoAtual.duracao < quantum_copia) {
					quantum_copia = processoAtual.duracao;
				}
				
				//Calculando a soma de tempos de execução e espera
				somaExecucao += (quantum_copia + tempo) - processoAtual.standby;
				somaEspera  += (tempo) - processoAtual.standby;
				
				//Calculando o tempo médio de execução e espera
				tempoMedioExecucao = somaExecucao/quantidade;
				tempoMedioEspera = somaEspera /quantidade;
				
				//Tempo restante do processo e passagem do tempo
				processoAtual.duracao = processoAtual.duracao - quantum_copia;
				tempo += quantum_copia;
				
				processoAtual.standby = tempo;
				//Caso o tempo do processo não tenha terminado, entra em um modo temporário para ser inserido na fila
				if(processoAtual.duracao > 0) {
					temporario = processoAtual;
				} else {
					//Printando o processo ao removê-lo da fila
					System.out.printf("%s ", processoAtual.processo);
				}
				
				//Remove processo executado no momento da fila
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
