/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updatePrice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import xlsxtocsv.FileManager;
import xlsxtocsv.ExtractorManager;
/**
 *
 * @author UPatryk
 */
public class PriceReaderFile extends FileManager {
    List<Collection> priceListFileMap = new ArrayList<>();
    FileManager fm = new FileManager();
    ExtractorManager em = new ExtractorManager();
    List<String> headers;
    List<Map<String, List<String>>> priceFileList = new ArrayList<>();
    Map<String, List<String>> priceList = new LinkedHashMap<>();
    List<String> availPL = new ArrayList<>();
    String path = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\updatePrice\\";
            
    public PriceReaderFile(){
        this.headers = fm.getFileNames();
        this.priceList = em.generatePriceMap();
    }
    
    public void readPriceFile(){
        for (String prFile : headers) {
            File inFile = new File(path + prFile + ".csv");
            if(inFile.exists() && !inFile.isDirectory()){
                this.availPL.add(inFile.getName());
                //System.out.println("available files:");
                //System.out.println(inFile);
                Scanner fs = null;
                Map<String, List<String>> priceFileMap = new LinkedHashMap<>();
                try {
                    Scanner ls = null;
                    String currentLine;                   
                    fs = new Scanner(new BufferedReader(new FileReader(inFile)));
                    while(fs.hasNextLine()){
                        currentLine = fs.nextLine();
                        String strEmpty = currentLine.replaceAll(",,", ", ,");   //replace 12345,,54321 into 12345," ",54321
                        List<String> tmpList = new ArrayList<>();
                        ls = new Scanner(strEmpty);
                        ls.useDelimiter(",");
                        while(ls.hasNext()){
                            tmpList.add(ls.next());
                        }
                        String prodCode = null;
                        if (tmpList.get(1).length() > 6) {
                            prodCode = tmpList.get(1).substring(0, 5);
                        }
                        else{
                            prodCode = tmpList.get(1);
                        }
                        String price = tmpList.get(9);
                        String priceListName = tmpList.get(0);
                        if (isInPriceList(prodCode)) {
                            List<String> updatedTmpList = updatePrice(tmpList,prodCode,price,priceListName);
                            priceFileMap.put(prodCode, updatedTmpList);
                        }
                    }
                }
                catch (Exception e) {
                }
                this.priceFileList.add(priceFileMap);
            }
        }
        printAllPriceFiles();
    }
    
    public List<String> getListFromMap(String key){
        List<String> tempList = priceList.get(key);
        System.out.println("list from map: " + tempList);
        return tempList;
    }
    
    public boolean isInPriceList(String code){
        return priceList.containsKey(code);
    }
    
    public List<String> updatePrice(List<String> tempList, String code, String price, String priceListName){
        List<String> tmpl = getListFromMap(code);
        //System.out.println("list from map: " + tmpl);
        int priceIndex = getIndexHeaderPrice(priceListName);
        String newPrice = tmpl.get(priceIndex);
        tempList.set(9, newPrice);
        return tempList;
    }
    
    public int getIndexHeaderPrice(String prc){
        List<String> headers = this.em.getHeaders();
        int hindex = headers.lastIndexOf(prc);
        return hindex;
    }
    
    
    public void printAllPriceFiles(){
        for (Map<String, List<String>> eachMap : priceFileList) {
            List<String> tempList = new ArrayList();
            for(String code : eachMap.keySet()){
                tempList = eachMap.get(code);
                System.out.println(code + " " + tempList);
            }
        }
    }
    
   
   
}
