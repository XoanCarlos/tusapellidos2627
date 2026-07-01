package com.clase.persistencia;

import com.clase.modelo.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAOJson {

    private static final String RUTA = "data/clientes.json";
   
    
    // =========================
    // ADAPTADOR PARA LOCALDATE

    
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    // =========================
    // LEER CLIENTES
    // =========================
    public List<Cliente> leer() {

        try {
            if (!Files.exists(Paths.get(RUTA))) {
                return new ArrayList<>();
            }

            Reader reader = new FileReader(RUTA);

            Type tipoLista = new TypeToken<List<Cliente>>() {}.getType();

            List<Cliente> lista = gson.fromJson(reader, tipoLista);

            reader.close();

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // =========================
    // GUARDAR CLIENTE
    // =========================
    public void guardar(Cliente cliente) {

        List<Cliente> lista = leer();

        lista.add(cliente);

        try (Writer writer = new FileWriter(RUTA)) {
            gson.toJson(lista, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}