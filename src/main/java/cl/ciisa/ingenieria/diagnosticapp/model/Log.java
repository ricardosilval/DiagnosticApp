/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.model;

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
public class Log extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int TYPE_DEBUG = 1;
    public static final int TYPE_INFO = 2;
    public static final int TYPE_WARNING = 3;
    public static final int TYPE_ERROR = 4;
    public static final int TYPE_CRITICAL = 5;

    private String id;
    private String processId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private Integer type;
    private String hostname;
    private String message;
    private String stackTraceFileId;
    private String clase;
    private Integer line;

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

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

}
