/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author ricardo
 */
public class PortalUtil {

    private static final String SALT = "ZmVib3NzZWN1cml0eXNhbHQ=";
    public static final Double TASAIVA = 0.19;

    public static String md5(String str) {
        str = SALT + str;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Logger.getLogger(PortalUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static String UUID() {
        String uuid = UUID.randomUUID().toString();
        String year = new SimpleDateFormat("yyyy").format(new Date());
        uuid = uuid.replace('-', year.charAt(0));
        uuid = uuid.replace('-', year.charAt(1));
        uuid = uuid.replace('-', year.charAt(2));
        uuid = uuid.replace('-', year.charAt(3));
        return uuid;
    }

    public static String getTipoMedidaDesc(String tipoMedida) {
        String tipoMedidaDesc = "";
        if (tipoMedida != null) {
            switch (tipoMedida.toLowerCase()) {
                case "d":
                    tipoMedidaDesc = "Documental";
                    break;
                case "v":
                    tipoMedidaDesc = "Verificado";
                    break;
                default:
                    tipoMedidaDesc = "";
                    break;
            }
        }

        return tipoMedidaDesc;
    }

    public static String getUnidadMedidaDesc(String unidadMedida) {
        String unidadMedidaDesc = "";
        if (unidadMedida != null) {
            switch (unidadMedida) {
                case "1":
                    unidadMedidaDesc = "Kilo Neto";
                    break;
                case "2":
                    unidadMedidaDesc = "Kilo Bruto";
                    break;
                case "3":
                    unidadMedidaDesc = "Cartones";
                    break;
                case "4":
                    unidadMedidaDesc = "Toneladas";
                    break;
                case "5":
                    unidadMedidaDesc = "Litros";
                    break;
                case "6":
                    unidadMedidaDesc = "Documental";
                    break;
                default:
                    unidadMedidaDesc = "";
                    break;

            }
        }

        return unidadMedidaDesc;
    }

    public static String getUbicacionMercanciaDesc(String code, String especial) {
        String text = "";
        if (code != null) {
            switch (code.toUpperCase()) {
                case "E":
                    text = "ESPECIAL: " + especial != null ? especial : "";
                    break;
                case "A-01":
                    text = "ADUANA";
                    break;
                case "A-02":
                    text = "EMPRESA PORTUARIA DE VALPARA\u00cdSO";
                    break;
                case "A-03":
                    text = "PARTICULAR";
                    break;
                case "A-04":
                    text = "CORREOS";
                    break;
                case "A-09":
                    text = "DEPOCARGO S.A.(Santiago)";
                    break;
                case "A-10":
                    text = "AEROSAN S.A.(Santiago)";
                    break;
                case "A-11":
                    text = "FAST - AIR S.A.(Santiago)";
                    break;
                case "A-13":
                    text = "DEPOSITOS PORTUARIOS LIRQUEN S.A.";
                    break;
                case "A-19":
                    text = "S.A.A.M.EXTRAPORTUARIOS S.A. (Bar\u00f3n - Valpara\u00edso)";
                    break;
                case "A-23":
                    text = "COSAF COMERCIAL S.A.(EX MUELLES DE PENCO S.A.)";
                    break;
                case "A-24":
                    text = "SEAPORT S.A.";
                    break;
                case "A-25":
                    text = "EMPRESA PORTUARIA ARICA";
                    break;
                case "A-26":
                    text = "EMPRESA PORTUARIA IQUIQUE";
                    break;
                case "A-27":
                    text = "EMPRESA PORTUARIA ANTOFAGASTA";
                    break;
                case "A-28":
                    text = "EMPRESA PORTUARIA COQUIMBO";
                    break;
                case "A-29":
                    text = "PUERTO SAN ANTONIO";
                    break;
                case "A-30":
                    text = "EMPRESA PORTUARIA TALCAHUANO";
                    break;
                case "A-31":
                    text = "EMPRESA PORTUARIA PUERTO MONTT";
                    break;
                case "A-32":
                    text = "EMPRESA PORTUARIA CHACABUCO";
                    break;
                case "A-33":
                    text = "EMPRESA PORTUARIA AUSTRAL";
                    break;
                case "A-34":
                    text = "TERMINAL PACIFICO SUR VALPARAISO S.A.";
                    break;
                case "A-35":
                    text = "PUERTO PANUL S.A.(San Antonio)";
                    break;
                case "A-36":
                    text = "SAN ANTONIO TERMINAL INTERNACIONAL S.A.";
                    break;
                case "A-37":
                    text = "SAN VICENTE TERMINAL INTERNACIONAL S.A.";
                    break;
                case "A-38":
                    text = "DEPOSITO ADUANERO VENTANAS S.A.";
                    break;
                case "A-39":
                    text = "IQUIQUE TERMINAL INTERNACIONAL S.A.";
                    break;
                case "A-40":
                    text = "S.A.A.M.EXTRAPORTUARIOS S.A.(San Antonio)";
                    break;
                case "A-42":
                    text = "TRANS WARRANTS";
                    break;
                case "A-43":
                    text = "ANTOFAGASTA TERMINAL INTERNACIONAL";
                    break;
                case "A-44":
                    text = "SITRANS ALMACENES EXTRAPORTUARIOS LTDA. (Curauma)";
                    break;
                case "A-45":
                    text = "PUERTO COLUMBO S.A.(San Antonio)";
                    break;
                case "A-46":
                    text = "ALMACEN EXTRAPORTUARIO EL SAUCE S.A. (LOS ANDES)";
                    break;
                case "A-47":
                    text = "CORONEL DEPOSITOS S.A.";
                    break;
                case "A-48":
                    text = "TERQUIM S.A.(San Antonio)";
                    break;
                case "A-56":
                    text = "SITRANS ALMACENES EXTRAPORTUARIOS LTDA.(San Antonio)";
                    break;
                case "A-60":
                    text = "DEPOSITO CHACAYA LTDA(Puerto Mejillones)";
                    break;
                case "A-61":
                    text = "FAST AIR ALMACENES DE CARGA S.A.(Antofagasta)";
                    break;
                case "A-62":
                    text = "FAST AIR ALMACENES DE CARGA S.A.(Punta Arenas)";
                    break;
                case "A-63":
                    text = "CONSORCIO PORTUARIO ARICA S.A.";
                    break;
                case "A-64":
                    text = "ALMACEN EXTRAPORTUARIO HANSEN LTDA.";
                    break;
                case "A-65":
                    text = "PUERTO TERRESTRE LOS ANDES, SOC.CONCESIONARIA S.A.";
                    break;
                case "A-66":
                    text = "SOCIEDAD MAR\u00cdTIMA Y COMERCIAL SOMARCO LTYDA.";
                    break;
                case "A-67":
                    text = "DEPOCARGO S.A. (Iquique)";
                    break;
                case "A-68":
                    text = "SAAM EXTRAPORTUARIO S.A.(Placilla - Valpara\u00edso)";
                    break;
                case "A-69":
                    text = "TERMINAL EXTRAPORTUARIO VALPARAISO S.A.";
                    break;
                case "A-70":
                    text = "EL SAUCE S.A. (VALPARAISO)";
                    break;
                case "A-71":
                    text = "PUERTO CENTRAL S.A. (San Antonio)";
                    break;
                case "A-72":
                    text = "TERMINAL PUERTO COQUIMBO S.A.";
                    break;
                case "A-73":
                    text = "TERMINAL CERROS DE VALPARAISO S.A.";
                    break;
                case "A-74":
                    text = "CONTOPSA INLAND TERMINALS SPA";
                    break;
                case "A-76":
                    text = "PUERTO COLUMBO S.A. (Valpara\u00edso)";
                    break;
                default:
                    text = "";
                    break;
            }
        }

        return text;

    }

    public static Double tasaImpuestoAdicional(Integer codigo) {

        Double tasa;
        switch (codigo) {
            case 10:
                tasa = 0.0;
                break;
            case 23:
                tasa = 0.15;
                break;
            case 44:
                tasa = 0.15;
                break;
            case 24:
                tasa = 0.27;
                break;
            case 25:
                tasa = 0.15;
                break;
            case 26:
                tasa = 0.15;
                break;
            case 27:
                tasa = 0.13;
                break;
            case 28:
                tasa = 0.0;
                break;
            case 35:
                tasa = 0.0;
                break;
            default:
                tasa = 0.0;
                break;

        }
        return tasa;
    }

    public static String descripcionImpuestosAdicionales(Integer code) {
        String text = "";
        if(code != null){
            switch (code) {
            case 10:
                text = "No tiene";
                break;
            case 23:
                text = "Art. de oro, Joyas y Pieles finas (15%)";
                break;
            case 44:
                text = "Tapices, Casas rod., Caviar y Arm.de aire 15%";
                break;
            case 24:
                text = "Licores, Pisco, Destilados 27%";
                break;
            case 25:
                text = "Vinos, Chichas, Sidras 15%";
                break;
            case 26:
                text = "Cervezas y Otras bebidas alcoh\u00f3licas 15%";
                break;
            case 27:
                text = "Aguas minerales y Beb. analcoh\u00f3licas. 13%";
                break;
            case 28:
                text = "Impuesto Espec\u00edfico Diesel";
                break;
            case 35:
                text = "Impuesto Espec\u00edfico Gasolina";
                break;
            default:
                text = "No tiene";
                break;

        }
        }
        

        return text;
    }

    public static String descripcionEstadosPreDocumentos(Integer code) {
        String text = "";

        switch (code) {
            case 1:
                text = "Pre Factura";
                break;
            case 2:
                text = "Adjudicada";
                break;
            case 3:
                text = "Facturada";
                break;
            case 4:
                text = "Nula";
                break;
            case 5:
                text = "Sin postor";
                break;

            default:
                text = "";
                break;

        }
        return text;

    }

    public static String descripcionEstadosAdjudicaciones(Integer code) {
        String text = "";

        switch (code) {
            case 1:
                text = "Adjudicado";
                break;
            case 2:
                text = "Comprobante Emitido";
                break;
            case 3:
                text = "Facturado";
                break;
            case 4:
                text = "Anulado";
                break;
            case 5:
                text = "Eliminado";
                break;
            case 6:
                text = "Error";
                break;

            default:
                text = "";
                break;

        }

        return text;

    }

    public static String descripcionEstadosComprobante(Integer code) {
        String text = "";

        switch (code) {
            case 1:
                text = "Emitido";
                break;
            case 2:
                text = "Facturado";
                break;
            case 3:
                text = "Nulo";
                break;
            case 4:
                text = "Eliminado";
            default:
                text = "";
                break;

        }

        return text;

    }

    public static String descripcionEstadosDocumentos(Integer code) {
        String text = "";

        switch (code) {
            case 1:
                text = "Vigente";
                break;
            case 2:
                text = "Nula";
                break;

            default:
                text = "";
                break;

        }

        return text;

    }

    public static String descripcionEstadosSubastas(Integer code) {
        String text = "";

        switch (code) {
            case 1:
                text = "Planificada";
                break;
            case 2:
                text = "Registro de lotes";
                break;
            case 3:
                text = "Verificaci\u00f3n de lotes";
                break;
            case 4:
                text = "Correcci\u00f3n de lotes";
                break;
            case 5:
                text = "Catalogado";
                break;
            case 6:
                text = "En Ejecuci\u00f3n";
                break;
            case 7:
                text = "Finalizada";
                break;
            case 8:
                text = "Eliminada";
                break;
            default:
                text = "";
                break;

        }

        return text;

    }

    public static String encodeBase64(String data, String encoding) {
        if (data == null) {
            return null;
        }
        byte[] bytes = Base64.encodeBase64(data.getBytes(Charset.forName(encoding)));

        return new String(bytes);
    }

    public static String decodeBase64(String data) {
        if (data == null) {
            return null;
        }
        byte[] bytes = Base64.decodeBase64(data.getBytes(Charset.forName("ISO-8859-1")));
        return new String(bytes, Charset.forName("ISO-8859-1"));
    }

    public static Long redondeaMiles(Long number) {
        Long rest = number % 1000;
        if (rest >= 500) {
            number = number - rest + 1000;
        } else {
            number = number - rest;
        }
        return number;
    }

    public static Date stringToDate(String sDate, String sPatern) {
        if (sDate == null || sDate.equals("")) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(sPatern);
        Date date = null;
        try {
            date = sdf.parse(sDate);
        } catch (ParseException ex) {

        }
        return date;
    }

}
