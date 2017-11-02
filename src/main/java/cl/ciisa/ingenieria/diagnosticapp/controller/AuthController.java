package cl.ciisa.ingenieria.diagnosticapp.controller;

import cl.ciisa.ingenieria.diagnosticapp.business.UsuarioLogic;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages;
import cl.ciisa.ingenieria.diagnosticapp.dao.UsuarioDao;
import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.model.Credenciales;
import cl.ciisa.ingenieria.diagnosticapp.dto.BaseResponse;
import cl.ciisa.ingenieria.diagnosticapp.dto.request.LoginRequest;
import cl.ciisa.ingenieria.diagnosticapp.dto.response.LoginResponse;
import cl.ciisa.ingenieria.diagnosticapp.model.Usuario;
import cl.ciisa.ingenieria.diagnosticapp.util.BaseLogger;
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
 * @author ricardo
 */
@Path("auth")
public class AuthController {

    private final BaseLogger LOG = new BaseLogger(false);
    @Context
    private HttpServletRequest httpRequest;

    /**
     * Permite la autenticacion al sistema
     * @param request
     * @return Respuesta en JSON (API)
     */
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequest request) throws BaseException {

        try {
            if (request.getMail() == null && request.getPassword() == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            Usuario usuario = usuarioLogic.login(request);

            if (usuario == null) {
                LOG.info("USUARIO NULL");
                return Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(Messages.Errores.LOGIN_INCORRECT)).build();
            }
            if (usuario.getEstado() == Usuario.ESTADO_INACTIVO) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(Messages.Errores.FORBIDDEN_ACCESS)).build();
            }

            return Response.ok(new LoginResponse(usuario)).build();

        } catch (BaseException ex) {
            LOG.error("Error realizando login", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(ex)).build();
        }
    }
}
