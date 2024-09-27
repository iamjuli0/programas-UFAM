package design;

import funcoes.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class MenuAdicionar {

	JFrame frameAdicionar;
	private JTextField campoAluno;
	private JTextField campoTitulo;
	private JTextField campoLocal;
	private JComboBox<Object> comboBoxTipo;
	private JTextField campoProfessor;
	private JTextField campoData;
	Defesa novaDefesa = new Defesa();

	public MenuAdicionar() {
		initialize();
	}

	private void initialize() {
		frameAdicionar = new JFrame("Adicionar Defesas v1.0");
		frameAdicionar.setResizable(false);
		frameAdicionar.setBounds(100, 100, 575, 439);
		frameAdicionar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameAdicionar.setLocationRelativeTo(null);
		frameAdicionar.getContentPane().setLayout(null);
		
		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAluno.setBounds(133, 72, 47, 13);
		frameAdicionar.getContentPane().add(lblAluno);
		
		campoAluno = new JTextField();
		campoAluno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoAluno.setBounds(190, 72, 226, 19);
		frameAdicionar.getContentPane().add(campoAluno);
		campoAluno.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo de Defesa:");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(74, 102, 112, 20);
		frameAdicionar.getContentPane().add(lblTipo);
		
		comboBoxTipo = new JComboBox<Object>();
		comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxTipo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Defesa de projeto final/TCC", "Defesa de qualificação de mestrado", "Defesa de dissertação de mestrado", "Defesa de qualificação de doutorado", "Defesa de tese de doutorado", "Defesa de artigo"}));
		comboBoxTipo.setMaximumRowCount(6);
		comboBoxTipo.setBounds(190, 102, 226, 21);
		frameAdicionar.getContentPane().add(comboBoxTipo);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(135, 132, 47, 13);
		frameAdicionar.getContentPane().add(lblTitulo);
		
		campoTitulo = new JTextField();
		campoTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTitulo.setBounds(190, 132, 226, 19);
		frameAdicionar.getContentPane().add(campoTitulo);
		campoTitulo.setColumns(10);
		
		JLabel lblData = new JLabel("Data (dd/mm/yyyy):");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setBounds(33, 192, 150, 13);
		frameAdicionar.getContentPane().add(lblData);
		
		campoData = new JTextField();
		campoData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoData.setColumns(10);
		campoData.setBounds(190, 192, 226, 19);
		frameAdicionar.getContentPane().add(campoData);
		
		JLabel lblLocal = new JLabel("Local:");
		lblLocal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocal.setBounds(138, 162, 47, 13);
		frameAdicionar.getContentPane().add(lblLocal);
		
		campoLocal = new JTextField();
		campoLocal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLocal.setColumns(10);
		campoLocal.setBounds(190, 162, 226, 19);
		frameAdicionar.getContentPane().add(campoLocal);
		
		JLabel lblProfessorOrientador = new JLabel("Professor Orientador:");
		lblProfessorOrientador.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorOrientador.setBounds(28, 222, 157, 13);
		frameAdicionar.getContentPane().add(lblProfessorOrientador);
		
		campoProfessor = new JTextField();
		campoProfessor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProfessor.setColumns(10);
		campoProfessor.setBounds(190, 222, 226, 19);
		frameAdicionar.getContentPane().add(campoProfessor);
		
		JLabel lblBanca = new JLabel("Banca:");
		lblBanca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBanca.setBounds(131, 252, 53, 13);
		frameAdicionar.getContentPane().add(lblBanca);
		
		JLabel lblInformacoes = new JLabel("<html><div style='text-align: center;'>Para adicionar a sua defesa, por favor, adicione as informações conforme as legendas abaixo:</div></html");
		lblInformacoes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInformacoes.setBounds(106, 21, 381, 41);
		frameAdicionar.getContentPane().add(lblInformacoes);
		
		JButton btnBanca = new JButton("Adicionar nomes");
		btnBanca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuBanca banca = new MenuBanca(novaDefesa);
				banca.frameBanca.setVisible(true);
			}
		});
		btnBanca.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBanca.setBounds(190, 252, 226, 21);
		frameAdicionar.getContentPane().add(btnBanca);
		
		JLabel lblAvaliacao = new JLabel("Avaliação:");
		lblAvaliacao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAvaliacao.setBounds(110, 282, 75, 13);
		frameAdicionar.getContentPane().add(lblAvaliacao);
		
		JButton btnAvaliacao = new JButton("Adicionar avaliação");
		btnAvaliacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAvaliacao avaliacao = new MenuAvaliacao(novaDefesa);
				avaliacao.frameAvaliacao.setVisible(true);
			}
		});
		btnAvaliacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAvaliacao.setBounds(190, 282, 226, 21);
		frameAdicionar.getContentPane().add(btnAvaliacao);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 334, 541, 58);
		frameAdicionar.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnVoltar = new JButton("Voltar");
		panel.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal frame = new MenuPrincipal();
				frame.framePrincipal.setVisible(true);
				frameAdicionar.dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnSalvar = new JButton("Salvar Defesa");
		panel.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarDefesa();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
	}
	
	public void salvarDefesa() {
        String aluno = campoAluno.getText().trim();
        String tipo = (String) comboBoxTipo.getSelectedItem();
        String titulo = campoTitulo.getText().trim();
        String data = campoData.getText().trim();
        String local = campoLocal.getText().trim();
        String professor = campoProfessor.getText().trim();
        
        if(VerificaCampos(aluno, tipo, titulo, local, professor)) {
        	novaDefesa.setAluno(aluno);
            novaDefesa.setTipo(tipo);
            novaDefesa.setTitulo(titulo);
            novaDefesa.setData(data);
            novaDefesa.setLocal(local);
            novaDefesa.setProfessorOrientador(professor);

            DefesaDAO defesaDAO = new DefesaDAO();
            defesaDAO.adicionarDefesa(novaDefesa);

            JOptionPane.showMessageDialog(null, "Defesa salva com sucesso!");
            MenuPrincipal frame = new MenuPrincipal();
            frame.framePrincipal.setVisible(true);
    		frameAdicionar.dispose();
        }
        
    }
	
	public boolean VerificaCampos(String aluno, String tipo, String titulo, String local, String professor) {
		if(aluno.isEmpty() || tipo.isEmpty() || titulo.isEmpty() || local.isEmpty() || professor.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campos Vazios. Por favor, complete!", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
		
	}
}
