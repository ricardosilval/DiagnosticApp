/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.controllers;

import cl.diagnosticapp.annotations.Secured;
import cl.diagnosticapp.dao.UsuarioDao;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.model.responses.DashboardResponse;
import cl.diagnosticapp.model.responses.MainChartResponse;
import cl.diagnosticapp.model.responses.BaseResponse;
import cl.diagnosticapp.utils.PortalUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
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
 * @author ricardo
 */
@Path("/dashboard")
public class DashboardController {

    @Inject
    private BaseLogger log;
    @Context
    private SecurityContext securityContext;

    

}
