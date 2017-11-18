package cl.diagnosticapp.handlers;

/**
 * Interfaz de mensajes de sistema.
 * @author ricardo
 */
public interface Message {
    public int getCode();
    public String getMessage();
    public String getHtmlMessage();
}
