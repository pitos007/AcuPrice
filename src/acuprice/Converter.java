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
public class Converter {
    
    public Converter(){
        
    }
    
    String plStr1 = "92290/1/2/3; 92309 95890/1/2/3/45/46/8/81/82: 92235/36 : 91788/8 ; 92753/54";
    String plStr2 = "92135/36/37/38 ; 92538/39 / 95097/98/5/6 92144/45/37";
    String dataInput = "The reset method clears all state information from " 
             + "the Matcher object it's called on. The Matcher is, in effect, " 
             + "reverted to the state it originally had when you first " 
             + "received a reference to it"; 
    
    public void extractLongTokens(){
        Pattern p1 = Pattern.compile("(?:\\d\\/*)+");
        Pattern p2 = Pattern.compile("(\\d\\/*)+");
        Matcher m1 = p1.matcher(plStr1);
        Matcher m2 = p2.matcher(plStr1);
        
         Pattern p3 = Pattern.compile("(text|value|application)"); 
         Matcher m3 = p3.matcher(dataInput); 
        
        while(m1.find()){
            //System.out.println(m1.group());
            //System.out.println(m2.group());
            System.out.println("\t\t" + m3.group());
        }
    }
    
    
}
