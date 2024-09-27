package janela;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImprimirBancoDeDados {

    // Isso é apenas para verificar a tabela de projetos do BD.
    public static void imprimirProjetos() {
        try (Connection conn = DBConnection.getConnection()) {  // Conecta ao banco de dados
            String sqlProjetos = "SELECT * FROM projetos";  // Consulta SQL para selecionar todos os projetos
            try (PreparedStatement pstmtProjetos = conn.prepareStatement(sqlProjetos);
                 ResultSet rsProjetos = pstmtProjetos.executeQuery()) {  // Executa a consulta e obtém os resultados

                // Imprime o cabeçalho das colunas
                System.out.printf("%-30s | %-15s | %-15s | %-15s%n", "Título do Projeto", "Orçamento Total", "Total Gasto", "Disponível");
                System.out.println("------------------------------------------------------------------------------");

                // Itera sobre os resultados dos projetos
                while (rsProjetos.next()) {
                    String titulo = rsProjetos.getString("titulo");  // Obtém o título do projeto

                    // Calcula o orçamento total somando as diferentes rubricas
                    double orcamentoTotal = rsProjetos.getDouble("despesas_capital")
                            + rsProjetos.getDouble("material_consumo")
                            + rsProjetos.getDouble("servicos_terceiros_pf")
                            + rsProjetos.getDouble("servicos_terceiros_pj")
                            + rsProjetos.getDouble("diarias")
                            + rsProjetos.getDouble("passagens");

                    // Calcula o total gasto para o projeto
                    double totalGasto = calcularTotalGasto(conn, titulo);

                    // Calcula o valor disponível subtraindo o total gasto do orçamento total
                    double disponivel = orcamentoTotal - totalGasto;

                    // Imprime os detalhes do projeto
                    System.out.printf("%-30s | %-15.2f | %-15.2f | %-15.2f%n", titulo, orcamentoTotal, totalGasto, disponivel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Trata exceções relacionadas ao banco de dados
        }
    }

    // Função auxiliar para calcular o total gasto de um projeto específico
    private static double calcularTotalGasto(Connection conn, String tituloProjeto) throws SQLException {
        String sqlDespesas = "SELECT SUM(valor) as totalGasto FROM despesas WHERE projetoTitulo = ?";  // Consulta SQL para somar os valores das despesas de um projeto
        try (PreparedStatement pstmtDespesas = conn.prepareStatement(sqlDespesas)) {
            pstmtDespesas.setString(1, tituloProjeto);  // Define o título do projeto como parâmetro da consulta
            try (ResultSet rsDespesas = pstmtDespesas.executeQuery()) {  // Executa a consulta e obtém os resultados
                if (rsDespesas.next()) {
                    return rsDespesas.getDouble("totalGasto");  // Retorna o total gasto
                }
            }
        }
        return 0.0;  // Retorna 0.0 se não houver despesas
    }

    // Função main para executar a impressão dos projetos
    public static void main(String[] args) {
        imprimirProjetos();  // Chama a função principal para imprimir os detalhes dos projetos
    }
}
