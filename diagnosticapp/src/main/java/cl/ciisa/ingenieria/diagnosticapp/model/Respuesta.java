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
public class Respuesta {

    private String id;
    private Pregunta pregunta;
    private String cuerpo;
    private int valor;
    private int estado;
    private Imagen imagen;

    public Respuesta() {
    }

    public Respuesta(String id, Pregunta pregunta, String cuerpo, int valor, int estado, Imagen imagen) {
        this.id = id;
        this.pregunta = pregunta;
        this.cuerpo = cuerpo;
        this.valor = valor;
        this.estado = estado;
        this.imagen = imagen;
    }

    public List<Respuesta> listar() {

        return new ArrayList<>();
    }

    public Respuesta ver(String respuestaId) {

        return new Respuesta();
    }

    public Respuesta crear(Respuesta respuesta) {
        return new Respuesta();
    }

    public Respuesta modificar(String respuestaId) {

        return new Respuesta();
    }

    public void eliminar(Respuesta respuesta) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

}
