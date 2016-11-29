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
public class MasterFileReader extends FileManager {
    private List<String> header1 = new ArrayList<>();
    private List<String> header2 = new ArrayList<>();
    private List<List<String>> sizes = new ArrayList<>();
    private Map<String,List<String>> masterFileMap = new LinkedHashMap<>();
    
    public MasterFileReader(){
        
    }

    
    public Map<String, List<String>> getMasterFileMap() {
        return masterFileMap;
    }
    
    
    public void readAndConvert(){
        this.sizes = readFile(MFManager.ITMA_CONT + "sizes.csv");
        try {
            Scanner bs = new Scanner(new BufferedReader(new FileReader(new File(MFManager.FJ_APP))));
            int lineCounter = 0;
            while(bs.hasNextLine()){
                lineCounter++;
                String lineStr = bs.nextLine();
                if (lineCounter > 2) {
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
                    tokenList.remove(10); // remove Sales U/M
                    tokenList.remove(14); // remove inv type
                    if (hasZero(tokenList.get(10))) {  // 03/02/2016
                        tokenList.set(10, effDateZero(tokenList.get(10)));
                    }
                    else{
                        tokenList.set(10, effDate(tokenList.get(10)));
                    }
                    tokenList.add("1");
                    tokenList.add("awaitcategory");
                    addSizes(tokenList);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
    
    public void addSizes(List<String> origTokenList){
        String prodStruct = getProductStructure(origTokenList); // LCHTY
        List<List<String>> sizesList = getXNum(origTokenList); // [[S, M, L, XL, XXL], [AB, AC, AD, AE, AF]]
        int sizeNum = sizesList.get(0).size();
        for (int i = 0; i < sizeNum; i++) {
            // beware! if you write: List<String> newTokenList = origTokenList;
            // then you will be modifying the same object in the loop - 92166SM, 92166SML, 92166SMLXL
            List<String> newTokenList = new ArrayList<>(origTokenList);
            newTokenList.set(0, (origTokenList.get(0) + sizesList.get(0).get(i))); // 92166 -> 92166S
            newTokenList.set(16, (prodStruct + sizesList.get(1).get(i))); // L -> LCHTYAB
            newTokenList.remove(17); // L
            newTokenList.remove(18); // C
            newTokenList.remove(19); // H
            newTokenList.remove(17); // L
            this.masterFileMap.put(newTokenList.get(0), newTokenList);
        }
    }
    
    
    
    public String getProductStructure(List<String> lineStr){
        String prodStruct = "";
        for (int i = 16; i < 20; i++) {
            prodStruct += lineStr.get(i);
        }
        return prodStruct;
    }
    
    public List<List<String>> getXNum(List<String> listStr){
        List<String> xList = new ArrayList<>();
        for (int i = 20; i < 77; i++) {
            xList.add(listStr.get(i));   // " ", " ", "X", "X", "X", "X", " ", " ",
        }
        List<List<String>> sizesFound = new ArrayList<>();
        List<String> sizeToken = new ArrayList<>();
        List<String> levelFiveToken = new ArrayList<>();
        int counter = -1;
        for (int i = 0; i < xList.size(); i++) {
            counter++;
            if (xList.get(i).equals("x") || xList.get(i).equals("X")) {  // i=2
                List<String> sizeAndPS = this.sizes.get(counter); // [S, AB...], [M, AC...]
                sizeToken.add(sizeAndPS.get(0)); // "S", "M"...
                levelFiveToken.add(sizeAndPS.get(1)); // "AB", "AC"...
            }
        }
        sizesFound.add(sizeToken); // S, M, L, XL
        sizesFound.add(levelFiveToken); // AB, AC, AD, AE
        return sizesFound;
    }
    
    public String effDateZero(String dateStr){
        String noZero = dateStr.substring(1);
        String noFSlash = noZero.replaceAll("/", "");
        String str1 = noFSlash.substring(0, 3);
        String str2 = noFSlash.substring(5, 7);
        return str1 + str2;
    }
    
    public String effDate(String dateStr){
        String noFSlash = dateStr.replaceAll("/", "");
        String str1 = noFSlash.substring(0, 3);
        String str2 = noFSlash.substring(5, 7);
        return str1 + str2;
    }
    
    public boolean hasZero(String dateStr){
        return dateStr.substring(0, 1).equals("0");
    }
    
    public String getComm1(String comm){
        return comm.substring(0, 8);
    }
    
    public String getComm2(String comm){
        return comm.substring(8, 10);
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
        System.out.println("headers1: " + header1); // 1,2,3,4,8,56,10,11...
        System.out.println("headers2: " + header2); // Code, Description, Generic Ledger...
    }
    
    
    public void printMap(Map<String, List<String>> mapList){
        Map<String, List<String>> prListMapa = new LinkedHashMap<>();
        prListMapa = mapList;
        List<String> tempList = new ArrayList();
        for(String codes : prListMapa.keySet()){
            tempList = prListMapa.get(codes);
            System.out.println(codes + " " + tempList);
        }
        System.out.println();
    }
    
    public void printListList(List<List<String>> listlistStr){
        listlistStr.forEach((list) -> {
            list.forEach((string) -> {
                System.out.println(string);
            });
        });
    }
    
    
}
