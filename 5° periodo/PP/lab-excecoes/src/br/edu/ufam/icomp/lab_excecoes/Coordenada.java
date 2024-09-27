package br.edu.ufam.icomp.lab_excecoes;

public class Coordenada {
	private int posX;
	private int posY;
	private int digito;
	
	public Coordenada(int posX, int posY, int digito) throws CoordenadaNegativaException, CoordenadaForaDosLimitesException, DigitoInvalidoException{
		
		if(posX < 0 || posY < 0) throw new CoordenadaNegativaException();
		if((posX < 0 || posX > 30000) || (posY < 0 || posY > 30000)) throw new CoordenadaForaDosLimitesException();
		if(digito < 0 || digito > 9) throw new DigitoInvalidoException();
		if((posX + posY) % 10 != digito) throw new DigitoInvalidoException();
		
		this.posX = posX;
		this.posY = posY;
		this.digito = digito;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public double distancia(Coordenada coordenada) {
		double distancia = Math.sqrt((Math.pow(coordenada.getPosX() - posX, 2)) + Math.pow(coordenada.getPosY() - posY, 2));
		return distancia;
		
	}
	
	public String toString() {
		String descricao = String.format("%d, %d", posX, posY);
		return descricao;
	}
	
	public int getDigito() {
		return digito;
	}
	
}
