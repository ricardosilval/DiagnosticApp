/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.ciisa.ingenieria.diagnosticapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class Rol {
    private String id;
    private String nombre;

    public Rol() {

        
    }

    public Rol(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    

    public List<Rol> listar() {

        return new ArrayList<>();
    }

    public Rol ver(String rolId) {

        return new Rol();
    }

    public Rol crear(Rol rol) {
        return new Rol();
    }

    public Rol modificar(String rolId) {

        return new Rol();
    }

    public void eliminar(Rol rol) {
        
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

}
