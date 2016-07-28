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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class Report extends PriceListUpdater implements Writer, Printer  {
    FileManager fileMgr = new FileManager();
    
    private File fileOut;
    private List<String> headers = new ArrayList<>();
    
    
    public Report(){
    }
    
    
    
    public void updateHeaders(){
        this.headers.add("Code");
        this.headers.add("PriceList");
        this.headers.add("OldPrice");
        this.headers.add("NewPrice");
    }

    @Override
    public void updateOutFile(Map<String, List<String>> priceChg) {
        updateHeaders();
        String fileName = createFileName();
        this.fileOut = new File(fileName);
        BufferedWriter bw = null;
        try {
            this.fileOut.createNewFile();
            bw = new BufferedWriter(new FileWriter(fileName));
            for (String header : this.headers) {
                bw.write(header);
                bw.write(",");
            }
            bw.newLine();
            for (String code : priceChg.keySet()) {
                bw.write(code);
                bw.write(",");
                for (String valPrice : priceChg.get(code)) {
                    bw.write(valPrice);
                    bw.write(",");
                }
                bw.newLine();
            }
        } catch (Exception e) {
            System.err.println("Problem with writing report " + e);
        }
        finally{
            try{
                bw.close();
                System.err.println("writing to the output file succesful");
            } catch(Exception ex){
                System.err.println("Problem with updateOutFile " + ex);
            }
        }
    }

    @Override
    public String createFileName() {
        String outPath = fileMgr.getOutPathName();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr + "PriceChg.csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }
    
    
    public File getFileOut() {
        return fileOut;
    }

    @Override
    public void printPriceListMap() {
        
    }

    @Override
    public void printPriceList(List<String> prListMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAllPriceFiles(List<Map<String, List<String>>> priceFileList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void updateOutFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
