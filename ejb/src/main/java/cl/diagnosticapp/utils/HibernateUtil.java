package cl.diagnosticapp.utils;

import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages.Errores;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static synchronized void buildSessionFactory() throws BaseException {
        if (sessionFactory == null) {

            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

                sessionFactory.getStatistics().setStatisticsEnabled(true);
            } catch (Exception e) {
                StandardServiceRegistryBuilder.destroy(registry);
                BaseException error = new BaseException(Errores.CANT_INITIALIZE_DB_CONNECTION)
                        .set("error", e.getMessage())
                        .set("causa", ExceptionUtils.getRootCauseMessage(e));
                System.err.println(e.getMessage());
                System.err.println(ExceptionUtils.getRootCauseMessage(e));
                throw error;
            }

        }
    }

    public static void shutdown() throws BaseException {
        getSessionFactory().close();
    }

    private static void openSessionAndBindToThread() throws BaseException {
        Session session = getSessionFactory().openSession();//sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }

    public static synchronized SessionFactory getSessionFactory() throws BaseException {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    private static void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if (session != null) {
            session.close();
        }
    }

    public static void closeSessionFactory() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
    }

    public static void setParams(Query query, Map<String, Object> params) {
        params.entrySet().stream().forEach((pair) -> {
            if (pair.getValue() instanceof Collection) {
                query.setParameterList(pair.getKey(), (Collection) pair.getValue());
            } else {
                query.setParameter(pair.getKey(), pair.getValue());
            }
        });
    }

}
