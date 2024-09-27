package gerenciador;

import modelo.Projeto;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GerenciadorProjetos {

    public List<Projeto> consultarProjetos() {
        return Projeto.carregarTodos();
    }

    public void removerProjeto(String titulo) {
        try (Connection conn = DBConnection.getConnection()) {
            // Deleta as despesas associadas ao projeto
            String sqlDespesas = "DELETE FROM despesas WHERE projetoTitulo = ?";
            try (PreparedStatement pstmtDespesas = conn.prepareStatement(sqlDespesas)) {
                pstmtDespesas.setString(1, titulo);
                pstmtDespesas.executeUpdate();
            }

            // Deleta o projeto
            String sqlProjeto = "DELETE FROM projetos WHERE titulo = ?";
            try (PreparedStatement pstmtProjeto = conn.prepareStatement(sqlProjeto)) {
                pstmtProjeto.setString(1, titulo);
                pstmtProjeto.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
