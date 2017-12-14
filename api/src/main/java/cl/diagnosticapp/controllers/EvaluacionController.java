/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.dao.CategoriaDao;
import cl.diagnosticapp.dao.EvaluacionDao;
import cl.diagnosticapp.dao.PreguntaDao;
import cl.diagnosticapp.dao.RespuestaDao;
import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Calendarizacion;
import cl.diagnosticapp.model.Categoria;
import cl.diagnosticapp.model.Evaluacion;
import cl.diagnosticapp.model.Pregunta;
import cl.diagnosticapp.model.Respuesta;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.model.requests.EvaluacionRequest;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.model.responses.EvaluacionListResponse;
import cl.diagnosticapp.model.responses.EvaluacionResponse;
import cl.diagnosticapp.utils.PortalUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Path("/evaluacion")
public class EvaluacionController {

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("pagina") @DefaultValue("1") int pagina,
            @QueryParam("filas") @DefaultValue("15") int filas,
            @QueryParam("titulo") String titulo,
            @QueryParam("categoria") String categoria
    ) {
        try {

            Pair<List<Evaluacion>, Long> evaluacion = EvaluacionDao.getInstance().getAll(titulo, categoria, pagina, filas);
            return Response.ok(new EvaluacionListResponse(evaluacion.getLeft(), evaluacion.getRight(), pagina, filas)).build();
        } catch (BaseException e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        try {
            Evaluacion evaluacion = EvaluacionDao.getInstance().get(id);
            if (evaluacion == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new BaseResponse(Messages.Errores.OBJECT_NOT_FOUND)).build();
            }

            HashMap<String, Object> fields = new HashMap<>();
            fields.put("evaluacion_id", id);

            /*
             String categoria,
            Integer estado,
            String evaluacionId,
            int pagina,
            int filas
             */
            List<Pregunta> p2 = PreguntaDao.getInstance().getListByFields(fields);
            HashMap<Pregunta, List<Respuesta>> hm = new HashMap<>();
            for (Pregunta pregunta : p2) {

                HashMap<String, Object> fieldsPr = new HashMap<>();
                fieldsPr.put("pregunta_id", pregunta.getId());

                List<Respuesta> respuestas = RespuestaDao.getInstance().getListByFields(fieldsPr);
                hm.put(pregunta, respuestas);
            }

            //  Pair<List<Pregunta>, Long> preguntas = PreguntaDao.getInstance().getAll(null,null,evaluacion.getId(),1,0);
            return Response.ok(new EvaluacionResponse(evaluacion, hm)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, EvaluacionRequest req) {
        try {

            Evaluacion evaluacion = EvaluacionDao.getInstance().getById(id);
            if (evaluacion == null) {
                throw new BaseException(Messages.Errores.OBJECT_NOT_FOUND);
            }
            /*
            Setear nuevos valores
             */

            if (req.getCategoria() != null) {
                Categoria categoria = CategoriaDao.getInstance().get(req.getCategoria());
                evaluacion.setCategoria(categoria);
            }
            if (req.getTitulo() != null) {
                evaluacion.setTitulo(req.getTitulo());
            }

            evaluacion = EvaluacionDao.getInstance().update(evaluacion);
            evaluacion = EvaluacionDao.getInstance().get(evaluacion.getId());
            return Response.ok(new EvaluacionResponse(evaluacion, null)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(EvaluacionRequest req) {
        try {
            Usuario usuario = UsuarioDao.getInstance().get(securityContext.getUserPrincipal().getName());

            if (req.getCategoria() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();

            }
            if (req.getTitulo() != null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();

            }

            Categoria categoria = CategoriaDao.getInstance().get(req.getCategoria());
            if (categoria == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
            }

            Evaluacion eval = new Evaluacion();
            eval.setCategoria(categoria);
            eval.setTitulo(req.getTitulo());
            eval.setUsuario(usuario);

            eval = EvaluacionDao.getInstance().insert(eval);
            eval = EvaluacionDao.getInstance().get(eval.getId());
            return Response.status(Response.Status.CREATED).entity(new EvaluacionResponse(eval, null)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

}
