/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Calendarizacion;

/**
 *
 * @author ricardo
 */
public class CalendarizacionDao extends BaseDao<Calendarizacion> {

    public CalendarizacionDao() {
        super(Calendarizacion.class);
    }

    public static CalendarizacionDao getInstance() {
        return CalendarizacionDaoHolder.INSTANCE;
    }

    private static class CalendarizacionDaoHolder {

        private static final CalendarizacionDao INSTANCE = new CalendarizacionDao();
    }

}
