/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footjoy;

/**
 *
 * @author UPatryk
 */
public class MissingFileException extends Exception {
    public MissingFileException(String message){
        super(message);
    }
    
    public MissingFileException(String message, Throwable throwable){
        super(message,throwable);
    }
}
