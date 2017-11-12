/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
public class Usuario extends BaseModel implements Serializable {
    
    public static final int ESTADO_ACTIVO = 1;
    public static final int ESTADO_INACTIVO = 2;

    @Id
    @Column(name = "usuario_id")
    private String id;
    @Column(name = "correo")
    private String correo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "run")
    private String run;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rol_id")
    @JsonIgnore
    private Rol rol;
    @Column(name = "clave")
    private String clave;
    @Column(name = "cambio_clave")
    private String cambioClave;
    @Column(name = "estado")
    private int estado;
    @Column(name = "creado")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creado;
    

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCambioClave() {
        return cambioClave;
    }

    public void setCambioClave(String cambioClave) {
        this.cambioClave = cambioClave;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

}
