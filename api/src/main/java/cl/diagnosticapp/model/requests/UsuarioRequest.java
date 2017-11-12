/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.requests;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ricardo
 */
public class UsuarioRequest extends PortalRequest {

    @NotNull
    private String rut;
    @NotNull
    private String username;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String password;
    @NotNull
    private String correo;
    private Integer habilitado;
    private List<String> sucursales;
    private List<String> roles;
    private String rePassword;

    public String getRut() {
        return rut;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPassword() {
        return password;
    }

    public String getCorreo() {
        return correo;
    }

    public Integer getHabilitado() {
        return habilitado;
    }

    public List<String> getSucursales() {
        return sucursales;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getRePassword() {
        return rePassword;
    }

}
