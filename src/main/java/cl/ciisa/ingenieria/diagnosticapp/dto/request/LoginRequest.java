package cl.ciisa.ingenieria.diagnosticapp.dto.request;

import cl.ciisa.ingenieria.diagnosticapp.services.internal.Validar;
import cl.ciisa.ingenieria.diagnosticapp.dto.BaseRequest;
import cl.ciisa.ingenieria.diagnosticapp.util.LogicUtil;
import cl.ciisa.ingenieria.diagnosticapp.util.ValidacionUtil;

/**
 *
 * @author ricardo
 */
public class LoginRequest extends BaseRequest {

    @Validar(puedeSerNull = false, formato = ValidacionUtil.Formato.CORREO)
    private String mail;
    @Validar(puedeSerNull = false)
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return LogicUtil.getInstance().hashPassword(this.password);
    }
}
