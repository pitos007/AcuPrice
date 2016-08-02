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
import java.util.Set;

/**
 *
 * @author UPatryk
 */
public class PriceListWriter implements Writer {
    private FileManager fileMgr = new FileManager();

    
    public PriceListWriter(){
        
    }
    
    
    public void writeUpdatedFile(List<Map<String, List<String>>> priceFileList){
        for (Map<String, List<String>> eachPriceList : priceFileList) {
            Set<String> tempSet = eachPriceList.keySet();
            List<String> tempList = new ArrayList<>(tempSet);
            String anyKey = tempList.get(1);
            List<String> anyKeyList = eachPriceList.get(anyKey);
            String priceFileName = anyKeyList.get(0);
            String fileName = createFileName(priceFileName);
            File outFile = new File(fileName);
            BufferedWriter bw = null;
            try {
                outFile.createNewFile();
                bw = new BufferedWriter(new FileWriter(outFile));
                List<String> eachLineList = new ArrayList<>();
                bw.newLine();
                for (String eachKey : eachPriceList.keySet()) {
                    for (String eachToken : eachPriceList.get(eachKey)) {
                        bw.write(eachToken);
                        bw.write(",");
                    }
                    bw.newLine();
                }
            } catch (Exception e) {
                System.err.println("Problem with writing price file " + e);
            }
            finally{
            try{
                bw.close();
                System.out.println("writing to the output file succesful");
            } catch(Exception ex){
                System.err.println("Problem with writing priceFile " + ex);
                }
            }
        }
    }
    

    @Override
    public String createFileName(String priceFileName) {
        String outPath = fileMgr.getOutPathName();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr + priceFileName + ".csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }

    @Override
    public String createFileName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
