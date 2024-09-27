package janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Projeto;
import gerenciador.GerenciadorProjetos;

public class AlterarProjetoFrame extends JFrame {
    private Projeto projeto;
    private JTextField tituloField;
    private JTextField professorField;
    private GerenciadorProjetos gerenciadorProjetos;

    public AlterarProjetoFrame(Projeto projeto) {
        this.projeto = projeto;
        this.gerenciadorProjetos = new GerenciadorProjetos();
        initUI();
    }

    private void initUI() {
        setTitle("Alterar Projeto");
        setSize(400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2, 10, 10));

        JLabel tituloLabel = new JLabel("Título do Projeto:");
        tituloField = new JTextField(projeto.getTitulo());

        JLabel professorLabel = new JLabel("Professor Orientador:");
        professorField = new JTextField(projeto.getProfessorResponsavel());

        JButton btnRubricas = new JButton("Alterar Rubricas");
        btnRubricas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
                abrirTelaRubricas();
            }
        });

        JButton btnDespesas = new JButton("Gerenciar Despesas");
        btnDespesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
                abrirTelaDespesas();
            }
        });

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
                JOptionPane.showMessageDialog(AlterarProjetoFrame.this, "Alterações salvas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                new MainMenu().setVisible(true);
                dispose();
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelecionarProjetoParaAlteracaoFrame().setVisible(true);
                dispose();
            }
        });

        panel.add(tituloLabel);
        panel.add(tituloField);
        panel.add(professorLabel);
        panel.add(professorField);
        panel.add(btnRubricas);
        panel.add(btnDespesas);
        panel.add(btnSalvar);
        panel.add(btnVoltar);

        getContentPane().add(panel);
    }

    private void abrirTelaRubricas() {
        AlterarRubricasFrame alterarRubricasFrame = new AlterarRubricasFrame(projeto);
        alterarRubricasFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                atualizarCampos();
            }
        });
        alterarRubricasFrame.setVisible(true);
    }

    private void abrirTelaDespesas() {
        GerenciarDespesasFrame gerenciarDespesasFrame = new GerenciarDespesasFrame(projeto);
        gerenciarDespesasFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                atualizarCampos();
            }
        });
        gerenciarDespesasFrame.setVisible(true);
    }

    private void salvarAlteracoes() {
        String novoTitulo = tituloField.getText();
        String novoProfessor = professorField.getText();

        if (novoTitulo.isEmpty() || novoProfessor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        projeto.setProfessorResponsavel(novoProfessor);
        projeto.atualizar(novoTitulo);
    }

    private void atualizarCampos() {
        tituloField.setText(projeto.getTitulo());
        professorField.setText(projeto.getProfessorResponsavel());
    }
}
