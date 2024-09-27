package design;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class MenuPrincipal {

	public JFrame framePrincipal;

	public MenuPrincipal() {
		initialize();
	}

	private void initialize() {
		framePrincipal = new JFrame("Sistema de Agendamento e Gerenciamento de Defesas v1.0");
		framePrincipal.setResizable(false);
		framePrincipal.setOpacity(1.0f);
		framePrincipal.setBounds(100, 100, 575, 439);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.getContentPane().setLayout(null);
		
		JButton btnAdicionarDefesas = new JButton("Adicionar Defesas");
		btnAdicionarDefesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdicionar adicionar = new MenuAdicionar();
				adicionar.frameAdicionar.setVisible(true);
				framePrincipal.setVisible(false);
				
			}
		});
		btnAdicionarDefesas.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdicionarDefesas.setBounds(182, 71, 197, 63);
		framePrincipal.getContentPane().add(btnAdicionarDefesas);
		
		JButton btnRemoverDefesas = new JButton("Remover Defesas");
		btnRemoverDefesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuRemover remover = new MenuRemover();
				remover.frameRemover.setVisible(true);
				framePrincipal.setVisible(false);
			}
		});
		btnRemoverDefesas.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRemoverDefesas.setBounds(182, 144, 197, 63);
		framePrincipal.getContentPane().add(btnRemoverDefesas);
		
		JButton btnAlterarDefesas = new JButton("Alterar Defesas");
		btnAlterarDefesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAlterar alterar = new MenuAlterar();
				alterar.frameAlterar.setVisible(true);
				framePrincipal.setVisible(false);
			}
		});
		btnAlterarDefesas.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAlterarDefesas.setBounds(182, 217, 197, 63);
		framePrincipal.getContentPane().add(btnAlterarDefesas);
		
		JButton btnConsultarDefesas = new JButton("Consultar Defesas");
		btnConsultarDefesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuConsultar consultar = new MenuConsultar();
				consultar.frameConsultar.setVisible(true);
				framePrincipal.setVisible(false);
			}
		});
		btnConsultarDefesas.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConsultarDefesas.setBounds(182, 290, 197, 63);
		framePrincipal.getContentPane().add(btnConsultarDefesas);
		
		JLabel lblSaudacoes = new JLabel("<html><div style='text-align: center;'>Saudações, usuário! Seja bem-vindo a Central de Agendamento e Gerenciamento de Defesas Universitárias</div></html>", SwingConstants.CENTER);
		lblSaudacoes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSaudacoes.setToolTipText("Mensagem Teste!");
		lblSaudacoes.setBounds(10, 0, 541, 73);
		framePrincipal.getContentPane().add(lblSaudacoes);
		
		JButton btnGeracaoDeRelatorios = new JButton("<html><div style='text-align: center;'>Geração de Relatórios</div></html>");
		btnGeracaoDeRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGeracaoRelatorio relatorio = new MenuGeracaoRelatorio();
				relatorio.frameRelatorio.setVisible(true);
				framePrincipal.setVisible(false);
			}
		});
		btnGeracaoDeRelatorios.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGeracaoDeRelatorios.setBounds(431, 329, 120, 63);
		framePrincipal.getContentPane().add(btnGeracaoDeRelatorios);
	}
}
