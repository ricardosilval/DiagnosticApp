/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.services;

import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.log.BaseLogger;
import cl.diagnosticapp.utils.PortalUtil;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/**
 *
 * @author ricardo
 */
public class ReportService {

    @Inject
    private BaseLogger log;
    private final String SEPARADOR = ";";
    private final String BR = "\n";
    public static final BaseLogger LOG = new BaseLogger();

    public static void main(String[] args) throws Exception {
        Double numero = 22.8891;
        DecimalFormat df = new DecimalFormat("0.00");
        String numeroConvertido = new DecimalFormat("#.00").format(numero);
        LOG.info("El numero es: " + numeroConvertido);

    }

}
