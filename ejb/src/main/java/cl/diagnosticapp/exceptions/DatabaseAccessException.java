/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.exceptions;


@SuppressWarnings("serial")
public class DatabaseAccessException extends Exception {

    public DatabaseAccessException() {
        super("Error accediendo a la base de datos");
    }
    
}
