package janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Projeto;
import modelo.Despesa;

public class AlterarRubricasFrame extends JFrame {
    private Projeto projeto;

    public AlterarRubricasFrame(Projeto projeto) {
        this.projeto = projeto;
        initUI();
    }

    private void initUI() {
        setTitle("Alterar Rubricas");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        String[] rubricas = {"Despesas de Capital", "Material de Consumo", "Serviços de Terceiros / Pessoa Física", "Serviços de Terceiros / Pessoa Jurídica", "Diárias", "Passagens"};

        for (String rubrica : rubricas) {
            JButton btnRubrica = new JButton(rubrica);
            btnRubrica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirDetalhesRubrica(rubrica);
                }
            });
            panel.add(btnRubrica);
        }

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlterarProjetoFrame(projeto).setVisible(true);
                dispose();
            }
        });
        panel.add(btnVoltar);

        getContentPane().add(panel);
    }

    private void abrirDetalhesRubrica(String rubrica) {
        double valorPrevisto = projeto.getRubricas().get(rubrica);
        double valorGasto = projeto.consultarDespesas(rubrica).stream().mapToDouble(Despesa::getValor).sum();
        double valorDisponivel = valorPrevisto - valorGasto;

        JDialog detalhesDialog = new JDialog(this, "Detalhes da Rubrica", true);
        detalhesDialog.setSize(400, 300);
        detalhesDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel labelRubrica = new JLabel("Rubrica: " + rubrica);
        JLabel labelPrevisto = new JLabel("Previsto: " + projeto.formatarValor(valorPrevisto));
        JLabel labelGasto = new JLabel("Gasto: " + projeto.formatarValor(valorGasto));
        JLabel labelDisponivel = new JLabel("Disponível: " + projeto.formatarValor(valorDisponivel));

        JTextField valorField = new JTextField(String.valueOf(valorPrevisto));

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double novoValor = Double.parseDouble(valorField.getText());
                projeto.getRubricas().put(rubrica, novoValor);
                projeto.atualizar(projeto.getTitulo());
                detalhesDialog.dispose();
            }
        });

        panel.add(labelRubrica);
        panel.add(labelPrevisto);
        panel.add(labelGasto);
        panel.add(labelDisponivel);
        panel.add(valorField);
        panel.add(btnSalvar);

        detalhesDialog.getContentPane().add(panel);
        detalhesDialog.setVisible(true);
    }
}
