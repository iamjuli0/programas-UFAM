package janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Projeto;
import modelo.Despesa;
import gerenciador.GerenciadorProjetos;

public class ConsultarProjetoFrame extends JFrame {
    private JComboBox<String> projetoComboBox;
    private JTextArea detalhesArea;
    private GerenciadorProjetos gerenciadorProjetos;

    public ConsultarProjetoFrame() {
        gerenciadorProjetos = new GerenciadorProjetos();
        initUI();
    }

    private void initUI() {
        setTitle("Consultar Projeto");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JLabel label = new JLabel("Selecione o Projeto:");
        topPanel.add(label);

        List<Projeto> projetos = gerenciadorProjetos.consultarProjetos();
        if (projetos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não Existem Projetos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        String[] titulos = projetos.stream().map(Projeto::getTitulo).toArray(String[]::new);
        projetoComboBox = new JComboBox<>(titulos);
        projetoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetalhes();
            }
        });
        topPanel.add(projetoComboBox);

        detalhesArea = new JTextArea();
        detalhesArea.setEditable(false);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu().setVisible(true);
                dispose();
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(detalhesArea), BorderLayout.CENTER);
        panel.add(btnVoltar, BorderLayout.SOUTH);

        getContentPane().add(panel);

        mostrarDetalhes(); // Mostrar detalhes do primeiro projeto por padrão
    }

    private void mostrarDetalhes() {
        String tituloSelecionado = (String) projetoComboBox.getSelectedItem();
        if (tituloSelecionado != null) {
            Projeto projeto = gerenciadorProjetos.consultarProjetos().stream()
                    .filter(p -> p.getTitulo().equals(tituloSelecionado))
                    .findFirst()
                    .orElse(null);
            if (projeto != null) {
                detalhesArea.setText("Título: " + projeto.getTitulo() + "\n");
                detalhesArea.append("Professor: " + projeto.getProfessorResponsavel() + "\n");

                // Exibir detalhes das rubricas
                for (Map.Entry<String, Double> entry : projeto.getRubricas().entrySet()) {
                    String rubrica = entry.getKey();
                    double valorPrevisto = entry.getValue();
                    double totalGastoPorRubrica = projeto.getDespesas().getOrDefault(rubrica, new ArrayList<>())
                            .stream()
                            .mapToDouble(Despesa::getValor)
                            .sum();
                    double disponivel = valorPrevisto - totalGastoPorRubrica;

                    detalhesArea.append(rubrica + ": " + projeto.formatarValor(valorPrevisto) + "\n");
                    detalhesArea.append("  Total Gasto: " + projeto.formatarValor(totalGastoPorRubrica) + "\n");
                    detalhesArea.append("  Disponível: " + projeto.formatarValor(disponivel) + "\n");
                }

                double totalPrevisto = projeto.getRubricas().values().stream().mapToDouble(Double::doubleValue).sum();
                double totalGasto = projeto.getDespesas().values().stream()
                        .flatMap(List::stream)
                        .mapToDouble(Despesa::getValor)
                        .sum();
                double totalDisponivel = totalPrevisto - totalGasto;

                detalhesArea.append("Orçamento: " + projeto.formatarValor(totalPrevisto) + "\n");
                detalhesArea.append("Total Gasto: " + projeto.formatarValor(totalGasto) + "\n");
                detalhesArea.append("Total Disponível: " + projeto.formatarValor(totalDisponivel) + "\n");
            }
        }
    }
}
