/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footjoy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class FJPriceListWriter implements Writer {
    private FJFileManager fileMgr = new FJFileManager();

    
    public FJPriceListWriter(){
        
    }
    
    
    public void writeUpdatedFile(List<Map<String, List<String>>> priceFileList){
        for (Map<String, List<String>> eachPriceList : priceFileList) {
            Set<String> tempSet = eachPriceList.keySet(); //[EXCATL, Code1Qty12Date16, Code2Qty6Date15 ...]
            List<String> tempList = new ArrayList<>(tempSet); //(EXCATL, Code1Qty12Date16, Code2Qty6Date15 ...)
            String anyKey = tempList.get(1); //Code1Qty12Date16
            List<String> anyKeyList = eachPriceList.get(anyKey); //[TRADEUK1, 92250-32, PERF SHORTS BLACK, 8Q, 67, PR, 10112, 311217, 1, 25,
            String priceFileName = anyKeyList.get(0); //TRADEUK1
            System.out.println("updating " + priceFileName);
            String fileName = createFileName(priceFileName); //%TRADEUK1.csv
            File outFile = new File(fileName);
            BufferedWriter bw = null;
            try {
                outFile.createNewFile();
                bw = new BufferedWriter(new FileWriter(outFile));
                //bw.newLine();
                for (String eachKey : eachPriceList.keySet()) {
                    for (String eachToken : eachPriceList.get(eachKey)) {
                        bw.write(eachToken);
                        bw.write(",");
                    }
                    bw.newLine();
                }
            } catch (IOException e) {
                System.err.println("Problem with writing price file " + e);
            }
            finally{
            try{
                bw.close();
                System.out.println("writing to the output file succesful");
            } catch(IOException ex){
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
