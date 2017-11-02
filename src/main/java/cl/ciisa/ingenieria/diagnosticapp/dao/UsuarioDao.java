/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.dao;

import cl.ciisa.ingenieria.diagnosticapp.model.Usuario;

/**
 *
 * @author ricardo
 */
public class UsuarioDao extends BaseDao<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public static UsuarioDao getInstance() {
        return UsuarioDaoHolder.INSTANCE;
    }

    private static class UsuarioDaoHolder {

        private static final UsuarioDao INSTANCE = new UsuarioDao();
    }

}
