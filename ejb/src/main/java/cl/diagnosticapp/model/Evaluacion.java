/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
@Table(name = "evaluaciones")
@XmlRootElement
public class Evaluacion  extends BaseModel implements Serializable {

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

    public Evaluacion() {
    }

    public String getId() {
        return id;
    }

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

}
