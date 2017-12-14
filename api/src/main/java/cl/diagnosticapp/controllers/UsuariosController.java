/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.annotations.Secured;
import cl.diagnosticapp.dao.RolDao;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.model.Rol;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.requests.UsuarioRequest;
import cl.diagnosticapp.utils.LogicUtil;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
@Path("/usuarios")
public class UsuariosController {

    @Inject
    private BaseLogger log;
    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured("listar_usuarios, listar_usuarios_empresa")
    public Response getAll(
            @QueryParam("pagina") @DefaultValue("1") int pagina,
            @QueryParam("filas") @DefaultValue("15") int filas,
            @QueryParam("rut") List<String> rut,
            @QueryParam("aduanaLoteadora") List<String> aduanasLoteadoras,
            @QueryParam("rol") List<String> roles,
            @QueryParam("aduanaGestora") String aduanaGestora,
            @QueryParam("correo") String correo,
            @QueryParam("nombre") String nombre,
            @QueryParam("apellido") String apellido,
            @QueryParam("habilitado") Integer habilitado
    ) {
        try {
            Usuario usuario = UsuarioDao.getInstance().get(securityContext.getUserPrincipal().getName());
            Pair<List<Usuario>, Long> results;
                   
            if (UsuarioDao.getInstance().hasPermission(usuario, "listar_usuarios")) 
            {
                results = UsuarioDao.getInstance().getAll(rut, aduanasLoteadoras, roles, aduanaGestora, correo, nombre, apellido, habilitado, pagina, filas, null);
                UsuarioDao.getInstance().getall
            } else {
                List<Empresa> empresasUsuario = usuario.getSucursales().stream().map(Sucursal::getEmpresa).distinct().collect(Collectors.toList());
                results = UsuarioDao.getInstance().getAll(rut, aduanasLoteadoras, roles, aduanaGestora, correo, nombre, apellido, habilitado, pagina, filas, empresasUsuario);
            }
            UsuarioListResponse response = new UsuarioListResponse(results.getKey(), results.getValue(), pagina, filas);
            return Response.ok(response).build();
        } catch (BaseException e) {
            log.error("E!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(e)).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured("ver_usuario")
    public Response get(@PathParam("id") String id) {
        try {
            log.info(id);
            Usuario usuario = UsuarioDao.getInstance().get(id);
            if (usuario == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(Messages.Errores.OBJECT_NOT_FOUND)).build();
            }
            UsuarioResponse response = new UsuarioResponse(usuario);
            return Response.ok().entity(response).build();

        } catch (BaseException e) {
            log.error("E!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(e)).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured("crear_usuarios")
    public Response create(@Valid UsuarioRequest req) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("correo", req.getCorreo());
            Usuario usuario = UsuarioDao.getInstance().getUniqueByFields(params);
            if (usuario != null) {
                return Response.status(Response.Status.CONFLICT).entity(new PortalResponse(Messages.Errores.EMAIL_ALREADY_EXISTS)).build();
            }
            
            usuario = new Usuario();
            usuario.setRut(req.getRut());
            usuario.setUsername(req.getUsername());
            
            String hashed = LogicUtil.getInstance().hashPassword(req.getPassword());
            usuario.setPassword(hashed);
            usuario.setCambioPassword(hashed);
            usuario.setNombre(req.getNombre());
            usuario.setApellido(req.getApellido());
            usuario.setCorreo(req.getCorreo());
            if (req.getSucursales() != null) {
                for (String s : req.getSucursales()) {
                    Sucursal sucursal = SucursalDao.getInstance().getById(s);
                    usuario.getSucursales().add(sucursal);
                }
            }
            if (req.getRoles() != null) {
                for (String r : req.getRoles()) {
                    Rol rol = RolDao.getInstance().getById(r);
                    usuario.getRoles().add(rol);
                }
            }
            usuario = UsuarioDao.getInstance().insert(usuario);
            usuario = UsuarioDao.getInstance().get(usuario.getId());
//            String asunto = "Creacion de usuario en diagnosticapp";
//            BaseMailBody fmb = new BaseMailBody();
//            fmb.setTitle("<h2>Registro de Usuario en Portal DIAGNOSTICAPP</h2>");
//            fmb.setFooter("<small><i>Este es una aplicacion automatica. No conteste este correo ni haga consultas o comentarios a la direccion de origen.<br/>Este texto puede no contener todos los acentos ni caracteres especiales para un despliegue mas claro en cualquier aplicacion.</i></small>");
//            fmb.setHeader("ADUANAS");
//            String urPortal = "http://diagnosticapp.ciisa.cl";
//            fmb.addBodyPart("Sr./Sra. " + usuario.getNombre() + " " + usuario.getApellido() + ",<br/>Le informamos que ha sido registrado en nuestro sistema de Documentos Tributarios.<br/> <strong>Su nombre de usuario es: </strong>" + usuario.getRut() + " o " + usuario.getCorreo() + " <br/> <strong>Su nueva clave es: </strong>" + req.getPassword() + " <br/> Puede acceder con su nombre de usuario haciendo click <a href=\"" + urPortal + "\">Aqui</a> <br/> Atentamente,");

            return Response.status(Response.Status.CREATED).entity(new UsuarioResponse(usuario)).build();
        } catch (BaseException e) {
            log.error("E!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(e)).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, UsuarioRequest req) {
        try {
            Usuario usuario = UsuarioDao.getInstance().get(id);
            if (usuario == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(Messages.Errores.OBJECT_NOT_FOUND)).build();
            }
            if (req.getRut() != null) {
                usuario.setRut(req.getRut());
            }
            if (req.getUsername() != null) {
                usuario.setUsername(req.getUsername());
            }
            
            
            if (req.getPassword() != null && req.getRePassword() != null) {
               log.info("PASSWORDS: " + req.getPassword() + " 2: " + req.getRePassword());
                if(LogicUtil.getInstance().hashPassword(req.getPassword()).equals(usuario.getPassword()) || !req.getPassword().equals(req.getRePassword())){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(Messages.Errores.PASSWORD_NOT_VALID)).build();
                }
                log.info("Setea password en :  " +LogicUtil.getInstance().hashPassword(req.getPassword()));
                usuario.setPassword(LogicUtil.getInstance().hashPassword(req.getPassword()));
                usuario.setCambioPassword(null);
                log.info("Setea  cambio passsword en NULL");
                
            } else if (req.getPassword() != null) {
                log.info("Solo cambia password");
             
                usuario.setPassword(LogicUtil.getInstance().hashPassword(req.getPassword()));
            }
            
            if (req.getNombre() != null) {
                usuario.setNombre(req.getNombre());
            }
            if (req.getApellido() != null) {
                usuario.setApellido(req.getApellido());
            }
            if (req.getCorreo() != null) {
                usuario.setCorreo(req.getCorreo());
            }
            if (req.getHabilitado() != null) {
                usuario.setHabilitado(req.getHabilitado());
            }
            if (req.getSucursales() != null) {
                usuario.getSucursales().clear();
                for (String s : req.getSucursales()) {
                    Sucursal sucursal = SucursalDao.getInstance().getById(s);
                    usuario.getSucursales().add(sucursal);
                }
            }
            if (req.getRoles() != null) {
                usuario.getRoles().clear();
                for (String r : req.getRoles()) {
                    Rol rol = RolDao.getInstance().getById(r);
                    usuario.getRoles().add(rol);
                }
            }
            usuario = UsuarioDao.getInstance().update(usuario);
            usuario = UsuarioDao.getInstance().get(usuario.getId());
            return Response.ok().entity(new UsuarioResponse(usuario)).build();
        } catch (BaseException e) {
            log.error("E!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new PortalResponse(e)).build();
        }
    }

}
