/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acuprice;

/**
 *
 * @author patry
 */
public class Main {
    public static void main(String[] args){
//        InputManager tm = new InputManager();
//        System.out.println("Generate files from headers...");
//        tm.createFilesFromHeaders();
//        System.out.println("Generate map: codeIn - prices list ");
//        tm.createPriceMaps();
//        tm.printCodeList();
//        tm.readUploadFile();
//        System.out.println("Generate map: CodeOut - price");
//        tm.printPriceList();
//        tm.updatePrice();
//        tm.printPriceList();
//        tm.updateOutFile();
        
        //Extractor extr = new Extractor();
        ExtractorManager extrMgr = new ExtractorManager();
        extrMgr.StringToCodes();
        extrMgr.printCodes();
    }
}
