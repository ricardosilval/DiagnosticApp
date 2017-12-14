/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.services;

import cl.diagnosticapp.dao.AlumnoDao;
import cl.diagnosticapp.dao.CarreraDao;
import cl.diagnosticapp.dao.JornadaDao;
import cl.diagnosticapp.dao.RolDao;
import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.Alumno;
import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Carrera;
import cl.diagnosticapp.model.Jornada;
import cl.diagnosticapp.model.Rol;
import cl.diagnosticapp.model.Usuario;
import cl.febos.api.ObtenerAlumnoByRunResponse;
import cl.febos.api.Users;
import cl.febos.api.UsersService;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import javax.ejb.Stateless;

/**
 *
 * @author ricardo
 */
@Stateless
public class UmasService {

    /**
     *
     * @param run
     * @param usuario
     * @return
     */
    public static ObtenerAlumnoByRunResponse.Response obtenerAlumno(String run) {

        UsersService usersService = new UsersService();
        Users users = usersService.getUsersPort();
        ObtenerAlumnoByRunResponse.Response getAlumnoResponse = users.obtenerAlumnoByRun(run);
        if (getAlumnoResponse.getData() == null || getAlumnoResponse.getData().getVariable().isEmpty()) {
            throw new BaseException(Messages.Errores.ERROR_COMMUNICACION_UMAS);
        }

        return getAlumnoResponse;
    }

    public static void main(String[] args) {
        obtenerAlumno("18974786-1");
    }
}
