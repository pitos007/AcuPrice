/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priceupdater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    
    
    public PriceWriter(){
        
    }
    
    public void updateOutFile(Map<String, List<String>> priceListMap){
        File fileOut = new File(createFileName());
        BufferedWriter bw = null;
        Map<String, List<String>> priceList = new LinkedHashMap<>();
        priceList = extrMgr.generatePriceMap();
        try {
            bw = new BufferedWriter(new FileWriter(createFileName()));
            List<String> headers = getHeaders();
            bw.newLine();
            for (String header : headers) {
                bw.write(header);
            }
            bw.newLine();
            for (String token : priceList.keySet()) {
                
            }
        } catch (Exception e) {
        }
    }
    
    public String createFileName(){
        FileManager fileMgr = new FileManager();
        String outPath = fileMgr.getOutPathName();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr;
        System.out.println(outFileName);
        return outFileName;
    }
}
