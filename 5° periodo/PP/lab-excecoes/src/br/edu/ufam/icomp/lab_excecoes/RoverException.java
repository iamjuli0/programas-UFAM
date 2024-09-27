package br.edu.ufam.icomp.lab_excecoes;

public class RoverException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public RoverException() {
		super();	
	}
	
	public RoverException(String s) {
		super(s);
	}
	
	public String getMessage() {
		String mensagem = "Exceção geral do rover";
		return mensagem;
	}

}
