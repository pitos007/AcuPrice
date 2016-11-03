/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

import footjoy.DuplicateElementException;
import footjoy.MissingFileException;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 *
 * @author UPatryk
 */
public class TitleistClient {
    public static void main(String[] args) throws IOException, MissingFileException, DuplicateElementException {
        PriceListExtender ple = new PriceListExtender();
        ple.extendPriceList();
        
        TPriceListWriter tplw = new TPriceListWriter();
        tplw.writeExtendedTPriceFile(ple.getPriceListMapExtend());
        
        TPriceMapReader pmr = new TPriceMapReader();
        pmr.readTPriceMap();
        
        TPriceUpdater pu = new TPriceUpdater();
        pu.updatePriceList();
        
        TReport tr = new TReport();
        tr.updateOutFile(pu.getPriceMapChangesList());
        tplw.writeUpdatedFile(pu.getPriceMapList());
    }
}
