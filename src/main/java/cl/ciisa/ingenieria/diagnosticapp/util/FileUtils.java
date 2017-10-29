package cl.ciisa.ingenieria.diagnosticapp.util;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 * Utilidades para manipular archivos
 *
 * @author Michel Munoz <michel@febos.cl>
 */
public class FileUtils {

    public static final int MAX_CSV_ROWS = 30000;

    /**
     * Lee el contenido de un archivo plano y lo deja en un string
     *
     * @param in Archivo de entrada
     * @return String con el contenido del archivo
     * @throws IOException
     * @author Michel M.
     */
    public static String fileToString(File in) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(in.getAbsolutePath()));
        return Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(encoded)).toString();
    }

    /**
     * Escribe un archivo en disco con el contenido del string
     *
     * @param in String a escribir en archivo
     * @param out Archivo resultante
     * @return true si el archivo fue correctamente escrito
     */
    public static boolean stringToFile(String in, File out) {
        try {
            FileWriter fileWriter = new FileWriter(out);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(in);
            try {
                writer.close();
                fileWriter.close();
            } catch (Exception ex) {
            }

            return out.exists();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @param in String a escribir en archivo
     * @param out Archivo resultante
     * @param charset El codigo de codificacion de caracteres<br/>
     * Ejemplo de charset:<br/>
     * "US-ASCII"<br/>
     * "ISO-8859-1"<br/>
     * "UTF-8"<br/>
     * "UTF-16BE"<br/>
     * "UTF-16LE"<br/>
     * "UTF-16"<br/>
     * @return true si el archivo fue correctamente escrito
     */
    public static boolean stringToFile(String in, File out, String charset) {
        try {
            FileWriter fileWriter = new FileWriter(out);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), charset));
            writer.write(in);
            try {
                writer.close();
                fileWriter.close();
            } catch (IOException ex) {
            }
            return out.exists();
        } catch (IOException e) {
            return false;
        }
    }

    public static String byteArrayToBase64(byte[] ar) {
        Base64 b = new Base64();
        return Arrays.toString(b.encode(ar));
    }

    public static String fileToBase64(File f) throws IOException {
        byte[] data = fileAsByteArray(f);
        byte[] encoded = Base64.encodeBase64(data);
        return new String(encoded);
    }

    public static byte[] fileAsByteArray(File f) throws IOException {
        InputStream is = new FileInputStream(f);
        byte[] toByteArray = IOUtils.toByteArray(is);
        try {
            is.close();
        } catch (Exception e) {
        }
        return toByteArray;
    }

    public static String getTimeStamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    public static File zip(File f) {
        int BUFFER = 2048;
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(f.getAbsolutePath() + ".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            //out.setMethod(ZipOutputStream.DEFLATED);
            byte data[] = new byte[BUFFER];
            // get a list of files from current directory

            System.out.println("Comprimiendo: " + f.getName());
            FileInputStream fi = new FileInputStream(f.getAbsolutePath());
            origin = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(f.getAbsolutePath());
            out.putNextEntry(entry);
            int count;
            while ((count = origin.read(data, 0,
                    BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            origin.close();
            out.close();
        } catch (Exception e) {
            return null;
        }
        return new File(f.getAbsolutePath() + ".zip");
    }

    public static HashMap<String, String> getMetadata(File file) {
        return null;
    }

    public static String getMetadataAttribute(File f, String attr) {
        try {
            Path file = FileSystems.getDefault().getPath(f.getAbsolutePath());
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
            ByteBuffer buf = ByteBuffer.allocate(view.size("Febos." + attr));
            view.read("Febos." + attr, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            return value;
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean putMetadata(String metadataName, String metadataValue, File f) {
        try {
            Path file = FileSystems.getDefault().getPath(f.getAbsolutePath());
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
            view.write("Febos." + metadataName, Charset.defaultCharset().encode(metadataValue));

            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean putMetadata(HashMap<String, String> metadata, File f) {
        try {
            Path file = FileSystems.getDefault().getPath(f.getAbsolutePath());
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
            Iterator it = metadata.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                view.write("Febos." + pair.getKey(), Charset.defaultCharset().encode((String) pair.getValue()));
                it.remove();
            }

            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean byteArrayToFile(byte[] data, String path, String filename) {
        try {
            OutputStream out = new FileOutputStream(path + filename);
            out.write(data);
            out.close();
            File file = new File(filename);
            file.deleteOnExit();
            return true;
        } catch (IOException ex) {
//Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

  

}
