/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model;

/**
 *
 * @author ricardo
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo de Log de sistema.
 *
 * @author ricardo
 */
@Entity
@Table(name = "logs")
@XmlRootElement
public class Log extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int TYPE_DEBUG = 1;
    public static final int TYPE_INFO = 2;
    public static final int TYPE_WARNING = 3;
    public static final int TYPE_ERROR = 4;
    public static final int TYPE_CRITICAL = 5;
    @Id
    @Column(name = "log_id")
    private String id;
    @Column(name = "proceso_id")
    private String procesoId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "hostname")
    private String hostname;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "stack_trace_field")
    private String stackTraceFileId;
    @Column(name = "clase")
    private String clase;
    @Column(name = "linea")
    private Integer linea;

    public Log() {
    }

    public Log(String logId) {
        this.id = logId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(String procesoId) {
        this.procesoId = procesoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getStackTraceFileId() {
        return stackTraceFileId;
    }

    public void setStackTraceFileId(String stackTraceFileId) {
        this.stackTraceFileId = stackTraceFileId;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

}
