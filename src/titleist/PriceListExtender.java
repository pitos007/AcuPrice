/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author UPatryk
 */
public class PriceListExtender extends TFileManager {
    private Map<String, List<String>> priceListMap = new LinkedHashMap<>();

    public PriceListExtender() {
    }
    
    public void extendPriceList(){
       Scanner bs = null;
        try {
            bs = new Scanner(new BufferedReader(new FileReader(TFileManager.IN_PATH)));
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine();
                Scanner ls = new Scanner(lineStr);
                ls.useDelimiter(",");
                List<String> lineList = new ArrayList<>();
                while(ls.hasNext()){
                    lineList.add(ls.next());
                    if (lineList.get(0).equals("irn")) {
                        converToSet(lineList);
                    }
                    else if(lineList.get(0).equals("hdw")){
                        convertToEa(lineList);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    
    public void converToSet(List<String> lineList){
        for (int set = 6; set < 10; set++) {
            List<String> irnSetList = new ArrayList<>();
            irnSetList.add(lineList.get(0)); // irn
            String irnKeySet = lineList.get(1) + " " + set; 
            irnSetList.add(irnKeySet); // 716 AP1 6, 716 AP1 7
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = set * Double.parseDouble(lineList.get(i));
                irnSetList.add(String.valueOf(priceD)); // double*6, double*7
            }
            this.priceListMap.put(irnKeySet, irnSetList);
        }
    }
    
    public void convertToEa(List<String> lineList){
        List<String> pkToEaList = new ArrayList<>();
        pkToEaList.add(lineList.get(0));
        // Performance (DoZeN) to Performance (EA)
        if (Pattern.compile(Pattern.quote("Dozen"), Pattern.CASE_INSENSITIVE).matcher(lineList.get(1)).find()) {
            String keyStr = lineList.get(1).replaceAll("(?i)Dozen", "EA");
            pkToEaList.add(keyStr);
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = (Double.parseDouble(lineList.get(i))) / 12;
                pkToEaList.add(String.valueOf(priceD));
            }
        }
        // Performance (Dz) to Performance (EA)
        else if (Pattern.compile(Pattern.quote("dz"), Pattern.CASE_INSENSITIVE).matcher(lineList.get(1)).find()) {
            String keyStr = lineList.get(1).replaceAll("(?i)dz", "EA");
            pkToEaList.add(keyStr);
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = (Double.parseDouble(lineList.get(i))) / 12;
                pkToEaList.add(String.valueOf(priceD));
            }
        }
        // Performance (1/2 dz) to Performance (EA)
        else if (Pattern.compile(Pattern.quote("1/2 dz"), Pattern.CASE_INSENSITIVE).matcher(lineList.get(1)).find()) {
            String keyStr = lineList.get(1).replaceAll("(?i)1/2 dz", "EA");
            pkToEaList.add(keyStr);
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = (Double.parseDouble(lineList.get(i))) / 12;
                pkToEaList.add(String.valueOf(priceD));
            }
        }
        else if (Pattern.compile(Pattern.quote("1/2dz"), Pattern.CASE_INSENSITIVE).matcher(lineList.get(1)).find()) {
            String keyStr = lineList.get(1).replaceAll("(?i)1/2dz", "EA");
            pkToEaList.add(keyStr);
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = (Double.parseDouble(lineList.get(i))) / 12;
                pkToEaList.add(String.valueOf(priceD));
            }
        }
    }
    
            
            
}
