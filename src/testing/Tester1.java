/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author UPatryk
 */
public class Tester1 {
    public static void main(String[] args) {
        
//        System.out.println(compareNewToOrig());
//        System.out.println(isZero("-0.01"));
//        System.out.println(isError("#REF!"));

//          System.out.println(roundDb(1.123,2));


    }
    
    public static boolean isZero(String str){
        double dbl = Double.parseDouble(str);
        return !(dbl == 0);
    }
    
    public static boolean isError(String str){
        return str.substring(0, 1).equals("#");
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
    
    public static double roundDouble(Number num, int decPl){
        String decFormat = "#.";
        for (int i = 0; i < decPl; i++) {
            decFormat = decFormat + "#";
        }
        System.out.println(decFormat);
        DecimalFormat df = new DecimalFormat(decFormat);
        df.setRoundingMode(RoundingMode.CEILING);
        String doubleStr = df.format(num.doubleValue());
        return Double.parseDouble(doubleStr);
    }
    
    public static double roundDb(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    
}
