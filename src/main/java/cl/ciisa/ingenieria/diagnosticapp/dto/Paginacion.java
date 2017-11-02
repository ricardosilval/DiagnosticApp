package cl.ciisa.ingenieria.diagnosticapp.dto;

import cl.ciisa.ingenieria.diagnosticapp.dto.BaseResponse;
import cl.ciisa.ingenieria.diagnosticapp.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper para contener un listado de elementos con su respectiva paginacion.
 * @author ricardo
 * @param <T> Clase correspondiente al listado
 */
public class Paginacion<T extends BaseModel> extends BaseResponse{
    List<T> filas;
    int paginaActual;
    int registrosPorPagina;
    int paginasTotales;
    long registrosTotales;
    long registrosEnEstaPagina;
    
    
    /**
     * Filas que contiene la paginacion.
     * @return ArrayList con la clase respectiva
     */
    public List<?> getFilas() {
        if(filas==null)return new ArrayList<>();
        return filas;
    }

    /**
     * Asigna un nuevo set de filas al wrapper.
     * @param filas 
     */
    public void setFilas(List<T> filas) {
        if(filas==null)return;
        this.registrosEnEstaPagina=filas.size();
        this.filas = filas;
    }

    /**
     * Obtiene la pagina actual.
     * @return 
     */
    public int getPaginaActual() {
        return paginaActual;
    }

    /**
     * Setea la pagina actual.
     * @param paginaActual 
     */
    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    /**
     * Obtiene la cantidad de registros por pagina.
     * @return 
     */
    public int getRegistrosPorPagina() {
        return registrosPorPagina;
    }

    /**
     * Setea la cantidad de registros por pagina.
     * @param registrosPorPagina 
     */
    public void setRegistrosPorPagina(int registrosPorPagina) {
        this.registrosPorPagina = registrosPorPagina;
    }

    /**
     * Obtiene la cantidad de paginas totales.
     * @return 
     */
    public int getPaginasTotales() {
        return paginasTotales;
    }

    /**
     * Setea la cantidad de paginas totales.
     * @param paginasTotales 
     */
    public void setPaginasTotales(int paginasTotales) {
        this.paginasTotales = paginasTotales;
    }

    /**
     * Obtiene la cantidad de registros totales.
     * @return 
     */
    public long getRegistrosTotales() {
        return registrosTotales;
    }

    /**
     * Setea la cantidad de registros totales.
     * @param registrosTotales 
     */
    public void setRegistrosTotales(long registrosTotales) {
        this.registrosTotales = registrosTotales;
    }

    /**
     * Obtiene la cantidad de registros en la pagina actual.
     * @return 
     */
    public long getRegistrosEnEstaPagina() {
        return registrosEnEstaPagina;
    }

    /**
     * Setea la cantidad de registros en la pagina actual.
     * @param registrosEnEstaPagina 
     */
    public void setRegistrosEnEstaPagina(long registrosEnEstaPagina) {
        this.registrosEnEstaPagina = registrosEnEstaPagina;
    }
    
}
