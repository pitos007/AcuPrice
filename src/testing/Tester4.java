/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author upatryk
 */
public class Tester4 {
    public static void main(String[] args) {
        List<String> tokenList = Arrays.asList("92166","SMTH PIQ MULBERY","FJPFSH","067300","8M","6105201000","00","EA","EA","1","EA","01/06/2017","0.25","4","C","27","FJAP","1");
        testSubstring(tokenList.get(5));
        delInsertInList(tokenList);
        
        printList(tokenList);
        
        System.out.println(tokenList.get(11).replaceAll("/", ""));
        
        System.out.println(effDateZero("01/02/2017"));
        
        System.out.println("--------");
        String doubleStr = "99.999";
        System.out.println(doubleStr.length());
        boolean res = doubleStr.length() < 6;
        System.out.println(res);
    }
    
    public static void testSubstring(String str){
        String s1 = str.substring(0, 7);
        String s2 = str.substring(8,10);
        
        System.out.println(s1 + ", " + s2);
    }
    
    public static void delInsertInList(List<String> list){
        List<String> listM = new ArrayList<>(list);
        System.out.println("\n" + listM);
        listM.set(5, "61052010");
        listM.add(6, "00");
        System.out.println(listM);
    }
    
    public static void printList(List<String> listStr){
        for (int i = 0; i < listStr.size(); i++) {
            System.out.println(i + " " + listStr.get(i));
        }
    }
    
    public static String effDateZero(String dateStr){
        String noZero = dateStr.substring(1);
        String noFSlash = noZero.replaceAll("/", "");
        String str1 = noFSlash.substring(0, 3);
        String str2 = noFSlash.substring(5, 7);
        return str1 + str2;
    }
}
