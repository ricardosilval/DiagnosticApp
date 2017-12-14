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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "evaluaciones")
@XmlRootElement
public class Evaluacion extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "evaluacion_id")
    private String id;
    @Column(name = "titulo")
    private String titulo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    @JsonIgnore
    private Categoria categoria;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendarizacion_id")
    @JsonIgnore
    private Calendarizacion calendarizacion;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "pregunta_id")
//    private List<Pregunta> preguntas = new ArrayList<>();

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "evaluacion", cascade = CascadeType.ALL)
//    private List<Pregunta> preguntas = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "pregunta_id")
//    private Set<Pregunta> preguntas = new HashSet<>();

    public Evaluacion() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

//    public Set<Pregunta> getPreguntas() {
//        return preguntas;
//    }
//
//    public void setPreguntas(Set<Pregunta> preguntas) {
//        this.preguntas = preguntas;
//    }

}
