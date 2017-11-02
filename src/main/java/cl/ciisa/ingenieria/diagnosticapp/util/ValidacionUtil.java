/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.util;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.services.internal.FormatoInterface;
import cl.ciisa.ingenieria.diagnosticapp.services.internal.Validar;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michel
 */
public class ValidacionUtil {

    public static long DEFAULT_LONG = Long.MIN_VALUE;
    public static final int MIN_PASSWD_LENGTH = 8;
    //private static final BaseLogger LOG = new BaseLogger();

    public static enum Formato implements FormatoInterface {

        DEFAULT("", ""),
        RUT("", "^0*(\\d{1,3}(\\d{3})*)\\-([\\dK])$"),
        IP_V4(
                "No es una IP valida, debe tener el formato xxx.xxx.xxx.xxx, donde xxx representa un numero entre 0 y 255",
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"),
        CORREO("No es una direccion de correo valida", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"),
        NIVEL_LOG("No es un nivel de log valido, los posibles valores son: info, warning, debug, critical y error", "(info|warning|debug|critical|error)"),
        TIMESTAMP("No es una fecha valida, debe tener el formato yyyy-MM-dd hh:mm:ss o hasta con 4 decimales para los nanosegundos, con el formato yyyy-MM-dd hh:mm:ss.SSSS", "(2[0-9]{3}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])) ([012][0-9]):([0-5][0-9]):([0-5][0-9])|(2[0-9]{3}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])) ([012][0-9]):([0-5][0-9]):([0-5][0-9])\\.[0-9]{1,4}");

        private final String mensaje;
        private final String pattern;

        private Formato(String message, String pattern) {
            this.mensaje = message;
            this.pattern = pattern;
        }

        @Override
        public String getMensaje() {
            return this.mensaje;
        }

        @Override
        public String getPattern() {
            return this.pattern;
        }

    }

    private ValidacionUtil() {
    }

    public static ValidacionUtil getInstance() {
        return ValidacionVOUtilHolder.INSTANCE;
    }

    private static class ValidacionVOUtilHolder {

        private static final ValidacionUtil INSTANCE = new ValidacionUtil();
    }

    public boolean checkPattern(String regex, String value) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(value);

        return matcher.matches();

    }

    public void validar(Object objeto) throws BaseException {
        Annotation[] annotations = objeto.getClass().getAnnotations();
        ArrayList<String> errores = new ArrayList<>();
        for (Field field : objeto.getClass().getDeclaredFields()) {
            Validar[] anotaciones = field.getDeclaredAnnotationsByType(Validar.class);
            if (anotaciones.length > 0) {
                for (Validar val : anotaciones) {
                    try {
                        validar(val, objeto, field);
                    } catch (BaseException e) {
                        if (e.getError().getCode() == cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.INVALID_ARGUMENT.getCode()) {
                            errores.add(e.getInfo().get("Campo") + ": " + e.getInfo().get("Error"));
                            //e.getInfo().put(e.getInfo().get("Campo"), e.getInfo().get("Error"))
                        } else {
                            throw e;
                        }
                    }
                }
            }
        }
        if (errores.size() > 0) {
            throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.INVALID_ARGUMENT)
                    .set("Clase", objeto.getClass().getCanonicalName())
                    .set("Errores", errores);
        }
    }

    private void validar(Validar val, Object objeto, Field field) throws BaseException {
        try {
            field.setAccessible(true);
            if (val.maximo() != Long.MIN_VALUE && ((String) field.get(objeto)).length() > val.maximo()) {
                throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.INVALID_ARGUMENT)
                        .set("Clase", objeto.getClass().getCanonicalName())
                        .set("Campo", field.getName())
                        .set("Error", "El campo " + field.getName() + " no debe tener mas de " + val.maximo() + " caracteres");
            }

            if (val.minimo() != Long.MIN_VALUE && ((String) field.get(objeto)).length() < val.maximo()) {
                throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.INVALID_ARGUMENT)
                        .set("Clase", objeto.getClass().getCanonicalName())
                        .set("Campo", field.getName())
                        .set("Error", "El campo " + field.getName() + " no debe tener menos de " + val.maximo() + " caracteres");
            }

            if (!val.puedeSerNull() && field.get(objeto) == null) {
                throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.INVALID_ARGUMENT)
                        .set("Clase", objeto.getClass().getCanonicalName())
                        .set("Campo", field.getName())
                        .set("Error", "Se debe definir un valor para el campo " + field.getName());
            }
            if (!val.formato().getPattern().equals("") && !checkPattern(val.formato().getPattern(), (String) field.get(objeto))) {
                throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.INVALID_ARGUMENT)
                        .set("Clase", objeto.getClass().getCanonicalName())
                        .set("Campo", field.getName())
                        .set("Error", val.formato().getMensaje());
            }
            if (val.obligatorio() && (field.get(objeto) == null || ((String) field.get(objeto)).isEmpty())) {
                throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.INVALID_ARGUMENT)
                        .set("Clase", objeto.getClass().getCanonicalName())
                        .set("Campo", field.getName())
                        .set("Error", "Se debe definir una valor");
            }

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.UNKNOWN_ERROR)
                    .set("Error", e.getMessage());
        }
    }

    public static boolean checkActEco(String actEco) {
        String ACTECO_PATTERN = "0|[0-9]{6}";
        Pattern pattern = Pattern.compile(ACTECO_PATTERN);
        Matcher matcher = pattern.matcher(actEco);
        return matcher.matches();
    }

    public static boolean validatePassWord(String password) {
        String PASSWD_PATTERN = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{" + MIN_PASSWD_LENGTH + ",})$";
        Pattern pattern = Pattern.compile(PASSWD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        boolean r = matcher.matches();
        return r;
    }

}
