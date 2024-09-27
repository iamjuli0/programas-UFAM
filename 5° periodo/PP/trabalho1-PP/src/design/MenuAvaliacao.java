package design;

import funcoes.Defesa;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAvaliacao {

	JFrame frameAvaliacao;
	private JTextField campoNota;
	private JTextArea textoObservacoes;
	private ButtonGroup groupStatus;
	
	public MenuAvaliacao(Defesa defesa) {
		initialize(defesa);
	}

	private void initialize(Defesa defesa) {
		frameAvaliacao = new JFrame();
		frameAvaliacao.setTitle("Adicionar Avaliacao v1.0");
		frameAvaliacao.setBounds(100, 100, 450, 300);
		frameAvaliacao.setLocationRelativeTo(null);
		frameAvaliacao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameAvaliacao.getContentPane().setLayout(null);
		
		JLabel lblNotaFinal = new JLabel("Nota Final:");
		lblNotaFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNotaFinal.setBounds(110, 12, 66, 13);
		frameAvaliacao.getContentPane().add(lblNotaFinal);
		
		campoNota = new JTextField();
		campoNota.setBounds(186, 10, 100, 19);
		frameAvaliacao.getContentPane().add(campoNota);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(127, 35, 49, 13);
		frameAvaliacao.getContentPane().add(lblStatus);
		
		JRadioButton rdbtnAprovado = new JRadioButton("Aprovado");
		rdbtnAprovado.setActionCommand("Aprovado");
		rdbtnAprovado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAprovado.setBounds(150, 54, 103, 21);
		frameAvaliacao.getContentPane().add(rdbtnAprovado);
		
		JRadioButton rdbtnAprovadoCond = new JRadioButton("Aprovado condicionalmente");
		rdbtnAprovadoCond.setActionCommand("Aprovado condicionalmente");
		rdbtnAprovadoCond.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAprovadoCond.setBounds(150, 77, 174, 21);
		frameAvaliacao.getContentPane().add(rdbtnAprovadoCond);
		
		JRadioButton rdbtnReprovado = new JRadioButton("Reprovado");
		rdbtnReprovado.setActionCommand("Reprovado");
		rdbtnReprovado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnReprovado.setBounds(150, 100, 103, 21);
		frameAvaliacao.getContentPane().add(rdbtnReprovado);
		
		groupStatus = new ButtonGroup();
		groupStatus.add(rdbtnAprovado);
		groupStatus.add(rdbtnAprovadoCond);
		groupStatus.add(rdbtnReprovado);
		
		JLabel lblObservacoes = new JLabel("Observações:");
		lblObservacoes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObservacoes.setBounds(59, 131, 82, 13);
		frameAvaliacao.getContentPane().add(lblObservacoes);
		
		textoObservacoes = new JTextArea();
		textoObservacoes.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textoObservacoes.setLineWrap(true);
		textoObservacoes.setBounds(59, 154, 312, 58);
		frameAvaliacao.getContentPane().add(textoObservacoes);
		
		JPanel painelSalvar = new JPanel();
		painelSalvar.setBounds(0, 220, 436, 33);
		frameAvaliacao.getContentPane().add(painelSalvar);
		
		JButton btnSalvarAvaliacao = new JButton("Salvar Avaliação");
		btnSalvarAvaliacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarAvaliacao(defesa);
			}
		});
		btnSalvarAvaliacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		painelSalvar.add(btnSalvarAvaliacao);
	}
	
	public void adicionarAvaliacao(Defesa defesa) {
		String nota = campoNota.getText().trim();
		String status = getSelectedOption(groupStatus);
		String observacoes = textoObservacoes.getText().trim();
		
		if (nota.matches(".*[a-zA-Z]+.*") || nota.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Caracteres Inválidos/Campo Vazio", "Input Error", JOptionPane.ERROR_MESSAGE);
		
        } else {
        	if(status == null) {
    			JOptionPane.showMessageDialog(null, "Status não selecionado", "Input Error", JOptionPane.ERROR_MESSAGE);
    		} else {
	        	defesa.setNotaFinal(nota);
	    		defesa.setStatus(status);
	    		defesa.setObservacoes(observacoes);
	    		JOptionPane.showMessageDialog(null, "Avaliação adicionada com sucesso!");
	    		frameAvaliacao.dispose();
	        }
			
		}
		
	}
	
	private static String getSelectedOption(ButtonGroup group) {
        ButtonModel selectedModel = group.getSelection();
        if (selectedModel != null) {
            return selectedModel.getActionCommand();
        }
        return null;
    }
}
