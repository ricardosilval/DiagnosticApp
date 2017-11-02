package cl.ciisa.ingenieria.diagnosticapp.handlers;

/**
 * Contenedor de mensajes de sistema
 *
 * @author ricardo
 */
public class Messages {

    /**
     * Mesajes de error generales, sin una clasificaci&oacute;n en particular
     */
    public enum Errores implements Message {

        /**
         * Error desconocido
         */
        UNKNOWN_ERROR(201, "Error desconocido"),
        /**
         * No se encontro el archivo especificado
         */
        FILE_NOT_FOUND(202, "No se encontr\u00F3 el archivo especificado"),
        /**
         * Uno o mas parametros son invalidos
         */
        INVALID_ARGUMENT(203, "Uno o mas par\u00E1metros son inv\u00E1lidos"),
        /**
         * No fue encontrado el objeto especificado
         */
        OBJECT_NOT_FOUND(204, "No fue encontrado el objeto especificado"),
        /**
         * No fue encontrado el objeto especificado
         */
        LOGIN_INCORRECT(206, "Usuario y/o contrase\u00F1a incorrectos"),
        /**
         * Token invalido
         */
        INVALID_TOKEN(207, "El Token utilizado esta caducado o no es v\u00E1lido"),
        /**
         * La clase especificada no ha sido encontrada
         */
        CLASS_NOT_FOUND(208, "La clase especificada no ha sido encontrada"),
        /**
         * Ha ocurrido un error al intentar leer o escribir un dato en la base
         * de datos
         */
        DATABASE_ERROR(209, "Ha ocurrido un error al intentar leer o escribir un dato en la base de datos"),
        /**
         * Usted no tiene los permisos necesarios para ejecutar esta acci\u00F3n
         */
        FORBIDDEN(210, "Usted no tiene los permisos necesarios para ejecutar esta acci\u00F3n"),
        /**
         * Su usuario se encuentra deshabilitado
         */
        FORBIDDEN_ACCESS(211, "Su usuario se encuentra deshabilitado"),
        /**
         * El campo indicado no existe
         */
        FIELD_NOT_EXIST(212, "El campo indicado no existe"),
        /**
         * La tarea fall\u00F3 al ejecutarse en todos sus reintentos
         */
        RETRY_FAILED(213, "La tarea fall\u00F3 al ejecutarse en todos sus reintentos"),
        /**
         * No existe la configuraci\u00F3n que intenta obtener
         */
        PARAMETER_NOT_EXIST(214, "No existe la configuraci\u00F3n que intenta obtener"),
        /**
         * No existe la configuraci\u00F3n que intenta obtener
         */
        CANT_INITIALIZE_PARSER(215, "Hubo un error al intentar inicializar el motor de mapeo"),
        /**
         * No existe la configuraci\u00F3n que intenta obtener
         */
        NOT_IMPLEMENTED_YET(216, "Esta funci\u00F3n a\u00FAn no se encuentra disponible"),
        /**
         * No existe la configuraci\u00F3n que intenta obtener
         */
        S3_GET_ERROR(217, "Error al intentar obtener el archivo"),
        /**
         * Imposible inicializar conexion a base de datos
         */
        CANT_INITIALIZE_DB_CONNECTION(218, "Imposible inicializar conexion a base de datos"),
        /**
         * Dte ya tiene asociado un adjunto
         */
        ATTACHMENT_EXIST(219, "El archivo que intenta subir ya existe"),
        RE_PASSWORD_ERROR(221, "Las contrase\u00F1as no coinciden, verifique informaci\u00F3n");

        private final int code;
        private final String message;
        private final String htmlMessage;

        private Errores(int code, String message, String htmlMessage) {
            this.code = code;
            this.message = message;
            this.htmlMessage = htmlMessage;
        }

        private Errores(int code, String message) {
            this.code = code;
            this.message = message;
            this.htmlMessage = null;
        }

        @Override
        public String toString() {
            return code + ": " + message;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String getHtmlMessage() {
            return (htmlMessage == null) ? message : htmlMessage;
        }
    }

    public enum Mensajes implements Message {

        /**
         * Todo Ok
         */
        OK(0, null),
        /**
         * Error desconocido
         */
        USER_ADDED(1001, "Usuario creado satisfactoriamente");

        private final int code;
        private final String message;
        private final String htmlMessage;

        private Mensajes(int code, String message, String htmlMessage) {
            this.code = code;
            this.message = message;
            this.htmlMessage = htmlMessage;
        }

        private Mensajes(int code, String message) {
            this.code = code;
            this.message = message;
            this.htmlMessage = null;
        }

        @Override
        public String toString() {
            return code + ": " + message;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String getHtmlMessage() {
            return (htmlMessage == null) ? message : htmlMessage;
        }
    }

}
