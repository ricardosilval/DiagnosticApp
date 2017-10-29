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
public class Jornada {

    private String id;
    private String codigo;
    private String nombre;

    public Jornada() {
    }

    public Jornada(String id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public List<Jornada> listar() {

        return new ArrayList<>();
    }

    public Jornada ver(String jornadaId) {

        return new Jornada();
    }

    public Jornada crear(Jornada jornada) {
        return new Jornada();
    }

    public Jornada modificar(String jornadaId) {

        return new Jornada();
    }

    public void eliminar(Jornada jornada) {

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
