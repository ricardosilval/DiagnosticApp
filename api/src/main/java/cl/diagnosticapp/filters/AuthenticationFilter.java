/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.filters;

import cl.diagnosticapp.annotations.Secured;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.dao.TokenDao;
import cl.diagnosticapp.model.Token;
import cl.diagnosticapp.model.Usuario;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 * Filtro para la autenticacion de usuarios. Revisa que se haya enviado el token
 * y que este sea valido.
 *
 * 
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    private BaseLogger log;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Obtener token de header
        String tokenId
                = requestContext.getHeaderString("X-Token");

        // Revisar que el token este presente
        if (tokenId == null) {
            log.warning("Error de autenticacion: Token no esta presente en header");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new BaseResponse(new BaseException(Messages.Errores.FORBIDDEN)))
                    .build());
            return;
        }
        // Validar token        
        try {
            Token token = TokenDao.getInstance().find(tokenId, false);
            if (token == null || !TokenDao.getInstance().isValid(token.getId())) {
                log.warning("Error de autenticacion: Token " + tokenId + " no es valido");
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new BaseResponse(Messages.Errores.INVALID_TOKEN))
                        .build());
                return;
            }
            TokenDao.getInstance().updateExpirationTime(token.getId());
            Usuario user = token.getUsuario();
            String userId = user.getId();
            requestContext.setSecurityContext(new SecurityContext() {

                @Override
                public Principal getUserPrincipal() {

                    return () -> userId;
                }

                @Override
                public boolean isUserInRole(String role) {
                    return true;
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return null;
                }
            });
        } catch (EJBException ex) {
            log.error("Error accediendo a la base de datos.", ex);
        } catch (BaseException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
