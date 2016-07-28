/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestClass implements Printer {
    public static void main(String[] args) {
        PriceListUpdater plu = new PriceListUpdater();
        plu.readAndUpdatePriceFile();
        Map<String, List<String>> upl = plu.getPriceChanges();
        System.out.println(upl.isEmpty());
        List<String> tempList = new ArrayList();
        for(String code : upl.keySet()){
            tempList = upl.get(code);
            System.out.println(code + " " + tempList);
        }
}
    
    public void updatePrice(){
       
    }

    @Override
    public void printPriceListMap() {
        
    }

    @Override
    public void printPriceList(List<String> prListMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAllPriceFiles(List<Map<String, List<String>>> priceFileList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    


}
