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
public class MissingRoundingException extends Exception {
    public MissingRoundingException(String message){
        super(message);
    }
    
    public MissingRoundingException(String message, Throwable throwable){
        super(message,throwable);
    }
}
