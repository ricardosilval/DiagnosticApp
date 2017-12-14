/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Respuesta;


/**
 *
 * @author ricardo
 */
public class RespuestaResponse{

    private final String id;
    private final String cuerpo;
    private final int valor;
    private final int estadoCodigo;
    private final String estadoNombre;


    public RespuestaResponse(Respuesta model) {
        id = model.getId();
        cuerpo = model.getCuerpo();
        valor = model.getValor();
        estadoCodigo = model.getEstado();
        estadoNombre = model.getEstado() == 1 ? "Activo" : "Inactivo";
    }

    public String getId() {
        return id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public int getValor() {
        return valor;
    }

    public int getEstadoCodigo() {
        return estadoCodigo;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }
    


}
