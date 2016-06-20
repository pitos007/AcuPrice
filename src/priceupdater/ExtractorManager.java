/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priceupdater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class ExtractorManager extends Extractor {
    
    public ExtractorManager(){
        
    }
    
    public List<String> stringToCodes(String str){
        List<String> groupTokens = getGroupTokens(str);
        for (String grToken : groupTokens) {
            List<String> tokenNum = getTokens(grToken);
            List<String> templList = createCodes(tokenNum);
            for (String eachToken : templList) {
                groupTokens.add(eachToken);
            }
        }
        Collections.replaceAll(groupTokens, "", "cover");
        return groupTokens;
    }
    
    
    
    public Map<String, List<String>> generatePriceMap(){
        String inPath = getInPathName();
        System.out.println("getting inPath " + inPath);
        Map<String, List<String>> priceList = new LinkedHashMap<>();
        Map<String, List<String>> tempPriceList = new LinkedHashMap<>();
        tempPriceList = codePricesMap(inPath); // [12345/6/7; 10, 11, 12]
        System.out.println("printing tempPriceList:");
        printPriceListMap(tempPriceList);
        for (String eachCode : tempPriceList.keySet()) { //12345/6/7;
            List<String> tempCodeList = new ArrayList<>();
            tempCodeList  = stringToCodes(eachCode); // 12345, 12346, 12347
            Collections.replaceAll(tempCodeList, "", "cover");
            for (String code : tempCodeList) { // 12345
                priceList.put(code, tempPriceList.get(eachCode)); //[12345; 10, 11, 12]
            }
        }
        return priceList;
    }
}
