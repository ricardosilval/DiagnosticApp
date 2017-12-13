/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Jornada;


/**
 *
 * @author ricardo
 */
public class JornadaResponse{

    private final String id;
    private final String codigo;
    private final String nombre;


    public JornadaResponse(Jornada model) {
        id = model.getId();
        nombre = model.getNombre();
        codigo = model.getCodigo();

    }

    public String getId() {
        return id;
    }
//Generar geter y ssetter

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }


}
