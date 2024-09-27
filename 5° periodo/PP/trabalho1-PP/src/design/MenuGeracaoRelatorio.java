package design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import funcoes.ConexaoDatabase;
import funcoes.DefesaDAO;

public class MenuGeracaoRelatorio {

    JFrame frameRelatorio;
    private static Connection connection;
    private static List<Integer> defesaIDs;
    private static int atualDefesaIndice = 0;
    private static JLabel informacoesLabel;
    private static List<Integer> certifiedDefenseIds = new ArrayList<>();

    public MenuGeracaoRelatorio() {
        initialize();
    }

    private void initialize() {
        connection = ConexaoDatabase.connectToDatabase(connection);
        defesaIDs = DefesaDAO.getDefesaIDs(connection);
        DefesaDAO.carregarDefesasCertificadasIDs(connection, certifiedDefenseIds);

        frameRelatorio = new JFrame("Relatório de Defesas");
        frameRelatorio.setResizable(false);
        frameRelatorio.setBounds(100, 100, 575, 439);
        frameRelatorio.setLocationRelativeTo(null);
        frameRelatorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRelatorio.getContentPane().setLayout(new BorderLayout());

        informacoesLabel = new JLabel();
        informacoesLabel.setVerticalAlignment(JLabel.CENTER);
        informacoesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        informacoesLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        DefesaDAO.atualizarDefesaInformacoes(connection, certifiedDefenseIds, defesaIDs, atualDefesaIndice, informacoesLabel);

        frameRelatorio.getContentPane().add(new JScrollPane(informacoesLabel), BorderLayout.CENTER);

        JPanel btnPainel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnProxDefesas = new JButton("Próximas defesas");
        btnProxDefesas.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnProxDefesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualDefesaIndice = (atualDefesaIndice + 1) % defesaIDs.size();
                DefesaDAO.atualizarDefesaInformacoes(connection, certifiedDefenseIds, defesaIDs, atualDefesaIndice, informacoesLabel);
            }
        });

        JButton btnProfDefesas = new JButton("Defesas passadas de um determinado professor");
        btnProfDefesas.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnProfDefesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String professor = JOptionPane.showInputDialog(frameRelatorio, "Digite o nome do professor:");
                if (professor != null && !professor.trim().isEmpty()) {
                    DefesaDAO.mostrarProfDefesas(connection, professor.trim());
                }
            }
        });

        JButton btnConvite = new JButton("Convite para defesa");
        btnConvite.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnConvite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefesaDAO.conviteProfessor(connection, defesaIDs, atualDefesaIndice);
            }
        });

        JButton btnCertificado = new JButton("Certificado de defesa");
        btnCertificado.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCertificado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefesaDAO.certificadoDefesa(connection, certifiedDefenseIds, defesaIDs, atualDefesaIndice, informacoesLabel);
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal frame = new MenuPrincipal();
                frame.framePrincipal.setVisible(true);
                frameRelatorio.dispose();
            }
        });

        btnPainel.add(btnProxDefesas);
        btnPainel.add(btnProfDefesas);
        btnPainel.add(btnConvite);
        btnPainel.add(btnCertificado);
        btnPainel.add(btnVoltar);

        frameRelatorio.getContentPane().add(btnPainel, BorderLayout.SOUTH);

        frameRelatorio.setSize(900, 400);
        DefesaDAO.centralizarJanela(frameRelatorio);
        frameRelatorio.setVisible(true);
    }

}
