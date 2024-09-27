package br.edu.ufam.icomp.lab_excecoes;

public class Caminho {
	private Coordenada[] caminho;
	private int tamanho;
	
	public Caminho(int maxTam) {
		caminho = new Coordenada[maxTam];
	}
	
	public int tamanho() {
		return tamanho;
	}
	
	public void addCoordenada(Coordenada coordenada) throws TamanhoMaximoExcedidoException, DistanciaEntrePontosExcedidaException {
		
		for(int i = 0; i<caminho.length; i++) {
			if(tamanho >= caminho.length) throw new TamanhoMaximoExcedidoException();
			
			if(caminho[i] == null) {
				caminho[i] = coordenada;
				
				if(i != 0 && coordenada.distancia(caminho[i-1]) > 15) {
					caminho[i] = null;
					throw new DistanciaEntrePontosExcedidaException();
				}
				
				tamanho++;
				break;
			}
			
		}

	}
	
	public void reset() {
		for(int i = 0; i<caminho.length; i++) {
			caminho[i] = null;
		}
		tamanho = 0;
	}
	
	public String toString() {
		String descricao = String.format("Dados do caminho:\n  - Quantidade de pontos: %d\n  - Pontos:\n", tamanho);
		
		for(int i = 0; i<caminho.length; i++) {
			if(caminho[i] == null) {
				return descricao;
			}
			
			descricao += String.format("    -> %d, %d\n", caminho[i].getPosX(), caminho[i].getPosY());
		}
		
		return descricao;
	}

}
