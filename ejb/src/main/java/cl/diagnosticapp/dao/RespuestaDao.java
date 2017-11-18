/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Respuesta;

/**
 *
 * @author ricardo
 */
public class RespuestaDao extends BaseDao<Respuesta> {

    public RespuestaDao() {
        super(Respuesta.class);
    }

    public static RespuestaDao getInstance() {
        return RespuestaDaoHolder.INSTANCE;
    }

    private static class RespuestaDaoHolder {

        private static final RespuestaDao INSTANCE = new RespuestaDao();
    }

}
