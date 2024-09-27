package design;

import funcoes.ConexaoDatabase;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuRemover {

    JFrame frameRemover;
    private static JList<String> list;
    private static DefaultListModel<String> listModel;
    private static Connection connection;
    private static Map<String, Integer> defesaMap;

    public MenuRemover() {
        initialize();
    }

    private void initialize() {
        frameRemover = new JFrame("Remover Defesas v1.0");
        frameRemover.setResizable(false);
        frameRemover.setBounds(100, 100, 575, 439);
        frameRemover.setLocationRelativeTo(null);
        frameRemover.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameRemover.getContentPane().setLayout(new BorderLayout(0, 0));

        connection = ConexaoDatabase.connectToDatabase(connection);
        carregarDefesasRemocao(connection);

        JLabel label = new JLabel("<html><center>Segue abaixo todos as defesas no banco de dados:<br>Selecione uma e aperte no botão \"Remover Defesa\" para apagá-la do banco de dados!</center></html>", JLabel.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        frameRemover.add(label, BorderLayout.NORTH);
        
        frameRemover.add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel botaoPainel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton removerButton = new JButton("Remover Defesa");
        removerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerDefesaSelecionada();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal frame = new MenuPrincipal();
                frame.framePrincipal.setVisible(true);
                frameRemover.dispose();
            }
        });

        botaoPainel.add(voltarButton);
        botaoPainel.add(removerButton);

        frameRemover.add(botaoPainel, BorderLayout.SOUTH);
        frameRemover.setVisible(true);
    }

    private static void carregarDefesasRemocao(Connection connection) {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setFont(new Font("Tahoma", Font.BOLD, 12));
        defesaMap = new HashMap<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM defesa")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String aluno = rs.getString("aluno");
                String listItem = String.format("%d - %s - %s", id, titulo, aluno);
                listModel.addElement(listItem);
                defesaMap.put(listItem, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados da lista.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removerDefesaSelecionada() {
        String defesaSelecionada = list.getSelectedValue();
        if (defesaSelecionada != null) {
            int defesaID = defesaMap.get(defesaSelecionada);
            String defesaTitulo = defesaSelecionada.split(" - ")[1];
            int confirmacao = JOptionPane.showConfirmDialog(
                    null,
                    "Você tem certeza que deseja remover a defesa: " + defesaTitulo + "?",
                    "Confirmar Remoção",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacao == JOptionPane.YES_OPTION) {
                removerDefesa(defesaID);

                listModel.removeElement(defesaSelecionada);
                defesaMap.remove(defesaSelecionada);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma defesa para remover.");
        }
    }

    private static void removerDefesa(int defesaID) {
        String query = "DELETE FROM defesa WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, defesaID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao remover defesa do banco de dados");
        }
    }
}
