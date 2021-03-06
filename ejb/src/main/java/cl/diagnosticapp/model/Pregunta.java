/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "preguntas")
@XmlRootElement
public class Pregunta extends BaseModel implements Serializable {

    @Id
    @Column(name = "pregunta_id")
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluacion_id")
    @JsonIgnore
    private Evaluacion evaluacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id")
    @JsonIgnore
    private Imagen imagen;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    @JsonIgnore
    private Categoria categoria;
    @Column(name = "cuerpo")
    private String cuerpo;
    @Column(name = "estado")
    private int estado;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pregunta", cascade = CascadeType.ALL)
//    private Set<Respuesta> respuestas = new HashSet<Respuesta>();

    public Pregunta() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

//    public Set<Respuesta> getRespuestas() {
//        return respuestas;
//    }
//
//    public void setRespuestas(Set<Respuesta> respuestas) {
//        this.respuestas = respuestas;
//    }

    
}
