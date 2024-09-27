import java.util.ArrayList;

class Ensalamento {
	ArrayList<Sala> salas;
	ArrayList<Turma> turmas;
	ArrayList<TurmaEmSala> 	ensalamento;
	
	Ensalamento(){
		this.salas = new ArrayList<>();
		this.turmas = new ArrayList<>();
		this.ensalamento = new ArrayList<>();
	}
	
	void addSala(Sala sala) {
		salas.add(sala);
	}
	
	void addTurma(Turma turma) {
		turmas.add(turma);
	}
	
	Sala getSala(Turma turma) {
		for(TurmaEmSala ensalamentoArray : ensalamento) {
			if(ensalamentoArray.turma == turma) {
				return ensalamentoArray.sala;
			}
		}
		return null;
	}
	
	boolean salaDisponivel(Sala sala, int horario) {
		for(TurmaEmSala ensalamentoArray : ensalamento) {
			if(ensalamentoArray.sala == sala) {
				
				for(int i = 0; i < ensalamentoArray.turma.horarios.size(); i++) {
					if(ensalamentoArray.turma.horarios.get(i) == horario) {
						return false;
					}
				}
			} 
		}
		return true;
	}
	
	boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios){
		
		for(Integer horariosArray : horarios) {
			if(salaDisponivel(sala, horariosArray) == false) {
				return false;
			}
		}
		return true;
	}
	
	boolean alocar(Turma turma, Sala sala) {
		if((turma.acessivel && !sala.acessivel) || (turma.numAlunos > sala.capacidade) || !salaDisponivel(sala, turma.horarios)){
			return false;
		}
		TurmaEmSala t1 = new TurmaEmSala(turma, sala);
		ensalamento.add(t1);
		return true;
	}
	
	void alocarTodas() {
		int diferenca;
		TurmaEmSala ensalar;
		
		for(Turma turmasArray : turmas) {
			int menorDif = Integer.MAX_VALUE;
			Sala salaIdeal = null;
			for(Sala salasArray : salas) {
				if((turmasArray.acessivel && !salasArray.acessivel) || (turmasArray.numAlunos > salasArray.capacidade) || !salaDisponivel(salasArray, turmasArray.horarios)){
					continue;
				}
				diferenca = salasArray.capacidade - turmasArray.numAlunos;
				
				if(diferenca < menorDif) {
					menorDif = diferenca;
					salaIdeal = salasArray;
					continue;
				}
				
			}
			if(salaDisponivel(salaIdeal, turmasArray.horarios) && salaIdeal != null) {
				ensalar = new TurmaEmSala(turmasArray, salaIdeal);
				ensalamento.add(ensalar);
			}
		}
	}
	
	int getTotalTurmasAlocadas() {
		int totalTurmas = 0;
		
		for(TurmaEmSala ensalamentoArray : ensalamento) {
			if(ensalamentoArray.sala != null) {
				
				totalTurmas++;
			}
		}
		return totalTurmas;
	}
	
	int getTotalEspacoLivre() {
		int totalEspacos = 0;
		Sala salaAtual;
		Turma turmaAtual;
		
		for(TurmaEmSala ensalamentoArray : ensalamento) {
			salaAtual = ensalamentoArray.sala;
			turmaAtual = ensalamentoArray.turma;
			
			if(salaAtual == null) {
				continue;
			}
			totalEspacos += salaAtual.capacidade - turmaAtual.numAlunos;
		}
		return totalEspacos;
	}
	
	String relatorioResumoEnsalamento() {
		String linha1 = String.format("Total de Salas: %d\n", salas.size());
		String linha2 = String.format("Total de Turmas: %d\n", turmas.size());
		String linha3 = String.format("Turmas Alocadas: %d\n", getTotalTurmasAlocadas());
		String linha4 = String.format("Espaços Livres: %d\n", getTotalEspacoLivre());
		
		return linha1+linha2+linha3+linha4;
	}
	
	String relatorioTurmasPorSala() {
		String resumo = relatorioResumoEnsalamento();
		String salaInfo = "";
		
		for(Sala salasArray : salas) {
			salaInfo += "\n--- "+salasArray.getDescricao()+" ---\n";
			for(TurmaEmSala ensalamentoArray : ensalamento) {
				if(ensalamentoArray.sala == salasArray) {
					salaInfo += "\n"+ensalamentoArray.turma.getDescricao()+"\n";
				}
			}
		}
		
		return resumo+salaInfo;
	}
	
//	String relatorioSalasPorTurma() {
//		String resumo = relatorioResumoEnsalamento();
//		String turmaInfo = "";
//		
//		for(TurmaEmSala ensalamentoArray : ensalamento) {
//			
//			turmaInfo += ensalamentoArray.turma.getDescricao();
//			
//			if(ensalamentoArray.sala == null) {
//				turmaInfo += "Sala: SEM SALA\n";
//			} else {
//				turmaInfo += "Sala: "+ensalamentoArray.sala.getDescricao()+"\n";
//			}
//		}
//		
//		return resumo+turmaInfo;
//	}
	
	String relatorioSalasPorTurma() {
		String resumo = relatorioResumoEnsalamento();
		String turmaInfo = "\n";
		boolean existe = false;
		
		for(Turma turmasArray : turmas) {
			for(TurmaEmSala ensalamentoArray : ensalamento) {
				if(ensalamentoArray == null) {
					break;
				}
				if(ensalamentoArray.turma.equals(turmasArray)) {
					existe = true;
					turmaInfo += ensalamentoArray.turma.getDescricao()+"\n";
					
					if(ensalamentoArray.sala == null) {
						turmaInfo += "Sala: SEM SALA\n";
					} else {
						turmaInfo += "Sala: "+ensalamentoArray.sala.getDescricao()+"\n";
					}
				}
				
			}
			if(!existe) {
				turmaInfo += turmasArray.getDescricao()+"\nSala: SEM SALA";
			}

		}
		
		return resumo+turmaInfo;
	}
	
	
}