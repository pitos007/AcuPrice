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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author UPatryk
 */
public class LineMaker {
    private List<String> headers = new ArrayList<>();
    String inPathName = "E:\\NetBeans_JavaSE_8.0_Portable\\Data\\Projects\\IO_tests\\src\\DB_tests\\FJpriceList.csv";
    
    
    public LineMaker(){
        
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
    }
}
