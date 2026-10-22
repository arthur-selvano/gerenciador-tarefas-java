package com.empresa.tarefas.view;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal(String nomeUsuario) {
        setTitle("Gerenciador de Tarefas");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuSessao = new JMenu("Sessão");
        JMenuItem itemSair = new JMenuItem("Sair / Logout");

        itemSair.addActionListener(e -> efectuarLogout());
        menuSessao.add(itemSair);

        JMenu menuUsuario = new JMenu("Olá, " + nomeUsuario);
        menuUsuario.setEnabled(false);
        menuUsuario.putClientProperty("FlatLaf.style", "disabledForeground: $menu.foreground; font: font.bold");

        barraMenu.add(javax.swing.Box.createHorizontalGlue());

        barraMenu.add(menuUsuario);
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
