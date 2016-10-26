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
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *
 * @author UPatryk
 */
public class PriceListExtender extends TFileManager implements Printer {
    private Map<String, List<String>> priceListMap = new LinkedHashMap<>();

    public PriceListExtender() {
    }
    
    public void extendPriceList(){
       Scanner bs = null;
        try {
            bs = new Scanner(new BufferedReader(new FileReader(new File(TFileManager.IN_PATH))));
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine(); // bll,Pro V1 ,37,28.5,29.6,26,28.3,24.34
                Scanner ls = new Scanner(lineStr);
                ls.useDelimiter(",");
                List<String> lineList = new ArrayList<>();
                while(ls.hasNext()){
                    lineList.add(ls.next()); // [irn,716 AP1,75,54,57,49,52,46.91,975]
                }
                if (lineList.get(0).equals("irn")) {
                        converToSet(lineList);  // 716 AP1 6, [irn,716 AP1,75,54,57,49,52,46.91,975]
                    }
                else if(lineList.get(0).equals("hdw")){
                    convertToEa(lineList);
                }
                this.priceListMap.put(lineList.get(1), lineList);
            }
            printPriceListMap();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void converToSet(List<String> lineList){
        for (int set = 6; set < 10; set++) {
            List<String> irnSetList = new ArrayList<>();
            irnSetList.add(lineList.get(0)); // irn
            String irnKeySet = lineList.get(1) + " " + set; 
            irnSetList.add(irnKeySet); // 716 AP1 6, ... 716 AP1 9
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = set * Double.parseDouble(lineList.get(i));
                //double roundD = round(priceD, 2);
                irnSetList.add(String.valueOf(priceD)); // double*6, double*7 ...
            }
            this.priceListMap.put(irnKeySet, irnSetList);
        }
    }
    
    public void convertToEa(List<String> lineList){
        List<String> pkToEaList = new ArrayList<>();
        pkToEaList.add(lineList.get(0)); // hdw
        // Performance (DoZeN) to Performance (EA)
        if (Pattern.compile(Pattern.quote("Dozen"), Pattern.CASE_INSENSITIVE).matcher(lineList.get(1)).find()) {
            String keyStr = lineList.get(1).replaceAll("(?i)Dozen", "EA");  // Tour Performance (EA)
            pkToEaList.add(keyStr);
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = (Double.parseDouble(lineList.get(i))) / 12;
                pkToEaList.add(String.valueOf(priceD));
            }
        }
        // Performance (Dz) to Performance (EA)
        else if (Pattern.compile(Pattern.quote("(?<=[^/])\\w\\w"), Pattern.CASE_INSENSITIVE).matcher(lineList.get(1)).find()) {
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
                double priceD = (Double.parseDouble(lineList.get(i))) / 6;
                pkToEaList.add(String.valueOf(priceD));
            }
        }
        else if (Pattern.compile(Pattern.quote("1/2dz"), Pattern.CASE_INSENSITIVE).matcher(lineList.get(1)).find()) {
            String keyStr = lineList.get(1).replaceAll("(?i)1/2dz", "EA");
            pkToEaList.add(keyStr);
            for (int i = 2; i < lineList.size(); i++) {
                double priceD = (Double.parseDouble(lineList.get(i))) / 6;
                pkToEaList.add(String.valueOf(priceD));
            }
        }
        this.priceListMap.put(pkToEaList.get(1), pkToEaList);
    }
    
    
    @Override
    public void printPriceListMap() {
        List<String> tempList = new ArrayList();
        for(String codes : this.priceListMap.keySet()){
            tempList = this.priceListMap.get(codes);
            System.out.println(codes + " " + tempList);
        }
        System.out.println();
    }
    


    @Override
    public void printPriceList(List<String> prListMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAllPriceFiles(List<Map<String, List<String>>> priceFileList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPriceListMap(Map<String, List<String>> prLm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
            
            
}
