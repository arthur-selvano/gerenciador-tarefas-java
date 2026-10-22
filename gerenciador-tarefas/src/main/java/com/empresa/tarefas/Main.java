package com.empresa.tarefas;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.empresa.tarefas.view.TelaLogin;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception ex) {
            System.err.println("Falha ao inicializar o Look and Feel");
        }

        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}