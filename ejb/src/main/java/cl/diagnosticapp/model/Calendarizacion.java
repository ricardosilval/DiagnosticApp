/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "calendarizaciones")
@XmlRootElement
public class Calendarizacion extends BaseModel implements Serializable {

    @Id
    @Column(name = "calendarizacion_id")
    private String id;
    @Column(name = "fecha_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_termino")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaTermino;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;
    @Column(name = "estado")
    private int estado;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "calendarizaciones_evaluaciones", joinColumns = @JoinColumn(name = "calendarizacion_id"), inverseJoinColumns = @JoinColumn(name = "evaluacion_id"))
    @JsonIgnore
    private Set<Evaluacion> evaluaciones;

    public Calendarizacion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
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

    public Set<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(Set<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

}
