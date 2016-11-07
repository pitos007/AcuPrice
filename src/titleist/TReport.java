/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import footjoy.DuplicateElementException;
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
public class TReport extends TFileManager {
    private List<String> headers;
    private File fileOut;
    
    public TReport() throws DuplicateElementException{
        this.headers = new ArrayList<>();
    }
    
    public void updateHeaders(){
        this.headers.add("PriceList");
        this.headers.add("Code");
        this.headers.add("Description");
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
                    List<String> tempList = eachMap.get(code); // TRADEDO1, TH5AUBM-1-ECBM, BALL MARKER, 85, 50, DZ, 10115,311217
                    List<String> reportList = new ArrayList<>(); // PriceList, Code, Description, OldPrice, NewPrice
                    reportList.add(tempList.get(0));
                    reportList.add(tempList.get(1));
                    reportList.add(tempList.get(2));
                    reportList.add(tempList.get(16));
                    reportList.add(tempList.get(9));
                    for (String eachToken : reportList) {
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
    
    
    public String createFileName() {
        String outPath = TFileManager.FILE_CONT;
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr + "PriceChg.csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }
}
