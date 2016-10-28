/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class TPriceListWriter extends TFileManager implements footjoy.Writer {
    
    
    public void writeExtendedTPriceFile(Map<String, List<String>> priceFileListMap) throws IOException{
        if (priceFileListMap.isEmpty()) {
            System.err.println("The map is empty. Did you run extendPriceList() ?");
        } 
        else{
            String fileName = createFileName("TpriceListExt");
            File outFile = new File(fileName);
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
                outFile.createNewFile();
                for (String k : priceFileListMap.keySet()) {
                    for(String t : priceFileListMap.get(k)){
                        bw.write(t);
                        bw.write(",");
                    }
                    bw.newLine();
                }
            }
        }
    }

    
    @Override
    public String createFileName(String priceFileName) {
        String outPath = TFileManager.OUT_PATH;
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
