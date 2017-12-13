
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class CategoriaListResponse {

    private List<CategoriaResponse> categorias;
    private long total;
    private long paginaActual;
    private long filas;

    public CategoriaListResponse(List<Categoria> categorias) {
        this.categorias = new ArrayList<>();
        categorias.forEach((r) -> this.categorias.add(new CategoriaResponse(r)));
    }
    public CategoriaListResponse(List<Categoria> categorias, long total, long paginaActual, long filas) {
        this(categorias);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<CategoriaResponse> getCategoriaos() {
        return categorias;
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
