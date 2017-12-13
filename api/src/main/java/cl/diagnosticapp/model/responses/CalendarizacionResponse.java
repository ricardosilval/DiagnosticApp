/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Calendarizacion;
import java.util.Date;

/**
 *
 * @author ricardo
 */
public class CalendarizacionResponse {

    private final String id;
    private final Date fechaInicio;
    private final Date fechaTermino;
    private final String titulo;
    private final String descripcion;
    private final Integer estado;
   

    public CalendarizacionResponse(Calendarizacion model) {
        id = model.getId();
        fechaInicio = model.getFechaInicio();
        fechaTermino = model.getFechaTermino();
        titulo = model.getTitulo();
        descripcion = model.getDescripcion();
        estado = model.getEstado();
        

    }

    public String getId() {
        return id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getEstado() {
        return estado;
    }


}
