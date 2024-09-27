package br.edu.ufam.icomp.lab_excecoes;

public class TamanhoMaximoExcedidoException extends RoverCaminhoException{
	
	private static final long serialVersionUID = 1L;
	
	public TamanhoMaximoExcedidoException() {
		super();
	}
	
	public TamanhoMaximoExcedidoException(String s) {
		super(s);
	}
	
	public String getMessage() {
		String mensagem = "Quantidade máxima de coordenadas excedida";
		return mensagem;
	}

}
