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
    private List<Collection> priceListFileMap = new ArrayList<>();
    private FileManager fm = new FileManager();
    private ExtractorManager em = new ExtractorManager();
    private List<String> headers;
    private List<Map<String, List<String>>> priceFileList = new ArrayList<>();
    private Map<String, List<String>> priceList = new LinkedHashMap<>();
    private Map<String, List<String>> priceChanges = new LinkedHashMap<>();
    private List<String> availPL = new ArrayList<>();
    private Printer pr = new PriceMapListPrinter();
    private String path = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\updatePrice\\";
            
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
                        String origCode = tmpList.get(1);
                        String templCode = formatCode(tmpList.get(1));
                        String origPrice = tmpList.get(9);
                        //System.out.println(templCode);
                        if (isInPriceList(templCode)) {
                            List<String> updatedTmpList = updatePrice(tmpList, templCode, origPrice, origCode);
                            
                            //tmpList = updatedTmpList;
                            priceFileMap.put(tmpList.get(1), updatedTmpList);
                        }
                    }
                }
                catch (Exception e) {
                    System.err.println("Probelm with reading " + prFile + " file");
                }
                this.priceFileList.add(priceFileMap);
            }
        }
        pr.printAllPriceFiles(priceFileList);
    }
    
    // converts 12345P, 12346S to 12345P, 12346
   public static String formatCode(String cd){
        String ncd = null;
        if (cd.length()>5) {
            ncd = cd.substring(0, 6);
        }
        else{
            ncd = cd;
        }
        Pattern p1 = Pattern.compile("(?i)[qadhrbpe]");
        Pattern p2 = Pattern.compile("(?i)(e-)");
        Matcher matcher = p1.matcher(ncd);
        if (!matcher.find()) {
            matcher.reset();
            matcher = p2.matcher(ncd);
            if (!(matcher.find())&&(ncd.length() > 4)) {
                ncd = ncd.substring(0,5);
            }
        }
        return ncd;
    }
    
    
    public boolean isInPriceList(String code){
        return priceList.containsKey(code);
    }
    
    
    
    public List<String> updatePrice(List<String> origList, String templCode, String origPrice, String origCode){
        
        List<String> mfMapKeyList = getListFromMapKey(templCode); //[95890/1/2/3/4, Womens FJ Lambswool, 34, 44.2, 47.4, 43.0]
        String priceListName = origList.get(0);
        int priceIndex = getIndexHeaderPrice(priceListName);
        String newPrice = mfMapKeyList.get(priceIndex - 1); // 1st column newCode is excluded from the PriceList, but headers contain the newCode
        if (!newPrice.equals(origPrice)) {
            System.out.println("original list: " + origList);
            System.out.println("search key: " + templCode);
            System.out.println("found in: " + mfMapKeyList);
            System.out.println("price file name: " + priceListName);
            System.out.println("new price in " + priceListName + " is " + newPrice);
            List<String> updatedList = origList;
            updatedList.set(9, newPrice);
            System.out.println("updated list: " + updatedList);
            System.out.println("-------------------------");
            List<String> reportList = new ArrayList<>();
            reportList.add(priceListName);
            reportList.add(origPrice);
            reportList.add(newPrice);
            this.priceChanges.put(origCode, reportList);
            return updatedList;
        }
        else{
            return origList;
        }
    }
    
    public List<String> getListFromMapKey(String key){
        List<String> listFromKey = priceList.get(key);
        //System.out.println(listFromKey);
        //System.out.println("list from map: " + listFromKey);
        return listFromKey;
    }
    
    public int getIndexHeaderPrice(String prc){
        List<String> headers = this.em.getHeaders();
        int hindex = headers.lastIndexOf(prc);
        //System.out.println(prc + " is at " + hindex);
        return hindex;
    }
    
    public Map<String, List<String>> getPriceChanges(){
        return this.priceChanges;
    }
}
