
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Calendarizacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class CalendarizacionListResponse {

    private List<CalendarizacionResponse> calendarizaciones;
    private long total;
    private long paginaActual;
    private long filas;

    public CalendarizacionListResponse(List<Calendarizacion> calendarizaciones) {
        this.calendarizaciones = new ArrayList<>();
        calendarizaciones.forEach((r) -> this.calendarizaciones.add(new CalendarizacionResponse(r)));
    }
    public CalendarizacionListResponse(List<Calendarizacion> calendarizaciones, long total, long paginaActual, long filas) {
        this(calendarizaciones);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<CalendarizacionResponse> getCalendarizaciones() {
        return calendarizaciones;
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
