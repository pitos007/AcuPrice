/*
 * Extractor class helps with extracting codes e.g 12345, 54321
 * from a single string containing product codes e.g 12345/6/7/55/56
 */
package priceupdater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pitos
 */
public class Extractor extends FileManager {
    //String plStr1 = "92290/1/2/3; 92309 95890/1/2/3/45/46/8/81/82: 92235/36 : 91788/8 ; 92753/54";
    String inPathName;

    public Extractor() {
        FileManager fm = new FileManager();
        this.inPathName = fm.getInPathName();
    }

    // extract all numbers from the single String
    public List<String> getGroupTokens(String str) {
        List<String> groupTokenList = new ArrayList<>();

        Pattern p1 = Pattern.compile("(\\d)+(\\/\\d+)*");
        Matcher m1 = p1.matcher(str);

        while (m1.find()) {
            groupTokenList.add(m1.group());
        }
        //System.out.println("string groups:" + groupTokenList);
        return groupTokenList;
    }

    // extract numbers from the single group of numbers
    public List<String> getTokens(String group) {
        List<String> tokenList = new ArrayList<>();
        Pattern p1 = Pattern.compile("\\d+");
        Matcher m1 = p1.matcher(group);
        while (m1.find()) {
            tokenList.add(m1.group());
        }
        //System.out.println("tokens from each group: " + tokenList);
        return tokenList;
    }

    // converts numbers into template codes
    public List<String> createCodes(List<String> numList) {
        List<String> templateList = new ArrayList<>();
        for (String str : numList) {
            String firstPart = numList.get(0).substring(0, numList.get(0).length() - str.length());
            String newToken = firstPart + str;
            templateList.add(newToken);
        }
        //System.out.println("codes from each token group: " + templateList);
        return templateList;
    }
    
    // creates a raw price map [12345/6/7; 10,20,30,40] 
    public synchronized Map<String, List<String>> codePricesMap(String inPathName){
        File inFile = new File(inPathName);
        Scanner bs = null;
        Map<String, List<String>> cdPrMap = new LinkedHashMap<>();
        try{
            Scanner ls = null;
            String currentLine;
            bs = new Scanner(new BufferedReader(new FileReader(inFile)));
            while(bs.hasNextLine()){
                currentLine = bs.nextLine();
                String emptyBeg = currentLine.replaceAll("^,", " ,"); //replace ,12345/6 into null,12345
                //System.out.println(emptyBeg);
                String emptyMid = emptyBeg.replaceAll(",,", ", ,");   //replace 12345,,54321 into 12345,null,54321
                //System.out.println(emptyMid);
                ls = new Scanner(emptyMid);
                ls.useDelimiter(",");
                List<String> tmpList = new ArrayList<>();
                while (ls.hasNext()){
                    tmpList.add(ls.next());
                }
                //System.out.println("tmpList: " + tmpList);
                if (tmpList.get(0).equals(" ")) {
                    String covr = tmpList.get(1);
                    tmpList.remove(0);
                    tmpList.add(0, covr);
                }
                String rawCodes = tmpList.get(0);
                tmpList.remove(0);
                cdPrMap.put(rawCodes, tmpList);
            }
            printPriceListMap(cdPrMap);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return cdPrMap;
    }
    
    public void printPriceListMap(Map<String, List<String>> prListMap){
        Map<String, List<String>> prListMapa = new LinkedHashMap<>();
        List<String> tempList = new ArrayList();
        for(String codes : prListMapa.keySet()){
            tempList = prListMapa.get(codes);
            System.out.println(codes + " " + tempList);
        }
        System.out.println();
    }
    
    public String getInPathName(){
        return this.inPathName;
    }
    
    
    public List<String> getHeaders(){
        List<String> headers = new ArrayList<>();
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
        return headers;
    }
}
