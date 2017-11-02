package cl.ciisa.ingenieria.diagnosticapp.util;

import cl.ciisa.ingenieria.diagnosticapp.dto.LogLocation;
import cl.ciisa.ingenieria.diagnosticapp.model.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Utilitario para registro de LOGS.
 * Permite gestionar los logs del sistema y guardarlos en base de datos si es necesario.
 * Por defecto los logs son guardados en la base de datos, de no ser requerido el constructor
 * debe ser iniciado con un boolean false
 * @author ricardo
 */
public class BaseLogger implements LogInterface {

    private final int DEBUG = 1;
    private final int INFO = 2;
    private final int WARNING = 3;
    private final int ERROR = 4;
    //private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BaseLogger.class);
    private static final Logger log = Logger.getLogger(BaseLogger.class.getName());
    public boolean persist=true;

    
    public BaseLogger(boolean persist) {
        this.persist=persist;
    }
    public BaseLogger() {
        
    }

    /**
     * Escribe un log de tipo WARNING con su stacktrace correspondiente.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     */
    @Override
    public void warning(String message, Throwable t) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(WARNING, message, t, caller.getClase(),caller.getMethod(), caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo INFO con su stacktrace correspondiente.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     */
    @Override
    public void info(String message, Throwable t) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(INFO, message, t, caller.getClase(),caller.getMethod(), caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo ERROR con su stacktrace correspondiente.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     */
    @Override
    public void error(String message, Throwable t) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(ERROR, message, t, caller.getClase(),caller.getMethod(), caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo DEBUG con su stacktrace correspondiente.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     */
    @Override
    public void debug(String message, Throwable t) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(DEBUG, message, t, caller.getClase(), caller.getMethod(),caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo WARNING.
     *
     * @param message Mensaje de log
     */
    @Override
    public void warning(String message) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(WARNING, message, null, caller.getClase(),caller.getMethod(), caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo INFO.
     *
     * @param message Mensaje de log
     */
    @Override
    public void info(String message) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(INFO, message, null, caller.getClase(),caller.getMethod(), caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo ERROR.
     *
     * @param message Mensaje de log
     */
    @Override
    public void error(String message) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(ERROR, message, null, caller.getClase(),caller.getMethod(), caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo DEBUG.
     *
     * @param message Mensaje de log
     */
    @Override
    public void debug(String message) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(DEBUG, message, null, caller.getClase(),caller.getMethod(), caller.getLine(), null);
    }

    /**
     * Escribe un log de tipo WARNING con su stacktrace correspondiente,
     * relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     * @param processId ID del proceso
     */
    @Override
    public void warning(String message, Throwable t, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(WARNING, message, t, caller.getClase(),caller.getMethod(), caller.getLine(), processId);
    }

    /**
     * Escribe un log de tipo INFO con su stacktrace correspondiente,
     * relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     * @param processId ID del proceso
     */
    @Override
    public void info(String message, Throwable t, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(INFO, message, t, caller.getClase(),caller.getMethod(), caller.getLine(), processId);
    }

    /**
     * Escribe un log de tipo ERROR con su stacktrace correspondiente,
     * relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     * @param processId ID del proceso
     */
    @Override
    public void error(String message, Throwable t, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(ERROR, message, t, caller.getClase(), caller.getMethod(),caller.getLine(), processId);
    }

    /**
     * Escribe un log de tipo DEBUG con su stacktrace correspondiente,
     * relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param t Objeto que contiene la exception
     * @param processId ID del proceso
     */
    @Override
    public void debug(String message, Throwable t, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(DEBUG, message, t, caller.getClase(),caller.getMethod(), caller.getLine(), processId);
    }

    /**
     * Escribe un log de tipo WARNING, relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param processId ID del proceso
     */
    @Override
    public void warning(String message, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(WARNING, message, null, caller.getClase(), caller.getMethod(),caller.getLine(), processId);
    }

    /**
     * Escribe un log de tipo INFO, relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param processId ID del proceso
     */
    @Override
    public void info(String message, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(INFO, message, null, caller.getClase(), caller.getMethod(),caller.getLine(), processId);
    }

    /**
     * Escribe un log de tipo ERROR, relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param processId ID del proceso
     */
    @Override
    public void error(String message, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(ERROR, message, null, caller.getClase(),caller.getMethod(), caller.getLine(), processId);
    }

    /**
     * Escribe un log de tipo DEBUG, relacionado a un ID de proceso.
     *
     * @param message Mensaje de log
     * @param processId ID del proceso
     */
    @Override
    public void debug(String message, String processId) {
        LogLocation caller = getCaller(Thread.currentThread().getStackTrace());
        log(DEBUG, message, null, caller.getClase(),caller.getMethod(), caller.getLine(), processId);
    }

    /**
     * Persiste un log en la base de datos y lo envia al log appender. Escribe
     * el log de forma normal mienras lo escribe en la base de datos.
     *
     * @param logLevel
     * @param message
     * @param t
     * @param callerClass
     * @param callerLine
     * @param processId
     */
    private void log(int logLevel, String message, Throwable t, String callerClass, String callerMethod,int callerLine, String processId) {
        Log logModel = new Log();
        switch (logLevel) {
            case DEBUG:
                logModel.setType(Log.TYPE_DEBUG);
                log.logp(Level.FINE,callerClass,callerMethod, "["+callerClass+":"+callerLine+"] "+message, t);
                //log.debug(message, t);
                break;
            case INFO:
                logModel.setType(Log.TYPE_INFO);
                //log.info(message, t);
                log.logp(Level.INFO, callerClass,callerMethod, "["+callerClass+":"+callerLine+"] "+message, t);
                break;
            case ERROR:
                logModel.setType(Log.TYPE_ERROR);
                //log.error(message, t);
                log.logp(Level.SEVERE, callerClass,callerMethod,"["+callerClass+":"+callerLine+"] "+ message, t);
                break;
            case WARNING:
                logModel.setType(Log.TYPE_WARNING);
                //log.warn(message, t);
                log.logp(Level.WARNING,callerClass,callerMethod,"["+callerClass+":"+callerLine+"] "+ message, t);
                break;
        }
        
        //verifica si esta configurado para persistir los datos en la BD, solo funciona bajo un server jboss
        if(!persist)return;

        try {
            if (processId == null) {
                processId = Thread.currentThread().getName();
            }
            if(Thread.currentThread().getName().contains("ServerService")){
                Thread.currentThread().setName(LogicUtil.getInstance().UUID());
                processId = Thread.currentThread().getName();
            }

            //cl.febos.model.File stacktraceFile = new cl.febos.model.File();
            //stacktraceFile.setFileId(UUID.randomUUID().toString());
            //stacktraceFile.setMime("plain/txt");
            //stacktraceFile.setAndEncodeContent(cleanStacktrace(t));

            logModel.setFecha(new Date());
            logModel.setHostname(Config.getInstance().getHostName());
            logModel.setId(UUID.randomUUID().toString());
            logModel.setClase(callerClass);
            logModel.setLine(callerLine);
            logModel.setMessage(message);
            logModel.setProcessId(processId);
            if (t != null) {
                //logModel.setStackTraceFileId(stacktraceFile.getFileId());
            }

            //se crea session y se inicia la transaccion
            Connection connect;
            PreparedStatement preparedStatement;

            //connect = DriverManager.getConnection("jdbc:mysql://localhost/Febos2?user=root&password=");
            Context ic = new InitialContext();
            String dsS=System.getProperty("log.db.jndi");
            dsS=(dsS==null||dsS.isEmpty())?"java:/FebosDS":dsS;
            DataSource ds = (DataSource) ic.lookup(dsS);
            connect = ds.getConnection();
            preparedStatement = connect.prepareStatement("insert into log (log_id,process_id,fecha,type,hostname,message,stack_trace_file_id,class,line) values (uuid(), ?, ?, ?, ? , ?, ? ,? ,?)");
            preparedStatement.setString(1, logModel.getProcessId()); //process_id
            preparedStatement.setString(2, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(logModel.getFecha())); //fecha
            preparedStatement.setInt(3, logModel.getType()); //type
            preparedStatement.setString(4, logModel.getHostname()); //hostname
            preparedStatement.setString(5, logModel.getMessage()); //message
            preparedStatement.setString(6, logModel.getStackTraceFileId()); //stacktracae _id
            preparedStatement.setString(7, logModel.getClase());//class
            preparedStatement.setInt(8, logModel.getLine());//line
            preparedStatement.executeUpdate();
            if (logModel.getStackTraceFileId() != null) {
                preparedStatement = connect.prepareStatement("insert into files (file_id,content,mime) values (?,?,?)");
                //preparedStatement.setString(1, stacktraceFile.getFileId()); //fileid
                //preparedStatement.setString(2, stacktraceFile.getContent()); //content
                preparedStatement.setString(3, "java"); //mime 
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
            connect.close();
            //se escriben los datos en la base de datos
            //se fabrica la respuesta
        } catch (Exception e) {
            //log.info("Imposible guardar log en la DB", e);
            log.log(Level.SEVERE, "Imposible guardar log en la DB", e);
        } finally {

        }
    }

    /**
     * Limpia un stacktrace dejando solo las lineas de codigo propio. De esta
     * forma el stacktrace solo mostrara seguimiento de codigo interno el cual
     * puede ser arreglado, omitiendo clases nativas o de librerias.
     *
     * @param throwable Objeto con la excepcion
     * @return Stacktrace como string, limpio
     */
    public String cleanStacktrace(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        String[] stackTrace = ExceptionUtils.getStackTrace(throwable).split("\n");
        StringBuilder filteredStackTrace = new StringBuilder();
        for (String stline : stackTrace) {
            if (stline.toLowerCase().contains("cl.febos") || stline.contains("Exception")) {
                filteredStackTrace.append(stline.replace("cl.febos ->", "\t")).append("\n");
            }
        }
        return filteredStackTrace.toString();
    }

    /**
     * Obtiene de donde se esta llamando al log. Analiza el stacktrace y omite
     * las lineas referentes al objeto logger, ubicando de esta manera la calse
     * y linea exacta desde donde esta siendo llamado.
     *
     * @param stackTraceElements Stacktrace como arreglo
     * @return Lugar de donde esta siendo llamado
     */
    private LogLocation getCaller(StackTraceElement[] stackTraceElements) {
        LogLocation lc = new LogLocation();
        if (stackTraceElements.length == 1) {
            StackTraceElement st = stackTraceElements[0];
            lc.setClase(st.getClassName());
            lc.setLine(st.getLineNumber());
            lc.setMethod(st.getMethodName());
            return lc;
        }

        for (int i = 1; i < stackTraceElements.length; i++) {
            if (stackTraceElements[i].getClassName().equals("cl.ciisa.ingenieria.diagnosticapp.util.FebosLogger")) {
                StackTraceElement st = stackTraceElements[i + 1];
                lc.setClase(st.getClassName());
                lc.setLine(st.getLineNumber());
                lc.setMethod(st.getMethodName());
                return lc;
            }
        }
        return null;
    }

}
