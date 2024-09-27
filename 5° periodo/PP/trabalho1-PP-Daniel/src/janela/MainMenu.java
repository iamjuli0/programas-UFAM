package janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        initUI();
    }

    private void initUI() {
        setTitle("Sistema de Gerenciamento de Projetos");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnCadastroProjeto = new JButton("CADASTRO DE PROJETOS");
        JButton btnAlterarProjeto = new JButton("ALTERAR PROJETO");
        JButton btnDeletarProjeto = new JButton("DELETAR PROJETO");
        JButton btnConsultarProjeto = new JButton("CONSULTAR PROJETO");

        btnCadastroProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroProjetoFrame().setVisible(true);
                dispose();
            }
        });

        btnAlterarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelecionarProjetoParaAlteracaoFrame().setVisible(true);
                dispose();
            }
        });

        btnDeletarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletarProjetoFrame().setVisible(true);
                dispose();
            }
        });

        btnConsultarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarProjetoFrame().setVisible(true);
                dispose();
            }
        });

        panel.add(btnCadastroProjeto);
        panel.add(btnAlterarProjeto);
        panel.add(btnDeletarProjeto);
        panel.add(btnConsultarProjeto);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
