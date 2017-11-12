/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Carrera;

/**
 *
 * @author ricardo
 */
public class CarreraDao extends BaseDao<Carrera> {

    public CarreraDao() {
        super(Carrera.class);
    }

    public static CarreraDao getInstance() {
        return CarreraDaoHolder.INSTANCE;
    }

    private static class CarreraDaoHolder {

        private static final CarreraDao INSTANCE = new CarreraDao();
    }

}
