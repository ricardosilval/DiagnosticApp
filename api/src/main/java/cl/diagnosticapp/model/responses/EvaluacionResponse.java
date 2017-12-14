/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Evaluacion;
import cl.diagnosticapp.model.Pregunta;
import cl.diagnosticapp.model.Respuesta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ricardo
 */
public class EvaluacionResponse {

    private final String id;
    private final String titulo;
    private final CategoriaResponse categoria;
    private final UsuarioResponse usuario;
    private final CalendarizacionResponse calendarizacion;
    private final List<PreguntaResponse> preguntas;

    public EvaluacionResponse(Evaluacion model, HashMap<Pregunta, List<Respuesta>> preguntasMap) {
        id = model.getId();
        titulo = model.getTitulo();
        categoria = new CategoriaResponse(model.getCategoria());
        usuario = new UsuarioResponse(model.getUsuario());
        calendarizacion = new CalendarizacionResponse(model.getCalendarizacion());
        //preguntas = model.getPreguntas();
        preguntas = new ArrayList<>();

        if (preguntasMap != null) {
            Iterator it = preguntasMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Pregunta pregunta = (Pregunta) pair.getKey();
                List<Respuesta> respuestas = (List<Respuesta>) pair.getValue();
                preguntas.add(new PreguntaResponse(pregunta, respuestas));

                it.remove(); // avoids a ConcurrentModificationException

            }
        }

//        if (preguntasList != null) {
//            preguntasList.forEach((p) -> {
//                preguntas.add(new PreguntaResponse(p));
//            });
//        }
    }

//    public EvaluacionResponse(Evaluacion model, List<Pregunta> preg) {
//        id = model.getId();
//        titulo = model.getTitulo();
//        categoria = new CategoriaResponse(model.getCategoria());
//        usuario = new UsuarioResponse(model.getUsuario());
//        calendarizacion = new CalendarizacionResponse(model.getCalendarizacion());
//        preguntas = preg;
//    }
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

    public List<PreguntaResponse> getPreguntas() {
        return preguntas;
    }

}
