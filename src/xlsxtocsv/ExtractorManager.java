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

/**
 *
 * @author UPatryk
 */
public class ExtractorManager extends Extractor{

    public ExtractorManager(){
    }
    
    
    /**
     * creates 12345, 12346, 12347 from 12345/6/7
     * calls getTokens in Extractor using regular expression pattern
     * @param str String got from codePricesMap(inPath) 12345/6/7
     * @return ArrayList templates codes 12345, 12346, 12347
     */
    public List<String> stringToCodes(String str){
        List<String> groupTokens = getGroupTokens(str);
        List<String> groupTokensFull = new ArrayList<>();
        //System.out.println("groupTokens: " + groupTokens);
        for (String grToken : groupTokens) {
            List<String> tokenNum = getTokens(grToken);
            //System.out.println("tokenNum: " + tokenNum);
            List<String> templList = createCodes(tokenNum);
            //System.out.println("templList: " + templList);
            for (String eachToken : templList) {
                groupTokensFull.add(eachToken);
            }
        }
        //System.out.println(groupTokensFull);
        return groupTokensFull;
    }
    
    
    
    
    /**
     * calls codePriceMap(path) on Extractor class to get price map 12345/6/7 [10, 11, 12]
     * calls stringToCodes(map) to convert 12345/6/7 to 12345, 12346, 12347
     * @return LinkedHashMap 12345 [10, 11, 12]
     * @throws xlsxtocsv.DuplicateElementException
     */
    public Map<String, List<String>> generatePriceMap() throws DuplicateElementException{
        String inPath = getInPathName();
        System.out.println("getting inPath: " + inPath);
        Map<String, List<String>> tempPriceList = new LinkedHashMap<>();
        Map<String, List<String>> priceList = new LinkedHashMap<>();
        tempPriceList = codePricesMap(inPath); // 12345/6/7; 10, 11, 12
        System.out.println("create price list map: [12345; 5.70, 3.20, 4.50]...");
        //printPriceListMap(tempPriceList);
        for (String eachCode : tempPriceList.keySet()) { //12345/6/7;
            List<String> tempCodeList = new ArrayList<>();
            tempCodeList  = stringToCodes(eachCode); // 12345, 12346, 12347
            //System.out.println(tempCodeList);
            for (String code : tempCodeList) { // 12345
                if (priceList.containsKey(code)) {
                    throw new DuplicateElementException("code: " + code + " already exists");
                }else{
                    priceList.put(code, tempPriceList.get(eachCode)); // 12345 [10, 11, 12]
                }
            }
        }
        return priceList;
    }
    
    

    
}
