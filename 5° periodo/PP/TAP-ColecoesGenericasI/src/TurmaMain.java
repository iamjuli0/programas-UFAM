 class TurmaMain {
	 public static void main(String[] args) {
		 Aluno aluno1 = new Aluno("Julio Melo Campos", 22250349, 2003);
		 Aluno aluno2 = new Aluno("Daniel Silveira Gonzalez", 22251338, 2003);
		 Aluno aluno3 = new Aluno("Alexandre Antonnacio Senna", 22055555, 2002);
		 
		 Professor professor1 = new Professor("Dr.", "Horacio", 222515151);
		 
		 Turma turma1 = new Turma("Projeto de Programas", 2023, 2, professor1);
		 
		 turma1.addAluno(aluno1);
		 turma1.addAluno(aluno2);
		 turma1.addAluno(aluno3);
		 
		 System.out.println(turma1.getDescricao());
	 }


}
