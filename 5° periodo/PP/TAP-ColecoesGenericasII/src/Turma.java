import java.util.ArrayList;

class Turma {
	String nome;
	String professor;
	int numAlunos;
	boolean acessivel;
	ArrayList<Integer> horarios;
	
	Turma(){
		this("","",0,false);
	}
	
	Turma(String nome, String professor, int numAlunos, boolean acessivel){
		this.nome = nome;
		this.professor = professor;
		this.numAlunos = numAlunos;
		this.acessivel = acessivel;
		this.horarios = new ArrayList<>();
	}
	
	void addHorario(int horario) {
		horarios.add(horario);
	}
	
	String getHorariosString() {
		String matrizFrase[][] = {{"segunda 8hs", "terça 8hs", "quarta 8hs", "quinta 8hs", "sexta 8hs"}, {"segunda 10hs", "terça 10hs", "quarta 10hs", "quinta 10hs", "sexta 10hs"}, {"segunda 12hs", "terça 12hs", "quarta 12hs", "quinta 12hs", "sexta 12hs"}, {"segunda 14hs", "terça 14hs", "quarta 14hs", "quinta 14hs", "sexta 14hs"}, {"segunda 16hs", "terça 16hs", "quarta 16hs", "quinta 16hs", "sexta 16hs"}, {"segunda 18hs", "terça 18hs", "quarta 18hs", "quinta 18hs", "sexta 18hs"}, {"segunda 20hs", "terça 20hs", "quarta 20hs", "quinta 20hs", "sexta 20hs"}};
		int matrizNum[][] = {{1,8,15,22,29},{2,9,16,23,30},{3,10,17,24,31},{4,11,18,25,32},{5,12,19,26,33},{6,13,20,27,34},{7,14,21,28,35}};
		String saida = "";
		for(Integer horarioArray : horarios) {
			
			for(int i = 0; i < matrizNum.length; i++) {
				for(int j = 0; j < matrizNum[0].length; j++) {
					if(matrizNum[i][j] == horarioArray) {
						saida = saida + matrizFrase[i][j] + ", ";
					}
					
				}
			}
		}
		saida = saida.substring(0, saida.length() - 2);
		return saida;
	}
	
	String getDescricao() {
		String acessibilidade = "";
		if(this.acessivel == false) {
			acessibilidade = "não";
		} else {
			acessibilidade = "sim";
		}
		
		String descricao = String.format("Turma: %s\nProfessor: %s\nNúmero de Alunos: %d\nHorário: %s\nAcessível: %s", nome, professor, numAlunos, getHorariosString(), acessibilidade);
		return descricao;
	}
	
}
