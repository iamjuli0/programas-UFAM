public class Processo {
	
	//Atributos básicos dos processos necessários para escalonamento
	public String processo;
	public int ingresso;
	public int duracao;
	public int prioridade;
	public int tipo;
	
	//Atributos de controle de presença de fila e tempo de processo deixado após quantum
	int fila;
	int standby;
	
	//Método construtor de processos
	public Processo(String processo, int ingresso, int duracao, int prioridade, int tipo, int fila, int standby) {
		this.processo = processo;
		this.ingresso = ingresso;
		this.duracao = duracao;
		this.prioridade = prioridade;
		this.tipo = tipo;
		this.fila = fila;
		this.standby = standby;
	}

}