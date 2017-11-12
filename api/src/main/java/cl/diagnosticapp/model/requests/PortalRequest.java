package cl.diagnosticapp.model.requests;

import cl.diagnosticapp.utils.JsonUtil;
import cl.diagnosticapp.utils.LogicUtil;


/**
 * Model generico para clases de Request en la API.
 * Contiene metodos de uso comun entre los modelos
 */
public abstract class PortalRequest {
    
    public final static String DATE_REGEX = "(^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$)|^\\s*";
        public final static String RUT_REGEX = "^0*(\\d{1,3}(\\.?\\d{3})*)\\-?([\\dkK])$";

    public PortalRequest() {
        
    }
    /**
     * Representacion en JSON de instancia de objeto.
     * @return 
     */
    @Override
    public String toString(){
        return JsonUtil.getInstance().toJson(this);
    }
    
    /**
     * Obtiene el Hash (como string) del objeto.
     * @return 
     */
    public String hash() {
        return LogicUtil.getInstance().hash(this.toString());
    }

    /**
     * Compara la instancia del objeto, con otra instacia de objeto.
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        if(object instanceof PortalRequest)return false;
        return object.toString().hashCode()==this.toString().hashCode();
    }

    /**
     * Obtiene el hash (numerico) del objeto.
     * @return 
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

}
