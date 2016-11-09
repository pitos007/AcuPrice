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
    private final String newPriceMapFileName;
    
    public PriceMapDiffChecker(String fileName){
        this.newPriceMapFileName = fileName;
    }
    
    public void checkPriceMapDiff() throws FileModifiedException{
        readPriceMapFileNew();
        readPriceMapFileExist();
        checkPriceMapDifferences(this.priceListMapExist, this.priceListMapNew);
    }
    
    
    public static void checkPriceMapDifferences(List<String> existPric, List<String> newPric) throws FileModifiedException{
        List<String> deletedInExist = new ArrayList<>();
        List<String> addedInNew = new ArrayList<>();
        int counter1 = 0;
        int counter2 = 0;
        for (String key : existPric) {
            if (!newPric.contains(key)) {
                deletedInExist.add(key);
                counter1 += 1;
            }
        }
        for (String key2 : newPric) {
            if (!existPric.contains(key2)) {
                addedInNew.add(key2);
                counter2 += 1;
            }
        }
        
        if (counter2 > 0) {
            throw new FileModifiedException(counter2 + " keys not found in the current version: " + addedInNew);
        }
        else{
            System.out.println("no differences in old file found");
        }
        
        if (counter1 > 0) {
            throw new FileModifiedException(counter1 + " keys not found in the new price list: " + deletedInExist);
        }
        else{
            System.out.println("no differences in new file found");
        }
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
    
    
    public void readPriceMapFileExist(){
        Scanner bs = null;
        try {
            bs = new Scanner(new BufferedReader(new FileReader(new File(TFileManager.FILE_CONT_OLD + "\\" + this.newPriceMapFileName + ".csv")))); //TpriceList.csv
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
