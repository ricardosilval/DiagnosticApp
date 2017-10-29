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
public class Pregunta {

    private String id;
    private Evaluacion evaluacion;
    private Imagen imagen;
    private Categoria categoria;
    private String cuerpo;
    private int estado;

    public Pregunta() {
    }

    public Pregunta(String id, Evaluacion evaluacion, Imagen imagen, Categoria subcategoria, String cuerpo, int estado) {
        this.id = id;
        this.evaluacion = evaluacion;
        this.imagen = imagen;
        this.categoria = subcategoria;
        this.cuerpo = cuerpo;
        this.estado = estado;
    }

    public List<Pregunta> listar() {

        return new ArrayList<>();
    }

    public Pregunta ver(String preguntaId) {

        return new Pregunta();
    }

    public Pregunta crear(Pregunta pregunta) {
        return new Pregunta();
    }

    public Pregunta modificar(String preguntaId) {

        return new Pregunta();
    }

    public void eliminar(Pregunta pregunta) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Categoria getSubcategoria() {
        return categoria;
    }

    public void setSubcategoria(Categoria subcategoria) {
        this.categoria = subcategoria;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
