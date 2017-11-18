/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.requests;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author benjamin
 */
public class RolRequest {

    @NotNull
    private String nombre;
    @NotNull
    private String codigo;
    @NotNull
    private String descripcion;
    private List<String> permisos;

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getPermisos() {
        return permisos;
    }

    

}
