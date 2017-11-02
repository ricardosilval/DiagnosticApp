package cl.ciisa.ingenieria.diagnosticapp.dto.response;

import cl.ciisa.ingenieria.diagnosticapp.model.Usuario;
import cl.ciisa.ingenieria.diagnosticapp.dto.BaseResponse;
import cl.ciisa.ingenieria.diagnosticapp.model.Rol;

/**
 *
 * @author ricardo
 */
public class LoginResponse extends BaseResponse {

    private String usuarioId;
    private String nombre;
    private String correoUsuario;
    private String run;
    private Rol rol;

    public LoginResponse(Usuario usuario, Rol rol) {
        this.usuarioId = usuario.getId();
        this.nombre = usuario.getNombre() + " " + usuario.getApellido();
        this.correoUsuario = usuario.getCorreo();
        this.rol = rol;

    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

   

}
