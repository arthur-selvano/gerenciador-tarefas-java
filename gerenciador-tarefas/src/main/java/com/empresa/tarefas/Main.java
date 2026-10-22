package com.empresa.tarefas;

import com.empresa.tarefas.dao.PessoaDAO;
import com.empresa.tarefas.dao.TarefaDAO;
import com.empresa.tarefas.model.Pessoa;
import com.empresa.tarefas.model.Tarefa;

public class Main {
    public static void main(String[] args) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        TarefaDAO tarefaDAO = new TarefaDAO();

        System.out.println("--- 1. Listando Pessoas Iniciais do Banco ---");
        pessoaDAO.listarTodas().forEach(p -> System.out.println(p.id() + ": " + p.nome() + " (" + p.email() + ")"));

        System.out.println("\n--- 2. Cadastrando Nova Pessoa ---");
        pessoaDAO.salvar(new Pessoa(null, "Roberto Lima", "roberto@empresa.com"));

        System.out.println("\n--- 3. Cadastrando Tarefa para Pessoa 1 ---");
        tarefaDAO.salvar(new Tarefa(null, "Revisar Código", "Fazer code review do PR #12", false, 1));

        System.out.println("\n--- 4. Listando Tarefas da Pessoa 1 ---");
        tarefaDAO.listarPorPessoa(1).forEach(t -> System.out.println("- [" + (t.concluida() ? "X" : " ") + "] " + t.titulo() + ": " + t.descricao()));
    }
}