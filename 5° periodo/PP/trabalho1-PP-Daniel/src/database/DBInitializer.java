package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    public static void initializeDatabase() {
        String url = "jdbc:sqlite:path/to/your/database.db";

        String createProjetosTable = "CREATE TABLE IF NOT EXISTS projetos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "titulo TEXT NOT NULL, "
                + "professor TEXT, "
                + "despesas_capital REAL, "
                + "material_consumo REAL, "
                + "servicos_terceiros_pf REAL, "
                + "servicos_terceiros_pj REAL, "
                + "diarias REAL, "
                + "passagens REAL"
                + ");";

        String createDespesasTable = "CREATE TABLE IF NOT EXISTS despesas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "descricao TEXT, "
                + "valor REAL, "
                + "data TEXT, "
                + "rubrica TEXT, "
                + "titulo_projeto TEXT"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createProjetosTable);
            stmt.execute(createDespesasTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
