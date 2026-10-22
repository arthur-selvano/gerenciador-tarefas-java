package com.empresa.tarefas.model;

public record Tarefa(Integer id, String titulo, String descricao, boolean concluida, Integer pessoaId) {}