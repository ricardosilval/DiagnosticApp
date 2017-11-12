/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.dao.TokenDao;
import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.model.Token;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.model.requests.LoginRequest;
import cl.diagnosticapp.model.requests.PasswordRecoveryRequest;
import cl.diagnosticapp.model.responses.LoginResponse;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.models.Credentials;
import cl.diagnosticapp.utils.LogicUtil;
import cl.diagnosticapp.utils.PortalUtil;
import java.util.HashMap;
import javax.annotation.ManagedBean;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ricardo
 */
@Path("/auth")
@ManagedBean
public class AuthController {

    private static final BaseLogger LOG = new BaseLogger();

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequest request) throws BaseException {

        try {
            if (request.getMail() == null && request.getPassword() == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            Credentials credentials = new Credentials();
            UsuarioDao.LoginType type;
            if (request.getMail() != null) {
                credentials.setUsername(request.getMail());
                System.out.println("correo: "+request.getMail());
                type = UsuarioDao.LoginType.MAIL;
            } else {
                credentials.setUsername(request.getUsername());
                System.out.println("username: "+request.getUsername());
                type = UsuarioDao.LoginType.USERNAME;
            }
            
            System.out.println("clave: "+request.getPassword());
            credentials.setPass(request.getPassword());

            System.out.println("Entrando al login del dao");
            Usuario usuario = UsuarioDao.getInstance().login(type, credentials);
            if (usuario == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(Messages.Errores.LOGIN_INCORRECT)).build();
            }
            if (usuario.getEstado() == Usuario.ESTADO_INACTIVO) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(Messages.Errores.FORBIDDEN_ACCESS)).build();
            }
                
            UsuarioDao.getInstance().update(usuario);
            Token token = TokenDao.getInstance().issueToken(usuario);
            return Response.ok(new LoginResponse(usuario, token)).build();
        } catch (BaseException ex) {
            System.out.println("Error "+ ex.getLocalizedMessage());
            LOG.error("Error realizando login", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(ex)).build();
        }
    }

    //Path Id del usuario que modifica
    @POST
    @Path("/recovery")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recovery(PasswordRecoveryRequest request) {
        LOG.info("request: " + request.getEmail());
        
        try {

            if (request.getEmail() == null || request.getEmail().equals("")) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            HashMap<String, Object> params = new HashMap<>();
            params.put("correo", request.getEmail());
            Usuario usuario = UsuarioDao.getInstance().getUniqueByFields(params);
            if (usuario == null) {
                return Response.ok(new BaseResponse(Messages.Errores.EMAIL_NOTIFICATION)).build();
            }

            String cadenaClave = PortalUtil.UUID().substring(0, 8);
            String nuevaClave = LogicUtil.getInstance().hashPassword(cadenaClave);
            usuario.setClave(nuevaClave);
            usuario.setCambioClave(nuevaClave);

            UsuarioDao.getInstance().update(usuario);

//            String asunto = "Sus credenciales Diagnosticapp";
//            MailBody fmb = new MailBody();
//            fmb.setTitle("<h2>Sus credenciales</h2>");
//            fmb.setFooter("<small><i>Este es una aplicacion automatica. No conteste este correo ni haga consultas o comentarios a la direccion de origen.<br/>Este texto puede no contener todos los acentos ni caracteres especiales para un despliegue mas claro en cualquier aplicacion.</i></small>");
//            fmb.setHeader("DIAGNOSTICAPP");
//            String urPortal = "http://diagnosticapp.ciisa.cl";
//            fmb.addBodyPart("Sr./Sra. " + usuario.getNombre() + " " + usuario.getApellido() + ",<br/>Le informamos que se le envian una nueva clave en el portal diagnosticapp diagnosticapp.<br/> <strong>Su nombre de usuario es: </strong>" + usuario.getRut() + " o " + usuario.getCorreo() + " <br/> <strong>Su nueva clave es: </strong>" + cadenaClave + " <br/> Puede acceder con su nombre de usuario haciendo click <a href=\"" + urPortal + "\">Aqui</a> <br/> Atentamente,");
//            String id = MailGunService.send("notificaciones@empresas.diagnosticapp.cl", usuario.getCorreo(), asunto, fmb.build(), "", null, (File) null);

            LOG.info("ENTRA Email!: " + request.getEmail());

            // LOG.info("Entro al mail5" + response);
            return Response.ok(new BaseResponse(Messages.Errores.EMAIL_NOTIFICATION)).build();

        } catch (BaseException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(ex)).build();

        }

    }

}
