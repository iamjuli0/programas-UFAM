package br.edu.icomp.ufam.lab_heranca;

public class Circulo extends FormaGeometrica {
	public double raio;
	
	public Circulo(int posX, int posY, double raio){
		super(posX, posY);
		this.raio = raio;		
	}
	
	public double getArea() {
		double pi = Math.PI;
		double area = pi * Math.pow(raio, 2);
		return area;
	}
	
	public double getPerimetro() {
		double pi = Math.PI;
		double perimetro = 2 * pi * raio;
		return perimetro;
	}
	
	public String toString() {
		String descricao1 = String.format("Círculo na posição (%d, %d) com raio de %.1fcm ", posX, posY, raio);
		String descricao2 = "(área="+getArea()+"cm2, perímetro="+getPerimetro()+"cm)";
		return descricao1 + descricao2;
	}
}
