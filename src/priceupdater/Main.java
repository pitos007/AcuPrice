/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priceupdater;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, List<String>> fullPriceList = new LinkedHashMap<>();
        extrMgr.generatePriceMap();
    }
        
}
