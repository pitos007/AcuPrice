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
    public static void main(String[] args) throws IOException, MissingFileException, DuplicateElementException, FileModifiedException {
//        PriceListExtender ple = new PriceListExtender(); // optional
//        ple.extendPriceList();
        
//        TPriceMapReader pmr = new TPriceMapReader(); // optional
//        pmr.readTPriceMap();
        
        PriceMapDiffChecker pmdc = new PriceMapDiffChecker("TpriceList_161101");
        pmdc.checkPriceMapDiff();
        
        TPriceUpdater pu = new TPriceUpdater();
        pu.updatePriceList();
        
        TReport tr = new TReport();
        tr.updateOutFile(pu.getPriceMapChangesList());
        
        TPriceListWriter tplw = new TPriceListWriter();
        tplw.writeUpdatedFile(pu.getPriceMapList());
        tplw.writeExtendedTPriceFile(pu.getPriceListMapExtend()); // irn, 2017 AP1 Steel, 10, 62; irn, 2017 AP1 Steel, 600, 372;
    }
}
