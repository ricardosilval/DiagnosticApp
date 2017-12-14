/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.dao.AlumnoDao;
import cl.diagnosticapp.dao.AlumnoRespuestaDao;
import cl.diagnosticapp.dao.CalendarizacionDao;
import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.Alumno;
import cl.diagnosticapp.model.AlumnoRespuesta;
import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Calendarizacion;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.model.requests.CalendarizacionRequest;
import cl.diagnosticapp.model.requests.RespuestaAlumnoRequest;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.model.responses.CalendarizacionListResponse;
import cl.diagnosticapp.model.responses.CalendarizacionResponse;
import cl.diagnosticapp.utils.PortalUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author ricardo
 */
@Path("/respuesta")
public class RespuestaController {

    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(List<RespuestaAlumnoRequest> req) {


        Usuario usuario = UsuarioDao.getInstance().get(securityContext.getUserPrincipal().getName());

        try {

            if (req == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
            }
            HashMap<String, Object> fields = new HashMap<>();
            fields.put("run", usuario.getRun());
            Alumno alumno = AlumnoDao.getInstance().getUniqueByFields(fields);

            for (RespuestaAlumnoRequest respuesta : req) {

                AlumnoRespuesta responde = new AlumnoRespuesta();
                responde.setAlumnoId(alumno.getId());
                responde.setRespuestaId(respuesta.getRespuestaId());
                
                
                AlumnoRespuestaDao.getInstance().insert(responde);

            }

            return Response.status(Response.Status.CREATED).entity(new BaseResponse()).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

}
