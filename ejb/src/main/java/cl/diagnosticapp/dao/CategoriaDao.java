/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import static cl.diagnosticapp.dao.BaseDao.LOG;
import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.Categoria;
import cl.diagnosticapp.model.Categoria;
import cl.diagnosticapp.utils.HibernateUtil;
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
public class CategoriaDao extends BaseDao<Categoria> {

    public CategoriaDao() {
        super(Categoria.class);
    }

    public static CategoriaDao getInstance() {
        return CategoriaDaoHolder.INSTANCE;
    }

    private static class CategoriaDaoHolder {

        private static final CategoriaDao INSTANCE = new CategoriaDao();
    }
    
     public Categoria get(String id) {
        return super.getById(id);
    }
     
     @SuppressWarnings("unchecked")
    public Pair<List<Categoria>, Long> getAll(
            String titulo,
            String categoriaId,
            int pagina,
            int filas
    ) throws BaseException {
        StringBuilder hql = new StringBuilder("SELECT DISTINCT c FROM Categoria c ");
        StringBuilder whereBuilder = new StringBuilder();
        HashMap<String, Object> params = new HashMap<>();

        if (categoriaId != null && !categoriaId.isEmpty()) {
            whereBuilder.append("c.id = :categoriaId AND ");
            params.put("categoriaId", categoriaId);
        }
        

        if (whereBuilder.length() > 0) {
            hql.append("WHERE ");
            hql.append(whereBuilder.toString().substring(0, whereBuilder.length() - 4));
        }
        hql.append(" ORDER BY c.nombre DESC");
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
            List<Categoria> results = query.list();
            results.stream().forEach((cat) -> {
                Hibernate.initialize(cat.getCategoria());
                
            });
            Query countQuery = session.createQuery(hql.toString().replace("SELECT DISTINCT c", "SELECT COUNT(c.id)").replace(" ORDER BY c.titulo DESC", ""));
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

}
