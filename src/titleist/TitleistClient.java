/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 *
 * @author UPatryk
 */
public class TitleistClient {
    public static void main(String[] args) throws IOException {
        PriceListExtender ple = new PriceListExtender();
        ple.extendPriceList();
        
        TPriceListWriter tplw = new TPriceListWriter();
        tplw.writeExtendedTPriceFile(ple.getPriceListMap());
        
        
    }
}
