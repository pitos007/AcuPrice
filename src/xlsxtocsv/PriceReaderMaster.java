/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class PriceReaderMaster {
    List<Map<String, List<String>>> priceFileMasterList = new ArrayList<>();
    TemplateWriter pw = new TemplateWriter();
    File masterFile;
    
    public PriceReaderMaster(){
        this.masterFile = pw.getFileOut();
    }
    
    
    public void readMasterFile(){
        Scanner fs = null;
        Map<String, List<String>> priceMasterFileMap = new LinkedHashMap<>();
        
    }
}
