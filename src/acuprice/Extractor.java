/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acuprice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author patry
 */
public class Extractor {
    String plStr1 = "92290/1/2/3; 92309 95890/1/2/3/45/46/8/81/82: 92235/36 : 91788/8 ; 92753/54";
    
    public Extractor(){
        
    }
    
    public void getGroupTokens(){
        Pattern p1 = Pattern.compile("(\\d)+(\\/\\d+)*");
        Matcher m1 = p1.matcher(plStr1);
        
        while(m1.find()){
            System.out.println(m1.group());
        }
    }
    
    public static void main(String[] args) {
        Extractor et = new Extractor();
        et.getGroupTokens();
    }
}
