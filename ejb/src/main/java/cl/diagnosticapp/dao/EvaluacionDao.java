/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Evaluacion;

/**
 *
 * @author ricardo
 */
public class EvaluacionDao extends BaseDao<Evaluacion> {

    public EvaluacionDao() {
        super(Evaluacion.class);
    }

    public static EvaluacionDao getInstance() {
        return EvaluacionDaoHolder.INSTANCE;
    }

    private static class EvaluacionDaoHolder {

        private static final EvaluacionDao INSTANCE = new EvaluacionDao();
    }

}
