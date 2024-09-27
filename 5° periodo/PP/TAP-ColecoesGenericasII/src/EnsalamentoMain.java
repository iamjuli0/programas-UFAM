 class EnsalamentoMain {
	 public static void main(String[] args) {
		 Ensalamento e1 = new Ensalamento();
//		 Sala s1 = new Sala(4, 202, 45, false);
//		 e1.addSala(s1);
		 Sala s2 = new Sala(4, 101, 100, false);
		 e1.addSala(s2);
		 
		 Turma t1 = new Turma("Sistemas Operacionais", "Andrew S. Tanenbaum", 70, false);
		 t1.addHorario(5);
		 t1.addHorario(19);
		 t1.addHorario(33);
		 e1.addTurma(t1);
		 
		 Turma t2 = new Turma("Organização de Computadores", "Andre N. Silva", 65, true);
		 t2.addHorario(6);
		 t2.addHorario(20);
		 t2.addHorario(34);
		 e1.addTurma(t2);

		 e1.alocarTodas();
		 System.out.println(e1.relatorioTurmasPorSala());
	 }

}
