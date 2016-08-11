/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class TemplateWriter extends Extractor {
    //Map<String, List<String>> priceListMap = new LinkedHashMap<>();
    ExtractorManager extrMgr = new ExtractorManager();
    private File fileOut;


    public TemplateWriter(){
        
    }
    
   /**
    * creates a file with templates codes: 12345, 12346 etc and prices
    */
    public void createTemplateFile() throws DuplicateElementException{
        String fileName = createFileName();
        this.fileOut = new File(fileName);
        BufferedWriter bw = null;
        Map<String, List<String>> priceList = new LinkedHashMap<>();
        priceList = extrMgr.generatePriceMap(); // 12345 [10, 11, 12]
        try {
            this.fileOut.createNewFile();
            bw = new BufferedWriter(new FileWriter(fileName));
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
            System.err.println("problem with writing templet priceFile " + e);
        }
        finally{
            try{
                bw.close();
                System.out.println("writing to the output file succesful");
            } catch(Exception ex){
                System.err.println("Problem with writing to the updateOutFile " + ex);
            }
        }
    }
    
    
    public String createFileName(){
        FileManager fileMgr = new FileManager();
        String outPath = fileMgr.getOutPathName();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr + "Templates.csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }
    
    
    public File getFileOut() {
        return fileOut;
    }
}
