/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acuprice;

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
    String inPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\AcuPrice\\src\\acuprice\\FJpriceList.csv";
    
    
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
                    String ct = ls.next();
                    ct = ct.trim();
                    headers.add(ct);
                }
                break;
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        System.out.println(headers);
    }
    
    public void codePricesMap(){
        File inFile = new File(inPathName);
        Scanner bs = null;
        
        try{
            Scanner ls = null;
            String currentLine;
            bs = new Scanner(new BufferedReader(new FileReader(inFile)));
            Map<String, List<String>> cdPrMap = new LinkedHashMap<>();
            while(bs.hasNextLine()){
                currentLine = bs.nextLine();
                ls = new Scanner(currentLine);
                ls.useDelimiter(",");
                List<String> tmpList = new ArrayList<>();
                while(ls.hasNext()){
                    tmpList.add(ls.next());
                }
                String tempKey = tmpList.get(0);
                tmpList.remove(0);
                tmpList.remove(0);
                tmpList.remove(0);
                cdPrMap.put(tempKey, tmpList);
            }
            printPriceListMap(cdPrMap);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void printPriceListMap(Map<String, List<String>> prListMap){
        List<String> tempList = new ArrayList();
        for(String eachCode : prListMap.keySet()){
            tempList = prListMap.get(eachCode);
            System.out.println(eachCode + " " + tempList);
        }
        System.out.println();
    }
    
    
}
