/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.model.responses.RolListResponse;
import cl.diagnosticapp.annotations.Secured;
import cl.diagnosticapp.dao.CalendarizacionDao;
import cl.diagnosticapp.dao.RolDao;
import cl.diagnosticapp.model.Rol;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Calendarizacion;
import cl.diagnosticapp.model.requests.CalendarizacionRequest;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.model.responses.CalendarizacionListResponse;
import cl.diagnosticapp.model.responses.CalendarizacionResponse;
import cl.diagnosticapp.utils.PortalUtil;
import freemarker.core.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
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
@Path("/calendarizacion")
public class CalendarizacionController {

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("pagina") @DefaultValue("1") int pagina,
            @QueryParam("filas") @DefaultValue("15") int filas,
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("estado") Integer estado
    ) {
        try {
            
            
            Date dFechaInicio = PortalUtil.stringToDate(fechaInicio + " 00:00:00", "yyyy-MM-dd hh:mm:ss");
            Pair<List<Calendarizacion>, Long> calendarizacion = CalendarizacionDao.getInstance().getAll(dFechaInicio, estado, pagina, filas);
            return Response.ok(new CalendarizacionListResponse(calendarizacion.getLeft(), calendarizacion.getRight(), pagina, filas)).build();
        } catch (BaseException e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        try {
            Calendarizacion calendarizacion = CalendarizacionDao.getInstance().get(id);
            if (calendarizacion == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new BaseResponse(Messages.Errores.OBJECT_NOT_FOUND)).build();
            }
            return Response.ok(new CalendarizacionResponse(calendarizacion)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, CalendarizacionRequest req) {
        try {
            Calendarizacion calendarizacion = CalendarizacionDao.getInstance().getById(id);
            if (calendarizacion == null) {
                throw new BaseException(Messages.Errores.OBJECT_NOT_FOUND);
            }

            /*
            Setear nuevos valores
             */
            if (req.getFechaInicio() != null) {
                calendarizacion.setFechaInicio(PortalUtil.stringToDate(req.getFechaInicio() + " 00:00:00", "yyyy-mm-dd hh:mm:ss"));
            }
            if (req.getFechaTermino() != null) {
                calendarizacion.setFechaTermino(PortalUtil.stringToDate(req.getFechaTermino() + " 00:00:00", "yyyy-mm-dd hh:mm:ss"));
            }
            if (req.getTitulo() != null) {
                calendarizacion.setTitulo(req.getTitulo());
            }
            if (req.getEstado() != null) {
                calendarizacion.setEstado(req.getEstado());
            }
            if (req.getDescripcion() != null) {
                calendarizacion.setDescripcion(req.getDescripcion());
            }
           

            calendarizacion = CalendarizacionDao.getInstance().update(calendarizacion);
            calendarizacion = CalendarizacionDao.getInstance().get(calendarizacion.getId());
            return Response.ok(new CalendarizacionResponse(calendarizacion)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CalendarizacionRequest req) {
        try {

            if (req.getFechaInicio() == null || req.getFechaTermino() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
            }

            if (req.getTitulo() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
            }

            Calendarizacion cal = new Calendarizacion();
            System.out.println("Fecha de inicio: " + req.getFechaInicio());
            cal.setFechaInicio(PortalUtil.stringToDate(req.getFechaInicio() + " 00:00:00", "yyyy-MM-dd hh:mm:ss"));
            cal.setFechaTermino(PortalUtil.stringToDate(req.getFechaTermino() + " 00:00:00", "yyyy-MM-dd hh:mm:ss"));
            cal.setTitulo(req.getTitulo());
            cal.setDescripcion(req.getDescripcion());
            cal.setEstado(BaseModel.ESTADO_ACTIVO);

            cal = CalendarizacionDao.getInstance().insert(cal);
            cal = CalendarizacionDao.getInstance().get(cal.getId());
            return Response.status(Response.Status.CREATED).entity(new CalendarizacionResponse(cal)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

}
