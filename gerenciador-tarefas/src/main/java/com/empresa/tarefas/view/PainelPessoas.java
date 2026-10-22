package com.empresa.tarefas.view;

import com.empresa.tarefas.dao.PessoaDAO;
import com.empresa.tarefas.model.Pessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelPessoas extends JPanel {

    private final PessoaDAO pessoaDAO = new PessoaDAO();

    private JTextField txtNome;
    private JTextField txtEmail;

    private JTable tabela;
    private DefaultTableModel modelTabela;

    public PainelPessoas() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastrar pessoa"));

        form.add(new JLabel("Nome: "));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Email: "));
        txtEmail = new JTextField();
        form.add(txtEmail);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        form.add(btnSalvar);

        add(form, BorderLayout.NORTH);

        modelTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Email"}, 0);
        tabela = new JTable(modelTabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        atualizarTabela();
    }

    private void salvar() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();

        if (nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        pessoaDAO.salvar(new Pessoa(null, nome, email));
        txtNome.setText("");
        txtEmail.setText("");
        atualizarTabela();
    }

    public void atualizarTabela(){
        modelTabela.setRowCount(0);

        for(Pessoa p : pessoaDAO.listarTodas()) {
            modelTabela.addRow(new Object[]{p.id(), p.nome(), p.email()});
        }
    }
}
