package design;

import funcoes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MenuConsultar {

	JFrame frameConsultar;
	private static JList<String> defesaLista;
    private static DefaultListModel<String> listModel;
    private static Connection connection;
    private static Map<String, Integer> defesaMap;


	public MenuConsultar() {
		initialize();
	}

	private void initialize() {
		connection = ConexaoDatabase.connectToDatabase(connection);

        frameConsultar = new JFrame("Consultar Defesas v1.0");
        frameConsultar.setResizable(false);
        frameConsultar.setBounds(100, 100, 575, 439);
        frameConsultar.setLocationRelativeTo(null);
        frameConsultar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameConsultar.getContentPane().setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        defesaLista = new JList<>(listModel);

        defesaLista.setFont(new Font("Tahoma", Font.BOLD, 12));

        defesaMap = new HashMap<>();

        DefesaDAO.carregarDefesasAlteracao(connection, listModel, defesaMap);

        frameConsultar.getContentPane().add(new JScrollPane(defesaLista), BorderLayout.CENTER);

        JLabel lblSegueAbaixoTodos = new JLabel("<html><center>Segue abaixo todos as defesas no banco de dados:<br>Selecione uma e aperte no botão \"Consultar Defesa\" para obter mais detalhes!</center></html>");
        lblSegueAbaixoTodos.setHorizontalAlignment(SwingConstants.CENTER);
        lblSegueAbaixoTodos.setFont(new Font("Tahoma", Font.BOLD, 12));
        frameConsultar.getContentPane().add(lblSegueAbaixoTodos, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnConsultar = new JButton("Consultar Defesa");
        btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                            String message = String.format(
                                    "ID: %d\nAluno: %s\nTipo: %s\nTítulo: %s\nData: %s\nLocal: %s\nProfessor Orientador: %s\nBanca: %s\nNota Final: %s\nStatus: %s\nObservações: %s",
                                    id, aluno, tipo, titulo, data, local, professorOrientador, banca, notaFinal, status, observacoes
                            );

                            JOptionPane.showMessageDialog(frameConsultar, message, "Informações da Defesa", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frameConsultar, "Erro ao carregar dados da defesa", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frameConsultar, "Por favor, selecione uma defesa para consultar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MenuPrincipal frame = new MenuPrincipal();
				frame.framePrincipal.setVisible(true);
				frameConsultar.dispose();
            }
        });

        buttonPanel.add(btnVoltar);
        buttonPanel.add(btnConsultar);

        frameConsultar.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        DefesaDAO.centralizarJanela(frameConsultar);
        frameConsultar.setVisible(true);
	}


}
