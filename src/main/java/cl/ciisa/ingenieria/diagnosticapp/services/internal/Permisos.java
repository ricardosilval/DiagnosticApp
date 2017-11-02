/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.services.internal;

/**
 *
 * @author ricardo
 */
public class Permisos {
    public enum API implements Permiso {
        CREAR_USUARIO_EN_EMPRESA("U001", "Crear usuario","Permite crear un usuario y asociarlo a una empresa"),
        LISTAR_USUARIOS("U002", "Listar usuarios","Permite listar y buscar usuarios")
        
        ;
        private final String code;
        private final String name;
        private final String description;

        API(String code,String name,String description){
           this.code=code;
           this.name=name;
           this.description=description;
        }
        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getDescription() {
            return this.description;
        }
        
        @Override
        public String toString(){
            StringBuilder sb=new StringBuilder();
            return sb.append(this.code).append(": ").append(this.name).toString(); 
        }
    
    }
}
