package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import database.DBConnection;

public class Projeto {
    private String titulo;
    private String professorResponsavel;
    private Map<String, Double> rubricas;
    private Map<String, List<Despesa>> despesas;

    public Projeto(String titulo, String professorResponsavel, Map<String, Double> rubricas) {
        this.titulo = titulo;
        this.professorResponsavel = professorResponsavel;
        this.rubricas = rubricas;
        this.despesas = new HashMap<>();
        carregarDespesas();
    }

    public void carregarDespesas() {
        despesas.clear();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM despesas WHERE projetoTitulo = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Despesa despesa = new Despesa(
                                rs.getInt("id"),
                                rs.getString("descricao"),
                                rs.getDouble("valor"),
                                rs.getString("data"),
                                rs.getString("rubrica"),
                                rs.getString("projetoTitulo")
                        );
                        despesas.computeIfAbsent(rs.getString("rubrica"), k -> new ArrayList<>()).add(despesa);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarDespesa(String rubrica, Despesa despesa) {
        despesas.computeIfAbsent(rubrica, k -> new ArrayList<>()).add(despesa);
        despesa.salvar();
    }

    public void removerDespesa(String rubrica, int despesaId) {
        if (despesas.containsKey(rubrica)) {
            despesas.get(rubrica).removeIf(d -> d.getId() == despesaId);
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "DELETE FROM despesas WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, despesaId);
                    pstmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void alterarDespesa(String rubrica, Despesa despesa) {
        if (despesas.containsKey(rubrica)) {
            List<Despesa> listaDespesas = despesas.get(rubrica);
            for (int i = 0; i < listaDespesas.size(); i++) {
                if (listaDespesas.get(i).getId() == despesa.getId()) {
                    listaDespesas.set(i, despesa);
                    despesa.atualizar();
                    break;
                }
            }
        }
    }

    public List<Despesa> consultarDespesas(String rubrica) {
        return despesas.getOrDefault(rubrica, new ArrayList<>());
    }

    public Map<String, Map<String, Double>> obterVisaoGeral() {
        Map<String, Map<String, Double>> visaoGeral = new HashMap<>();
        for (Map.Entry<String, List<Despesa>> entry : despesas.entrySet()) {
            String rubrica = entry.getKey();
            List<Despesa> despesasList = entry.getValue();
            double totalGasto = despesasList.stream().mapToDouble(Despesa::getValor).sum();
            Map<String, Double> valores = new HashMap<>();
            valores.put("valor_previsto", rubricas.get(rubrica));
            valores.put("gasto", totalGasto);
            valores.put("disponivel", rubricas.get(rubrica) - totalGasto);
            visaoGeral.put(rubrica, valores);
        }
        return visaoGeral;
    }

    public void salvar() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO projetos (titulo, professor, despesas_capital, material_consumo, servicos_terceiros_pf, servicos_terceiros_pj, diarias, passagens) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                pstmt.setString(2, professorResponsavel);
                pstmt.setDouble(3, rubricas.get("Despesas de Capital"));
                pstmt.setDouble(4, rubricas.get("Material de Consumo"));
                pstmt.setDouble(5, rubricas.get("Serviços de Terceiros / Pessoa Física"));
                pstmt.setDouble(6, rubricas.get("Serviços de Terceiros / Pessoa Jurídica"));
                pstmt.setDouble(7, rubricas.get("Diárias"));
                pstmt.setDouble(8, rubricas.get("Passagens"));
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(String novoTitulo) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE projetos SET titulo = ?, professor = ?, despesas_capital = ?, material_consumo = ?, servicos_terceiros_pf = ?, servicos_terceiros_pj = ?, diarias = ?, passagens = ? WHERE titulo = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, novoTitulo);
                pstmt.setString(2, professorResponsavel);
                pstmt.setDouble(3, rubricas.get("Despesas de Capital"));
                pstmt.setDouble(4, rubricas.get("Material de Consumo"));
                pstmt.setDouble(5, rubricas.get("Serviços de Terceiros / Pessoa Física"));
                pstmt.setDouble(6, rubricas.get("Serviços de Terceiros / Pessoa Jurídica"));
                pstmt.setDouble(7, rubricas.get("Diárias"));
                pstmt.setDouble(8, rubricas.get("Passagens"));
                pstmt.setString(9, titulo);
                pstmt.executeUpdate();
                this.titulo = novoTitulo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Projeto> carregarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM projetos";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String titulo = rs.getString("titulo");
                    String professor = rs.getString("professor");
                    Map<String, Double> rubricas = new HashMap<>();
                    rubricas.put("Despesas de Capital", rs.getDouble("despesas_capital"));
                    rubricas.put("Material de Consumo", rs.getDouble("material_consumo"));
                    rubricas.put("Serviços de Terceiros / Pessoa Física", rs.getDouble("servicos_terceiros_pf"));
                    rubricas.put("Serviços de Terceiros / Pessoa Jurídica", rs.getDouble("servicos_terceiros_pj"));
                    rubricas.put("Diárias", rs.getDouble("diarias"));
                    rubricas.put("Passagens", rs.getDouble("passagens"));

                    Projeto projeto = new Projeto(titulo, professor, rubricas);
                    projetos.add(projeto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }

    public static Projeto carregarPorTitulo(String titulo) {
        Projeto projeto = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM projetos WHERE titulo = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String professor = rs.getString("professor");
                        Map<String, Double> rubricas = new HashMap<>();
                        rubricas.put("Despesas de Capital", rs.getDouble("despesas_capital"));
                        rubricas.put("Material de Consumo", rs.getDouble("material_consumo"));
                        rubricas.put("Serviços de Terceiros / Pessoa Física", rs.getDouble("servicos_terceiros_pf"));
                        rubricas.put("Serviços de Terceiros / Pessoa Jurídica", rs.getDouble("servicos_terceiros_pj"));
                        rubricas.put("Diárias", rs.getDouble("diarias"));
                        rubricas.put("Passagens", rs.getDouble("passagens"));

                        projeto = new Projeto(titulo, professor, rubricas);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projeto;
    }

    public static boolean projetoExiste(String titulo) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM projetos WHERE titulo = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void visualizarProjetos() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM projetos";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String titulo = rs.getString("titulo");
                    System.out.println("Título: " + titulo);
                    System.out.println("Professor: " + rs.getString("professor"));

                    Map<String, List<Despesa>> despesas = carregarDespesasDoProjeto(titulo);
                    Map<String, Double> rubricas = carregarRubricasDoProjeto(titulo);

                    System.out.println("Rubricas e Despesas:");
                    for (String rubrica : rubricas.keySet()) {
                        double valorPrevisto = rubricas.get(rubrica);
                        double totalGasto = despesas.getOrDefault(rubrica, new ArrayList<>())
                                .stream()
                                .mapToDouble(Despesa::getValor)
                                .sum();
                        double disponivel = valorPrevisto - totalGasto;

                        System.out.println("  Rubrica: " + rubrica);
                        System.out.println("    Valor Previsto: " + valorPrevisto);
                        System.out.println("    Total Gasto: " + totalGasto);
                        System.out.println("    Disponível: " + disponivel);
                    }

                    System.out.println("-----------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<Despesa>> carregarDespesasDoProjeto(String titulo) {
        Map<String, List<Despesa>> despesas = new HashMap<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM despesas WHERE projetoTitulo = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Despesa despesa = new Despesa(
                                rs.getInt("id"),
                                rs.getString("descricao"),
                                rs.getDouble("valor"),
                                rs.getString("data"),
                                rs.getString("rubrica"),
                                rs.getString("projetoTitulo")
                        );
                        despesas.computeIfAbsent(rs.getString("rubrica"), k -> new ArrayList<>()).add(despesa);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }

    private static Map<String, Double> carregarRubricasDoProjeto(String titulo) {
        Map<String, Double> rubricas = new HashMap<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT rubrica, valor FROM rubricas WHERE projetoTitulo = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        rubricas.put(rs.getString("rubrica"), rs.getDouble("valor"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rubricas;
    }

    public Map<String, Double> getRubricas() {
        return rubricas;
    }

    public Map<String, List<Despesa>> getDespesas() {
        return despesas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(String professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    public void setRubricas(Map<String, Double> rubricas) {
        this.rubricas = rubricas;
    }

    public String formatarValor(double valor) {
        NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatador.format(valor);
    }
}
