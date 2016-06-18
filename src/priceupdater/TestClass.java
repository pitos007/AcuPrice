/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priceupdater;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass{ 
  public static void main(String args[]){ 
      test(); 
  } 
  public static void test(){ 
     String dataInput = "The reset method clears all state information from " 
             + "the Matcher object it's called on. The Matcher is, in effect, " 
             + "reverted to the state it originally had when you first " 
             + "received a reference to it"; 
 
     Pattern p = Pattern.compile("(text|value|application)"); 
     Matcher m1 = p.matcher(dataInput); 
 
      while (m1.find()){ 
      System.out.println("\t\t" + m1.group()); 
     } 
      
     m1.reset(); 
     System.out.println("After resetting the Matcher"); 
     
 
     //this would not be possible without Matcher.reset(); 
     while (m1.find()){ 
      System.out.println("\t\t" + m1.group()); 
     } 
  } 
} 
