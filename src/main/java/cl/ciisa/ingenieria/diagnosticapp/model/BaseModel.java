/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.ciisa.ingenieria.diagnosticapp.model;

/**
 *
 * @author ricardo
 */

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores;
import cl.ciisa.ingenieria.diagnosticapp.util.JsonUtil;
import cl.ciisa.ingenieria.diagnosticapp.util.LogicUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Model generico para clases de DB Mapping. Contiene metodos de uso comun entre
 * los modelos
 *
 * @author Michel Munoz <michel@febos.cl>
 */
public abstract class BaseModel extends Observable {

    public abstract String getId();

    public abstract void setId(String id);

    /**
     * Representacion en JSON de instancia de objeto.
     *
     * @return
     */
    @Override
    public String toString() {
        return JsonUtil.getInstance().toJson(this);
    }

    /**
     * Obtiene el Hash (como string) del objeto.
     *
     * @return
     */
    public String hash() {
        return LogicUtil.getInstance().hash(this.getId());
    }

    /**
     * Compara la instancia del modelo, con otra instacia de modelo.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof BaseModel) {
            return false;
        }
        return ((BaseModel) other).getId().equals(this.getId());
    }

    /**
     * Obtiene el hash (numerico) del objeto.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    /**
     * Retorna el nombre de la columna en la base de datos en base a campo de la
     * clase modelo.
     *
     * @param property
     * @return
     */
    public String getColumnName(String property) {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.getName().equals(property)) {
                    f.setAccessible(true);
                    Column a = f.getAnnotation(Column.class);
                    return a.name();
                }

            }
            throw new NoSuchFieldException("No existe el campo " + property);
        } catch (NoSuchFieldException ex) {
            throw new BaseException(Errores.FIELD_NOT_EXIST)
                    .set("Campo", property)
                    .set("Clase", this.getClass().getCanonicalName());
        } catch (SecurityException ex) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("Campo", property)
                    .set("Clase", this.getClass().getCanonicalName());
        }
    }

    /**
     * Obtiene el nombre de la tabla en la base de datos correspondiente al
     * modelo.
     *
     * @return
     */
    public String getTableName() {

        Table table = this.getClass().getAnnotation(Table.class);
        return (table == null) ? "" : table.name();

    }

    public String getSqlSelectFields(List<String> fields, BaseModel model) {
        StringBuilder sb = new StringBuilder();
        List<String> select = new ArrayList<>();
        if (fields == null || fields.isEmpty()) {
            return "*";
        } else {
            for (String campo : fields) {
                try {
                    select.add(model.getTableName() + "." + model.getColumnName(campo));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
        return sb.append(String.join(", \n\t", select)).toString();
    }

    public String getSqlOrderFields(List<String> fields, BaseModel model) {
        StringBuilder sb = new StringBuilder();
        List<String> select = new ArrayList<>();
        if (fields == null || fields.isEmpty()) {
            return "";
        } else {
            sb.append("");
            for (String campo : fields) {
                try {
                    char simbolo = campo.charAt(0);
                    String c = campo.substring(1);
                    String orden;
                    switch (simbolo) {
                        case '+':
                            orden = " asc";
                            break;
                        case '-':
                            orden = " desc";
                            break;
                        default:
                            throw new Exception("No se especifico el orden para el campo " + campo);
                    }
                    select.add(model.getTableName() + "." + model.getColumnName(c) + orden);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }
        return sb.append(String.join(", \n\t", select)).toString();
    }
}
