/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

/**
 *
 * @author UPatryk
 */
public class FileModifiedException extends Exception {
    public FileModifiedException(String message){
        super(message);
    }
    
    public FileModifiedException(String message, Throwable throwable){
        super(message, throwable);
    }
}
