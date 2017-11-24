/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.requests;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ricardo
 */
public class ResetPassRequest extends PortalRequest {


    @NotNull
    private String password;
    @NotNull
    private String rePassword;

    public String getPassword() {
        return password;
    }

    public String getRePassword() {
        return rePassword;
    }


}
