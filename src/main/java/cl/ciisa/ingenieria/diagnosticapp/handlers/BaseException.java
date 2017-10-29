package cl.ciisa.ingenieria.diagnosticapp.handlers;

import cl.ciisa.ingenieria.diagnosticapp.dto.BaseResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.apache.commons.httpclient.HttpStatus;

/**
 * Clase para manejo de errores. Encapsula en tiempo de ejecucion los distintos
 * errores registrados en la aplicacion, pudiendo ademas agregar informacion
 * adicional en la excepcion, como los argumentos ingresados que causaron el
 * error
 *
 * @author ricardo
 */
public class BaseException extends RuntimeException {

    private Message error;
    private HashMap<Object, Object> info;

    /**
     * Contructor en base a un Mensaje predefinido. Recibe como argumento un
     * mensaje predefinido como Enum
     *
     * @param e Mensaje predefinido
     */
    public BaseException(Message e) {
        this.error = e;
        this.info = new HashMap<>();
    }

    /**
     * Obtiene el mensaje del error.
     *
     * @return Mensaje del error (codigo y mensaje humano)
     */
    public Message getError() {
        return error;
    }

    /**
     * Agrega informacion adicional a una excepcion. Permite agregar una o mas
     * informaciones adicionales en cadena
     *
     * @param key Nombre de la informacion adicional, usualmente un String
     * @param value Valor de la informacion adicional, un String o un tipo
     * basico, tambien puede ser usado un JSON representando un objeto
     * @return Retorna el mismo objeto con la nueva informacion agregada
     */
    public BaseException set(Object key, Object value) {
        this.info.put(key, value);
        return this;
    }

    /**
     * Informacion adicional. HashMap que contiene todos los elementos que
     * conforman la informacion adicional
     *
     * @return Hashmap con la informacion adicional
     */
    public HashMap<Object, Object> getInfo() {
        return info;
    }

    @Override
    public String getMessage() {
        return this.getError().toString();
    }

    @Override
    public String getLocalizedMessage() {
        String out = "";
        boolean haveUserVariables = false;
        for (Map.Entry<Object, Object> entry : this.info.entrySet()) {
            haveUserVariables = true;
            out += "cl.febos ->" + entry.getKey() + "=" + ((entry.getValue() == null) ? "null" : entry.getValue()) + "\n";
        }
        if (haveUserVariables) {
            out = "****** USER VARIABLES ******\n" + out + "****************************\n";
        } else {
            out = "****** NO USER VARIABLES ******\n" + out;
        }
        out = this.getMessage() + "\n" + out;
        return out;
    }

    public Response toResponse(BaseResponse response) {
        response.setCode(this.getError().getCode());
        response.setMessage(this.getError().getMessage());
        if (this.getError().getCode() == Messages.Errores.INVALID_ARGUMENT.getCode()) {
            ArrayList<String> errores = (ArrayList<String>) this.getInfo().get("Errores");
            response.setExtra(errores);
            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(response).build();
        } else if (this.getError().getCode() == Messages.Errores.FORBIDDEN.getCode()) {
            response.setMessage(Messages.Errores.FORBIDDEN.getMessage());
            return Response.status(HttpStatus.SC_UNAUTHORIZED).entity(response).build();

        } else {
            ArrayList<String> errores = (ArrayList<String>) this.getInfo().getOrDefault("Errores", new ArrayList<>());
            for (Map.Entry<Object, Object> entry : this.getInfo().entrySet()) {
                String value = (String) entry.getValue();
                errores.add(value);
            }
            if(!errores.isEmpty())response.setExtra(errores);
            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }

}
