package br.edu.icomp.ufam.lab_heranca;
import java.util.ArrayList;

public class FormasMain {
	public static void main(String[] args) {
		ArrayList<FormaGeometrica> formas = new ArrayList<FormaGeometrica>();
		
		Circulo circulo1 = new Circulo(0,3,5);
		Retangulo retangulo1 = new Retangulo(2,10,3,6);
		Quadrado quadrado1 = new Quadrado(8,9,10);
		
		formas.add(circulo1);
		formas.add(retangulo1);
		formas.add(quadrado1);
		
		for(FormaGeometrica formasArray : formas) {
			System.out.println(formasArray.toString());
		}
	}
}
