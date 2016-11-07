/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author UPatryk
 */
public class Tester2 {
    public static void main(String[] args) {
        checkIfDupliate();
    }
    
    public static void checkIfDupliate(){
        List<String> l1 = Arrays.asList("one", "two", "three", "four", "two");
        List<String> l2 = Arrays.asList("one", "two", "three", "four","four");
        Map<String, List<String>> mapList = new LinkedHashMap<>();
        mapList.put("TRADEUK", l1);
        mapList.put("TRADEEU", l2);
        
        List<Map<String, List<String>>> listMap = new ArrayList<>();
        listMap.add(mapList);
        
        for (Map<String, List<String>> priceListName : listMap){
            for (String uniqueCode : priceListName.keySet()) {
                List<String> tokens = new ArrayList<>();
                tokens = priceListName.get(uniqueCode);
                for (String token : tokens) {
                    System.out.println(token);
                }
            }
        } 
        
    }
}
