/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.dao;

import cl.ciisa.ingenieria.diagnosticapp.model.Rol;

/**
 *
 * @author ricardo
 */
public class RolDao extends BaseDao<Rol> {

    public RolDao() {
        super(Rol.class);
    }

    public static RolDao getInstance() {
        return RolDaoHolder.INSTANCE;
    }

    private static class RolDaoHolder {

        private static final RolDao INSTANCE = new RolDao();
    }

}
