/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class PriceListExtender extends TFileManager {
    private Map<String, List<String>> priceListMap = new LinkedHashMap<>();

    public PriceListExtender() {
    }
    
    public void extendPriceList(){
        File inFile = new File(getInPathName());
        Scanner bs = null;
        try {
            Scanner ls = null;
            String currentLine;
            bs = new Scanner(new BufferedReader(new FileReader(inFile)));
            while(bs.hasNextLine()){
                currentLine = bs.nextLine();
            }
        } catch (Exception e) {
        }
    }
    
            
            
}
