/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priceupdater;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass{ 
  public static void main(String args[]){ 
      test(); 
  } 
  public static void test(){
      String currentLine = ",95390/91/92/88,FJ Lambswool Lined 1/2 Zip (Midweight),47.5,,66.5,630,500,630,73.2,0,65,55.25,,42.75,41.33,42.75,55.58,42.75,42.75,42.75,42.75";
      String emptyBeg = currentLine.replaceAll("^,", "null,");
      String emptyMid = emptyBeg.replaceAll(",,", ",null,");
      System.out.println(emptyMid);
      List<String> strList = new ArrayList<>();
 
  }
     
} 
