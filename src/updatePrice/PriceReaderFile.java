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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import xlsxtocsv.FileManager;
import xlsxtocsv.ExtractorManager;
import xlsxtocsv.Printer;
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
    Printer pr = new PriceMapListPrinter();
    String path = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\updatePrice\\";
            
    public PriceReaderFile(){
        this.headers = fm.getFileNames();
        this.priceList = em.generatePriceMap(); //[12345; 10, 11, 12]
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
                        String prodCode = formatCode(tmpList.get(1));
                        if (isInPriceList(prodCode)) {
                            List<String> updatedTmpList = updatePrice(tmpList, prodCode);
                            tmpList = updatedTmpList;
                            priceFileMap.put(tmpList.get(1), updatedTmpList);
                        }
                    }
                }
                catch (Exception e) {
                }
                this.priceFileList.add(priceFileMap);
            }
        }
        pr.printAllPriceFiles(priceFileList);
    }
    
    // converts 12345P, 12346S to 12345P, 12346
    public String formatCode(String cd){
        String ncd = null;
        if (cd.length()>5) {
            ncd = cd.substring(0, 6);
        }
        else{
            ncd = cd;
        }
        Pattern p1 = Pattern.compile("(?i)[qadhrbpe]");
        Pattern p2 = Pattern.compile("(?i)(e-)");
        Matcher m1 = p1.matcher(ncd);
        if (m1.find()) {
            m1.reset();
            m1 = p2.matcher(ncd);
            if(m1.find()){
                return ncd.substring(0, 4);
            }
            else{
                return ncd;
            }
        }
        else{
            return ncd.substring(0, 5);
        }
    }
    
    
    public boolean isInPriceList(String code){
        return priceList.containsKey(code);
    }
    
    public List<String> updatePrice(List<String> tempList, String prodCode){
        System.out.println("original list: " + tempList);
        System.out.println("search key: " + prodCode);
        List<String> mfMapKeyList = getListFromMapKey(prodCode); //[95890/1/2/3/4, Womens FJ Lambswool, 34, 44.2, 47.4, 43.0]
        System.out.println("found in: " + mfMapKeyList);
        
        String priceListName = tempList.get(0);
        System.out.println(priceListName);
        int priceIndex = getIndexHeaderPrice(priceListName);
        
        String newPrice = mfMapKeyList.get(priceIndex - 1); // 1st column newCode is excluded from the PriceList, but headers contain the newCode
        System.out.println("new price in " + priceListName + " is " + newPrice);
        
        tempList.set(8, newPrice);
        System.out.println("updated list: " + tempList);
        return tempList;
    }
    
    public List<String> getListFromMapKey(String key){
        List<String> tempList = priceList.get(key);
        //System.out.println(tempList);
        //System.out.println("list from map: " + tempList);
        return tempList;
    }
    
    public int getIndexHeaderPrice(String prc){
        List<String> headers = this.em.getHeaders();
        int hindex = headers.lastIndexOf(prc);
        //System.out.println(prc + " is at " + hindex);
        return hindex;
    }
    
    
    
}
