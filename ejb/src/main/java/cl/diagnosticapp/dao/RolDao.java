/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.Rol;
import cl.diagnosticapp.utils.HibernateUtil;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
public class RolDao extends BaseDao<Rol> {

    public RolDao() {
        super(Rol.class);
    }

    public static RolDao getInstance() {
        return RolDaoHolder.INSTANCE;
    }

    private static class RolDaoHolder {

        private static final RolDao INSTANCE = new RolDao();
    }

    @SuppressWarnings("unchecked")
    public Pair<List<Rol>, Long> getAll(
            String nombre,
            String descripcion,
            String codigo,
            List<String> permisos,
            Integer pagina,
            Integer filas
    ) throws BaseException {
        StringBuilder hql = new StringBuilder("SELECT DISTINCT r FROM Rol r");
        StringBuilder whereBuilder = new StringBuilder();
        HashMap<String, Object> params = new HashMap<>();

        if (nombre != null && !nombre.isEmpty()) {
            whereBuilder.append("r.nombre LIKE :nombre AND ");
            params.put("nombre", "%" + nombre + "%");
        }

        if (descripcion != null && !descripcion.isEmpty()) {
            whereBuilder.append("r.descripcion LIKE :descripcion AND ");
            params.put("descripcion", "%" + descripcion + "%");
        }

        if (whereBuilder.length() > 0) {
            hql.append("WHERE ");
            hql.append(whereBuilder.toString().substring(0, whereBuilder.length() - 4));
        }
        LOG.info(hql.toString());
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql.toString());
            params.entrySet().stream().forEach((pair) -> {
                query.setParameter(pair.getKey(), pair.getValue());
            });
            if (pagina != null && filas != null) {
                query.setFirstResult((pagina - 1) * filas);
            }
            if (filas != null) {
                query.setMaxResults(filas);
            }
            List<Rol> results = query.list();

            Query countQuery = session.createQuery(hql.toString().replace("DISTINCT r FROM Rol r", "COUNT(DISTINCT r.id) FROM Rol r"));
            params.entrySet().stream().forEach((pair) -> {
                countQuery.setParameter(pair.getKey(), pair.getValue());
            });
            long count = (long) countQuery.setMaxResults(1).uniqueResult();
            System.out.println("count: "+count);
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
}
