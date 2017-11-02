package cl.ciisa.ingenieria.diagnosticapp.dao;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores;
import cl.ciisa.ingenieria.diagnosticapp.model.BaseModel;
import cl.ciisa.ingenieria.diagnosticapp.util.BaseLogger;
import cl.ciisa.ingenieria.diagnosticapp.util.HibernateUtil;
import cl.ciisa.ingenieria.diagnosticapp.util.LogicUtil;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.transform.Transformers;

/**
 * Objeto generico para el acceso a datos. Permite otorgar una funcionalidad
 * basica a un DAO
 *
 * @author ricardo
 * @param <T> Modelo que requiere manipular el DAO, debe extender de BaseModel
 */
public abstract class BaseDao<T extends BaseModel> {

    public static final String DEFAULT_CSV_SEPARATOR = ";";
    public static final BaseLogger LOG = new BaseLogger(false);
    public String modelPackage;
    protected final Class<T> model;

    public BaseDao(Class<T> model) {
        this.model = model;

    }

    /**
     * Inserta un nuevo registro en la BD
     *
     * @param obj objeto del tipo entity a insertar
     */
    public T insert(T obj) {
        if (obj.getId() == null) {
            obj.setId(LogicUtil.getInstance().UUID());
        }
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
            return obj;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //error al guarda
                    .set("metodo", "insert")
                    .set("objeto", obj.toString())
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * Elimina un registro en base a su id
     *
     * @param id id del registro a eliminar
     */
    public void delete(String id) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Object load = session.load(model, id);
            session.delete(load);
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //error al borrar
                    .set("metodo", "delete")
                    .set("id", id)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        } catch (Exception e) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("metodo", "delete")
                    .set("id", id)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     * Actualiza un registro con los datos del objeto que se pasa como argumento
     *
     * @param obj Objeto a actualizar con los nuevos datos
     */
    public T update(T obj) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();
            return obj;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //error al guardar
                    .set("metodo", "update")
                    .set("objeto", obj.toString())
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        } catch (Exception e) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("metodo", "update")
                    .set("objeto", obj.toString())
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     * Retorna todos los registros existentes
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            List<?> lstObjs;
            transaction = session.beginTransaction();
            String clazz = model.getName();
            String query = "from " + clazz;

            Class.forName(clazz);

            lstObjs = session.createQuery(query).list();

            return (List<T>) lstObjs;
        } catch (ClassNotFoundException e) {
            throw new BaseException(Errores.CLASS_NOT_FOUND) //class not found
                    .set("metodo", "getAll")
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));

        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //error al leer
                    .set("metodo", "getAll")
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     * Retorna un registro en base a su ID
     *
     * @param id id del registro
     * @return
     */
    public T getById(String id) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            if (session == null) {
                throw new BaseException(Errores.DATABASE_ERROR).set("session", "no iniciada (null)");
            }
            Object obj;
            transaction = session.beginTransaction();
//            String packageLocation = this.modelPackage;
            //if(this.getClass().getSimpleName().equalsIgnoreCase("logdao"))packageLocation="cl.ciisa.modell.";
            obj = session.get(model, id);
            transaction.commit();
            return (T) obj;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //no se puede leer
                    .set("metodo", "getById")
                    .set("id", id)
                    .set("clase", this.getClass().getSimpleName())
                    .set("modelo", model.getName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     * Obtiene un objeto UNICO indicando los campos necesarios. Si existe mas de
     * un objeto retornara error.
     *
     * @param fields HashMap String,Object indicado que campo debe ser igual a
     * "que cosa"
     * @return
     */
    public T getUniqueByFields(HashMap<String, Object> fields) {
        Transaction transaction = null;

        String sql = "";
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String clazz = model.getSimpleName();
            sql = "from " + clazz + " where ";
            HashMap<String, Integer> orden = new HashMap<>();
            if (fields != null && !fields.isEmpty()) {
                int pos = 1;
                for (Map.Entry<String, Object> pair : fields.entrySet()) {
                    orden.put(pair.getKey(), pos);
//                    System.out.println("llenando query con llave:" + (String) pair.getKey() + " y valor: " + (String) pair.getKey());
                    sql += " " + pair.getKey() + " = :" + pair.getKey() + " and";
                    pos++;
                }
            }

            sql = sql.substring(0, sql.length() - 4);
//            System.out.println("QUERY: " + sql);
            Query query = session.createQuery(sql);
//            String[] namedParameters = query.getNamedParameters();
//            for (String np : namedParameters) {
//                System.out.println("NamedParameter encontrado: " + np);
//            }
            if (fields != null && !fields.isEmpty()) {
                for (Map.Entry<String, Object> pair : fields.entrySet()) {
                    //  System.out.println("seteando valores de query con llave:"+(String) pair.getKey()+" y valor: "+(String) pair.getValue());
                    //query.setParameter(orden.get((String) pair.getKey()), pair.getValue());
                    query.setParameter(pair.getKey(), pair.getValue());
                }
            }
            T t = (T) query.uniqueResult();
            transaction.commit();
            return t;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //no se puede leer
                    .set("error", e.getMessage())
                    .set("metodo", "getUniqueByFields")
                    .set("sql", sql)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        } catch (Exception e) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("error", e.getMessage())
                    .set("metodo", "getUniqueByFields")
                    .set("sql", sql)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     * Obtiene un listado de objetos indicando los campos necesarios.
     *
     * @param fields HashMap String,Object indicado que campo debe ser igual a
     * "que cosa"
     * @return
     */
    public List<T> getListByFields(HashMap<String, Object> fields) {
        Transaction transaction = null;

        String sql = "";
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String clazz = model.getName();
            sql = "from " + clazz + " where ";
            if (fields != null && !fields.isEmpty()) {
                for (Map.Entry pair : fields.entrySet()) {
                    sql += " " + (String) pair.getKey() + " = :" + (String) pair.getKey() + " and";
                }
            }

            sql = sql.substring(0, sql.length() - 4);

            Query query = session.createQuery(sql);
            if (fields != null && !fields.isEmpty()) {
                for (Map.Entry pair : fields.entrySet()) {
                    query.setParameter((String) pair.getKey(), pair.getValue());
                }
            }
            return query.list();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //no se puede leer
                    .set("error", e.getMessage())
                    .set("metodo", "getUniqueByFields")
                    .set("sql", sql)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        } catch (Exception e) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("error", e.getMessage())
                    .set("metodo", "getUniqueByFields")
                    .set("sql", sql)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     *
     * @param maxResults Cantidad maxima de resultados (para paginacion), para
     * no usar limite, dejar en cero
     * @param firstResult Posicion del primer registro de la lista, usado para
     * paginacion, para no usar, dejar en cero
     * @param condition Conjunto de condiciones del tipo:
     * Restrictions.ge("campo", valor)
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> find(int maxResults, int firstResult, SimpleExpression... condition) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<T> lstObjs;
            Criteria criteria = session.createCriteria(model);
            for (SimpleExpression cond : condition) {
                criteria.add(cond);
            }
            if (maxResults > 0) {
                criteria.setMaxResults(maxResults);
            }
            if (firstResult > 0) {
                criteria.setFirstResult(firstResult);
            }
            lstObjs = criteria.list();
            return lstObjs;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR)
                    .set("metodo", "find")
                    .set("clase", this.getClass().getSimpleName())
                    .set("maxResults", maxResults)
                    .set("firsResult", firstResult)
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     * Metodo que retorna la cantidad de registros en base a un conjunto de
     * condiciones. Solo se debe usar para poca cantidad de registros.
     *
     * @param condition Conjunto de condiciones del tipo:
     * Restrictions.ge("campo", valor)
     * @return
     */
    public Number count(SimpleExpression... condition) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Number result;
            Criteria criteria = session.createCriteria(model);
            for (SimpleExpression cond : condition) {
                criteria.add(cond);
            }
            result = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
            return result;
        } catch (RuntimeException r) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR)
                    .set("metodo", "count")
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", r.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(r));
        }
    }

    /**
     *
     * @param query
     * @param parameters
     * @param TRACE
     * @param resultClass
     * @return
     */
    public List<T> execute(String query, HashMap<String, String> parameters, String TRACE, Class<?> resultClass) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String formattedSQL = new BasicFormatterImpl().format(query);
            LOG.debug(formattedSQL, TRACE);
            SQLQuery sql = session.createSQLQuery(query);
            if (resultClass != null) {
                sql.setResultTransformer(Transformers.aliasToBean(resultClass));
            }

            if (parameters != null && !parameters.isEmpty()) {
                Iterator it = parameters.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    sql.setParameter((String) pair.getKey(), pair.getValue());
                    it.remove();
                }
            }
            return sql.list();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Messages.Errores.DATABASE_ERROR) //no se puede leer
                    .set("metodo", "execute")
                    .set("query", query)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        } catch (Exception e) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("metodo", "execute")
                    .set("query", query)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    public int executeSaveOrUpdate(String query, List<Class<?>> classes, HashMap<String, String> parameters, String TRACE) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            LOG.debug(query, TRACE);
            SQLQuery sql = session.createSQLQuery(query);
            if (classes.size() > 0) {
                for (Class<?> clazz : classes) {
                    sql.addEntity(clazz.getClass());
                }
            }
            if (parameters != null && !parameters.isEmpty()) {
                Iterator it = parameters.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    sql.setParameter((String) pair.getKey(), pair.getValue());
                    it.remove();
                }
            }
            return sql.executeUpdate();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //no se puede leer
                    .set("metodo", "execute")
                    .set("query", query)
                    .set("clase", this.getClass().getSimpleName())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        } catch (Exception e) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("metodo", "execute")
                    .set("query", query)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     * Genera la sentencia SQL para el order by. Recibe una clase Model y un
     * string de entrada de API retornando "Tabla.Campo asc|desc". Si por
     * ejemplo se entrega el model Usuarios, y el argumento order es
     * "+nombreCompleto" se obtiene como resultado: "usuarios.nombre_completo
     * asc" (utilizando nombres de las tablas)
     *
     * @param modelObject
     * @param order
     * @return
     * @throws NoSuchFieldException
     */
    public String buildSqlOrderBy(Object modelObject, String... order) throws NoSuchFieldException {
        BaseModel modelLocal = (BaseModel) modelObject;
        StringBuilder sb = new StringBuilder();
        for (String o : order) {
            sb.append(modelLocal.getTableName())
                    .append(".")
                    .append(modelLocal.getColumnName(o.substring(1)))
                    .append(" ")
                    .append(o.charAt(0) == '+' ? "asc" : "desc");
            sb.append(", ");
        }
        if (sb.toString().length() > 2) {
            return sb.toString().substring(0, sb.toString().length() - 3);
        } else {
            return "";
        }
    }

    /**
     * Transforma un String SQL de una query normal a la misma query efectuando
     * un count(1). Elimina toda la seleccion de campos del select, cambiandola
     * por un count(1) y elimina la clausula order by, solo de la query externa.
     * Mantiene intactas las sentencias having, group by y limit ademas de no
     * interferir de ninguna forma en las subquerys.
     *
     * @param query
     * @return
     */
    public String getSqlCounterFromQuery(String query) {

        query = new StringBuilder(query).replace(0, query.indexOf(" from "), "select count(1)").toString();
        String limit = "";
        String groupby = "";
        String having = "";
        if (query.contains(" limit ")) {
            int start = query.lastIndexOf(" limit ");
            int end = query.length();
            limit = query.substring(query.lastIndexOf(" limit "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        if (query.contains(" having ")) {
            int start = query.lastIndexOf(" having ");
            int end = query.length();
            having = query.substring(query.lastIndexOf(" having "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        if (query.contains(" group by ")) {
            int start = query.lastIndexOf(" group by ");
            int end = query.length();
            groupby = query.substring(query.lastIndexOf(" group by "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        if (query.contains(" order by ")) {
            int start = query.lastIndexOf(" order by ");
            int end = query.length();
            String orderby = query.substring(query.lastIndexOf(" order by "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        return query + groupby + having + limit;
    }

    public long getCounterFromQuery(String query, String TRACE) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String counterQuery = getSqlCounterFromQuery(query);
            LOG.debug(counterQuery, TRACE);
            SQLQuery sql = session.createSQLQuery(counterQuery);
            return ((BigInteger) sql.uniqueResult()).longValue();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Errores.DATABASE_ERROR) //no se puede leer
                    .set("metodo", "getCounterFromQuery")
                    .set("query", query)
                    .set("clase", this.getClass().getSimpleName())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        } catch (Exception e) {
            throw new BaseException(Errores.UNKNOWN_ERROR)
                    .set("metodo", "getCounterFromQuery")
                    .set("query", query)
                    .set("clase", this.getClass().getSimpleName())
                    .set("exepcion", e.getMessage())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

    /**
     *
     * @param hql
     * @param parameters
     * @return
     */
//    public List<T> executeHQL(String hql, HashMap<String, Object> parameters) throws BaseException {
//        Transaction transaction = null;
//        try {
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            transaction = session.beginTransaction();
//            Query query = session.createQuery(hql);
//            if (parameters != null && !parameters.isEmpty()) {
//                parameters.entrySet().stream().forEach((pair) -> {
//                    query.setParameter(pair.getKey(), pair.getValue());
//                });
//            }
//            List<T> results = query.list();
//            transaction.commit();
//            return results;
//        } catch (RuntimeException e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            throw new BaseException(Errores.DATABASE_ERROR) //no se puede leer
//                    .set("metodo", "executeHQL")
//                    .set("query", hql)
//                    .set("clase", this.getClass().getSimpleName())
//                    .set("stacktrace", LOG.cleanStacktrace(e));
//        }
//    }

//    public Object executeUniqueHQL(String hql) throws BaseException {
//        return executeUniqueHQL(hql, null);
//    }

    /**
     *
     * @param hql
     * @param parameters
     * @return
     */
//    public Object executeUniqueHQL(String hql, HashMap<String, Object> parameters) throws BaseException {
//        Transaction transaction = null;
//        try {
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            transaction = session.beginTransaction();
//            Query query = session.createQuery(hql);
//            if (parameters != null && !parameters.isEmpty()) {
//                parameters.entrySet().stream().forEach((pair) -> {
//                    query.setParameter(pair.getKey(), pair.getValue());
//                });
//            }
//            Object obj = query.uniqueResult();
//            transaction.commit();
//            return obj;
//        } catch (RuntimeException e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            throw new BaseException(Errores.DATABASE_ERROR) //no se puede leer
//                    .set("metodo", "executeHQL")
//                    .set("query", hql)
//                    .set("clase", this.getClass().getSimpleName())
//                    .set("stacktrace", LOG.cleanStacktrace(e));
//        }
//    }

    
}
