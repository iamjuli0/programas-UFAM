class Carro {
	String marca;
	String modelo;
	Proprietario proprietario;
	Placa placa;
	Motor motor;
	
	Carro(String marca, String modelo, Proprietario proprietario, Placa placa, Motor motor){
		this.marca = marca;
		this.modelo = modelo;
		this.proprietario = proprietario;
		this.placa = placa;
		this.motor = motor;
	}
	
	String getDescricao(){
		String descricao = String.format("Carro %s/%s. %s %s %s", marca, modelo, proprietario.getDescricao(), placa.getDescricao(), motor.getDescricao());
		return descricao;
	}
}
