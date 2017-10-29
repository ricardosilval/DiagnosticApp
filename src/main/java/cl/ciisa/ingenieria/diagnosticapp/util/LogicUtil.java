package cl.ciisa.ingenieria.diagnosticapp.util;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages.Errores;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import javax.ws.rs.core.MediaType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * Utilidades de logica en general.
 * @author ricardo
 */
public class LogicUtil {

    private final String salt = "ZmVib3NzZWN1cml0eXNhbHQ=";
    public static final int TIME_TYPE_SECONDS = 1;
    public static final int TIME_TYPE_MINUTES = 2;
    public static final int TIME_TYPE_HOURS = 3;
    public static final int TIME_TYPE_DAYS = 4;
    public static final int TIME_TYPE_WEEKS = 5;
    public static final int TIME_TYPE_MONTHS = 6;
    public static final int TIME_TYPE_YEARS = 7;
    

    private LogicUtil() {
    }

    public static LogicUtil getInstance() {
        return LogicUtilHolder.INSTANCE;
    }

    public String getMediaType(String type) {
        return type.equalsIgnoreCase("json")?MediaType.APPLICATION_JSON:MediaType.APPLICATION_XML;
    }

    private static class LogicUtilHolder {

        private static final LogicUtil INSTANCE = new LogicUtil();
    }

    public String hashPassword(String password) {
         try {
            password = salt + password;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            byte[] hash = m.digest(password.getBytes("ISO-8859-1"));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            
        }
        return null;
    }

    public String hash(String str) {
        try {
            str = (str == null || str.isEmpty()) ? " " : str;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public Date addToDate(Date fecha, int cantidad, int time_type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        switch (time_type) {
            case TIME_TYPE_SECONDS:
                cal.add(Calendar.SECOND, cantidad);
                break;
            case TIME_TYPE_MINUTES:
                cal.add(Calendar.MINUTE, cantidad);
                break;
            case TIME_TYPE_HOURS:
                cal.add(Calendar.HOUR, cantidad);
                break;
            case TIME_TYPE_DAYS:
                cal.add(Calendar.DATE, cantidad);
                break;
            case TIME_TYPE_WEEKS:
                cal.add(Calendar.DATE, cantidad*7);
                break;
            case TIME_TYPE_MONTHS:
                cal.add(Calendar.MONTH, cantidad);
                break;
            case TIME_TYPE_YEARS:
                cal.add(Calendar.YEAR, cantidad);
                break;
            default:
                throw new BaseException(Errores.INVALID_ARGUMENT)
                        .set("cantidad", cantidad)
                        .set("time_type",time_type);
        }
        return cal.getTime();
    }
    
    public String UUID(){
        String uuid=UUID.randomUUID().toString();
        String year=new SimpleDateFormat("yyyy").format(new Date());
        uuid=uuid.replace('-', year.charAt(0));
        uuid=uuid.replace('-', year.charAt(1));
        uuid=uuid.replace('-', year.charAt(2));
        uuid=uuid.replace('-', year.charAt(3));
        return uuid;
    }
    
    public String base64Encode(String content,String charset){
        if(content==null)return "";
        try {
            return new String(Base64.encodeBase64(content.getBytes(charset==null?"ISO-8859-1":charset)));
        } catch (UnsupportedEncodingException ex) {
            return base64Encode(content,null);
        }
    }
    
    public String base64Decode(String content,String charset){
        if(content==null)return "";
        try {
            return new String(Base64.decodeBase64(content.getBytes(charset==null?"ISO-8859-1":charset)));
        } catch (UnsupportedEncodingException ex) {
            return base64Encode(content,null);
        }
    }
    
    
}
