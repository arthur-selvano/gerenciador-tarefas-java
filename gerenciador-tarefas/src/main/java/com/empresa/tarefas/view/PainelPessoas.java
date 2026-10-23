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
    private JTextField txtSenha;
    private JCheckBox chkAdmin;

    private JTable tabela;
    private DefaultTableModel modelTabela;

    public PainelPessoas() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(5, 3, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastrar pessoa"));

        form.add(new JLabel("Nome: "));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Email: "));
        txtEmail = new JTextField();
        form.add(txtEmail);

        form.add(new JLabel("Senha: "));
        txtSenha = new JTextField();
        form.add(txtSenha);

        form.add(new JLabel("Admin: "));
        chkAdmin = new JCheckBox();
        form.add(chkAdmin);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        form.add(btnSalvar);

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(e -> deletar());
        form.add(btnDeletar);

        add(form, BorderLayout.NORTH);

        modelTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Email", "Admin"}, 0);
        tabela = new JTable(modelTabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        atualizarTabela();
    }

    private void salvar() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        boolean isAdmin = chkAdmin.isSelected();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        pessoaDAO.salvar(new Pessoa(null, nome, email, senha, isAdmin));
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        chkAdmin.isSelected();

        atualizarTabela();
    }

    public void atualizarTabela(){
        modelTabela.setRowCount(0);

        for(Pessoa p : pessoaDAO.listarTodas()) {
            modelTabela.addRow(new Object[]{p.id(), p.nome(), p.email(), p.isAdmin()});
        }
    }


    private void deletar() {
        int row = tabela.getSelectedRow();
        if (row >= 0) {
            int id = (int) modelTabela.getValueAt(row, 0);
            pessoaDAO.deletar(id);
            atualizarTabela();
        }
    }
}
