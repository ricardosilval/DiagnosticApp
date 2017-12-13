
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Alumno;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class AlumnoListResponse {

    private List<AlumnoResponse> alumnos;
    private long total;
    private long paginaActual;
    private long filas;

    public AlumnoListResponse(List<Alumno> alumnos) {
        this.alumnos = new ArrayList<>();
        alumnos.forEach((r) -> this.alumnos.add(new AlumnoResponse(r)));
    }
    public AlumnoListResponse(List<Alumno> alumnos, long total, long paginaActual, long filas) {
        this(alumnos);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<AlumnoResponse> getAlumnoos() {
        return alumnos;
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
