class Placa{
    String placa;
    int tipo;
    
    Placa(){
        this("",0);
    }
    
    Placa(String placa, int tipo){
        this.placa = placa;
        this.tipo = tipo;
    }
    
    String getTipoString(){
        switch(tipo){
            case 1:
                return "Normal";
            case 2:
                return "Servico";
            case 3:
                return "Oficial";
            case 4:
                return "Auto Escola";
            case 5:
                return "Prototipo";
            case 6:
                return "Colecionador";
            default:
                return "Outros";
        }
    }
    
    boolean temEstacionamentoLivre(){
        if(tipo == 2 || tipo == 3){
            return true;
        } else {
            return false;
        }
    }
    
    String getDescricao(){
        String descricao = String.format("Placa: placa=%s, tipo=%s, estacionamentoLivre=%b.", placa, getTipoString(), temEstacionamentoLivre()); 
        return descricao;
    }
    
}
