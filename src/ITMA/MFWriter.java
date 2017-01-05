/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITMA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class MFWriter {
    
    
    public void writeMPLApp(Map<String,List<String>> masterFileMap) throws IOException{
        if (masterFileMap.isEmpty()) {
            System.err.println("The map is empty. Did you run readAndConvert() ?");
        }
        else{
            String fileName = createFileName("MPL_app");
            File outFile = new File(fileName);
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
                outFile.createNewFile();
                bw.write(writeHeader());
                bw.newLine();
                
                for (String k : masterFileMap.keySet()) {
                    List<String> line = masterFileMap.get(k);
                    bw.write(line.get(0)); bw.write(","); // Code
                    bw.write(line.get(1)); bw.write(","); // Desc
                    bw.write(line.get(2)); bw.write(","); // Generic
                    bw.write(line.get(3)); bw.write(","); // GL
                    bw.write(line.get(5)); bw.write(","); // comm1
                    bw.write(line.get(6)); bw.write(","); // comm2
                    bw.write(line.get(10)); bw.write(","); // date 
                    bw.write(line.get(4)); bw.write(","); // PL
                    bw.write(line.get(13)); bw.write(","); // PC
                    bw.write(line.get(7)); bw.write(","); // EAS
                    bw.write(line.get(8)); bw.write(","); // EAP
                    bw.write(line.get(9)); bw.write(","); // convFctr
                    bw.write("1"); bw.write(","); // pkgQty
                    bw.write(line.get(11)); bw.write(","); // wgt
                    Double dbWgtCnvFctr = roundDb(Double.valueOf(line.get(12)), 4);
                    String StrWgtCnvFctr = String.valueOf(dbWgtCnvFctr);
                    bw.write(StrWgtCnvFctr); bw.write(","); // wgtCnvFctr
                    bw.write(line.get(111)); bw.write(","); // awaitCat
                    bw.write(line.get(16)); bw.write(","); // prodStruct
                    bw.write(line.get(14)); bw.write(","); // priceCat
                    bw.write(line.get(112)); bw.write(","); // USProdStr
                    bw.write(line.get(0)); bw.write(","); // PriceAsProd
                    bw.write(getSalesAnUM(line.get(15))); bw.write(","); //salesAnUM
                    bw.write(line.get(73)); bw.write(","); // BoughtMade
                    bw.write(line.get(74)); bw.write(","); // Country
                    bw.write(line.get(77)); bw.write(","); // rwear
                    bw.write(line.get(76)); bw.write(","); // year
                    bw.write("1"); bw.write(","); // issueCode
                    bw.write(line.get(75)); bw.write(","); // ttf
                    bw.write(line.get(79)); bw.write(","); // stdCst
                    
                    for (int i = 1; i < 12; i++) {
                        bw.write(","); // prodAttr
                    }
                    
                    for (int i = 91; i < 109; i++) {
                        bw.write(line.get(i)); bw.write(","); // priceList
                    }
                    bw.newLine();
                }
            }
        }
    }
    
    public String getSalesAnUM(String token){
        if (token.equals("1")) {
            return "";
        }
        else{
            return token;
        }
    }
    
    public String writeHeader(){
        String line = "";
        for (int i = 0; i < 39; i++) {
            line += i + ",";
        }
        for (int i = 0; i < 18; i++) {
            line += "PriceList" + i + ",";
        }
        return line;
    }
    
    
    public String createFileName(String priceFileName) {
        String outPath = MFManager.ITMA_CONT;
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr + priceFileName + ".csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }
    
    public static double roundDb(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
