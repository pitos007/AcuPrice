/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import footjoy.DuplicateElementException;
import footjoy.ExtractorManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class MissingCodesChecker extends ExtractorManager {
    private Map<String, List<String>> priceListMap = new LinkedHashMap<>();
    private Map<String, List<String>> missingCodeMap = new LinkedHashMap<>();
    
    
    public void modifyTemplates() throws DuplicateElementException{
        Map<String, List<String>> tempPriceListMap = new LinkedHashMap<>();
        tempPriceListMap = generatePriceMap();
        for (String eachCode : tempPriceListMap.keySet()) {
            String fiveCharCode = eachCode.substring(0,4);
            List tempPriceList = new ArrayList<>(tempPriceListMap.values());
            priceListMap.put(fiveCharCode, tempPriceList);
        }
    }
    
    
    public void readAndCheckMissingCodes(){
        
    }
}
