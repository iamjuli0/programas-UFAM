package br.edu.ufam.icomp.lab_excecoes;

public class RoverCaminhoException extends RoverException {
	
	private static final long serialVersionUID = 1L;

	public RoverCaminhoException() {
		super();	
	}
	
	public RoverCaminhoException(String s) {
		super(s);
	}
	
	public String getMessage() {
		String mensagem = "Exceção geral de caminho do rover";
		return mensagem;
	}

}
