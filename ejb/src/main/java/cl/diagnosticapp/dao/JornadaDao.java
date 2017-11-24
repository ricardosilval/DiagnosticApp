/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Jornada;

/**
 *
 * @author ricardo
 */
public class JornadaDao extends BaseDao<Jornada> {

    public JornadaDao() {
        super(Jornada.class);
    }

    public static JornadaDao getInstance() {
        return JornadaDaoHolder.INSTANCE;
    }

    private static class JornadaDaoHolder {

        private static final JornadaDao INSTANCE = new JornadaDao();
    }

}
