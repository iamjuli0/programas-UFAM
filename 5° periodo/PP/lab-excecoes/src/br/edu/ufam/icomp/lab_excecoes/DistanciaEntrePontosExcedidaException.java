package br.edu.ufam.icomp.lab_excecoes;

public class DistanciaEntrePontosExcedidaException extends RoverCaminhoException{
	
	private static final long serialVersionUID = 1L;
	
	public DistanciaEntrePontosExcedidaException() {
		super();
	}
	
	public DistanciaEntrePontosExcedidaException(String s) {
		super(s);
	}
	
	public String getMessage() {
		String mensagem = "Distância máxima entre duas coordenadas vizinhas excedida";
		return mensagem;
	}

}
