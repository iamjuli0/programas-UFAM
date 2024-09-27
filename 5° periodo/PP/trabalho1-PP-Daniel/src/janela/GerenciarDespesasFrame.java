package janela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Projeto;
import modelo.Despesa;

public class GerenciarDespesasFrame extends JFrame {
    private Projeto projeto;
    private DefaultTableModel tableModel;
    private JTable despesaTable;

    public GerenciarDespesasFrame(Projeto projeto) {
        this.projeto = projeto;
        initUI();
    }

    private void initUI() {
        setTitle("Gerenciar Despesas");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JButton btnAdicionarDespesa = new JButton("Adicionar Despesa");
        btnAdicionarDespesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarDespesa();
            }
        });

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(btnAdicionarDespesa)
        );

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnAdicionarDespesa)
        );

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Descrição", "Valor", "Data", "Rubrica"}, 0);
        despesaTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(despesaTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnEditarDespesa = new JButton("Editar Despesa");
        btnEditarDespesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarDespesa();
            }
        });

        JButton btnDeletarDespesa = new JButton("Deletar Despesa");
        btnDeletarDespesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarDespesa();
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Código para voltar para a tela anterior ou menu principal
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnEditarDespesa);
        buttonPanel.add(btnDeletarDespesa);
        buttonPanel.add(btnVoltar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        atualizarListaDespesas();  // Atualiza a lista de despesas ao iniciar
    }

    private void adicionarDespesa() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField descricaoField = new JTextField(10);
        JTextField valorField = new JTextField(10);
        JTextField dataField = new JTextField(10);
        JComboBox<String> rubricaComboBox = new JComboBox<>(projeto.getRubricas().keySet().toArray(new String[0]));

        panel.add(new JLabel("Descrição da Despesa:"));
        panel.add(descricaoField);
        panel.add(new JLabel("Valor da Despesa:"));
        panel.add(valorField);
        panel.add(new JLabel("Data da Despesa (dd/MM/yyyy):"));
        panel.add(dataField);
        panel.add(new JLabel("Rubrica:"));
        panel.add(rubricaComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Despesa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String descricao = descricaoField.getText();
            String valorStr = valorField.getText();
            String data = dataField.getText();
            String rubrica = (String) rubricaComboBox.getSelectedItem();

            if (descricao != null && valorStr != null && data != null && rubrica != null) {
                try {
                    double valor = Double.parseDouble(valorStr);
                    Despesa despesa = new Despesa(0, descricao, valor, data, rubrica, projeto.getTitulo());
                    despesa.salvar();
                    atualizarListaDespesas();  // Atualiza a lista de despesas após salvar
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void editarDespesa() {
        int index = despesaTable.getSelectedRow();
        if (index != -1) {
            Despesa despesa = projeto.getDespesas().values().stream()
                    .flatMap(List::stream)
                    .filter(d -> d.getId() == (int) tableModel.getValueAt(index, 0))
                    .findFirst()
                    .orElse(null);

            if (despesa != null) {
                JPanel panel = new JPanel(new GridLayout(0, 1));
                JTextField descricaoField = new JTextField(despesa.getDescricao(), 10);
                JTextField valorField = new JTextField(String.valueOf(despesa.getValor()), 10);
                JTextField dataField = new JTextField(despesa.getData(), 10);

                panel.add(new JLabel("Descrição da Despesa:"));
                panel.add(descricaoField);
                panel.add(new JLabel("Valor da Despesa:"));
                panel.add(valorField);
                panel.add(new JLabel("Data da Despesa (dd/MM/yyyy):"));
                panel.add(dataField);

                descricaoField.getDocument().addDocumentListener(new SimpleDocumentListener(() -> despesa.setDescricao(descricaoField.getText())));
                valorField.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
                    try {
                        despesa.setValor(Double.parseDouble(valorField.getText()));
                    } catch (NumberFormatException ignored) {}
                }));
                dataField.getDocument().addDocumentListener(new SimpleDocumentListener(() -> despesa.setData(dataField.getText())));

                int result = JOptionPane.showConfirmDialog(null, panel, "Editar Despesa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    despesa.atualizar();
                    atualizarListaDespesas();
                }
            }
        }
    }

    private void deletarDespesa() {
        int index = despesaTable.getSelectedRow();
        if (index != -1) {
            Despesa despesa = projeto.getDespesas().values().stream()
                    .flatMap(List::stream)
                    .filter(d -> d.getId() == (int) tableModel.getValueAt(index, 0))
                    .findFirst()
                    .orElse(null);

            if (despesa != null) {
                String rubrica = despesa.getRubrica();
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar esta despesa?", "Deletar Despesa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    despesa.deletar();
                    atualizarListaDespesas();  // Atualiza a lista de despesas após deletar
                }
            }
        }
    }

    private void atualizarListaDespesas() {
        tableModel.setRowCount(0);  // Limpa a tabela antes de atualizar
        projeto.carregarDespesas();  // Carrega despesas do banco de dados
        for (List<Despesa> despesasList : projeto.getDespesas().values()) {
            for (Despesa despesa : despesasList) {
                tableModel.addRow(new Object[]{despesa.getId(), despesa.getDescricao(), despesa.getValor(), despesa.getData(), despesa.getRubrica()});
            }
        }
    }

    private static class SimpleDocumentListener implements javax.swing.event.DocumentListener {
        private final Runnable onChange;

        public SimpleDocumentListener(Runnable onChange) {
            this.onChange = onChange;
        }

        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            onChange.run();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            onChange.run();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            onChange.run();
        }
    }
}
