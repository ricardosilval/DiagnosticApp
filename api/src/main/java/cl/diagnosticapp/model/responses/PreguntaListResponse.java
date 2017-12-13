
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Pregunta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class PreguntaListResponse {

    private List<PreguntaResponse> preguntas;
    private long total;
    private long paginaActual;
    private long filas;

    public PreguntaListResponse(List<Pregunta> preguntas) {
        this.preguntas = new ArrayList<>();
        preguntas.forEach((r) -> this.preguntas.add(new PreguntaResponse(r)));
    }
    public PreguntaListResponse(List<Pregunta> preguntas, long total, long paginaActual, long filas) {
        this(preguntas);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<PreguntaResponse> getPreguntaos() {
        return preguntas;
    }

    public long getTotal() {
        return total;
    }

    public long getPaginaActual() {
        return paginaActual;
    }

    public long getFilas() {
        return filas;
    }
    

}
