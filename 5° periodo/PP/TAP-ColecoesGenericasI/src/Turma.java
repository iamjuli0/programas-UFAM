import java.util.*;

class Turma {
	String disciplina;
	int ano;
	int semestre;
	Professor professor;
	ArrayList<Aluno> alunos;
	
	Turma(String disciplina, int ano, int semestre, Professor professor){
		this.disciplina = disciplina;
		this.ano = ano;
		this.semestre = semestre;
		this.professor = professor;
		this.alunos = new ArrayList<>();
	}
	
	void addAluno(Aluno aluno) {

		boolean igualdade = false;
		for(Aluno alunoArray : alunos) {
			if(alunoArray.matricula == aluno.matricula) {
				igualdade = true;
				break;
			}
		}
		if(igualdade == false) {
			alunos.add(aluno);
		}
	}
	
	Aluno getAluno(int matricula){
		for(int i = 0; i<alunos.size(); i++) {
			if(alunos.get(i).matricula == matricula) {
				return alunos.get(i);
			}
		}
		return null;
	}
	
	double getMediaIdade() {
		double soma = 0.0;
		int quantidade = 0;
		for(int i = 0; i<alunos.size(); i++) {
			if(alunos.get(i) != null) {
				soma = soma + alunos.get(i).getIdade();
				quantidade++;
			}
		}
		double media = soma/quantidade;
		return media;
	}
	
	String getDescricao() {
		String descricao1 = String.format("Turma %s - %d/%d (Prof. %s %s - mat %d):\n", disciplina, ano, semestre, professor.titulacao, professor.nome, professor.matricula);
		String descricao2 = "";
		
		for(int i = 0; i<alunos.size(); i++) {
			descricao2 = descricao2 + String.format("  - Aluno %d: %s (mat=%d, idade=%d)\n", i+1, alunos.get(i).nome, alunos.get(i).matricula, alunos.get(i).getIdade());
		}
		return descricao1 + descricao2;
	}
}
