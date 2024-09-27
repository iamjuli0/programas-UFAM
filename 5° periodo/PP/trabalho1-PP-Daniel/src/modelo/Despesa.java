package modelo;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Despesa {
    private int id;
    private String descricao;
    private double valor;
    private String data;
    private String rubrica;
    private String projetoTitulo;

    public Despesa(int id, String descricao, double valor, String data, String rubrica, String projetoTitulo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.rubrica = rubrica;
        this.projetoTitulo = projetoTitulo;
    }

    // Métodos get e set para os atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRubrica() {
        return rubrica;
    }

    public void setRubrica(String rubrica) {
        this.rubrica = rubrica;
    }

    public String getProjetoTitulo() {
        return projetoTitulo;
    }

    public void setProjetoTitulo(String projetoTitulo) {
        this.projetoTitulo = projetoTitulo;
    }

    public void salvar() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO despesas (descricao, valor, data, rubrica, projetoTitulo) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, descricao);
                pstmt.setDouble(2, valor);
                pstmt.setString(3, data);
                pstmt.setString(4, rubrica);
                pstmt.setString(5, projetoTitulo);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM despesas WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE despesas SET descricao = ?, valor = ?, data = ?, rubrica = ?, projetoTitulo = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, descricao);
                pstmt.setDouble(2, valor);
                pstmt.setString(3, data);
                pstmt.setString(4, rubrica);
                pstmt.setString(5, projetoTitulo);
                pstmt.setInt(6, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
