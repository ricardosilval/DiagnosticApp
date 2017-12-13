package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class UsuarioListResponse {

    private List<UsuarioResponse> usuarios;
    private long total;
    private long paginaActual;
    private long filas;

    public UsuarioListResponse(List<Usuario> usuarios) {
        this.usuarios = new ArrayList<>();
        usuarios.forEach((r) -> this.usuarios.add(new UsuarioResponse(r)));
    }

    public UsuarioListResponse(List<Usuario> usuarios, long total, long paginaActual, long filas) {
        this(usuarios);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<UsuarioResponse> getUsuarioos() {
        return usuarios;
    }

    public long getTotal() {
        return total;
    }

    public long getPaginaActual() {
        return paginaActual;
    }

    public long getFilas() {
        return filas;
    }

}
