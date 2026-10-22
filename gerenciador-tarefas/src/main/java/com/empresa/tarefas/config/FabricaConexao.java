package com.empresa.tarefas.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/tarefas_db";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgrespassword";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}