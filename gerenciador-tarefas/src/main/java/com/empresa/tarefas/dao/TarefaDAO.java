package com.empresa.tarefas.dao;

import com.empresa.tarefas.config.FabricaConexao;
import com.empresa.tarefas.model.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void salvar(Tarefa t) {
        String sql = "INSERT INTO tarefas (titulo, descricao, pessoa_id) VALUES (?, ?, ?)";
        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.titulo());
            stmt.setString(2, t.descricao());
            stmt.setInt(3, t.pessoaId());
            stmt.executeUpdate();
            System.out.println("Tarefa cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listarPorPessoa(int pessoaId) {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE pessoa_id = ?";
        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pessoaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBoolean("concluida"),
                        rs.getInt("pessoa_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarStatus(int id, boolean concluida) {
        String sql = "UPDATE tarefas SET concluida = ? WHERE id = ?";
        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, concluida);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Status da tarefa atualizado!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Tarefa deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar tarefa: " + e.getMessage());
        }
    }
}