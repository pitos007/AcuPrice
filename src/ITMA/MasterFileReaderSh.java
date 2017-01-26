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
 * @author UPatryk
 */
public class MasterFileReaderSh extends MasterFileReaderApp {
    private List<String> header1 = new ArrayList<>();
    private List<String> header2 = new ArrayList<>();
    private List<List<String>> sizes = new ArrayList<>();
    private Map<String,List<String>> masterFileMap = new LinkedHashMap<>();
    
    
    public MasterFileReaderSh(){}

    @Override
    public Map<String, List<String>> getMasterFileMap() {
        return this.masterFileMap;
    }
    
    
    
    
    public void readAndConvertSh(){
        this.sizes = super.readFile(MFManager.ITMA_CONT + "sizesSh.csv");
        try {
            Scanner bs = new Scanner(new BufferedReader(new FileReader(new File(MFManager.FJ_SH))));
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
                    String comm = tokenList.get(6);
                    tokenList.set(6, getComm1(comm)); // replaces 6105201000 with 61052010
                    tokenList.add(7, getComm2(comm)); // inserts 00 between 61052010 and EA
                    if (hasZero(tokenList.get(13))) {  // 03/02/2016
                        tokenList.set(13, effDateZero(tokenList.get(13)));
                    }
                    else{
                        tokenList.set(13, effDate(tokenList.get(13)));
                    }
                    tokenList.remove(12); // remove Sales U/M
                    tokenList.remove(15); // remove inv type
                    tokenList.add("1");
                    tokenList.add("AWAIT CATEGORY");
                    tokenList.add("USProdStr");
                    addSizesSh(tokenList);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e + " when readAndConvertSh");
        }
    }
    
    
    
    
    public void addSizesSh(List<String> origTokenList){
        String prodStruct = getProductStructure(origTokenList); // LCHTY
        List<List<String>> sizesList = getXNum(origTokenList); // [[S, M, L, XL, XXL], [AB, AC, AD, AE, AF]]
        int sizeNum = sizesList.get(0).size();
        for (int i = 0; i < sizeNum; i++) {
            // beware! if you write: List<String> newTokenList = origTokenList;
            // then you will be modifying the same object in the loop - 92166SM, 92166SML, 92166SMLXL
            List<String> newTokenList = new ArrayList<>(origTokenList);
            newTokenList.set(0, (origTokenList.get(0) + sizesList.get(0).get(i))); // 92166 -> 92166S
            newTokenList.set(21, (prodStruct + sizesList.get(1).get(i))); // L -> LCHTYAB
            newTokenList.remove(22); // L
            newTokenList.remove(23); // C
            newTokenList.remove(24); // H
            newTokenList.remove(25); // L
            this.masterFileMap.put(newTokenList.get(0), newTokenList);
        }
    }
    
    @Override
    public String getProductStructure(List<String> lineStr){
        String prodStruct = "";
        for (int i = 20; i < 24; i++) {
            prodStruct += lineStr.get(i);
        }
        return prodStruct;
    }
    
    
    @Override
    public List<List<String>> getXNum(List<String> listStr){
        List<String> xList = new ArrayList<>();
        for (int i = 26; i < 52; i++) {
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
    
}
