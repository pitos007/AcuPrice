/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acuprice;

import java.util.ArrayList;
import java.util.List;
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
    // extract all numbers from the single String
    public List<String> getGroupTokens(){
        List<String> groupTokenList = new ArrayList<>();
        
        Pattern p1 = Pattern.compile("(\\d)+(\\/\\d+)*");
        Matcher m1 = p1.matcher(plStr1);
        
        while(m1.find()){
            groupTokenList.add(m1.group());
        }
        System.out.println(groupTokenList);
        return groupTokenList;
    }
    
    // extract numbers from the single group of numbers
    public List<String> getTokens(String group){
        List<String> tokenList = new ArrayList<>();
        
        Pattern p1 = Pattern.compile("\\d+");
        Matcher m1 = p1.matcher(group);
        while(m1.find()){
            tokenList.add(m1.group());
        }
        return tokenList;
    }
    
    // converts numbers into template codes
    public List<String> createCodes(List<String> numList){
        List<String> templateList = new ArrayList<>();
        templateList.add(numList.get(0));
        
        for (String str : templateList) {
            if(str.length()<5){
                String firstPart = numList.get(0).substring(0, numList.get(0).length()-str.length());
                String newToken = firstPart + str;
                templateList.add(newToken);
            }
        }
        System.out.println(templateList);
        return templateList;
    }
    
    
    
    
    public static void main(String[] args) {
        Extractor et = new Extractor();
        
    }
}
