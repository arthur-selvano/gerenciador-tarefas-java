package com.empresa.tarefas.view;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        setTitle("Gerenciador de Tarefas - Empresa");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Pessoas", new PainelPessoas());
        abas.addTab("Tarefas", new PainelTarefas());

        add(abas);
    }
}