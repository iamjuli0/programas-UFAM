class CarroMain{
    public static void main(String[] args){
    	Proprietario proprietario1 = new Proprietario("Julio", 20, 2003);
        Placa placa1 = new Placa("IOP123", 6);
        Motor motor1 = new Motor(3, 15, 130);
    	Carro carro1 = new Carro("DeLorean", "DMC-12", proprietario1, placa1, motor1);
        
        /*System.out.println(proprietario1.getDescricao());
        System.out.println(placa1.getDescricao());
        System.out.println(motor1.getDescricao());*/
        
        System.out.println(carro1.getDescricao());
    }
}