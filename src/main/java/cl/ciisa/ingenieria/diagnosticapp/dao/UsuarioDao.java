/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.dao;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages;
import cl.ciisa.ingenieria.diagnosticapp.model.Credenciales;
import cl.ciisa.ingenieria.diagnosticapp.model.Usuario;
import cl.ciisa.ingenieria.diagnosticapp.util.HibernateUtil;
import cl.ciisa.ingenieria.diagnosticapp.util.LogicUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ricardo
 */
public class UsuarioDao extends BaseDao<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public static UsuarioDao getInstance() {
        return UsuarioDaoHolder.INSTANCE;
    }

    private static class UsuarioDaoHolder {

        private static final UsuarioDao INSTANCE = new UsuarioDao();
    }

    public Usuario login(Credenciales credentials) throws BaseException {
        StringBuilder hqlBuilder = new StringBuilder("FROM Usuario WHERE ");

        hqlBuilder.append("mail = :username AND ");

        hqlBuilder.append("pass = :password ");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            LOG.info("BUSCAR USUARIO con credenciales: " + credentials.getClave() + " " + credentials.getNombreUsuario());
            Usuario usuario = (Usuario) session.createQuery(hqlBuilder.toString())
                    .setParameter("username", credentials.getNombreUsuario())
                    .setParameter("password", LogicUtil.getInstance().hashPassword(credentials.getClave()))
                    .uniqueResult();
            LOG.info("BUSCO USUARIO");
            if (usuario != null) {
                LOG.info("USUARIO EXISTE");
                Hibernate.initialize(usuario.getRol());
                LOG.info("INICIALIZANDO ROL");

            }
            transaction.commit();
            LOG.info("transaction.COMMIT Y RETURN USUARIO");
            return usuario;
        } catch (RuntimeException e) {
            LOG.error("LOGIN ERROR", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(Messages.Errores.DATABASE_ERROR)
                    .set("metodo", "login")
                    .set("query", hqlBuilder.toString())
                    .set("clase", this.getClass().getSimpleName())
                    .set("stacktrace", LOG.cleanStacktrace(e));
        }
    }

}
