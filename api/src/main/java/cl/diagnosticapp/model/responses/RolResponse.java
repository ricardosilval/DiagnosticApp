/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author benjamin
 */
public class RolResponse {

    private final String id;
    private final String nombre;


    public RolResponse(Rol rol) {
        id = rol.getId();
        nombre = rol.getNombre();
        

    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


}
