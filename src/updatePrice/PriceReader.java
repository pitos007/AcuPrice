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
/**
 *
 * @author UPatryk
 */
public class PriceReader extends FileManager {
    List<Collection> priceListFileMap = new ArrayList<>();
    FileManager fm = new FileManager();
    List<String> headers;
    List<Map<String, List<String>>> priceFileList = new ArrayList<>();
    String path = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\updatePrice\\";
            
    public PriceReader(){
        this.headers = fm.getFileNames();
    }
    
    public void readFile(){
        for (String prFile : headers) {
            File inFile = new File(path + prFile + ".csv");
            if(inFile.exists() && !inFile.isDirectory()){
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
                        ls = new Scanner(strEmpty);
                        ls.useDelimiter(",");
                        List<String> tmpList = new ArrayList<>();
                        while(ls.hasNext()){
                            tmpList.add(ls.next());
                        }
                        tmpList.remove(0);
                        String prodCode = tmpList.get(0);
                        tmpList.remove(0);
                        priceFileMap.put(prodCode, tmpList);
                    }
                }
                catch (Exception e) {
                }
                this.priceFileList.add(priceFileMap);
                
            }
        }
    }
}
