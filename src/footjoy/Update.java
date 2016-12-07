/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footjoy;

/**
 *
 * @author UPatryk
 */
public class Update {
    public static void main(String[] args) throws DuplicateElementException, MissingFileException {
        
        /**
         * invoke crateTemplateFile only if you want the file in the folder
         * generatePriceMap() will be invoked on Extractor manager
         * in the PriceListUpdater constructor
         */
        TemplateWriter tempWrit = new TemplateWriter();
        tempWrit.createTemplateFile();
        
//        PriceListUpdater pr = new PriceListUpdater();
//        pr.readAndUpdatePriceFile();
//        Report rep = new Report();
//        rep.updateOutFile(pr.getPriceChangesList());
//        
//        FJPriceListWriter plw = new FJPriceListWriter();
//        plw.writeUpdatedFile(pr.getPriceFileList());
        
    }
}
