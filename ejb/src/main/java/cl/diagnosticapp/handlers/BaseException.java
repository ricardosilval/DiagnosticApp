package cl.diagnosticapp.handlers;

import java.util.HashMap;
import java.util.Map;

public class BaseException extends RuntimeException {

    private final Message error;
    private final HashMap<Object, Object> info;

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
            out += "cl.diagnosticapp->" + entry.getKey() + "=" + ((entry.getValue() == null) ? "null" : entry.getValue()) + "\n";
        }
        if (haveUserVariables) {
            out = "****** USER VARIABLES ******\n" + out + "****************************\n";
        } else {
            out = "****** NO USER VARIABLES ******\n" + out;
        }
        out = this.getMessage() + "\n" + out;
        return out;
    }

}
