package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String sqlProjetos = "CREATE TABLE IF NOT EXISTS projetos (" +
                    "titulo TEXT PRIMARY KEY, " +
                    "professor TEXT, " +
                    "despesas_capital REAL, " +
                    "material_consumo REAL, " +
                    "servicos_terceiros_pf REAL, " +
                    "servicos_terceiros_pj REAL, " +
                    "diarias REAL, " +
                    "passagens REAL)";
            stmt.execute(sqlProjetos);

            String sqlDespesas = "CREATE TABLE IF NOT EXISTS despesas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao TEXT, " +
                    "valor REAL, " +
                    "data TEXT, " +
                    "rubrica TEXT, " +
                    "projetoTitulo TEXT, " +
                    "FOREIGN KEY(projetoTitulo) REFERENCES projetos(titulo))";
            stmt.execute(sqlDespesas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
