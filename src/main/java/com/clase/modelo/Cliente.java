package com.clase.modelo;

import java.time.LocalDate;

public class Cliente {

    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String movil;
    private String email;
    private String tipoCliente;
    private LocalDate fechaAlta;
    private boolean activo;
    private String observaciones;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String apellidos,
                   String dni, String movil, String email,
                   String tipoCliente, LocalDate fechaAlta,
                   boolean activo, String observaciones) {

        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.movil = movil;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}