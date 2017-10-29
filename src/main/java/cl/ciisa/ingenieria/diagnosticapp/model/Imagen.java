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
public class Imagen {
    private String id;
    private String nombre;
    private byte[] data;
    private String entidad;

    public Imagen() {
    }

    public Imagen(String id, String nombre, byte[] data, String entidad) {
        this.id = id;
        this.nombre = nombre;
        this.data = data;
        this.entidad = entidad;
    }

    public List<Imagen> listar() {

        return new ArrayList<>();
    }

    public Imagen ver(String imagenId) {

        return new Imagen();
    }

    public Imagen crear(Imagen imagen) {
        return new Imagen();
    }

    public Imagen modificar(String imagenId) {

        return new Imagen();
    }

    public void eliminar(Imagen imagen) {

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    

}
