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
public class Categoria {

    private String id;
    private String nombre;
    private String descripcion;
    private List<Categoria> subcategorias;

    public Categoria() {
    }

    public Categoria(String id, String nombre, String descripcion, List<Categoria> subcategorias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.subcategorias = subcategorias;
    }

    public List<Categoria> listar() {

        return new ArrayList<>();
    }

    public Categoria ver(String categoriaId) {

        return new Categoria();
    }

    public Categoria crear(Categoria categoria) {
        return new Categoria();
    }

    public Categoria modificar(String categoriaId) {

        return new Categoria();
    }

    public void eliminar(Categoria categoria) {

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

}
