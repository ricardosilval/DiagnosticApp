package cl.ciisa.ingenieria.diagnosticapp.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * Utilitario para intercambiar encodigns
 * @author ricardo
 */
public class EncodingUtil {
    
    private EncodingUtil() {
    }
    
    public static EncodingUtil getInstance() {
        return EncodingUtilHolder.INSTANCE;
    }
    
    private static class EncodingUtilHolder {

        private static final EncodingUtil INSTANCE = new EncodingUtil();
    }
    public byte[] toEncoding(byte[] content,String encoding){
        try {
            String t=new String(content,getEncodig(content));
            return new String(t.getBytes()).getBytes(encoding);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EncodingUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getEncodig(byte[] content){
        try {
            byte[] buf = new byte[4096];
            InputStream fis = new ByteArrayInputStream(content);
            UniversalDetector detector = new UniversalDetector(null);
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            return  detector.getDetectedCharset();
        } catch (IOException ex) {
            return null;
        }
        
    }
}
