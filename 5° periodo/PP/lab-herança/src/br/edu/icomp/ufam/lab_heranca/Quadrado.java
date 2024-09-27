package br.edu.icomp.ufam.lab_heranca;

public class Quadrado extends Retangulo {
	
	public Quadrado(int posX, int posY, double lado) {
		super(posX,posY,0,0);
		this.altura = lado;
		this.largura = lado;
	}
	
	public String toString() {
		String descricao = String.format("Quadrado na posição (%d, %d) com lado de %.1fcm (área=%.1fcm2, perímetro=%.1fcm)", posX, posY, largura, getArea(), getPerimetro());
		return descricao;
	}

}