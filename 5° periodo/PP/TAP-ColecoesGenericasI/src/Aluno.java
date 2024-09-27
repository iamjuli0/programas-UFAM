import java.util.Calendar;

class Aluno {
    String nome;
    int matricula;
    int anoNascimento;

    Aluno(){
        this("",0,0);
    }
    
    Aluno(String nome, int matricula, int anoNascimento){
        this.nome = nome;
        this.matricula = matricula;
        this.anoNascimento = anoNascimento;
    }
    
    int getIdade(){
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int idade = ano - anoNascimento;
        return idade;
    }
    
    String getDescricao(){
        String descricao = String.format("%s (mat=%d, idade=%d)", nome, matricula, getIdade());
        return descricao;
    }
}