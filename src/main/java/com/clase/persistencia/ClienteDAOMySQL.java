package com.clase.persistencia;

import com.clase.modelo.Cliente;
import java.sql.Connection;
import java.sql.SQLException;

// Implementación de la interfaz ClienteDAO para MySQL

public class ClienteDAOMySQL implements ClienteDAO {

    @Override
    public void guardar(Cliente cliente) {

        try (Connection conexion = ConexionMySQL.getConexion()) {
            //cadena SQL para insertar un cliente en la base de datos

            String sql = """
                INSERT INTO clientes
                (nombre, apellidos, dni, movil, email,
                tipoCliente, fechaAlta, activo, observaciones)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
            // Preparar la sentencia SQL

            // Representa una sentencia SQL precompilada que se puede ejecutar varias veces con diferentes parámetros.
            var ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getMovil());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getTipoCliente());
            // Convertir LocalDate a java.sql.Date antes de establecerlo en la sentencia SQL
            ps.setDate(7, java.sql.Date.valueOf(cliente.getFechaAlta()));
            // Establecer el valor del campo activo como un booleano
            ps.setBoolean(8, cliente.isActivo());
            ps.setString(9, cliente.getObservaciones());

            // Ejecutar la sentencia SQL
            ps.executeUpdate();

            System.out.println("Conexión realizada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}