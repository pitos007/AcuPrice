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
public interface Printer {
    public void printPriceListMap(Map<String, List<String>> prListMap);
}
