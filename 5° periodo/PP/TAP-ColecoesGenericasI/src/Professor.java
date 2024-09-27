class Professor {
	String titulacao;
	String nome;
	int matricula;
	
	Professor(){
		this("","",0);
	}

	Professor(String titulacao, String nome, int matricula){
		this.titulacao = titulacao;
		this.nome = nome;
		this.matricula = matricula;
	}
	
	String getDescricao(){
		String descricao = String.format("Prof. %s %s - mat %d", titulacao, nome, matricula);
		return descricao;
	}

}