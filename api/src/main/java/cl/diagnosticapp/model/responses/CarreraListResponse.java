
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Carrera;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class CarreraListResponse {

    private List<CarreraResponse> carreras;
    private long total;
    private long paginaActual;
    private long filas;

    public CarreraListResponse(List<Carrera> carreras) {
        this.carreras = new ArrayList<>();
        carreras.forEach((r) -> this.carreras.add(new CarreraResponse(r)));
    }
    public CarreraListResponse(List<Carrera> carreras, long total, long paginaActual, long filas) {
        this(carreras);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<CarreraResponse> getCarreraos() {
        return carreras;
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
