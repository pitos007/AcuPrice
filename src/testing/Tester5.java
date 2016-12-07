/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import ITMA.MFManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UPatryk
 */
public class Tester5 {
    public static void main(String[] args) {
        String token1 = "z";
        System.out.println(token1.equals("X") || token1.equals("x"));
        
        System.out.println(writeHeader());
    }
    
    public static String writeHeader(){
        String line = "";
        for (int i = 0; i < 39; i++) {
            line += i + ",";
        }
        return line;
    }
}
