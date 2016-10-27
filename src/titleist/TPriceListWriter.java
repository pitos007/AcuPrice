/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.io.File;
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
    
    
    public void writeExtendedTPriceFile(Map<String, List<String>> priceFileList){
        String fileName = createFileName();
        File outFile = new File("TpriceList.csv");
        for (String v : priceFileList.keySet()) {
            
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
