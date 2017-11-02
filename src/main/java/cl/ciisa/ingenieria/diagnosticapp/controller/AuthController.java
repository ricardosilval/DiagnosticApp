package cl.ciisa.ingenieria.diagnosticapp.controller;

import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages;
import cl.ciisa.ingenieria.diagnosticapp.data.UsuarioDao;
import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.model.Credenciales;
import cl.ciisa.ingenieria.diagnosticapp.model.Rol;
import cl.ciisa.ingenieria.diagnosticapp.dto.BaseResponse;
import cl.ciisa.ingenieria.diagnosticapp.dto.request.LoginRequest;
import cl.ciisa.ingenieria.diagnosticapp.dto.response.LoginResponse;
import cl.ciisa.ingenieria.diagnosticapp.util.BaseLogger;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michel Munoz <michel@febos.cl>
 */
@Path("auth")
public class AuthController {
    
    private final FebosLogger LOG = new FebosLogger(false);
    @Context
    private HttpServletRequest httpRequest;

    /**
     * Permite la autenticacion a la API.
     *
     * @param mail Correo electronico de ingreso del usuario
     * @param password Contrasena de ingreso del usuario
     * @return Respuesta en JSON (API)
     */
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequest request) throws FebosException {
        
        try {
            if (request.getMail() == null && request.getPassword() == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            Credentials credentials = new Credentials();
            
            credentials.setUsername(request.getMail());
            
            credentials.setPass(request.getPassword());
            
            User usuario = UserDao.getInstance().login(credentials);
            LOG.info("PASO EL LOGIN DAO");
            
            if (usuario == null) {
                LOG.info("USUARIO NULL");
                return Response.status(Response.Status.UNAUTHORIZED).entity(new FebosResponse(Messages.Errores.LOGIN_INCORRECT)).build();
            }
            if (usuario.getEstado() == User.STATUS_DISABLED) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(new FebosResponse(Messages.Errores.FORBIDDEN_ACCESS)).build();
            }
            int isAdminC = 0;

            //UserDao.getInstance().update(usuario);
            LOG.info("VAMO A NEGOCIAr EL TOKEN");
            ApiToken token = ApiTokenDao.getInstance().issueToken(usuario);
            LOG.info("VAMOA  VER LOS ROLES ");
            
            for (Role rol : usuario.getRoles()) {
                LOG.info("ROL ID: " + rol.getId() + " ROL NAME : " + rol.getNombre());
                if (rol.getId().equalsIgnoreCase("superadmin") || rol.getNombre().equalsIgnoreCase("admin")) {
                    isAdminC++;
                }
            }
            LOG.info("PASO EL TEMA DE ROLES ADMIN");
            
            if (isAdminC == 0) {
                return Response.ok(new LoginResponse(usuario, token)).build();
            } else {
                LOG.info("ROLES DE NO ADMIN");
                List<Empresa> empresas = EmpresaDao.getInstance().getAllEmisores();
                LOG.info("ALOOO");
                return Response.ok(new LoginResponse(usuario, token, empresas)).build();
            }
        } catch (FebosException ex) {
            LOG.error("Error realizando login", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new FebosResponse(ex)).build();
        }
    }
}
