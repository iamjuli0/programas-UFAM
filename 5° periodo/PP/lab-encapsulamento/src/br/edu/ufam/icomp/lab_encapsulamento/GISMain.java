 package br.edu.ufam.icomp.lab_encapsulamento;
//import java.util.*;

public class GISMain {
	public static void main(String args[]) {
		
//		ArrayList<Localizavel> vetorLocalizaveis = new ArrayList<>();
		Localizavel vetorLocalizaveis[] = new Localizavel[100];
		
		Celular celular1 = new Celular(50,20,984525888);
		Celular celular2 = new Celular(40,30,984525999);
		Celular celular3 = new Celular(40,30,984525000);
		CarroLuxuoso luxuoso1 = new CarroLuxuoso("PHB1234");
		CarroLuxuoso luxuoso2 = new CarroLuxuoso("PHB3456");
		CarroLuxuoso luxuoso3 = new CarroLuxuoso("PHB5678");
		
//		vetorLocalizaveis.add(luxuoso1);
//		vetorLocalizaveis.add(luxuoso2);
//		vetorLocalizaveis.add(luxuoso3);
//		vetorLocalizaveis.add(celular1);
//		vetorLocalizaveis.add(celular2);
//		vetorLocalizaveis.add(celular3);
		vetorLocalizaveis[0] = celular1;
		vetorLocalizaveis[1] = celular2;
		vetorLocalizaveis[2] = celular3;
		vetorLocalizaveis[3] = luxuoso1;
		vetorLocalizaveis[4] = luxuoso2;
		vetorLocalizaveis[5] = luxuoso3;
		
//		for(Localizavel vetorArray : vetorLocalizaveis) {
//			System.out.println(vetorArray.getPosicao());
//		}
		for(int i = 0; i<vetorLocalizaveis.length; i++) {
			if(vetorLocalizaveis[i] == null) {
				break;
			}
			System.out.println(vetorLocalizaveis[i].getPosicao());
		}
	}

}
