
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Jornada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class JornadaListResponse {

    private List<JornadaResponse> jornadas;
    private long total;
    private long paginaActual;
    private long filas;

    public JornadaListResponse(List<Jornada> jornadas) {
        this.jornadas = new ArrayList<>();
        jornadas.forEach((r) -> this.jornadas.add(new JornadaResponse(r)));
    }
    public JornadaListResponse(List<Jornada> jornadas, long total, long paginaActual, long filas) {
        this(jornadas);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<JornadaResponse> getJornadaos() {
        return jornadas;
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
