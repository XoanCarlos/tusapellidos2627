package com.clase.persistencia;

import com.clase.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    @Override // Implementación del método listar() de la interfaz ClienteDAO

    public List<Cliente> listar() {

        // Lista donde iremos almacenando los clientes obtenidos
        // de la base de datos para devolverlos al finalizar.
        List<Cliente> clientes = new ArrayList<>();      

        // Consulta SQL que recupera todos los clientes
        // ordenados por apellidos y nombre.
        String sql = """
            SELECT *
            FROM clientes
            ORDER BY apellidos, nombre
            """;


        try (Connection conexion = ConexionMySQL.getConexion()) {

            // Preparamos la sentencia SQL.
            // PreparedStatement permite ejecutar consultas SQL
            // de forma segura y eficiente.
            PreparedStatement ps = conexion.prepareStatement(sql);


            // Ejecutamos la consulta.
            // El resultado se almacena en un ResultSet.
            ResultSet rs = ps.executeQuery();


            // Recorremos todas las filas obtenidas.
            while (rs.next()) {

                // Creamos un nuevo objeto Cliente
                Cliente cliente = new Cliente();

                // Copiamos cada columna de la base de datos
                // al atributo correspondiente del objeto.
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setDni(rs.getString("dni"));
                cliente.setMovil(rs.getString("movil"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTipoCliente(rs.getString("tipoCliente"));

                // Convertimos java.sql.Date en LocalDate
                cliente.setFechaAlta(rs.getDate("fechaAlta").toLocalDate());

                cliente.setActivo(rs.getBoolean("activo"));
                cliente.setObservaciones(rs.getString("observaciones"));

                // Añadimos el cliente a la lista
                clientes.add(cliente);
            }

        } catch (SQLException e) {

            // Si ocurre cualquier error con la base de datos, mostramos la información por consola.
            e.printStackTrace();
        }

        // Devolvemos la lista completa de clientes.
        return clientes;
    }

    // ==========================================
    // BUSCAR CLIENTE POR SU ID
    // ==========================================
    @Override
    public Cliente buscarPorId(int id) {

        Cliente cliente = null;

        try (Connection conexion = ConexionMySQL.getConexion()) {

            String sql = """
                    SELECT *
                    FROM clientes
                    WHERE id = ?
                    """;

            var ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            var rs = ps.executeQuery();

            if (rs.next()) {

                cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setDni(rs.getString("dni"));
                cliente.setMovil(rs.getString("movil"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTipoCliente(rs.getString("tipoCliente"));
                cliente.setFechaAlta(rs.getDate("fechaAlta").toLocalDate());
                cliente.setActivo(rs.getBoolean("activo"));
                cliente.setObservaciones(rs.getString("observaciones"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;

        }


    // ==========================================
    // MODIFICAR UN CLIENTE
    // ==========================================
    @Override
    public void modificar(Cliente cliente) {

        try (Connection conexion = ConexionMySQL.getConexion()) {

            String sql = """
                UPDATE clientes
                SET nombre = ?,
                    apellidos = ?,
                    dni = ?,
                    movil = ?,
                    email = ?,
                    tipoCliente = ?,
                    fechaAlta = ?,
                    activo = ?,
                    observaciones = ?
                WHERE id = ?
                """;

            var ps = conexion.prepareStatement(sql);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getMovil());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getTipoCliente());
            ps.setDate(7, java.sql.Date.valueOf(cliente.getFechaAlta()));
            ps.setBoolean(8, cliente.isActivo());
            ps.setString(9, cliente.getObservaciones());

            // ID del cliente que se va a modificar
            ps.setInt(10, cliente.getId());

            ps.executeUpdate();

            System.out.println("Cliente modificado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // ==========================================
    // ELIMINAR UN CLIENTE
    // ==========================================
    @Override
    public void eliminar(int id) {

        try (Connection conexion = ConexionMySQL.getConexion()) {

            String sql = "DELETE FROM clientes WHERE id = ?";

            var ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Cliente eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}