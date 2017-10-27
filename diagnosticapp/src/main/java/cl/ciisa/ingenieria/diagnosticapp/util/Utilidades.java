package cl.ciisa.ingenieria.diagnosticapp.util;

/**
 *
 * @author ricardo
 */
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Hex;

public class Utilidades {

    //public final static Logger logger = Logger.getLogger(Main.class.getName());
    public static DateFormat fechaFormat = null;

    public static DateFormat fechaEstadoDte = null;

    public static DateFormat fechaMesFormat = null;

    public static DateFormat fechaHoraFormat = null;

    public static ResourceBundle verificationLabels = null;

    public static ResourceBundle netLabels = null;

    public static ResourceBundle exceptions = null;

   

    public static Date getDate(String date) throws ParseException {

        return fechaFormat.parse(date);

    }


    public static String getRutFormatted(String rut) {
        rut = rut.substring(0, rut.length() - 8) + "."
                + rut.substring(rut.length() - 8, rut.length() - 5) + "."
                + rut.substring(rut.length() - 5, rut.length());
        return rut;
    }

    public static String hashPassword(String password) {
        try {
            String securitySalt = "ZmVib3NzZWN1cml0eXNhbHQ=";
            password = securitySalt + password;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            byte[] hash = m.digest(password.getBytes("ISO-8859-1"));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
