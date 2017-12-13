/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import cl.diagnosticapp.model.Alumno;


/**
 *
 * @author ricardo
 */
public class AlumnoResponse {

    private final String id;
    private final String nombres;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private final String run;
    private final CarreraResponse carrera;
    private final JornadaResponse jornada;


    public AlumnoResponse(Alumno model) {
        id = model.getId();
        nombres = model.getNombres();
        apellidoPaterno = model.getApellidoPaterno();
        apellidoMaterno = model.getApellidoMaterno();
        run = model.getRun();
        carrera = new CarreraResponse(model.getCarrera());
        jornada = new JornadaResponse(model.getJornada());

    }

    public String getId() {
        return id;
    }
//Generar geter y ssetter

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getRun() {
        return run;
    }

    public CarreraResponse getCarrera() {
        return carrera;
    }

    public JornadaResponse getJornada() {
        return jornada;
    }


}
