/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Pregunta;

/**
 *
 * @author ricardo
 */
public class PreguntaDao extends BaseDao<Pregunta> {

    public PreguntaDao() {
        super(Pregunta.class);
    }

    public static PreguntaDao getInstance() {
        return PreguntaDaoHolder.INSTANCE;
    }

    private static class PreguntaDaoHolder {

        private static final PreguntaDao INSTANCE = new PreguntaDao();
    }

}
