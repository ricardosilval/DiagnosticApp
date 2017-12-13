
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Respuesta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class RespuestaListResponse {

    private List<RespuestaResponse> respuestas;
    private long total;
    private long paginaActual;
    private long filas;

    public RespuestaListResponse(List<Respuesta> respuestas) {
        this.respuestas = new ArrayList<>();
        respuestas.forEach((r) -> this.respuestas.add(new RespuestaResponse(r)));
    }
    public RespuestaListResponse(List<Respuesta> respuestas, long total, long paginaActual, long filas) {
        this(respuestas);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<RespuestaResponse> getRespuestaos() {
        return respuestas;
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
