/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import titleist.TFileManager;

/**
 *
 * @author UPatryk
 */
public class TPriceMapReader {
    private Map<String, List<String>> tPriceMap = new LinkedHashMap<>();
    
    
    
    public void readTPriceMap(){
        Scanner bs = null;
        try {
            bs = new Scanner(new BufferedReader(new FileReader(new File(TFileManager.T_PRICE_MAP))));
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine();
                Scanner ls = new Scanner(lineStr);
                ls.useDelimiter(",");
                List<String> tokenList = new ArrayList<>();
                while(ls.hasNext()){
                    tokenList.add(ls.next()); // 6623E-1S, Players, PLAYERS MLH
                }
                String keyStr = tokenList.get(0);
                tokenList.remove(0);
                this.tPriceMap.put(keyStr, tokenList);
                //System.out.println(keyStr + ", " + tokenList);
            }
        } 
        catch (FileNotFoundException e) {
            System.err.println("Problem with reading TPriceMap file " + e);
        }
    }
    
    public Boolean containsCode(String code){
        return this.tPriceMap.containsKey(code);
    }
    
    public String getKey(String code){
        List<String> values = this.tPriceMap.get(code);
        return values.get(0);
    }

    public Map<String, List<String>> gettPriceMap() {
        return tPriceMap;
    }
    
    
    
    
}
