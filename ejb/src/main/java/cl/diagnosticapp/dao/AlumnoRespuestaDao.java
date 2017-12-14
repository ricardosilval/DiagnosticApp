/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.AlumnoRespuesta;

/**
 *
 * @author ricardo
 */
public class AlumnoRespuestaDao extends BaseDao<AlumnoRespuesta> {

    public AlumnoRespuestaDao() {
        super(AlumnoRespuesta.class);
    }

    public static AlumnoRespuestaDao getInstance() {
        return AlumnoRespuestaDaoHolder.INSTANCE;
    }

    private static class AlumnoRespuestaDaoHolder {

        private static final AlumnoRespuestaDao INSTANCE = new AlumnoRespuestaDao();
    }

}
