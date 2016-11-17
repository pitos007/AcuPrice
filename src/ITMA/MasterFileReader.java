/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITMA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author upatryk
 */
public class MasterFileReader {
    List<String> header1 = new ArrayList<>();
    List<String> header2 = new ArrayList<>();
    
    public void readAndConvert(){
        try {
            Scanner bs = new Scanner(new BufferedReader(new FileReader(new File(MFManager.FJ_APP))));
            while(bs.hasNextLine()){
                String lineStr = bs.nextLine();
                String emptyMid = lineStr.replaceAll(",,", ", ,"); // //replace 12345,,54321 into 12345," ",54321
                Scanner ls = new Scanner(emptyMid);
                ls.useDelimiter(",");
                List<String> tokenList = new ArrayList<>();
                while(ls.hasNext()){
                    tokenList.add(ls.next());
                }
                String comm = tokenList.get(5);
                tokenList.set(5, getComm1(comm)); // replaces 6105201000 with 61052010
                tokenList.add(6, getComm2(comm)); // inserts 00 between 61052010 and EA
                tokenList.remove(9); // removes Sales U/M
            }
            
        } catch (FileNotFoundException e) {
        }
    }
    
    public String getComm1(String comm){
        return comm.substring(0, 7);
    }
    
    public String getComm2(String comm){
        return comm.substring(8, 9);
    }
    
    public void getHeaders(){
        int counter = 0;
        try{
            Scanner bs = new Scanner(new BufferedReader(new FileReader(new File(MFManager.FJ_APP))));
            while (bs.hasNextLine()){
                counter++;
                if (counter == 3) {
                    break;
                }
                else{
                    String lineStr = bs.nextLine();
                    String emptyMid = lineStr.replaceAll(",,", ", ,"); // //replace 12345,,54321 into 12345," ",54321
                    Scanner ls = new Scanner(emptyMid);
                    ls.useDelimiter(",");
                    while(ls.hasNext()){
                        if (counter == 1) {
                            this.header1.add(ls.next());
                        }
                        else{
                            this.header2.add(ls.next());
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println(ex);
        }
        System.out.println("headers1: " + header1);
        System.out.println("headers2: " + header2);
    }
    
    
}
