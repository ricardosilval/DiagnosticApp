/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import static cl.diagnosticapp.dao.BaseDao.LOG;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.Pregunta;
import cl.diagnosticapp.model.Pregunta;
import cl.diagnosticapp.utils.HibernateUtil;
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
public class PreguntaDao extends BaseDao<Pregunta> {

    public PreguntaDao() {
        super(Pregunta.class);
    }

    public static PreguntaDao getInstance() {
        return PreguntaDaoHolder.INSTANCE;
    }

    private static class PreguntaDaoHolder {

        private static final PreguntaDao INSTANCE = new PreguntaDao();
    }

    public Pregunta get(String id) {
        return super.getById(id);
    }

    @SuppressWarnings("unchecked")
    public Pair<List<Pregunta>, Long> getAll(
            String categoria,
            Integer estado,
            String evaluacionId,
            int pagina,
            int filas
    ) throws BaseException {
        StringBuilder hql = new StringBuilder("SELECT DISTINCT p FROM Pregunta p ");
        StringBuilder whereBuilder = new StringBuilder();
        HashMap<String, Object> params = new HashMap<>();
//        whereBuilder.append("s.estado <> :eliminado AND ");
//        params.put("eliminado", Subasta.STATUS_DELETED);

        if (evaluacionId != null) {
            whereBuilder.append("p.categoria = :categoria AND ");
            params.put("categoria", categoria);
        } else {
            if (categoria != null) {
                whereBuilder.append("p.categoria = :categoria AND ");
                params.put("categoria", categoria);

            }

            if (estado != null) {
                whereBuilder.append("p.estado = :estado AND ");
                params.put("estado", estado);
            }
        }

        if (whereBuilder.length() > 0) {
            hql.append("WHERE ");
            hql.append(whereBuilder.toString().substring(0, whereBuilder.length() - 4));
        }
        hql.append(" ORDER BY p.cuerpo DESC");
        //LOG.info(hql.toString());
        System.out.println("" + hql.toString());
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
            List<Pregunta> results = query.list();
             
            results.stream().forEach((eval) -> {
                Hibernate.initialize(eval.getCategoria());
            });
     

            Query countQuery = session.createQuery(hql.toString().replace("SELECT DISTINCT p", "SELECT COUNT(p.id)").replace(" ORDER BY p.cuerpo DESC", ""));
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
     * TODO: Crear validacion si existe pregunta para un rango
     *
     * @param fechaInicio
     * @param fechaTermino
     * @return
     */
    public boolean existByDate(Date fechaInicio, Date fechaTermino) {

        return false;
    }

}
