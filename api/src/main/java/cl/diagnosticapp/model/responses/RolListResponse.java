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
public class RolListResponse {

    private List<RolResponse> roles;
    private long total;
    private long paginaActual;
    private long filas;

    public RolListResponse(List<Rol> roles) {
        this.roles = new ArrayList<>();
        roles.forEach((r) -> this.roles.add(new RolResponse(r)));
    }
    public RolListResponse(List<Rol> roles, long total, long paginaActual, long filas) {
        this(roles);
        this.total = total;
        this.paginaActual = paginaActual;
        this.filas = filas;
    }

    public List<RolResponse> getRoles() {
        return roles;
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
