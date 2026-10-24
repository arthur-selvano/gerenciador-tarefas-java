package com.empresa.tarefas.view;

import com.empresa.tarefas.dao.TarefaDAO;
import com.empresa.tarefas.model.Pessoa;
import com.empresa.tarefas.model.Tarefa;
import com.empresa.tarefas.dao.PessoaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelTarefas extends JPanel {

    private final TarefaDAO tarefaDAO = new TarefaDAO();
    private final PessoaDAO pessoaDAO = new PessoaDAO();

    private JTextField txtTitulo, txtDescricao, txtPessoaId;
    private JComboBox<Pessoa> comboBox;
    private JTable tabela;
    private DefaultTableModel modelTabela;

    public PainelTarefas() {
        setLayout(new BorderLayout(10, 20));

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastrar Tarefa"));

        form.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        form.add(txtTitulo);

        form.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        form.add(txtDescricao);

        form.add(new JLabel("ID da Pessoa:"));
        txtPessoaId = new JTextField();
        form.add(txtPessoaId);

        JButton btnSalvar = new JButton("Salvar Tarefa");
        btnSalvar.addActionListener(e -> salvar());
        form.add(btnSalvar);

        add(form, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filtrar por Pessoa:"));

        DefaultComboBoxModel<Pessoa> model = new DefaultComboBoxModel<>();
        for(Pessoa p : pessoaDAO.listarTodas()) {
            model.addElement(p);
        }

        comboBox = new JComboBox<>(model);
        comboBox.setPreferredSize(new Dimension(400, 40));
        filterPanel.add(comboBox);

        comboBox.addActionListener(e -> filtrar());

        centerPanel.add(filterPanel, BorderLayout.NORTH);

        modelTabela = new DefaultTableModel(new String[]{"ID", "Título", "Descrição", "Concluída", "ID Pessoa"}, 0);
        tabela = new JTable(modelTabela);
        centerPanel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel actionsPanel = new JPanel();
        JButton btnConcluir = new JButton("Concluir");
        btnConcluir.addActionListener(e -> concluir());

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(e -> deletar());

        actionsPanel.add(btnConcluir);
        actionsPanel.add(btnDeletar);
        centerPanel.add(actionsPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void salvar() {
        try {
            String titulo = txtTitulo.getText().trim();
            String desc = txtDescricao.getText().trim();
            int pessoaId = Integer.parseInt(txtPessoaId.getText().trim());

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Título é obrigatório!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            tarefaDAO.salvar(new Tarefa(null, titulo, desc, false, pessoaId));
            txtTitulo.setText("");
            txtDescricao.setText("");
            txtPessoaId.setText("");

            atualizarTabela(pessoaId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID da Pessoa inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrar() {
        Pessoa pessoaSelecionada = (Pessoa) comboBox.getSelectedItem();
        if (pessoaSelecionada != null) {
            atualizarTabela(pessoaSelecionada.id());
        }
    }

    private void concluir() {
        int row = tabela.getSelectedRow();
        if (row >= 0) {
            int id = (int) modelTabela.getValueAt(row, 0);
            int pessoaId = (int) modelTabela.getValueAt(row, 4);
            tarefaDAO.atualizarStatus(id, true);
            atualizarTabela(pessoaId);
        }
    }

    private void deletar() {
        int row = tabela.getSelectedRow();
        if (row >= 0) {
            int id = (int) modelTabela.getValueAt(row, 0);
            int pessoaId = (int) modelTabela.getValueAt(row, 4);
            tarefaDAO.deletar(id);
            atualizarTabela(pessoaId);
        }
    }

    private void atualizarTabela(int pessoaId) {
        modelTabela.setRowCount(0);
        for (Tarefa t : tarefaDAO.listarPorPessoa(pessoaId)) {
            modelTabela.addRow(new Object[]{t.id(), t.titulo(), t.descricao(), t.concluida() ? "Sim" : "Não", t.pessoaId()});
        }
    }
}