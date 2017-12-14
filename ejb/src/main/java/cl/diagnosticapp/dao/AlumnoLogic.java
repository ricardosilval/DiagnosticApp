/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.model.Alumno;
import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Carrera;
import cl.diagnosticapp.model.Jornada;
import cl.diagnosticapp.model.Rol;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.models.AlumnoUmas;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ricardo 
 */
public class AlumnoLogic {

    public static Usuario crearAlumnoFromUmas(AlumnoUmas umas) {

        BaseLogger log = new BaseLogger();
        try {
            Carrera carrera = null;
            HashMap<String, Object> params = new HashMap<>();
            params.put("codigo", umas.getCodigoCarrera());
            carrera = CarreraDao.getInstance().getUniqueByFields(params);

            if (carrera == null) {
                carrera = new Carrera();
                carrera.setCodigo(umas.getCodigoCarrera());
                carrera.setNombre(umas.getNombreCarrera());
                CarreraDao.getInstance().insert(carrera);
            }

            Jornada oJornada = null;
            HashMap<String, Object> paramsJornada = new HashMap<>();
            params.put("codigo", umas.getJornada());
            oJornada = JornadaDao.getInstance().getUniqueByFields(paramsJornada);

            if (oJornada == null) {
                oJornada = new Jornada();
                oJornada.setCodigo(umas.getJornada());
                oJornada.setNombre(umas.getJornada());
                JornadaDao.getInstance().insert(oJornada);
            }

            Alumno alumno = null;
            HashMap<String, Object> paramsAlumno = new HashMap<>();
            paramsAlumno.put("run", umas.getRun());
            alumno = AlumnoDao.getInstance().getUniqueByFields(paramsAlumno);
            if (alumno == null) {
                alumno = new Alumno();
                alumno.setNombres(umas.getNombres());
                alumno.setApellidoMaterno(umas.getApellidoMaterno());
                alumno.setApellidoPaterno(umas.getApellidoPaterno());
                alumno.setCarrera(carrera);
                alumno.setJornada(oJornada);
                alumno.setRun(umas.getRun());
                AlumnoDao.getInstance().insert(alumno);
            }

            Usuario usuario = null;
            usuario = UsuarioDao.getInstance().getUniqueByFields(paramsAlumno);

            if (usuario == null) {
                usuario = new Usuario();

                usuario.setNombre(umas.getNombres());
                usuario.setApellido(umas.getApellidoPaterno());
                usuario.setRun(umas.getRun());
                usuario.setClave(umas.getRun());
                usuario.setEstado(BaseModel.ESTADO_ACTIVO);
                Rol rol = RolDao.getInstance().getById("5656e043-38c9-4eb4-aeb2-3cd3ffac3eed");
                usuario.setRol(rol);
                usuario.setCreado(new Date());
                usuario = UsuarioDao.getInstance().insert(usuario);
                usuario = UsuarioDao.getInstance().get(usuario.getId());
            }

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;

    }
     
    
    

}
