/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestClass extends Extractor {
    public static void main(String[] args) {
//        String str = "66241E/58E/70E/79E; 67860E/88E; 67901E/02E";
//        str = str.replaceAll("[A-Za-z]", "");
//        System.out.println(str);
        
        Map<String, List<String>> prListM = new LinkedHashMap<>();
        String p1 = "Patryk";
        String p2 = "Iwona";
        List<String> strList = new ArrayList<>();
        strList.add("gagdsgfa");
        strList.add("gtefsfse");
        prListM.put(p1,strList);
        prListM.put(p2,strList);
        
        List<String> tempList = new ArrayList<>();
        for(String codes : prListM.keySet()){
            tempList = prListM.get(codes);
            System.out.println(codes + " " + tempList);
        }
        
        System.out.println("remove key:");
        prListM.remove("Iwona");
        
        List<String> tempList2 = new ArrayList<>();
        for(String codes : prListM.keySet()){
            tempList2 = prListM.get(codes);
            System.out.println(codes + " " + tempList2);
        }
        
    }
}
