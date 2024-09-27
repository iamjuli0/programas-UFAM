package design;

import funcoes.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MenuAlterar {

	JFrame frameAlterar;
	private static JList<String> defesaLista;
    private static DefaultListModel<String> listModel;
    private static Connection connection;
    private static Map<String, Integer> defesaMap;

	public MenuAlterar() {
		initialize();
	}

	private void initialize() {
    	connection = ConexaoDatabase.connectToDatabase(connection);

        frameAlterar = new JFrame("Alterar Defesas v1.0");
        frameAlterar.setResizable(false);
		frameAlterar.setBounds(100, 100, 575, 439);
		frameAlterar.setLocationRelativeTo(null);
        frameAlterar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAlterar.getContentPane().setLayout(new BorderLayout(0, 0));

        listModel = new DefaultListModel<>();
        defesaLista = new JList<>(listModel);
        defesaLista.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        defesaMap = new HashMap<>();

        DefesaDAO.carregarDefesasAlteracao(connection, listModel, defesaMap);
        
        JLabel label = new JLabel("<html><center>Segue abaixo todos as defesas no banco de dados:<br>Selecione uma e aperte no botão \"Alterar Defesa\" para alterá-la no banco de dados!</center></html>", JLabel.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        frameAlterar.add(label, BorderLayout.NORTH);

        frameAlterar.add(new JScrollPane(defesaLista), BorderLayout.CENTER);
        
        JPanel botaoPainel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnAlterar = new JButton("Alterar Defesa");
        btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edicaoFrame();
            }
        });
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MenuPrincipal frame = new MenuPrincipal();
				frame.framePrincipal.setVisible(true);
				frameAlterar.dispose();
            }
        });

        botaoPainel.add(btnVoltar);
        botaoPainel.add(btnAlterar);
        
        frameAlterar.add(botaoPainel, BorderLayout.SOUTH);
        frameAlterar.setVisible(true);
	}

	private static void edicaoFrame() {
		String selectedValue = defesaLista.getSelectedValue();
        if (selectedValue != null) {
            int id = defesaMap.get(selectedValue);

            String query = "SELECT * FROM defesa WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String aluno = rs.getString("aluno");
                    String tipo = rs.getString("tipo");
                    String titulo = rs.getString("titulo");
                    String data = rs.getString("data");
                    String local = rs.getString("local");
                    String professorOrientador = rs.getString("professorOrientador");
                    String banca = rs.getString("banca");
                    String notaFinal = rs.getString("notaFinal");
                    String status = rs.getString("status");
                    String observacoes = rs.getString("observacoes");

                    JFrame editFrame = new JFrame("Editar Defesa");
                    editFrame.setResizable(false);
                    editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    editFrame.setLocationRelativeTo(null);
                    editFrame.setLayout(new GridLayout(13, 2));
                    editFrame.setBounds(100, 100, 800, 500);

                    JTextField campoID = new JTextField(String.valueOf(id));
                    campoID.setEditable(false);
                    JTextField campoAluno = new JTextField(aluno);
                    JTextField campoTipo = new JTextField(tipo);
                    JTextField campoTitulo = new JTextField(titulo);
                    JTextField campoData = new JTextField(data);
                    JTextField campoLocal = new JTextField(local);
                    JTextField campoprofessorOrientador = new JTextField(professorOrientador);
                    JTextField campoBanca = new JTextField(banca);
                    JTextField campoNotaFinal = new JTextField(notaFinal);
                    JTextField campoObservacoes = new JTextField(observacoes);

                    JRadioButton rdbtnAprovado = new JRadioButton("Aprovado");
                    rdbtnAprovado.setActionCommand("Aprovado");
                    JRadioButton rdbtnAprovadoCond = new JRadioButton("Aprovado Condicionalmente");
                    rdbtnAprovadoCond.setActionCommand("Aprovado Condicionalmente");
                    JRadioButton rdbtnReprovado = new JRadioButton("Reprovado");
                    rdbtnReprovado.setActionCommand("Reprovado");

                    ButtonGroup groupStatus = new ButtonGroup();
                    groupStatus.add(rdbtnAprovado);
                    groupStatus.add(rdbtnAprovadoCond);
                    groupStatus.add(rdbtnReprovado);

                    switch (status) {
                        case "Aprovado":
                            rdbtnAprovado.setSelected(true);
                            break;
                        case "Aprovado Condicionalmente":
                            rdbtnAprovadoCond.setSelected(true);
                            break;
                        case "Reprovado":
                            rdbtnReprovado.setSelected(true);
                            break;
                    }

                    JButton btnSalvar = new JButton("Salvar Alterações");
                    btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
                    btnSalvar.addActionListener(new ActionListener() {
                    	@Override
                        public void actionPerformed(ActionEvent e) {
                            String newAluno = campoAluno.getText();
                            String newTipo = campoTipo.getText();
                            String newTitulo = campoTitulo.getText();
                            String newData = campoData.getText();
                            String newLocal = campoLocal.getText();
                            String newProfessorOrientador = campoprofessorOrientador.getText();
                            String newBanca = campoBanca.getText();
                            String newNotaFinal = campoNotaFinal.getText();
                            String newStatus = groupStatus.getSelection().getActionCommand();
                            String newObservacoes = campoObservacoes.getText();
                            try {
                                DefesaDAO.atualizarDefesa(connection, id, newAluno, newTipo, newTitulo, newData, newLocal, newProfessorOrientador, newBanca, newNotaFinal, newStatus, newObservacoes);
                                JOptionPane.showMessageDialog(editFrame, "Defesa alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                editFrame.dispose();
                                listModel.clear();
                                defesaMap.clear();
                                DefesaDAO.carregarDefesasAlteracao2(connection, listModel, defesaMap);
                            } catch (SQLException e2) {
                                e2.printStackTrace();
                                JOptionPane.showMessageDialog(editFrame, "Erro ao atualizar defesa no banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    
                    JButton btnVoltar = new JButton("Voltar");
                    btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
                    btnVoltar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            editFrame.dispose();
                        }
                    });

                    JPanel btnPainel = new JPanel();
                    btnPainel.add(btnSalvar);
                    btnPainel.add(btnVoltar);

                    editFrame.add(new JLabel("ID:"));
                    editFrame.add(campoID);
                    editFrame.add(new JLabel("Aluno:"));
                    editFrame.add(campoAluno);
                    editFrame.add(new JLabel("Tipo:"));
                    editFrame.add(campoTipo);
                    editFrame.add(new JLabel("Título:"));
                    editFrame.add(campoTitulo);
                    editFrame.add(new JLabel("Data:"));
                    editFrame.add(campoData);
                    editFrame.add(new JLabel("Local:"));
                    editFrame.add(campoLocal);
                    editFrame.add(new JLabel("Professor Orientador:"));
                    editFrame.add(campoprofessorOrientador);
                    editFrame.add(new JLabel("Banca:"));
                    editFrame.add(campoBanca);
                    editFrame.add(new JLabel("Nota Final:"));
                    editFrame.add(campoNotaFinal);
                    editFrame.add(new JLabel("Status:"));

                    JPanel statusPanel = new JPanel();
                    statusPanel.add(rdbtnAprovado);
                    statusPanel.add(rdbtnAprovadoCond);
                    statusPanel.add(rdbtnReprovado);
                    editFrame.add(statusPanel);

                    editFrame.add(new JLabel("Observações:"));
                    editFrame.add(campoObservacoes);
                    editFrame.add(btnPainel);

                    DefesaDAO.centralizarJanela(editFrame);
                    editFrame.setVisible(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao carregar dados da defesa selecionada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma defesa para editar.");
        }
    }

}
