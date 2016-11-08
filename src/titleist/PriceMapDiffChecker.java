/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class PriceMapDiffChecker extends TFileManager implements Printer {
    private List<String> priceListMapNew = new ArrayList<>();
    private List<String> priceListMapExist = new ArrayList<>();
    
    public void PriceMapDiffChecker(){
        
    }
    
    
    public void checkPriceMapDifferences(){
        
    }
    
    public void newAdditions(){
        
    }
    
    public void readPriceMapFileNew(){
        Scanner bs = null;
        try {
            bs = new Scanner(new BufferedReader(new FileReader(new File(TFileManager.T_PRICE_LST)))); //TpriceList.csv
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine(); // irn,716 AP1,75,54,57,49,52,46.91,975
                Scanner ls = new Scanner(lineStr);
                ls.useDelimiter(",");
                List<String> lineList = new ArrayList<>();
                while(ls.hasNext()){
                    lineList.add(ls.next()); // [irn,716 AP1,75,54,57,49,52,46.91,975]
                }
                this.priceListMapNew.add(lineList.get(1));
            }
            System.out.println("New price map: " + priceListMapNew.size() + " records");
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
    
    
    public void readPriceMapFileExist(String fileName){
        Scanner bs = null;
        try {
            bs = new Scanner(new BufferedReader(new FileReader(new File(TFileManager.FILE_CONT + fileName + ".csv")))); //TpriceList.csv
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine(); // irn,716 AP1,75,54,57,49,52,46.91,975
                Scanner ls = new Scanner(lineStr);
                ls.useDelimiter(",");
                List<String> lineList = new ArrayList<>();
                while(ls.hasNext()){
                    lineList.add(ls.next()); // [irn,716 AP1,75,54,57,49,52,46.91,975]
                }
                this.priceListMapExist.add(lineList.get(1));
            }
            System.out.println("Existing price map: " + priceListMapExist.size() + " records");
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
    
    
    
    
    @Override
    public void printPriceListMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPriceList(List<String> prListMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPriceListMap(Map<String, List<String>> prLm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAllPriceFiles(List<Map<String, List<String>>> priceFileList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
