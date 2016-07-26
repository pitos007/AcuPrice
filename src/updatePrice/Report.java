/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updatePrice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import xlsxtocsv.FileManager;
import xlsxtocsv.Writer;

/**
 *
 * @author UPatryk
 */
public class Report implements Writer  {
    private PriceReaderFile prf;
    private FileManager fileMgr;
    private Map<String, List<String>> priceChanges;
    private File fileOut;
    private List<String> headers = new ArrayList<>();
    
    
    public Report(){
        this.priceChanges = prf.getPriceChanges();
    }
    
    public void updateHeaders(){
        this.headers.add("PriceList");
        this.headers.add("OldPrice");
        this.headers.add("NewPrice");
    }

    @Override
    public void updateOutFile() {
        updateHeaders();
        String fileName = createFileName();
        System.out.println(fileName);
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
            for (String token : this.priceChanges.keySet()) {
                bw.write(token);
                bw.write(",");
                for (String valPrice : this.priceChanges.get(token)) {
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
        String outFileName = outPath + dateTimeStr + ".csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }
    
    
    public File getFileOut() {
        return fileOut;
    }
}
