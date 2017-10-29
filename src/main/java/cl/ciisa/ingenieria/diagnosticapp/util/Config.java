package cl.ciisa.ingenieria.diagnosticapp.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Configuracion del servidor. Obtiene la configuracion que tiene el servidor en
 * base al archivo de configuracion. Adicionalmente permite obtener informacion
 * propia del servidor, como su nombre o version de la aplicacion.
 *
 * @author Michel Munoz <michel@febos.cl>
 */
public class Config {

    private Properties version;

    private Config() {
        try {
            version = new Properties();
            version.load(Config.class.getResourceAsStream("/version.properties"));
        } catch (Exception e) {

        }
    }

    public static Config getInstance() {
        return ConfigHolder.INSTANCE;
    }

    public String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            return "limbo";
        }
    }

    private static class ConfigHolder {

        private static final Config INSTANCE = new Config();
    }

    private void loadConfig() {

    }

    private void saveConfig() {

    }

    public String get(String variable) {

        return null;
    }

    public String getOrDefault(String variable, String defaultValue) {

        return null;
    }

    public void set(String variable, String value) {

    }

    public String getCurrentVersion() {
        return version.getProperty("version", "desconocida");
    }

    public String getCurrentBranch() {
        return version.getProperty("branch", "desconocida");
    }

    public String getBuiltTime() {
        return version.getProperty("timestamp", "desconocida");
    }

}
