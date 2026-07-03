package com.clase.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    // ============================
    // DATOS DE CONEXIÓN
    // ============================

    private static final String URL =
            "jdbc:mysql://localhost:3306/bbdd?serverTimezone=UTC";

    private static final String USER = "user";

    private static final String PASSWORD = "abc123";

    // ============================
    // OBTENER CONEXIÓN
    // ============================

    public static Connection getConexion() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

} 