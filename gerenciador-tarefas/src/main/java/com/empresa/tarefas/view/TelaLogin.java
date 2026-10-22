package com.empresa.tarefas.view;

import com.empresa.tarefas.dao.UsuarioDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public TelaLogin() {
        setTitle("Acesso ao Sistema");
        setSize(320, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        panel.add(txtSenha);

        btnEntrar = new JButton("Entrar");
        panel.add(new JLabel());
        panel.add(btnEntrar);

        add(panel);

        btnEntrar.addActionListener((ActionEvent e) -> realizarLogin());
    }

    private void realizarLogin() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        boolean autenticado = dao.autenticar(usuario, senha);

        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

            JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
            janelaPrincipal.setVisible(true);

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}