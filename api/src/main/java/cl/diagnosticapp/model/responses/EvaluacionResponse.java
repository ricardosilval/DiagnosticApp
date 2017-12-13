/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Evaluacion;


/**
 *
 * @author ricardo
 */
public class EvaluacionResponse{

    private final String id;
    private final String titulo;
    private final CategoriaResponse categoria;
    private final UsuarioResponse usuario;
    private final CalendarizacionResponse calendarizacion;

    public EvaluacionResponse(Evaluacion model) {
        id = model.getId();
        titulo = model.getTitulo();
        categoria = new CategoriaResponse(model.getCategoria());
        usuario = new UsuarioResponse(model.getUsuario());
        calendarizacion = new CalendarizacionResponse(model.getCalendarizacion());
    }

    public String getId() {
        return id;
    }
//Generar geter y ssetter

    public String getTitulo() {
        return titulo;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public CalendarizacionResponse getCalendarizacion() {
        return calendarizacion;
    }

}
