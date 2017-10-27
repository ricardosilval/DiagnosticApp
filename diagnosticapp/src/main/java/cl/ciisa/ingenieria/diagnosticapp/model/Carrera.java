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
public class Carrera {

    private String id;
    private String codigo;
    private String nombre;

    public Carrera() {
    }

    public Carrera(String id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public List<Carrera> listar() {

        return new ArrayList<>();
    }

    public Carrera ver(String carreraId) {

        return new Carrera();
    }

    public Carrera crear(Carrera carrera) {
        return new Carrera();
    }

    public Carrera modificar(String carreraId) {

        return new Carrera();
    }

    public void eliminar(Carrera carrera) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
