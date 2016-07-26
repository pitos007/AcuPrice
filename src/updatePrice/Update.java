/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updatePrice;

/**
 *
 * @author UPatryk
 */
public class Update {
    public static void main(String[] args) {
        
        // 92533 new price: 100
        PriceReaderFile pr = new PriceReaderFile();
        pr.readPriceFile();
        Report rep = new Report();
        rep.updateOutFile();
    }
}
