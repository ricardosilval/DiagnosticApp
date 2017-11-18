/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.handlers.BaseException;
import cl.diagnosticapp.model.Token;
import cl.diagnosticapp.model.Usuario;
import cl.diagnosticapp.utils.LogicUtil;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.NoResultException;

/**
 *
 * @author ricardo
 */
public class TokenDao extends BaseDao<Token> {

    public TokenDao() {
        super(Token.class);
    }

    public static TokenDao getInstance() {
        return TokenDaoHolder.INSTANCE;
    }

    private static class TokenDaoHolder {

        private static final TokenDao INSTANCE = new TokenDao();
    }

    public Token issueToken(Usuario user) throws BaseException {
        Token token = new Token();
        token.setTipo(Token.TYPE_VOLATILE);
        token.setCreado(new Date());
        token.setExpira(LogicUtil.getInstance().addToDate(token.getCreado(), 30, LogicUtil.TIME_TYPE_MINUTES));
        token.setUsuario(user);
        token.setId(LogicUtil.getInstance().UUID());
        TokenDao.getInstance().insert(token);
        return token;
    }

    public boolean isValid(String token_id) throws BaseException {
        Token token = TokenDao.getInstance().getById(token_id);
        return token != null && token.getExpira().compareTo(new Date()) > 0;
    }
 public void updateExpirationTime(String token_id) throws BaseException {
        Token token = TokenDao.getInstance().getById(token_id);
        if (token != null) {
            token.setExpira(LogicUtil.getInstance().addToDate(new Date(), 60, LogicUtil.TIME_TYPE_MINUTES));
            TokenDao.getInstance().update(token);
        }
    }

    public Token find(String id, boolean fetch) throws BaseException {
        try {
            if (fetch) {
                throw new UnsupportedOperationException();
            } else {
                HashMap<String, Object> search = new HashMap<>();
                search.put("id", id);
                Token tokenFound = TokenDao.getInstance().getUniqueByFields(search);
                return tokenFound;
            }
        } catch (NoResultException e) {
            System.out.println("ERROR" + e.getMessage());
            return null;
        }
    }

}
