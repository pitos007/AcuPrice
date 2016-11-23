/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITMA;

import ITMA.MFManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author upatryk
 */
public abstract class FileManager {
    
    public List<List<String>> readFile(String path){
        List<List<String>> fileSize = new ArrayList<>();
        try {
            Scanner bs = new Scanner(new BufferedReader(new java.io.FileReader(new File(path))));
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine();
                String emptyMid = lineStr.replaceAll(",,", ", ,"); // //replace 12345,,54321 into 12345," ",54321
                Scanner ls = new Scanner(emptyMid);
                ls.useDelimiter(",");
                List<String> tokenList = new ArrayList<>();
                while(ls.hasNext()){
                    tokenList.add(ls.next());
                }
                fileSize.add(tokenList);
            }
        } catch (FileNotFoundException e) {
        }
        return fileSize;
    }
    
    
    
}
