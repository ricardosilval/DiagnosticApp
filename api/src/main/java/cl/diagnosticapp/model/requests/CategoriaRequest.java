
package cl.diagnosticapp.model.requests;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ricardo
 */
public class CategoriaRequest extends PortalRequest {

    @NotNull //opcional
    private String campo;
    //private List<String> listas;
//
//    public String getCampo() {
//        return rut;
//    }
//
//    public List<String> getListas() {
//        return sucursales;
//    }


}
