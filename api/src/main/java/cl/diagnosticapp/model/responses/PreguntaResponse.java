/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Pregunta;


/**
 *
 * @author ricardo
 */
public class PreguntaResponse extends BaseResponse {

    private final String id;
    private final EvaluacionResponse evaluacion;
    private final CategoriaResponse categoria;
    private final String cuerpo;
    private final int estadoCodigo;
    private final String estadoNombre;

    public PreguntaResponse(Pregunta model) {
        id = model.getId();
        evaluacion = new EvaluacionResponse(model.getEvaluacion());
        categoria = new CategoriaResponse(model.getSubcategoria());
        estadoCodigo = model.getEstado();
        estadoNombre = model.getEstado() == 1 ? "Activo" : "Inactivo";
        cuerpo = model.getCuerpo();
    }

    public String getId() {
        return id;
    }
    
    
//Generar geter y ssetter

    public EvaluacionResponse getEvaluacion() {
        return evaluacion;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public int getEstadoCodigo() {
        return estadoCodigo;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

}
