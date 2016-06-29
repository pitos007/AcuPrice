/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priceupdater;

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
    private List<String> headers = new ArrayList<>();
    //String inPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\acuprice\\FJpriceList.csv";
    //String inPathName = "D:\\OneDrive\\Projects\\AcuPrice\\src\\priceupdater\\FjPriceList.csv";
    private String inPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\priceupdater\\FJpriceList.csv";
    
    public FileManager(){
        
    }
    
    public void getHeaders(){
        File inFile = new File(inPathName);
        Scanner bs = null;
        try{
            Scanner ls = null;
            String currentLine;
            bs = new Scanner(new BufferedReader(new FileReader(inFile)));
            while (bs.hasNextLine()){
                currentLine = bs.nextLine();
                ls = new Scanner(currentLine);
                ls.useDelimiter(",");
                while (ls.hasNext()){
                    headers.add(ls.next());
                }
                break;
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        System.out.println(headers);
    }
    
    public String getInPathName(){
        return this.inPathName;
    }
}
