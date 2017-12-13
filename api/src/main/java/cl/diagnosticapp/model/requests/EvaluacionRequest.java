
package cl.diagnosticapp.model.requests;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ricardo
 */
public class EvaluacionRequest extends PortalRequest {

    @NotNull //opcional
    private String titulo;
    private String categoria;

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

 
    


}
