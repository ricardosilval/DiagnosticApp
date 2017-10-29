package cl.ciisa.ingenieria.diagnosticapp.util;

/**
 * Interfaz para definir funcionamiento de los logs
 * @author Michel Munoz <michel@febos.cl>
 */
public interface LogInterface {
    void warning(String message,Throwable t);
    void info(String message,Throwable t);
    void error(String message,Throwable t);
    void debug(String message,Throwable t);
    void warning(String message);
    void info(String message);
    void error(String message);
    void debug(String message);
    void warning(String message,Throwable t,String processId);
    void info(String message,Throwable t,String processId);
    void error(String message,Throwable t,String processId);
    void debug(String message,Throwable t,String processId);
    void warning(String message,String processId);
    void info(String message,String processId);
    void error(String message,String processId);
    void debug(String message,String processId);
}
