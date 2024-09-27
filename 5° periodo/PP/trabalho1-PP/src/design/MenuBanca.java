package design;

import funcoes.*;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MenuBanca {

	JFrame frameBanca;
	private JTextField campoNomesBanca;
	private DefaultListModel<String> listModel;
    private JList<String> NomesLista;
    List<String> novaBanca = new ArrayList<>();

	public MenuBanca(Defesa defesa) {
		initialize(defesa);
	}

	private void initialize(Defesa defesa) {
		frameBanca = new JFrame("Adicionar Nomes à Banca");
		frameBanca.setResizable(false);
		frameBanca.setTitle("Adicionar Nomes à Banca v1.0");
		frameBanca.setBounds(100, 100, 450, 300);
		frameBanca.setLocationRelativeTo(null);
		frameBanca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameBanca.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel inputPainel = new JPanel();
		frameBanca.getContentPane().add(inputPainel, BorderLayout.NORTH);
		
		JLabel lblNomes = new JLabel("Nome:");
		lblNomes.setFont(new Font("Tahoma", Font.BOLD, 12));
		inputPainel.add(lblNomes);
		
		campoNomesBanca = new JTextField();
		inputPainel.add(campoNomesBanca);
		campoNomesBanca.setColumns(20);
		
		listModel = new DefaultListModel<>();
        NomesLista = new JList<>(listModel);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = campoNomesBanca.getText().trim();
                if (!nome.isEmpty()) {
                    listModel.addElement(nome);
                    campoNomesBanca.setText("");
                    novaBanca.add(nome);
                }
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 12));
		inputPainel.add(btnAdicionar);
		
		JPanel SalvarPainel = new JPanel();
		frameBanca.getContentPane().add(SalvarPainel, BorderLayout.SOUTH);
		SalvarPainel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSalvarNomes = new JButton("Salvar Nomes");
		btnSalvarNomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarBanca(defesa);
			}
		});
		btnSalvarNomes.setFont(new Font("Tahoma", Font.BOLD, 12));
		SalvarPainel.add(btnSalvarNomes);
		
		JPanel ListaPainel = new JPanel();
		frameBanca.getContentPane().add(ListaPainel, BorderLayout.CENTER);
		ListaPainel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(NomesLista);
		ListaPainel.add(scrollPane);
	}
	
	public void adicionarBanca(Defesa defesa) {
		defesa.setBanca(novaBanca);
		JOptionPane.showMessageDialog(null, "Banca adicionada com sucesso!");
		frameBanca.dispose();
	}
	

}
