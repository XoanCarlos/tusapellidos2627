package com.clase.persistencia;

import java.util.List;

import com.clase.modelo.Cliente;

public interface ClienteDAO {

    void guardar(Cliente cliente);

    //llamada a la bbdd cliente para devolver todos los clientes que hay en la bbdd
    //se almacena en una lista de clientes y se devuelve al controlador para que lo pinte en la tabla
    
    List<Cliente> listar();


    //llamada a la bbdd cliente para devolver un cliente que hay en la bbdd
    Cliente buscarPorId(int id);

    // =========================
    // MODIFICAR UN CLIENTE
    // =========================
    void modificar(Cliente cliente);

    // ============================
    // ELIMINAR UN CLIENTE
    // ============================
    void eliminar(int id);


}