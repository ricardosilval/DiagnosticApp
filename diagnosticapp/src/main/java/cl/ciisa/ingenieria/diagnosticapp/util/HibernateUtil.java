package cl.ciisa.ingenieria.diagnosticapp.util;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 *
 * @author michel
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    /*private volatile static SessionFactory factory;
     static {
     try {
     // Create the SessionFactory from hibernate.cfg.xml
     Configuration config = new Configuration();
     config.configure();
     //ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
     StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
     //srBuilder.applySettings(config.getProperties());
     //ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
     factory = config.buildSessionFactory(serviceRegistry);

     } catch (Throwable ex) {
     // Make sure you log the exception, as it might be swallowed
     System.err.println("Initial SessionFactory creation failed." + ex);
     throw new ExceptionInInitializerError(ex);
     }
     }*/

    public static synchronized void buildSessionFactory() {
        if (sessionFactory == null) {
            //intento por cargar clases automaticamente
            /*Configuration config = new Configuration();
            // config.addAnnotatedClass(JenisPembayaran.class);
            Reflections reflections = new Reflections();
            Set<Class<? extends BaseModel>> allClasses = reflections.getSubTypesOf(BaseModel.class);
            Object[] clases = allClasses.toArray();
            System.out.println("Listando Objetos encontrados en hibernate!");
            for (Object p : clases) {
                System.out.println(p.getClass().getSimpleName());
            }
            config.configure("hibernate.cfg.xml");
            sessionFactory = config.buildSessionFactory();
           // new SchemaExport(config).create(true, true);
            */

            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

                sessionFactory.getStatistics().setStatisticsEnabled(true);
            } catch (Exception e) {
                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                // so destroy it manually.
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

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    private static void openSessionAndBindToThread() {
        Session session = getSessionFactory().openSession();//sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }

    public static synchronized SessionFactory getSessionFactory() {
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

}
