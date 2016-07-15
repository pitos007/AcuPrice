/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class PriceWriter extends Extractor {
    //Map<String, List<String>> priceListMap = new LinkedHashMap<>();
    ExtractorManager extrMgr = new ExtractorManager();
    File masterFile;
    File fileOut;


    public PriceWriter(){
        
    }
    
    public void updateOutFile(Map<String, List<String>> priceListMap){
        String fileName = createFileName();
        this.fileOut = new File(fileName);
        BufferedWriter bw = null;
        Map<String, List<String>> priceList = new LinkedHashMap<>();
        priceList = extrMgr.generatePriceMap();
        try {
            this.fileOut.createNewFile();
            bw = new BufferedWriter(new FileWriter(fileName));
            List<String> headers = getHeaders();
            for (String header : headers) {
                bw.write(header);
                bw.write(",");
            }
            bw.newLine();
            for (String token : priceList.keySet()) {
                bw.write(token);
                bw.write(",");
                for (String valPrice : priceList.get(token)) {
                    bw.write(valPrice);
                    bw.write(",");
                }
                bw.newLine();
            }
        } catch (Exception e) {
        }
        finally{
            try{
                bw.close();
                System.out.println("writing to the output file succesful");
            } catch(Exception ex){
                System.out.println("Problem with updateOutFile");
            }
        }
    }
    
    public String createFileName(){
        FileManager fileMgr = new FileManager();
        String outPath = fileMgr.getOutPathName();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr + ".csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }
    
    
    public File getFileOut() {
        return fileOut;
    }
}
