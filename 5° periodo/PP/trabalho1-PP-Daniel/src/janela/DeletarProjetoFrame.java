package janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Projeto;
import gerenciador.GerenciadorProjetos;

public class DeletarProjetoFrame extends JFrame {
    private JComboBox<String> projetoComboBox;
    private GerenciadorProjetos gerenciadorProjetos;

    public DeletarProjetoFrame() {
        gerenciadorProjetos = new GerenciadorProjetos();
        initUI();
    }

    private void initUI() {
        setTitle("Deletar Projeto");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label = new JLabel("Selecione o Projeto para Deletar:");
        panel.add(label);

        List<Projeto> projetos = gerenciadorProjetos.consultarProjetos();
        if (projetos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não Existem Projetos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new MainMenu().setVisible(true);
            return;
        }

        String[] titulos = projetos.stream().map(Projeto::getTitulo).toArray(String[]::new);
        projetoComboBox = new JComboBox<>(titulos);
        panel.add(projetoComboBox);

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tituloSelecionado = (String) projetoComboBox.getSelectedItem();
                if (tituloSelecionado != null) {
                    int confirm = JOptionPane.showConfirmDialog(DeletarProjetoFrame.this,
                            "Tem certeza de que deseja deletar o projeto: " + tituloSelecionado + "?",
                            "Confirmar Exclusão",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        gerenciadorProjetos.removerProjeto(tituloSelecionado);
                        JOptionPane.showMessageDialog(DeletarProjetoFrame.this, "Projeto deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new MainMenu().setVisible(true);
                    }
                }
            }
        });
        panel.add(btnDeletar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu().setVisible(true);
                dispose();
            }
        });
        panel.add(btnVoltar);

        getContentPane().add(panel);
    }
}
