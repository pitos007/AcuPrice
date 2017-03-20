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
import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class MFWriterSh extends MFWriterApp {
    
    public void writeMPLSh(Map<String,List<String>> masterFileMap) throws IOException{
        if (masterFileMap.isEmpty()) {
            System.err.println("The map is empty. Did you run readAndConvert() ?");
        }
        else{
            String fileName = createFileName("MPL_sh");
            File outFile = new File(fileName);
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
                outFile.createNewFile();
                bw.write(writeHeader());
                bw.newLine();
                for (String k : masterFileMap.keySet()) {
                    List<String> line = masterFileMap.get(k);
                    //System.out.println(line.get(13));
                    
                    bw.write(line.get(0)); bw.write(","); // Code
                    bw.write(line.get(2)); bw.write(","); // Desc
                    bw.write(line.get(3)); bw.write(","); // Generic
                    bw.write(line.get(4)); bw.write(","); // GL
                    bw.write(line.get(7)); bw.write(","); // comm1
                    bw.write(line.get(8)); bw.write(","); // comm2
                    bw.write(line.get(14)); bw.write(","); // date 
                    bw.write(line.get(5)); bw.write(","); // PL
                    bw.write(line.get(60)); bw.write(","); // PC
                    bw.write(line.get(9)); bw.write(","); // EAS
                    bw.write(line.get(11)); bw.write(","); // EAP
                    bw.write(line.get(12)); bw.write(","); // convFctr
                    bw.write("12"); bw.write(","); // pkgQty
                    bw.write(line.get(15)); bw.write(","); // wgt
                    Double dbWgtCnvFctr = roundDb(Double.valueOf(line.get(13)), 4);
                    String StrWgtCnvFctr = String.valueOf(dbWgtCnvFctr);
                    bw.write(StrWgtCnvFctr); bw.write(","); // wgtCnvFctr
                    bw.write("AWAIT CATEGORY"); bw.write(","); // awaitCat
                    bw.write(line.get(21)); bw.write(","); // prodStruct
                    bw.write(line.get(19)); bw.write(","); // priceCat
                    bw.write("PROD_STR"); bw.write(","); // USProdStr
                    bw.write(line.get(0)); bw.write(","); // PriceAsProd
                    bw.write(getSalesAnUM(line.get(20))); bw.write(","); //salesAnUM
                    bw.write(line.get(50)); bw.write(","); // BoughtMade
                    bw.write(line.get(51)); bw.write(","); // Country
                    bw.write(line.get(54)); bw.write(","); // shoes
                    bw.write(line.get(53)); bw.write(","); // year
                    bw.write("1"); bw.write(","); // issueCode
                    bw.write(line.get(52)); bw.write(","); // trTimeFence
                    bw.write(line.get(70)); bw.write(","); // stdCst
                    
                    for (int i = 1; i < 12; i++) {
                        bw.write(","); // prodAttr
                    }
                    
//                    for (int i = 72; i < 87; i++) {
//                        bw.write(line.get(i)); bw.write(","); // priceList
//                    }
                    bw.newLine();
                }
            }
        }
    }
}
