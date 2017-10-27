package cl.ciisa.ingenieria.diagnosticapp.services.internal;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

/**
 * Clase para abstraer reintentos de tareas especificas.
 * 
 * @author ricardo
 * @param <T>
 */
public class RetriableTask<T> implements Callable<T> {

    private Callable<T> task;
    public static final int DEFAULT_NUMBER_OF_RETRIES = 5;
    public static final long DEFAULT_WAIT_TIME = 1000;

    private int numberOfRetries; // total de reintentos
    private int numberOfTriesLeft; // cantidad de reintentos que van
    private long timeToWait; // intervalo de tiempo entre reintentos

    /**
     * Constructor para iniciar por defecto una tarea "reintentable".
     * La tarea debe ser una clase que implemente la interfaz Callable, con un intervalo
     * de tiempo de 1 segundo, y un numero maximo de 5 reintentos.
     * @param task Tarea a ser ejecutada con reintentos
     */
    public RetriableTask(Callable<T> task) {
        this(DEFAULT_NUMBER_OF_RETRIES, DEFAULT_WAIT_TIME, task);
    }

    /**
     * Constructor para iniciar por defecto una tarea "reintentable".
     * La tarea debe ser una clase que implemente la interfaz Callable, permitiendo ademas
     * re-definir la cantidad de reintentos y su intervalo de siempo en milisegundos
     * @param numberOfRetries
     * @param timeToWait
     * @param task 
     */
    public RetriableTask(int numberOfRetries, long timeToWait,Callable<T> task) {
        this.numberOfRetries = numberOfRetries;
        numberOfTriesLeft = numberOfRetries;
        this.timeToWait = timeToWait;
        this.task = task;
    }

    /**
     * Inicia la tarea con sus respectivos reintentos
     * @return
     * @throws Exception 
     */
    @Override
    public T call() throws Exception {
        while (true) {
            try {
                return task.call();
            } catch (InterruptedException | CancellationException e) {
                throw e;
            } catch (Exception e) {
                numberOfTriesLeft--;
                if (numberOfTriesLeft == 0) {
                    throw new BaseException(cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores.RETRY_FAILED);
                }
                Thread.sleep(timeToWait);
            }
        }
    }
}
