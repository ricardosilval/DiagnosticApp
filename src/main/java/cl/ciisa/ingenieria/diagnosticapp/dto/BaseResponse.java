package cl.ciisa.ingenieria.diagnosticapp.dto;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Message;
import java.util.ArrayList;

/**
 * Model generico para clases de Response en la API. Contiene metodos de uso
 * comun entre los modelos
 *
 * @author ricardo
 */
public class BaseResponse {

    public String version;
    public String branch;
    public String built;
    public String server;
    public String trackid;
    public int code;
    public String message;
    public ArrayList<String> extra;

    public BaseResponse() {

    }

    public BaseResponse(Message message) {
        this.code = message.getCode();
        this.message = message.toString();
    }

    public BaseResponse(BaseException y) {
        this.code = y.getError().getCode();
        this.message = y.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getExtra() {
        return extra;
    }

    public void setExtra(ArrayList<String> extra) {
        this.extra = extra;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBuilt() {
        return built;
    }

    public void setBuilt(String built) {
        this.built = built;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

}
