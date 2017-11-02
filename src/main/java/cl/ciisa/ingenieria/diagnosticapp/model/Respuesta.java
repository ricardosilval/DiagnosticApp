/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "respuestas")
@XmlRootElement
public class Respuesta extends BaseModel implements Serializable {

    @Id
    @Column(name = "respuesta_id")
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pregunta_id")
    @JsonIgnore
    private Pregunta pregunta;
    @Column(name = "cuerpo")
    private String cuerpo;
    @Column(name = "valor")
    private int valor;
    @Column(name = "estado")
    private int estado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id")
    @JsonIgnore
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
