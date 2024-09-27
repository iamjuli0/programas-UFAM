class Sala {
	int bloco;
	int sala;
	int capacidade;
	boolean acessivel;
	
	Sala(){
		this(0,0,0,false);
	}
	
	Sala(int bloco, int sala, int capacidade, boolean acessivel){
		this.bloco = bloco;
		this.sala = sala;
		this.capacidade = capacidade;
		this.acessivel = acessivel;
	}
	
	String getDescricao(){
		String acessibilidade = "";
		if(this.acessivel == false) {
			acessibilidade = "não acessível";
		} else {
			acessibilidade = "acessível";
		}
		
		String descricao = String.format("Bloco %d, Sala %d (%d lugares, %s)", bloco, sala, capacidade, acessibilidade);
		return descricao;
	}
	
}
