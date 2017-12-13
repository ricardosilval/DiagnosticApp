/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Categoria;

/**
 *
 * @author ricardo
 */
public class CategoriaResponse {

    private final String id;
    private final String nombre;


    public CategoriaResponse(Categoria model) {
        id = model.getId();
        nombre = model.getNombre();
        

    }

    public String getId() {
        return id;
    }
//Generar geter y ssetter

    public String getNombre() {
        return nombre;
    }


}
