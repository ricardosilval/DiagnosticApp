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
public class Evaluacion {

    private String id;
    private Categoria categoria;
    private Usuario usuario;
    private Calendarizacion calendarizacion;

    public Evaluacion() {
    }

    public Evaluacion(String id, Categoria categoria, Usuario usuario, Calendarizacion calendarizacion) {
        this.id = id;
        this.categoria = categoria;
        this.usuario = usuario;
        this.calendarizacion = calendarizacion;
    }

    public List<Evaluacion> listar() {

        return new ArrayList<>();
    }

    public Evaluacion ver(String evaluacionId) {

        return new Evaluacion();
    }

    public Evaluacion crear(Evaluacion evaluacion) {
        return new Evaluacion();
    }

    public Evaluacion modificar(String evaluacionId) {

        return new Evaluacion();
    }

    public void eliminar(Evaluacion evaluacion) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Calendarizacion getCalendarizacion() {
        return calendarizacion;
    }

    public void setCalendarizacion(Calendarizacion calendarizacion) {
        this.calendarizacion = calendarizacion;
    }

}
