/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author UPatryk
 */
public class FileManager {
    //String inPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\acuprice\\FJpriceList.csv";
    //String inPathName = "D:\\OneDrive\\Projects\\AcuPrice\\src\\priceupdater\\FjPriceList.csv";
    private String inPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\xlsxtocsv\\FJpriceList.csv";
    private String outPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\xlsxtocsv\\";
    
    public FileManager(){
        
    }
    
    
    
    public String getInPathName(){
        return this.inPathName;
    }
    
    public String getOutPathName(){
        return this.outPathName;
    }
    
    public List<String> getFileNames(){
        // Extractor has FileManager.getInPathName() in the constructor
        // so it has to be initialised before invocing getHeaders()
        Extractor extr = new Extractor();
        List<String> fileNameList = extr.getHeaders();
        fileNameList.remove(0);
        fileNameList.remove(0);
        fileNameList.remove(0);
        System.out.println("fileNameList: " + fileNameList);
        return fileNameList;
    }
}
