
package cl.diagnosticapp.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Configuracion del servidor. Obtiene la configuracion que tiene el servidor en
 * base al archivo de configuracion. Adicionalmente permite obtener informacion
 * propia del servidor, como su nombre o version de la aplicacion.
 *
 * 
 */
public class VersionConfig {

    private Properties version;

    private VersionConfig() {
        try {
            version = new Properties();
            version.load(VersionConfig.class.getResourceAsStream("/version.properties"));
        } catch (Exception e) {

        }
    }

    public static VersionConfig getInstance() {
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

        private static final VersionConfig INSTANCE = new VersionConfig();
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
