
package cl.diagnosticapp.model.requests;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ricardo
 */
public class CarreraRequest extends PortalRequest {

    @NotNull //opcional
    private String nombre;
    private List<String> jonadas;

//    public String getCampo() {
//        return rut;
//    }
//
//    public List<String> getListas() {
//        return sucursales;
//    }


}
