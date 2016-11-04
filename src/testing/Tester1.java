/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

/**
 *
 * @author UPatryk
 */
public class Tester1 {
    public static void main(String[] args) {
        
        System.out.println(compareNewToOrig());
    }
    
    public static boolean compareNewToOrig(){
        String d1str = "49.10";
        String d2str = "49";
        
        double d1 = Double.parseDouble(d1str);
        System.out.println("d1: " + d1);
        double d2 = Double.parseDouble(d2str);
        System.out.println("d2: " + d2);
        
//        double d3 = (d1) - (d2);
//        System.out.println("d3: " + d3);
//        System.out.println("d1 - 1.0: " + (d3 - 1.0));
//        
//        int drj = Double.compare(d1, d2);
//        System.out.println(drj);
//        return Math.abs(d3 - 1.0) <= 0.0001;
        
        return Double.compare(d1, d2) != 0;
        
        
        
    }
}
