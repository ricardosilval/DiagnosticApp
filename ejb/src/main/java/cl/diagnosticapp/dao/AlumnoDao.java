/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Alumno;

/**
 *
 * @author ricardo
 */
public class AlumnoDao extends BaseDao<Alumno> {

    public AlumnoDao() {
        super(Alumno.class);
    }

    public static AlumnoDao getInstance() {
        return AlumnoDaoHolder.INSTANCE;
    }

    private static class AlumnoDaoHolder {

        private static final AlumnoDao INSTANCE = new AlumnoDao();
    }

}
