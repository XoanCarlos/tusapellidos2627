package com.clase.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos MySQL del CRM.
 *
 * Esta clase proporciona un único método estático para obtener
 * una conexión con la base de datos utilizando JDBC.
 *
 * @author Juan Carlos
 * @version 1.0
 * @since 2026
 */
public class ConexionMySQL {

    /** URL de conexión a la base de datos. */
    private static final String URL =
            "jdbc:mysql://localhost:3306/bbdd?serverTimezone=UTC";

    /** Usuario de la base de datos. */
    private static final String USER = "user";

    /** Contraseña de acceso. */
    private static final String PASSWORD = "abc123";

    /**
     * Obtiene una conexión con la base de datos.
     *
     * @return conexión abierta con MySQL.
     * @throws SQLException si se produce un error durante la conexión.
     */
    public static Connection getConexion() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);

    }
}
