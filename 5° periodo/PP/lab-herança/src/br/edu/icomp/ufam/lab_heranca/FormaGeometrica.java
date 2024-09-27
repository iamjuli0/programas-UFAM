package br.edu.icomp.ufam.lab_heranca;

public abstract class FormaGeometrica {
	
	public int posX;
	public int posY;
	
	public FormaGeometrica(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
//	public void FormaGeometrica2(int posX, int posY) {
//		System.out.println("oi");
//	}
	
	public abstract double getArea();
	
	public abstract double getPerimetro();
	
	public String getPosString() {
		String posicao = String.format("posicao (%d, %d)", posX, posY);
			return posicao;
	}
}