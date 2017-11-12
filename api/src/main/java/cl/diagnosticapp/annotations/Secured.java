/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;

/**
 * Anotacion para el manejo de permisos en los endpoints.
 * @author ricardo
 */
@NameBinding
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Secured {
    String value() default "";
}
