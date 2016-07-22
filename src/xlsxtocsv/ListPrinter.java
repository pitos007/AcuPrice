/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public class ListPrinter implements Printer {

    @Override
    public void printPriceListMap(Map<String, List<String>> prListMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPriceList(List<String> prList) {
        for (String eachString : prList) {
            System.out.println(eachString);
        }
    }

    @Override
    public void printAllPriceFiles(List<Map<String, List<String>>> priceFileList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
