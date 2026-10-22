package com.empresa.tarefas.dao;

import com.empresa.tarefas.config.FabricaConexao;
import com.empresa.tarefas.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void salvar(Pessoa p) {
        String sql = "INSERT INTO pessoas (nome, email, senha, isAdmin) VALUES (?, ?, ?, ?)";

        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.nome());
            stmt.setString(2, p.email());
            stmt.setString(3, p.senha());
            stmt.setBoolean(4, p.isAdmin());

            stmt.executeUpdate();

            System.out.println("Pessoa cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar pessoa: " + e.getMessage());
        }
    }

    public List<Pessoa> listarTodas() {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        try (Connection conn = FabricaConexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBoolean("isAdmin")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pessoas: " + e.getMessage());
        }
        return lista;
    }
}