/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.filters;

/**
 *
 * @author ricardo
 */
import cl.diagnosticapp.annotations.Secured;
import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.model.responses.BaseResponse;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject
    private BaseLogger log;
    @Context
    private ResourceInfo resourceInfo;

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

        // Obtiene la clase del recurso
        // Extrae el permiso correspondiente
        Class<?> resourceClass = resourceInfo.getResourceClass();
        String[] classPermission = extractPermissions(resourceClass);

        // Obtiene el metodo del recurso
        // Extrae el permiso correspondiente
        Method resourceMethod = resourceInfo.getResourceMethod();
        String[] methodPermission = extractPermissions(resourceMethod);

        try {
            // Revisar si el usuario cuenta con el permiso correspondiente
            // El permiso del metodo sobreescribe el permiso de la clase
            String[] permissions;
            if (methodPermission == null || methodPermission.length == 0) {
                permissions = classPermission;
            } else {
                permissions = methodPermission;
            }

            log.info("-------- >El usuario Principal " + requestContext.getSecurityContext().getUserPrincipal().getName() + " ");

            if (!hasPermissions(permissions, requestContext.getSecurityContext().getUserPrincipal().getName())) {
                log.error("El usuario " + requestContext.getSecurityContext().getUserPrincipal().getName() + " no cuenta con el permiso ", Arrays.toString(permissions));
                requestContext.abortWith(
                        Response.status(Response.Status.FORBIDDEN).entity(new BaseResponse(Messages.Errores.FORBIDDEN)).build());
            }
        } catch (EJBException e) {
            log.error("Error revisando permisos de usuario", e);
            requestContext.abortWith(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new BaseResponse(Messages.Errores.DATABASE_ERROR)).build());
        } catch (BaseException ex) {
            Logger.getLogger(AuthorizationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Extrae permiso de anotacion.
     *
     * @param annotatedElement
     * @return String permiso
     */
    private String[] extractPermissions(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return null;
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return null;
            } else {
                return secured.value().split(",");
            }
        }
    }

    private boolean hasPermissions(String[] permissions, String userId) throws BaseException {
//        Usuario usuario = UsuarioDao.getInstance().getWithRoles(userId);
//        if (usuario.getRun().equals("555555555-5")) {
//            return true;
//        }
//        for (String permission : permissions) {
//            if (UsuarioDao.getInstance().hasPermission(usuario, permission)) {
//                return true;
//            }
//        }
//        return false;
        return true;
    }
}
