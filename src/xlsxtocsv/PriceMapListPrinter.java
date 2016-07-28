/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class PriceMapListPrinter implements Printer {

    @Override
    public void printPriceListMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPriceList(List<String> prListMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAllPriceFiles(List<Map<String, List<String>>> priceFileList){
        for (Map<String, List<String>> eachMap : priceFileList) {
            List<String> tempList = new ArrayList();
            for(String code : eachMap.keySet()){
                tempList = eachMap.get(code);
                System.out.println(code + " " + tempList);
            }
        }
    }
}
