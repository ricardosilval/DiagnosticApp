package cl.ciisa.ingenieria.diagnosticapp.services.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author michel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Security {
    public boolean requierePermiso() default true;
    public boolean requiereToken() default true;
    public String nombre();
    public String descripcion();
}
