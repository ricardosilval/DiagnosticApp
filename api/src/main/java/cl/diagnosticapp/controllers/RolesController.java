/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.model.responses.RolListResponse;
import cl.diagnosticapp.annotations.Secured;
import cl.diagnosticapp.dao.RolDao;
import cl.diagnosticapp.model.Rol;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.model.responses.BaseResponse;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author benjamin
 */
@Path("/roles")
public class RolesController {

    @Inject
    private BaseLogger log;
    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured("listar_roles")
    public Response getAll(
            @QueryParam("pagina") @DefaultValue("1") int pagina,
            @QueryParam("filas") @DefaultValue("15") int filas,
            @QueryParam("nombre") String nombre,
            @QueryParam("descripcion") String descripcion,
            @QueryParam("codigo") String codigo,
            @QueryParam("permiso") List<String> permiso
    ) {
        try {
            Pair<List<Rol>, Long> rol = RolDao.getInstance().getAll(nombre, descripcion, codigo, permiso, pagina, filas);
            return Response.ok(new RolListResponse(rol.getLeft(), rol.getRight(), pagina, filas)).build();
        } catch (BaseException e) {
            log.error("E!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
        }
    }

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Secured("ver_roles")
//    public Response get(@PathParam("id") String id) {
//        try {
//            Rol rol = RolDao.getInstance().get(id);
//            if (rol == null) {
//                return Response.status(Response.Status.NOT_FOUND).entity(new BaseResponse(Messages.Errores.OBJECT_NOT_FOUND)).build();
//            }
//            return Response.ok(new RolResponse(rol)).build();
//        } catch (BaseException e) {
//            log.error("E!", e);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(e)).build();
//        }
//
//    }

//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Secured("actualizar_roles")
//    public Response update(@PathParam("id") String id, RolRequest req) {
//        try {
//            Rol rol = RolDao.getInstance().getById(id);
//            if (rol == null) {
//                throw new BaseException(Messages.Errores.ROL_NO_EXISTE);
//            }
//            if (req.getNombre() != null) {
//                rol.setNombre(req.getNombre());
//            }
//            if (req.getCodigo() != null) {
//                rol.setCodigo(req.getCodigo());
//            }
//            if (req.getDescripcion() != null) {
//                rol.setDescripcion(req.getDescripcion());
//            }
//            if (req.getPermisos() != null) {
//                rol.getPermisos().clear();
//                for (String p : req.getPermisos()) {
//                    Permiso permiso = PermisoDao.getInstance().getById(p);
//                    rol.getPermisos().add(permiso);
//                }
//            }
//
//            rol = RolDao.getInstance().update(rol);
//            rol = RolDao.getInstance().get(rol.getId());
//            return Response.ok(new RolResponse(rol)).build();
//        } catch (BaseException e) {
//            log.error("E!", e);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(e)).build();
//        }
//
//    }
}
