package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Token;
import cl.diagnosticapp.model.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginResponse extends BaseResponse {

    private final String usuarioId;
    private final String run;
    private final String nombre;
    private final String apellido;
    private final Integer estado;
    private final Long creado;
    private final Long modificado;
    private final String correoElectronico;
    private final String token;
    private final String cambioClave;
    private final RolItemResponse rol;

    public LoginResponse(Usuario user, Token token) {
        this.usuarioId = user.getId();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.cambioClave = user.getCambioClave();
        this.correoElectronico = user.getCorreo();
        this.run = user.getRun();
        this.token = token.getId();
        this.estado = user.getEstado();
        this.creado = user.getCreado().getTime();
        this.modificado = user.getCreado().getTime();
        this.rol = new RolItemResponse(user.getRol());

    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getRun() {
        return run;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEstado() {
        return estado;
    }

    public Long getCreado() {
        return creado;
    }

    public Long getModificado() {
        return modificado;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getToken() {
        return token;
    }

    public String getCambioClave() {
        return cambioClave;
    }

    public RolItemResponse getRol() {
        return rol;
    }

  
}
