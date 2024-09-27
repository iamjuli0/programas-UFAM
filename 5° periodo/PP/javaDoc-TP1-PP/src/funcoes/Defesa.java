package funcoes;

import java.util.ArrayList;
import java.util.List;

public class Defesa {
	private String aluno;
	private String tipo;
	private String titulo;
	private String data;
	private String local;
	private String professorOrientador;
	private List<String> banca;
	private String notaFinal;
    private String status;
    private String observacoes;
    
    public Defesa() {
    	this("","","","","","",new ArrayList<>(),"","","");
    }
    
    public Defesa(String aluno, String tipo, String titulo, String data, String local, String professorOrientador, List<String> banca, String notaFinal, String status, String observacoes) {
    	this.aluno = aluno;
    	this.tipo = tipo;
    	this.titulo = titulo;
    	this.data = data;
    	this.local = local;
    	this.professorOrientador = professorOrientador;
    	this.banca = banca;
    	this.notaFinal = notaFinal;
    	this.status = status;
    	this.observacoes = observacoes;
    }
    
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getProfessorOrientador() {
		return professorOrientador;
	}
	public void setProfessorOrientador(String professorOrientador) {
		this.professorOrientador = professorOrientador;
	}
	
	public List<String> getBanca() {
		return banca;
	}
	public void setBanca(List<String> banca) {
		this.banca = banca;
	}
	
	public String getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(String notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
    

}
