
package cl.diagnosticapp.model.requests;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ricardo
 */
public class CalendarizacionRequest extends PortalRequest {

    @NotNull //opcional
    private String fechaInicio;
    @NotNull //opcional
    private String fechaTermino;
    private String titulo;
    private Integer estado;
    private String descripcion;
    

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }


}
