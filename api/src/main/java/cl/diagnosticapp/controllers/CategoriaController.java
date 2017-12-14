/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.dao.CategoriaDao;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.BaseModel;
import cl.diagnosticapp.model.Categoria;
import cl.diagnosticapp.model.requests.CategoriaRequest;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.model.responses.CategoriaListResponse;
import cl.diagnosticapp.model.responses.CategoriaResponse;
import cl.diagnosticapp.utils.PortalUtil;
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
@Path("/categoria")
public class CategoriaController {

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("pagina") @DefaultValue("1") int pagina,
            @QueryParam("filas") @DefaultValue("15") int filas,
            @QueryParam("categoria") String categoria,
            @QueryParam("titulo") String titulo
    ) {
        try {
            Pair<List<Categoria>, Long> cat = CategoriaDao.getInstance().getAll(titulo, categoria, pagina, filas);
            return Response.ok(new CategoriaListResponse(cat.getLeft(), cat.getRight(), pagina, filas)).build();
        } catch (BaseException e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        try {
            Categoria categoria = CategoriaDao.getInstance().get(id);
            if (categoria == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new BaseResponse(Messages.Errores.OBJECT_NOT_FOUND)).build();
            }
            return Response.ok(new CategoriaResponse(categoria)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, CategoriaRequest req) {
        try {
            Categoria categoria = CategoriaDao.getInstance().getById(id);
            if (categoria == null) {
                throw new BaseException(Messages.Errores.OBJECT_NOT_FOUND);
            }

            /*
            Setear nuevos valores
             */
//            if (req.getFechaInicio() != null) {
//                categoria.setFechaInicio(PortalUtil.stringToDate(req.getFechaInicio() + " 00:00:00", "yyyy-mm-dd hh:mm:ss"));
//            }
//            if (req.getFechaTermino() != null) {
//                categoria.setFechaTermino(PortalUtil.stringToDate(req.getFechaTermino() + " 00:00:00", "yyyy-mm-dd hh:mm:ss"));
//            }
//            if (req.getTitulo() != null) {
//                categoria.setTitulo(req.getTitulo());
//            }
//            if (req.getEstado() != null) {
//                categoria.setEstado(req.getEstado());
//            }
//            if (req.getDescripcion() != null) {
//                categoria.setDescripcion(req.getDescripcion());
//            }
            categoria = CategoriaDao.getInstance().update(categoria);
            categoria = CategoriaDao.getInstance().get(categoria.getId());
            return Response.ok(new CategoriaResponse(categoria)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CategoriaRequest req) {
        try {

//            if (req.getFechaInicio() == null || req.getFechaTermino() == null) {
//                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
//            }
//
//            if (req.getTitulo() == null) {
//                return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(Messages.Errores.INVALID_ARGUMENT)).build();
//            }
//
            Categoria cal = new Categoria();
//            System.out.println("Fecha de inicio: " + req.getFechaInicio());
//            cal.setFechaInicio(PortalUtil.stringToDate(req.getFechaInicio() + " 00:00:00", "yyyy-MM-dd hh:mm:ss"));
//            cal.setFechaTermino(PortalUtil.stringToDate(req.getFechaTermino() + " 00:00:00", "yyyy-MM-dd hh:mm:ss"));
//            cal.setTitulo(req.getTitulo());
//            cal.setDescripcion(req.getDescripcion());
//            cal.setEstado(BaseModel.ESTADO_ACTIVO);

            cal = CategoriaDao.getInstance().insert(cal);
            cal = CategoriaDao.getInstance().get(cal.getId());
            return Response.status(Response.Status.CREATED).entity(new CategoriaResponse(cal)).build();
        } catch (BaseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

}
