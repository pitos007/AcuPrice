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
        List<String> tokenList = Arrays.asList("92166","SMTH PIQ MULBERY","FJPFSH","067300","8M","6105201000","EA","EA");
        testSubstring(tokenList.get(5));
        delInsertInList(tokenList);
    }
    
    public static void testSubstring(String str){
        String s1 = str.substring(0, 7);
        String s2 = str.substring(8,10);
        
        System.out.println(s1 + ", " + s2);
    }
    
    public static void delInsertInList(List<String> list){
        List<String> listM = new ArrayList<>(list);
        System.out.println(listM);
        listM.set(5, "61052010");
        listM.add(6, "00");
        System.out.println(listM);
    }
}
