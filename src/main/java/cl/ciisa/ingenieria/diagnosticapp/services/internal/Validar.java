/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.services.internal;

import cl.ciisa.ingenieria.diagnosticapp.util.ValidacionUtil.Formato;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interfaz que permite realizar validaciones sobre POJOs.
 * @author ricardo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Validar {
    public long maximo() default Long.MIN_VALUE;
    public long minimo() default Long.MIN_VALUE;
    public Formato formato() default Formato.DEFAULT;
    public boolean puedeSerNull() default true;
    public boolean intObligatorio() default false;
    public boolean longObligatorio() default false;
    public boolean obligatorio() default false;
}
