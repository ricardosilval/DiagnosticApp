package cl.ciisa.ingenieria.diagnosticapp.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interfaz para definir los titulos de las columnas en un CSV.
 * @author ricardo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CSV {
    public String titulo();
}
