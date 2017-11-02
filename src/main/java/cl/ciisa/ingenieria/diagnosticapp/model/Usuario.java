/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class Usuario {

    private String id;
    private String correo;
    private String nombre;
    private String apellido;
    private String run;
    private Rol rol;
    private String clave;

    public Usuario() {
    }

    public Usuario(String id, String correo, String nombre, String apellido, String run, Rol rol, String clave) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.run = run;
        this.rol = rol;
        this.clave = clave;
    }

    public List<Usuario> listar() {

        return new ArrayList<>();
    }

    public Usuario ver(String usuarioId) {

        return new Usuario();
    }

    public Usuario crear(Usuario usuario) {
        return new Usuario();
    }

    public Usuario modificar(String usuarioId) {

        return new Usuario();
    }

    public void eliminar(Usuario usuario) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
