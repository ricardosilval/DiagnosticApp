
package cl.diagnosticapp.model.requests;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ricardo
 */
public class PreguntaRequest extends PortalRequest {

    @NotNull //opcional
    private String campo;
    //private List<String> listas;
    
    private String identificador;
    private String evaluacion;
    private String imagen;
    private String categoria;
    private String cuerpo;
    private int estado;
    
//    public String getCampo() {
//        return rut;
//    }
//
//    public List<String> getListas() {
//        return sucursales;
//    }

    public String getCampo() {
        return campo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public int getEstado() {
        return estado;
    }


}
