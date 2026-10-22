package com.empresa.tarefas.dao;

import com.empresa.tarefas.config.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean autenticar(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}