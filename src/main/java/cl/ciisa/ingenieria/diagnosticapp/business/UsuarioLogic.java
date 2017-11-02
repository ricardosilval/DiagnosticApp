/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.business;

import cl.ciisa.ingenieria.diagnosticapp.dao.UsuarioDao;
import cl.ciisa.ingenieria.diagnosticapp.dto.request.LoginRequest;
import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.model.Credenciales;
import cl.ciisa.ingenieria.diagnosticapp.model.Usuario;

/**
 *
 * @author ricardo
 */
public class UsuarioLogic {

    public Usuario login(LoginRequest request) {

        try {
            Credenciales credentials = new Credenciales();

            credentials.setNombreUsuario(request.getMail());

            credentials.setClave(request.getPassword());

            return UsuarioDao.getInstance().login(credentials);
        } catch (Exception e) {

        }

        return null;
    }

}
