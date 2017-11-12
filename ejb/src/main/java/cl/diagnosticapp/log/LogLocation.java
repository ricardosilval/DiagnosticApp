
package cl.diagnosticapp.log;

/**
 * Clase que permite contener la informacion de origen de un log especifico.
 */
public class LogLocation {
    String clase;
    int line;
    String method;

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    
}
