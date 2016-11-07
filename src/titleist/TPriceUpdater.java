/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import footjoy.MissingFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class TPriceUpdater extends TFileManager {
    PriceListExtender ple;
    TPriceMapReader tpmr;
    List<String> headersList;
    private List<Map<String, List<String>>> priceMapList = new ArrayList<>();
    private List<Map<String, List<String>>> priceMapChangesList = new ArrayList<>();
    
    public TPriceUpdater(){
        this.ple = new PriceListExtender();
        this.tpmr = new TPriceMapReader();
        this.headersList = getHeaders();
    }
    
    public void updatePriceList() throws MissingFileException{
        ple.extendPriceList();
        tpmr.readTPriceMap();
        for (String header : this.headersList) {
            Map<String, List<String>> changesMap = new LinkedHashMap<>();
            int priceIndex = getPriceFileIndex(header); // 0, 1, 2...
            File inFile = new File(TFileManager.FILE_CONT + header + ".csv");
            if (!inFile.exists()) {
                listMissingFiles();
                throw new MissingFileException("file does not exist");
            }else{
                Map<String, List<String>> priceFileMap = new LinkedHashMap<>();
                try {
                    String currentLine;
                    Scanner fs = new Scanner(new BufferedReader(new FileReader(inFile)));
                    while(fs.hasNextLine()){
                        currentLine = fs.nextLine();
                        String strEmpty = currentLine.replaceAll(",,", ", ,");   //replace 12345,,54321 into 12345," ",54321
                        List<String> lineList = new ArrayList<>();
                        Scanner ls = new Scanner(strEmpty);
                        ls.useDelimiter(",");
                        while(ls.hasNext()){
                            lineList.add(ls.next()); //[TRADEUK1, 92250-32, PERF SHORTS BLACK, 8Q, 67, PR, 10112, 311217, 1, 25,
                        }
                        String origCode = lineList.get(1); // 92250-32
                        String origPrice = lineList.get(9); // "25.00"
                        // uniqueCode must be unique in the Map
                        String uniqueCode = lineList.get(1) + lineList.get(8) + lineList.get(6);  // Code1Qty12Date16, 92250-3212010116
                        String keyStr = getKey(origCode); // "Perf shorts", "",
                        if (keyStr.length() > 0) {
                            String newPrice = ple.getPrice(keyStr, priceIndex); //"25.259"
                            double newPriceDbl = Double.parseDouble(newPrice); // 25.259
                            double newPriceDblRnd = roundDb(newPriceDbl,2); // 25.26
                            if (!(newPriceDblRnd == 0)) {
                                double origPriceDbl = Double.parseDouble(origPrice);
                                if (compareNewToOrig(newPriceDblRnd, origPriceDbl)) {
                                    lineList.set(9, newPrice);
                                    System.out.println(header + ", " + origCode + ", " + origPrice + " changed to " + newPrice);
                                    List<String> changesList = lineList; //this is needed to add an old and new price
                                    changesList.add(origPrice);
                                    changesMap.put(uniqueCode, changesList);
                                }
                            }
                        }
                        priceFileMap.put(uniqueCode, lineList);
                        //System.out.println(uniqueCode + ", " + lineList);
                    }
                }
                catch (FileNotFoundException e) {
                    System.err.println("Probelm with reading " + header + " file" + e);
                }
                this.priceMapList.add(priceFileMap);
                this.priceMapChangesList.add(changesMap);
            }
        }
    }
    
    public static double roundDb(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    

    public List<Map<String, List<String>>> getPriceMapList() {
        return priceMapList;
    }

    public void setPriceMapList(List<Map<String, List<String>>> priceMapList) {
        this.priceMapList = priceMapList;
    }

    public List<Map<String, List<String>>> getPriceMapChangesList() {
        return priceMapChangesList;
    }

    public void setPriceMapChangesList(List<Map<String, List<String>>> priceMapChangesList) {
        this.priceMapChangesList = priceMapChangesList;
    }
    
    
    public Map<String, List<String>> getPriceListMapExtend(){
        return ple.getPriceListMapExtend();
    }
    
    
    /**
     * returns a key (description) from the price map file, e.g Dri-Hood Towel
     * the key is later used to retrieve a price from the price file 
     * @param code
     * @return 
     */
    public String getKey(String code){
        String key = "";
        if (tpmr.containsCode(code)) {
            key = tpmr.getKey(code); // pap, >>Dri-Hood Towel<<, desc
        }
        return key;
    }
    
    public boolean compareNewToOrig(double d1, double d2){
        return Double.compare(d1, d2) != 0;
    }
    
    public int getPriceFileIndex(String priceFileName){
        return this.headersList.indexOf(priceFileName);
    }
    
    /**
     * returns price list names from the price list file e.g TRADEUK, TRADEEU, ...
     * @return 
     */
    public final List<String> getHeaders(){
        List<String> headerList = new ArrayList<>();
        File inFile = new File(TFileManager.T_PRICE_LST); // TpriceList.csv
        try {
            Scanner bs = new Scanner(new BufferedReader(new FileReader(inFile)));
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine();
                Scanner ls = new Scanner(lineStr);
                ls.useDelimiter(",");
                while(ls.hasNext()){
                    headerList.add(ls.next());
                }
                break;
            }
        } 
        catch (FileNotFoundException e) {
        }
        headerList.remove(0);
        headerList.remove(0);
        System.out.println("Available price lists: " + headerList);
        return headerList;
    }
    
    
    public void listMissingFiles(){
        List<String> fileNamesList = new ArrayList<>();
        getHeaders().stream().forEach((fileName) -> {
            File inFile = new File(TFileManager.FILE_CONT + fileName + ".csv");
            if (!inFile.exists()) {
                fileNamesList.add(fileName);
            }
        });
        String missingFiles = "";
        missingFiles = fileNamesList.stream()
                .map((String missingFile) -> ", " + missingFile)
                .reduce(missingFiles, String::concat);
        System.out.println("missing files: " + missingFiles);
    }

    
    
    
    
    
}
