/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class Calendarizacion extends BaseModel{

    private String id;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private String titulo;
    private String descripcion;
    private Usuario usuario;
    private int estado;
    private List<Evaluacion> evaluaciones;

    public Calendarizacion() {
    }

    public List<Calendarizacion> listar() {

        return new ArrayList<>();
    }

    public Calendarizacion ver(String calendarizacionId) {

        return new Calendarizacion();
    }

    public Calendarizacion crear(Calendarizacion calendarizacion) {
        return new Calendarizacion();
    }

    public Calendarizacion modificar(String calendarizacionId) {

        return new Calendarizacion();
    }

    public void eliminar(Calendarizacion calendarizacion) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(LocalDate fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

}
