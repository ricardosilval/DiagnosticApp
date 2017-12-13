/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Usuario;
import java.util.Date;

/**
 *
 * @author ricardo
 */
public class UsuarioResponse {

    private final String id;
    private final String correo;
    private final String nombre;
    private final String apellido;
    private final String run;
    private final RolResponse rol;
    private final int estadoCodigo;
    private final String estadoNombre;
    private final Date fechaCreacion;

    public UsuarioResponse(Usuario model) {
        id = model.getId();
        nombre = model.getNombre();
        correo = model.getCorreo();
        apellido = model.getApellido();
        run = model.getRun();
        estadoCodigo = model.getEstado();
        estadoNombre = model.getEstado() == 1 ? "Activo" : "Inactivo";
        rol = new RolResponse(model.getRol());
        fechaCreacion = model.getCreado();

    }

    public String getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRun() {
        return run;
    }

    public RolResponse getRol() {
        return rol;
    }

    public int getEstadoCodigo() {
        return estadoCodigo;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

}
