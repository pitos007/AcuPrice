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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass extends Extractor {
    public static void main(String[] args) {
        List<String> strL = new ArrayList<>();
        strL.add("One");
        strL.add("Two");
        strL.add("Three");
        strL.add("Four");
        updatePrice(strL);
        System.out.println(strL);
}
    
    public static List<String> updatePrice(List<String> list){
        list.set(0, "Zero");
        
        return list;
        
    }


}
