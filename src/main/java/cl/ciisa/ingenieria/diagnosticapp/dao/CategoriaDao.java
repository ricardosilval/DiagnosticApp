/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.dao;

import cl.ciisa.ingenieria.diagnosticapp.model.Categoria;

/**
 *
 * @author ricardo
 */
public class CategoriaDao extends BaseDao<Categoria> {

    public CategoriaDao() {
        super(Categoria.class);
    }

    public static CategoriaDao getInstance() {
        return CategoriaDaoHolder.INSTANCE;
    }

    private static class CategoriaDaoHolder {

        private static final CategoriaDao INSTANCE = new CategoriaDao();
    }

}