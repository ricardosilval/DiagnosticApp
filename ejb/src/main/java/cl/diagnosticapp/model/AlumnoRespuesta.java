/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "alumnos_respuestas")
@XmlRootElement
public class AlumnoRespuesta extends BaseModel implements Serializable {

    @Id
    @Column(name = "alumno_respuesta_id")
    private String id;
    @Column(name = "alumno_id")
    private String alumnoId;
    @Column(name = "respuesta_id")
    private String respuestaId;
    
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "alumnos_respuestas", joinColumns = @JoinColumn(name = "alumno_id"), inverseJoinColumns = @JoinColumn(name = "respuesta_id"))
//    @JsonIgnore
//    private Set<Respuesta> respuestas;

    public AlumnoRespuesta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(String alumnoId) {
        this.alumnoId = alumnoId;
    }


    public String getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(String respuestaId) {
        this.respuestaId = respuestaId;
    }

    
}
