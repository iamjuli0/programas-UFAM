package funcoes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DefesaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/defesasdb"; // Seu banco de dados
    private static final String USER = "admin"; // Seu usuário do MySQL
    private static final String PASSWORD = "admin"; // Sua senha do MySQL

    public void adicionarDefesa(Defesa defesa) {
        String sql = "INSERT INTO Defesa (aluno, tipo, titulo, data, local, professorOrientador, banca, notaFinal, status, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, defesa.getAluno());
            pstmt.setString(2, defesa.getTipo());
            pstmt.setString(3, defesa.getTitulo());
            pstmt.setString(4, defesa.getData());
            pstmt.setString(5, defesa.getLocal());
            pstmt.setString(6, defesa.getProfessorOrientador());
            pstmt.setString(7, String.join(",", defesa.getBanca()));
            pstmt.setString(8, defesa.getNotaFinal());
            pstmt.setString(9, defesa.getStatus());
            pstmt.setString(10, defesa.getObservacoes());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void centralizarJanela(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
    
    public static void carregarDefesasAlteracao(Connection connection, DefaultListModel<String> listModel, Map<String, Integer> defesaMap) {
        String query = "SELECT id, titulo, aluno FROM defesa ORDER BY titulo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String aluno = rs.getString("aluno");
                String listItem = id + " - " + titulo + " - " + aluno;
                listModel.addElement(listItem);
                defesaMap.put(listItem, id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do banco de dados");
        }
    }
    
    public static List<Integer> getDefesaIDs(Connection connection) {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT id FROM defesa ORDER BY id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar IDs das defesas do banco de dados");
        }
        return ids;
    }

    public static void carregarDefesasCertificadasIDs(Connection connection, List<Integer> certifiedDefenseIds) {
        String query = "SELECT id FROM defesa WHERE verificacao = 'Certificada'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                certifiedDefenseIds.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar IDs das defesas certificadas");
        }
    }

    public static void atualizarDefesaInformacoes(Connection connection, List<Integer> certifiedDefenseIds, List<Integer> defesaIDs, int atualDefesaIndice, JLabel informacoesLabel) {
        int id = defesaIDs.get(atualDefesaIndice);
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

                String info = String.format(
                        "<html><center>ID: %d<br>Aluno: %s<br>Tipo: %s<br>Título: %s<br>Data: %s<br>Local: %s<br>Professor Orientador: %s<br>Banca: %s<br>Nota Final: %s<br>Status: %s<br>Observações: %s</center></html>",
                        id, aluno, tipo, titulo, data, local, professorOrientador, banca, notaFinal, status, observacoes
                );
                informacoesLabel.setText(info);
                informacoesLabel.setForeground(certifiedDefenseIds.contains(id) ? Color.GREEN : Color.BLACK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados da defesa");
        }
    }

    public static void mostrarProfDefesas(Connection connection, String professor) {
        String query = "SELECT * FROM defesa WHERE professorOrientador LIKE ? OR banca LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + professor + "%");
            pstmt.setString(2, "%" + professor + "%");
            ResultSet rs = pstmt.executeQuery();

            StringBuilder defenses = new StringBuilder("Defesas:\n");
            while (rs.next()) {
                String aluno = rs.getString("aluno");
                String titulo = rs.getString("titulo");
                defenses.append(String.format("Aluno: %s, Título: %s\n", aluno, titulo));
            }
            JOptionPane.showMessageDialog(null, defenses.toString(), "Defesas do Professor", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar defesas do professor");
        }
    }

    public static void conviteProfessor(Connection connection, List<Integer> defesaIDs, int atualDefesaIndice) {
        int id = defesaIDs.get(atualDefesaIndice);
        String query = "SELECT * FROM defesa WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String aluno = rs.getString("aluno");
                String titulo = rs.getString("titulo");
                String data = rs.getString("data");
                String local = rs.getString("local");

                String message = String.format(
                        "<html><center>Saudações, professor!<br>Fica intimado o convite para a defesa<br>\"%s\" do aluno \"%s\"<br>no local \"%s\" e na data \"%s\".<br>Será um prestígio tê-lo tanto para os discentes e docentes da instituição.</center></html>",
                        titulo, aluno, local, data
                );
                JOptionPane.showMessageDialog(null, message, "Convite para Defesa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados da defesa para convite");
        }
    }

    public static void certificadoDefesa(Connection connection, List<Integer> certifiedDefenseIds, List<Integer> defesaIDs, int atualDefesaIndice, JLabel informacoesLabel) {
        int id = defesaIDs.get(atualDefesaIndice);
        String query = "SELECT * FROM defesa WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String aluno = rs.getString("aluno");
                String titulo = rs.getString("titulo");
                String professorOrientador = rs.getString("professorOrientador");
                String banca = rs.getString("banca");

                String message = String.format(
                        "<html><center>A defesa \"%s\" do aluno \"%s\"<br>será certificada pela banca composta<br>pelos professor \"%s\" e professor Orientador \"%s\".<br>Deseja confirmar?</center></html>",
                        titulo, aluno, banca, professorOrientador
                );

                int response = JOptionPane.showConfirmDialog(null, message, "Certificado de Defesa", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    String updateQuery = "UPDATE defesa SET verificacao = 'Certificada' WHERE id = ?";
                    try (PreparedStatement updatePstmt = connection.prepareStatement(updateQuery)) {
                        updatePstmt.setInt(1, id);
                        updatePstmt.executeUpdate();
                    }
                    if (!certifiedDefenseIds.contains(id)) {
                        certifiedDefenseIds.add(id);
                    }
                    atualizarDefesaInformacoes(connection, certifiedDefenseIds, defesaIDs, atualDefesaIndice, informacoesLabel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados da defesa para certificação");
        }
    }
    
    public static void carregarDefesasAlteracao2(Connection connection, DefaultListModel<String> listModel, Map<String, Integer> defesaMap) {
        String query = "SELECT id, titulo, aluno FROM defesa ORDER BY titulo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String aluno = rs.getString("aluno");
                String listItem = id + " - " + titulo + " - " + aluno;
                listModel.addElement(listItem);
                defesaMap.put(listItem, id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do banco de dados");
        }
    }
    
    public static void atualizarDefesa(Connection connection, int id, String aluno, String tipo, String titulo, String data, String local, String professorOrientador, String banca, String notaFinal, String status, String observacoes) throws SQLException {
		String query = "UPDATE defesa SET aluno = ?, tipo = ?, titulo = ?, data = ?, local = ?, professorOrientador = ?, banca = ?, notaFinal = ?, status = ?, observacoes = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, aluno);
            pstmt.setString(2, tipo);
            pstmt.setString(3, titulo);
            pstmt.setString(4, data);
            pstmt.setString(5, local);
            pstmt.setString(6, professorOrientador);
            pstmt.setString(7, banca);
            pstmt.setString(8, notaFinal);
            pstmt.setString(9, status);
            pstmt.setString(10, observacoes);
            pstmt.setInt(11, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar defesa no banco de dados");
        }
    }
    
    
}
