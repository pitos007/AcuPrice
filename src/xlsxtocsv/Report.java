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
    
    
    public Report() throws Exception{
    }
    
    
    
    public void updateHeaders(){
        //this.headers.add("UniqueCode");
        this.headers.add("Code");
        this.headers.add("Description");
        this.headers.add("PriceList");
        this.headers.add("OldPrice");
        this.headers.add("NewPrice");
    }

    public void updateOutFile(List<Map<String, List<String>>> priceChgList) {
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
            for (Map<String, List<String>> eachMap : priceChgList) {
                for (String code : eachMap.keySet()) {
                    List<String> tempList = eachMap.get(code);
                    for (String eachToken : tempList) {
                        bw.write(eachToken);
                        bw.write(",");
                    }
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            System.err.println("Problem with writing report " + e);
        }
        finally{
            try{
                bw.close();
                System.out.println("writing to the output file succesful");
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
}
