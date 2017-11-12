/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.handlers.Messages;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.models.Credentials;
import cl.diagnosticapp.utils.HibernateUtil;
import cl.diagnosticapp.utils.LogicUtil;
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

    public enum LoginType {
        MAIL, USERNAME
    }

    public Usuario login(LoginType type, Credentials credentials) throws BaseException {
        System.out.println("ENTRO AL DAO");
        StringBuilder hqlBuilder = new StringBuilder("FROM Usuario WHERE ");
        if (type == LoginType.MAIL) {
            hqlBuilder.append("correo = :username AND ");
        } else {
            hqlBuilder.append("run = :username AND ");
        }
        hqlBuilder.append("clave = :clave ");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Usuario usuario = (Usuario) session.createQuery(hqlBuilder.toString())
                    .setParameter("username", credentials.getUsername())
                    .setParameter("clave", LogicUtil.getInstance().hashPassword(credentials.getPass()))
                    .uniqueResult();
            if (usuario != null) {
                Hibernate.initialize(usuario.getRol());

            }
            transaction.commit();
            return usuario;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                throw new BaseException(Messages.Errores.DATABASE_ERROR)
                        .set("metodo", "login")
                        .set("query", hqlBuilder.toString())
                        .set("clase", this.getClass().getSimpleName())
                        .set("stacktrace", LOG.cleanStacktrace(e));
            }
        }
        return null;

    }
}