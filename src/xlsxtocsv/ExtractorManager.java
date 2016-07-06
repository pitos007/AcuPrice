/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author UPatryk
 */
public class ExtractorManager extends Extractor {

    public ExtractorManager(){
    }
    
    // creates 12345, 12346, 12347 from 12345/6/7
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
    
    
    
    
    
    public Map<String, List<String>> generatePriceMap(){
        String inPath = getInPathName();
        System.out.println("getting inPath: " + inPath);
        Map<String, List<String>> tempPriceList = new LinkedHashMap<>();
        Map<String, List<String>> priceList = new LinkedHashMap<>();
        tempPriceList = codePricesMap(inPath); // [12345/6/7; 10, 11, 12]
        System.out.println("create price list map...");
        //printPriceListMap(tempPriceList);
        for (String eachCode : tempPriceList.keySet()) { //12345/6/7;
            List<String> tempCodeList = new ArrayList<>();
            tempCodeList  = stringToCodes(eachCode); // 12345, 12346, 12347
            //System.out.println(tempCodeList);
            for (String code : tempCodeList) { // 12345
                priceList.put(code, tempPriceList.get(eachCode)); //[12345; 10, 11, 12]
            }
        }
        //printPriceListMap(priceList);
        return priceList;
    }
    

    public void printPriceListMap(Map<String, List<String>> prListMap){
        List<String> tempList = new ArrayList();
        for(String codes : prListMap.keySet()){
            tempList = prListMap.get(codes);
            //System.out.println(codes + " " + tempList);
        }
        //System.out.println();
    }
}
