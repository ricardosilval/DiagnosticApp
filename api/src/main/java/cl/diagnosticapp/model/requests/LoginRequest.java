/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.requests;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class LoginRequest extends PortalRequest {

    private String mail;
    private String rut;
    @NotNull
    private String password;

    public String getRut() {
        return rut;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

}
