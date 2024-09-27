package janela;

import gerenciador.GerenciadorProjetos;
import modelo.Projeto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelecionarProjetoParaAlteracaoFrame extends JFrame {
    private JComboBox<String> projetoComboBox;
    private GerenciadorProjetos gerenciadorProjetos;

    public SelecionarProjetoParaAlteracaoFrame() {
        gerenciadorProjetos = new GerenciadorProjetos();
        initUI();
    }

    private void initUI() {
        setTitle("Selecionar Projeto para Alteração");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label = new JLabel("Selecione o Projeto para Alterar:");
        panel.add(label);

        List<Projeto> projetos = gerenciadorProjetos.consultarProjetos();
        if (projetos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não Existem Projetos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        String[] titulos = projetos.stream().map(Projeto::getTitulo).toArray(String[]::new);
        projetoComboBox = new JComboBox<>(titulos);
        panel.add(projetoComboBox);

        JButton btnSelecionar = new JButton("Selecionar");
        btnSelecionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tituloSelecionado = (String) projetoComboBox.getSelectedItem();
                Projeto projeto = gerenciadorProjetos.consultarProjetos().stream()
                        .filter(p -> p.getTitulo().equals(tituloSelecionado))
                        .findFirst()
                        .orElse(null);
                if (projeto != null) {
                    new AlterarProjetoFrame(projeto).setVisible(true);
                    dispose();
                }
            }
        });
        panel.add(btnSelecionar);

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
