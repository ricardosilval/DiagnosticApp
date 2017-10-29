package cl.ciisa.ingenieria.diagnosticapp.dto;

import cl.ciisa.ingenieria.diagnosticapp.util.JsonUtil;
import cl.ciisa.ingenieria.diagnosticapp.util.LogicUtil;

/**
 * Model generico para clases de Request en la API.
 * Contiene metodos de uso comun entre los modelos
 * @author ricardo
 */
public abstract class BaseRequest {
    

    public BaseRequest() {
        
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
        if(object instanceof BaseRequest)return false;
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
