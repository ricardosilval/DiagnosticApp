/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Rol;

/**
 *
 * @author ricardo
 */
class RolItemResponse extends BaseResponse{

    private final String nombre;
    
    public RolItemResponse(Rol rol) {
        this.nombre = rol.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    
    
}
