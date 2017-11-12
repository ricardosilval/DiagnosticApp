package cl.diagnosticapp.controllers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Gestion de acciones a ejecutar cuando se deploya Diagnostic y cuando se detiene
 *
 */
public class StartHandler implements ServletContextListener {


    //public DiagnosticLogger LOG = new DiagnosticLogger(false);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        if (!checkIfSuperadminExists()) {
//            createSuperAdmin();
//        }
//        try {
//            //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
//            cfg.setDirectoryForTemplateLoading(new File("/vol/pdf_templates"));
//            cfg.setDefaultEncoding("UTF-8");
//            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//            cfg.setLogTemplateExceptions(false);
//        } catch (IOException ex) {
//            Logger.getLogger(StartHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

//    private boolean checkIfSuperadminExists() {
//
//        Session session = null;
//        Transaction transaction = null;
//        //LOG.info("Verificando si existe el usuario superadmin");
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            User admin = (User) UserDao.getInstance().getById("diagnosticappadmin");
//            transaction.commit();
//            return admin != null;
//        } catch (DiagnosticException e) {
//            if (transaction != null) {
//                transaction.rollback();
//                //LOG.warning("Ejecutando rollback");
//            }
//            //LOG.error("Codigo del Error: " + e.getError().getCode());
//            //LOG.error("Descripcion: " + e.getError().getMessage());
//            //LOG.error(e.getMessage(), e);
//        } finally {
//            if (session != null && session.isOpen()) {
//              //  LOG.debug("Cerrando sesion de BD");
//                session.close();
//            }
//        }
//        return false;
//    }
//    private void createSuperAdmin() {
//        //TODO: crear un usuario super admin
//        final String defaultMail = "sysadmin@iasolutions.cl";
//        final String defaultPassword = "iasolutions";
//
//        Session session = null;
//        Transaction transaction = null;
//        try {
//           // LOG.info("Creando usuario Super Administrador");
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            User admin = new User();
//            admin.setUserId("diagnosticappadmin");
//            admin.setNombre("Super");
//            
//            admin.setAllowWebLogin(true);
//            admin.setCreado(new Date());
//            admin.setMail(defaultMail);
//            admin.setPass(LogicUtil.getInstance().hashPassword(defaultPassword));
//            admin.setEstado(User.STATUS_ENABLED);
//            admin.setRut("1-9");
//            UserDao.getInstance().insert(admin);
//            transaction.commit();
//        } catch (DiagnosticException e) {
//            if (transaction != null) {
//                transaction.rollback();
//             //   LOG.warning("Ejecutando rollback");
//            }
//         //   LOG.error("Codigo del Error: " + e.getError().getCode());
//         //   LOG.error("Descripcion: " + e.getError().getMessage());
//        //   LOG.error(e.getMessage(), e);
//        } finally {
//            if (session != null && session.isOpen()) {
//         //       LOG.debug("Cerrando sesion de BD");
//                session.close();
//            }
//        }
//
//    }
}
