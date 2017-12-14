
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Evaluacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class EvaluacionListResponse {

    private List<EvaluacionResponse> evaluaciones;
    private long total;
    private long paginaActual;
    private long filas;

    public EvaluacionListResponse(List<Evaluacion> evaluaciones) {
        this.evaluaciones = new ArrayList<>();
        evaluaciones.forEach((r) -> this.evaluaciones.add(new EvaluacionResponse(r,null)));
    }
    public EvaluacionListResponse(List<Evaluacion> evaluaciones, long total, long paginaActual, long filas) {
        this(evaluaciones);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<EvaluacionResponse> getEvaluaciones() {
        return evaluaciones;
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
