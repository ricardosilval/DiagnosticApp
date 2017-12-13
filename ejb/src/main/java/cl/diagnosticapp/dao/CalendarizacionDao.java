/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.Calendarizacion;
import cl.diagnosticapp.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ricardo
 */
public class CalendarizacionDao extends BaseDao<Calendarizacion> {

    public CalendarizacionDao() {
        super(Calendarizacion.class);
    }

    public static CalendarizacionDao getInstance() {
        return CalendarizacionDaoHolder.INSTANCE;
    }

    private static class CalendarizacionDaoHolder {

        private static final CalendarizacionDao INSTANCE = new CalendarizacionDao();
    }

    public Calendarizacion get(String id) {
        return super.getById(id);
    }

    @SuppressWarnings("unchecked")
    public Pair<List<Calendarizacion>, Long> getAll(
            Long fechaInicio,
            Integer estado,
            int pagina,
            int filas
    ) throws BaseException {
        StringBuilder hql = new StringBuilder("SELECT DISTINCT c FROM Calendarizacion c ");
        StringBuilder whereBuilder = new StringBuilder();
        HashMap<String, Object> params = new HashMap<>();
//        whereBuilder.append("s.estado <> :eliminado AND ");
//        params.put("eliminado", Subasta.STATUS_DELETED);
        if (fechaInicio != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(fechaInicio));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            whereBuilder.append("c.fechaInicio >= :fechaInicio AND ");
            params.put("fechaInicio", calendar.getTime());

        }

        if (estado != null) {
            whereBuilder.append("c.estado = :estado AND ");
            params.put("estado", estado);
        }

        if (whereBuilder.length() > 0) {
            hql.append("WHERE ");
            hql.append(whereBuilder.toString().substring(0, whereBuilder.length() - 4));
        }
        hql.append(" ORDER BY c.fechaInicio DESC");
        //LOG.info(hql.toString());
        System.out.println(""+ hql.toString());
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql.toString());
            params.entrySet().stream().forEach((pair) -> {
                query.setParameter(pair.getKey(), pair.getValue());
            });
            query.setFirstResult((pagina - 1) * filas);
            query.setMaxResults(filas);
            List<Calendarizacion> results = query.list();

            Query countQuery = session.createQuery(hql.toString().replace("SELECT DISTINCT c", "SELECT COUNT(c.id)").replace(" ORDER BY c.fechaInicio DESC", ""));
            params.entrySet().stream().forEach((pair) -> {
                countQuery.setParameter(pair.getKey(), pair.getValue());
            });
            long count = (long) countQuery.uniqueResult();
            System.out.println(count);
            transaction.commit();
            return new ImmutablePair<>(results, count);
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Messages.Errores.DATABASE_ERROR)
                    .set("metodo", "getAll")
                    .set("query", hql.toString())
                    .set("clase", this.getClass().getSimpleName())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }

    }
    /**
     * TODO: Crear validacion si existe calendarizacion para un rango 
     * @param fechaInicio
     * @param fechaTermino
     * @return 
     */
    public boolean existByDate(Date fechaInicio, Date fechaTermino) {
        
        return false;
    }

}
