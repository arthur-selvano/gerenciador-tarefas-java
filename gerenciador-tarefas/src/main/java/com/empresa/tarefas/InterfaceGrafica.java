package com.empresa.tarefas;

import com.empresa.tarefas.dao.PessoaDAO;
import com.empresa.tarefas.dao.TarefaDAO;

import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica extends JFrame {

    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final TarefaDAO tarefaDAO = new TarefaDAO();

    public InterfaceGrafica(){
        setTitle("Gerenciador de tarefas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Pessoas", criarAbaPessoas());
        abas.addTab("Tarefas", criarAbaTarefas());

        add(abas);
    }

    private JPanel criarAbaPessoas(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Aba de pessoas"));
        return panel;
    }

    private JPanel criarAbaTarefas(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Aba de tarefas"));
        return panel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new InterfaceGrafica().setVisible(true));
    }

}
