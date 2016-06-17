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
    //E:/NetBeans_JavaSE_8.0_Portable/Data/Projects/AcuPrice    
        
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
        String str = "92290/1/2/3; 92309 95890/1/2/3/45/46/8/81/82: 92235/36 : 91788/8 ; 92753/54";
        extrMgr.StringToCodes(str);
        FileManager fm = new FileManager();
        fm.codePricesMap();
    }
}
