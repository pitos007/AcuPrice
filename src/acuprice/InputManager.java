
package acuprice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class InputManager {
    List<String> headers = new ArrayList<>();
    List<String> paths = new ArrayList<>();
    List<String> priceList = new ArrayList<>();
    String inPathName;
    String outPathName;
    
    // 50001 [5, 13, 25, 37, 49, 61, 73] FJpriceList.csv
    Map<String, List<String>> codePricesMap = new LinkedHashMap<>();
    
    // 50001 [5]  pricexp.csv
    Map<String, String> pricePricesMap = new LinkedHashMap<>();
    
    // List of 50001 [5], 50001 [7], 50001 [2]
    List<Map<String, String>> mapList = new ArrayList<>();
    
    
    public InputManager(){
        this.inPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\IO_tests\\src\\DB_tests\\FJpriceList.csv";
        this.outPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\IO_tests\\src\\DB_tests\\";
        this.priceList.add("TRADEUK");
        this.priceList.add("TRADEEU");
        this.priceList.add("TRADESK");
        this.priceList.add("TRADEDK");
        this.priceList.add("TRADESF");
        this.priceList.add("TRADENK");
        this.priceList.add("TRADEZR");
    }
    /*
    * Loops through the first line of the file and saves each token
    * in a list of headers.
    */
    public void getHeaders(){
        File inFile = new File(inPathName);
        
        Scanner bs = null;
        
        try{
            Scanner ls = null;
            
            String currentLine;
            bs = new Scanner(new BufferedReader(new FileReader(inFile)));
            
            while (bs.hasNextLine()){
                currentLine = bs.nextLine();
                ls = new Scanner(currentLine);
                ls.useDelimiter(",");
                
                while (ls.hasNext()){
                    String ct = ls.next();
                    ct = ct.trim();
                    headers.add(ct);
                }
                break;
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    /*
    * Loops through all header-tokens and creates
    * paths "%path.PriceList.csv"
    */
    public void createFilesFromHeaders(){
        getHeaders();
        String path = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\IO_tests\\src\\DB_tests\\";
        for (int i = 1; i < headers.size(); i++){
            paths.add(path + headers.get(i));
        }
        for (String eachPath : this.paths){
            System.out.println(eachPath);
        }
        System.out.println("-----------------------");
    }
    
    public void printMap(Map<Object, Object> mapName){
        for(Object o : mapName.values()){
            String value = o.toString();
            System.out.println(value);
        }
    }
    
    
    /*
    * Loops through all tokens in the master price list
    * and creates map: Code key - Price list: 50001 [5, 13, 25, 37, 49, 61, 73]
    */
    public void createPriceMaps(){
        File inFile = new File(inPathName);
        Scanner bs = null;
        
        try{
            Scanner ls = null;
            
            String currentLine;
            bs = new Scanner(new BufferedReader(new FileReader(inFile)));
            
            while (bs.hasNextLine()){
                currentLine = bs.nextLine();
                List<String> currList = new ArrayList<>();
                ls = new Scanner(currentLine);
                ls.useDelimiter(",");
                while(ls.hasNext()){
                    String currToken = ls.next();
                    currList.add(currToken);
                }
                List<String> tempList = new ArrayList<>();
                for (int i = 1; i < currList.size(); i++){
                    String t = currList.get(i);
                    tempList.add(t);
                }
                codePricesMap.put(currList.get(0), tempList);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void printCodeList(){
        for(String eachCode : codePricesMap.keySet()){
            List<String> tempList = new ArrayList<>();
            tempList = codePricesMap.get(eachCode);
            System.out.println(eachCode + " " + tempList);
        }
        System.out.println();
    }
    
    /*
    * loops through each price file e.g TRADEUK
    * then loops through all tokens in the price file
    * and creates map: Code - price (in EXNRTL column): 50001 [5]
    * then puts each map in the list of maps
    */
    public void readUploadFile(){
        for (int i = 0; i < this.priceList.size()-1; i++) {
// get an index of specified priceN: code, desc, priceN1, priceN2
            String outPath = outPathName + this.priceList.get(i) + ".csv";
            Scanner bs = null;
            File outFile = new File(outPath);
            try {
                Scanner ls = null;
                String currentLine;
                
                bs = new Scanner(new BufferedReader(new FileReader(outFile)));
                
                while (bs.hasNextLine()) {
                    currentLine = bs.nextLine();
                    List<String> currList = new ArrayList<>();
                    ls = new Scanner(currentLine);
                    ls.useDelimiter(",");
                    while (ls.hasNext()) {
                        String currToken = ls.next();
                        currList.add(currToken);
                    }
                    List<String> tempList = new ArrayList<>();
                    for (int j = 0; j < currList.size(); j++) {
                        String t = currList.get(j);
                        tempList.add(t);
                    }
                    pricePricesMap.put(currList.get(1), tempList.get(9));
                }
                this.mapList.add(pricePricesMap);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    
    public void printPriceList(){
        for(String eachCode : pricePricesMap.keySet()){
            String val = pricePricesMap.get(eachCode);
            System.out.println(eachCode + " " + val);
        }
        System.out.println();
    }
    
    
    /*
    * get key "code" from codePriceMap
    * get price list name at index i e.g TRADEUK
    * find index of in the list where map has key e.g TRADEUK
    * create tempList
    * update the prices in each price list
    */
    public void updatePrice(){
        for (int i = 0; i < mapList.size()-1; i++){
            for (String eachCode : codePricesMap.keySet()){
                List<String> tempList = new ArrayList<>();
                tempList = codePricesMap.get(eachCode);
                Map<String, String> tempMap = new HashMap<>();
                tempMap = mapList.get(i);
                if (tempMap.containsKey(eachCode)){
                    String toRep = tempList.get(0);
                    String replacedV = tempMap.put(eachCode, toRep);
                    mapList.set(i, tempMap);
                    System.out.println("Value " + replacedV + " in TRADEUK, replaced with value " + toRep);
                }
            }
            System.out.println();
        }
        
    }
    
    /*
    * loops through all price list names
    */
    public void updateOutFile(){
        String outPath = outPathName + this.priceList.get(0) + ".csv";
        String outPathTemp = outPathName + this.priceList.get(0) + "_Temp.csv";
        File fileTemp = new File(outPathTemp);
        fileTemp.delete();
        Scanner bs = null;
        BufferedWriter bw = null;
        try {
            Scanner ls;
            String cl;
            fileTemp.createNewFile();
            bs = new Scanner(new BufferedReader(new FileReader(new File(outPath))));
            bw = new BufferedWriter(new FileWriter(outPathTemp));
            while (bs.hasNextLine()){
                cl = bs.nextLine();
                ls = new Scanner(cl);
                ls.useDelimiter(",");
                List<String> tokenList = new ArrayList<>();
                while (ls.hasNext()){
                    tokenList.add(ls.next());
                }
                int tokenCounter = 0;
                String code = tokenList.get(1);  // get a code from each line
                code = code.trim();
                System.out.print(code + " ");
                String priceToRep = null;
                // get a price for the current code
                if (pricePricesMap.containsKey(code)){
                    priceToRep = pricePricesMap.get(code);
                }
                tokenList.set(9, priceToRep);
                for (String eachString : tokenList){
                    bw.write(eachString);
                    bw.write(",");
                }
                bw.newLine();
            }
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        finally {
            try{
                bs.close();
                bw.close();
            }
            catch(Exception e){
                System.out.println("Problem with updateOutFile - " + e);
            }
        }
    }
}
