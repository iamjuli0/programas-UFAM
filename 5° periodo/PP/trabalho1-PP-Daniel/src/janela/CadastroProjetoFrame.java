package janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import modelo.Projeto;

public class CadastroProjetoFrame extends JFrame {
    private JTextField tituloField;
    private JTextField professorField;
    private Map<String, JTextField> rubricasFields;
    private JLabel somaLabel;
    private double somaRubricas;

    public CadastroProjetoFrame() {
        rubricasFields = new HashMap<>();
        initUI();
    }

    private void initUI() {
        setTitle("Cadastro de Projetos");
        setSize(400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));

        JLabel tituloLabel = new JLabel("Título do Projeto:");
        tituloField = new JTextField();

        JLabel professorLabel = new JLabel("Professor Orientador:");
        professorField = new JTextField();

        JButton btnRubricas = new JButton("Rubricas");
        btnRubricas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirRubricas();
            }
        });

        JButton btnCriar = new JButton("Criar Projeto");
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarProjeto();
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu().setVisible(true);
                dispose();
            }
        });

        somaLabel = new JLabel("Orçamento: R$ 0,00");

        panel.add(tituloLabel);
        panel.add(tituloField);
        panel.add(professorLabel);
        panel.add(professorField);
        panel.add(btnRubricas);
        panel.add(new JLabel());
        panel.add(somaLabel);
        panel.add(new JLabel());
        panel.add(btnCriar);
        panel.add(btnVoltar);

        getContentPane().add(panel);
    }

    private void inserirRubricas() {
        JDialog rubricasDialog = new JDialog(this, "Inserir Rubricas", true);
        rubricasDialog.setSize(400, 400);
        rubricasDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        String[] rubricas = {"Despesas de Capital", "Material de Consumo", "Serviços de Terceiros / Pessoa Física", "Serviços de Terceiros / Pessoa Jurídica", "Diárias", "Passagens"};

        for (String rubrica : rubricas) {
            JLabel label = new JLabel(rubrica + ":");
            JTextField textField = new JTextField();
            rubricasFields.put(rubrica, textField);
            panel.add(label);
            panel.add(textField);
        }

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarRubricas()) {
                    calcularSomaRubricas();
                    rubricasDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(rubricasDialog, "Todos os valores devem ser maiores que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(btnSalvar);
        panel.add(new JLabel());

        rubricasDialog.getContentPane().add(panel);
        rubricasDialog.setVisible(true);
    }

    private boolean validarRubricas() {
        for (JTextField textField : rubricasFields.values()) {
            try {
                double valor = Double.parseDouble(textField.getText());
                if (valor <= 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private void calcularSomaRubricas() {
        somaRubricas = rubricasFields.values().stream()
                .mapToDouble(textField -> Double.parseDouble(textField.getText()))
                .sum();
        somaLabel.setText("Orçamento: " + formatarValor(somaRubricas));
    }

    private void criarProjeto() {
        String titulo = tituloField.getText();
        String professor = professorField.getText();

        if (titulo.isEmpty() || professor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (rubricasFields.isEmpty() || somaRubricas <= 0) {
            JOptionPane.showMessageDialog(this, "Insira valores válidos para todas as rubricas.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, Double> rubricas = new HashMap<>();
        for (Map.Entry<String, JTextField> entry : rubricasFields.entrySet()) {
            rubricas.put(entry.getKey(), Double.parseDouble(entry.getValue().getText()));
        }

        Projeto projeto = new Projeto(titulo, professor, rubricas);
        projeto.salvar();

        JOptionPane.showMessageDialog(this, "Projeto criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new MainMenu().setVisible(true);
    }

    private String formatarValor(double valor) {
        NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatador.format(valor);
    }
}
