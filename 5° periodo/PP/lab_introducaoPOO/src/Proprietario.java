class Proprietario {
    String nome;
    int cnh;
    int anoNascimento;
    
    Proprietario(){
        this("", 0, 0);
    }
    
    Proprietario(String nome, int cnh, int anoNascimento){
        this.nome = nome;
        this.cnh = cnh;
        this.anoNascimento = anoNascimento;
    }
    
    int getIdade(int anoReferencia){
        return anoReferencia - anoNascimento;
    }
    
    String getDescricao(){
        String descricao = "Proprietario: nome="+nome+", cnh="+cnh+", anoNascimento="+anoNascimento+".";
        /*String descricao = String.format("Proprietario: nome=%s, cnh=%d, anoNascimento=%d.", nome, cnh, anoNascimento);*/
        return descricao;
    }
    
}