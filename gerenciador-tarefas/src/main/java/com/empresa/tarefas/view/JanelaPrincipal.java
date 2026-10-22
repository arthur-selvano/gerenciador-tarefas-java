package com.empresa.tarefas.view;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        setTitle("Gerenciador de Tarefas - Empresa");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuSessao = new JMenu("Sessão");
        JMenuItem itemSair = new JMenuItem("Sair / Logout");

        itemSair.addActionListener(e -> efectuarLogout());
        menuSessao.add(itemSair);
        barraMenu.add(menuSessao);
        setJMenuBar(barraMenu);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Pessoas", new PainelPessoas());
        abas.addTab("Tarefas", new PainelTarefas());

        add(abas);
    }

    private void efectuarLogout() {
        new TelaLogin().setVisible(true);
        this.dispose();
    }
}