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
 * @author UPatryk
 */
public class Tester3 {
    public static void main(String[] args) {
        List<String> existPric = Arrays.asList("zero", "one", "two", "three");
        List<String> newPric = Arrays.asList("one1", "twoNh", "three", "five");
        
        checkPriceMapDifferences(existPric, newPric);
    }
    
    public static void checkPriceMapDifferences(List<String> existPric, List<String> newPric){
        List<String> deletedInExist = new ArrayList<>();
        List<String> addedInNew = new ArrayList<>();
        for (String key : existPric) {
            if (!newPric.contains(key)) {
                deletedInExist.add(key);
            }
        }
        for (String key2 : newPric) {
            if (!existPric.contains(key2)) {
                addedInNew.add(key2);
            }
        }
        System.out.println("Codes deleted or modified: " + deletedInExist);
        System.out.println("Codes added: " + addedInNew);
    }
}
