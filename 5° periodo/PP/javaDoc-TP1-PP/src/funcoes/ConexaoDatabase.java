package funcoes;

import java.sql.*;
import javax.swing.JOptionPane;


public class ConexaoDatabase {
	
	public static Connection connectToDatabase(Connection connection) {
        try {
            String url = "jdbc:mysql://localhost:3306/defesasdb";
            String username = "admin";
            String password = "admin";

            connection = DriverManager.getConnection(url, username, password);
            return connection;
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }
    }

}
