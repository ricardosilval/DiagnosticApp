/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.dao.CategoriaDao;
import cl.diagnosticapp.dao.EvaluacionDao;
import cl.diagnosticapp.dao.PreguntaDao;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Pregunta;
import cl.diagnosticapp.model.requests.PreguntaRequest;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.model.responses.PreguntaListResponse;
import cl.diagnosticapp.model.responses.PreguntaResponse;
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
@Path("/pregunta")
public class PreguntaController {

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("pagina") @DefaultValue("1") int pagina,
            @QueryParam("filas") @DefaultValue("15") int filas,
            @QueryParam("categoria") String categoria,
            @QueryParam("estado") Integer estado
    ) {
        try {            
            Pair<List<Pregunta>, Long> pregunta = PreguntaDao.getInstance().getAll(categoria, estado, pagina, filas);
            return Response.ok(new PreguntaListResponse(pregunta.getLeft(), pregunta.getRight(), pagina, filas)).build();
        } catch (BaseException e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        try {
            Pregunta pregunta = PreguntaDao.getInstance().get(id);
            if (pregunta == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new BaseResponse(Messages.Errores.OBJECT_NOT_FOUND)).build();
            }
            return Response.ok(new PreguntaResponse(pregunta)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, PreguntaRequest req) {
        try {
            Pregunta pregunta = PreguntaDao.getInstance().getById(id);
            if (pregunta == null) {
                throw new BaseException(Messages.Errores.OBJECT_NOT_FOUND);
            }
            /*
            Setear nuevos valores
             */
            if (req.getCategoria() != null) {
                pregunta.setSubcategoria(CategoriaDao.getInstance().getById(req.getCategoria()));
            }
            if (req.getEvaluacion() != null) {
                pregunta.setEvaluacion(EvaluacionDao.getInstance().getById(req.getEvaluacion()));
            }
            if (req.getIdentificador() != null) {
                pregunta.setId(req.getIdentificador());
            }
            if (req.getEstado() != 0) {
                pregunta.setEstado(req.getEstado());
            }
            if (req.getCuerpo() != null) {
                pregunta.setCuerpo(req.getCuerpo());
            }
           
            pregunta = PreguntaDao.getInstance().update(pregunta);
            pregunta = PreguntaDao.getInstance().get(pregunta.getId());
            return Response.ok(new PreguntaResponse(pregunta)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PreguntaRequest req) {
        try {

            if (req.getEvaluacion() == null || req.getCategoria() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
            }

            if (req.getCuerpo() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
            }

            Pregunta pregunta = new Pregunta();
            System.out.println("Categoria: " + req.getCategoria());
            pregunta.setCuerpo( req.getCuerpo() );
            pregunta.setEstado(req.getEstado());
            pregunta.setEvaluacion(EvaluacionDao.getInstance().getById(req.getEvaluacion()));
            pregunta.setSubcategoria(CategoriaDao.getInstance().getById(req.getCategoria()));
            pregunta.setEstado(BaseModel.ESTADO_ACTIVO);
            pregunta = PreguntaDao.getInstance().insert(pregunta);
            pregunta = PreguntaDao.getInstance().get(pregunta.getId());
            return Response.status(Response.Status.CREATED).entity(new PreguntaResponse(pregunta)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

}
