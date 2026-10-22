package com.empresa.tarefas.model;

public record Pessoa(Integer id, String nome, String email, String senha, boolean isAdmin) {}