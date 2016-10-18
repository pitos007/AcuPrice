/*
 * Extractor class helps with extracting codes e.g 12345, 54321
 * from a single string containing product codes e.g 12345/6/7/55/56
 */
package footjoy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
public class Extractor extends FJFileManager {
    //String plStr1 = "92290/1/2/3; 92309H, 95890/1/2/3/45/46/8/81/82: 92235D/36 : 91788/8 ; 92753/54 62758P";
    //String plStr2 = "60003/37/60/94, 60128/51; 60241/74, 68254P 65706/44/51/68/75/82/91/99, 64814H : 65702E"
    //String plStr3 = "64815E/16E/18E/20E/20P/24E/53E/60E/70E; 66241E/58E/70E/79; 67860E/88E; 67901E/02E;
    String inPathName;

    public Extractor() {
        FJFileManager fm = new FJFileManager();
        this.inPathName = fm.getInPathName();
    }

    // extract tokens separated by commas from the single String
    // 66241E/58E/70E/79E, 67860E/88E, 66820E, 67882E 
    public List<String> getGroupTokens(String str) {
        List<String> groupTokenList = new ArrayList<>();

        Pattern p1 = Pattern.compile("[\\w\\/]+");
        Matcher m1 = p1.matcher(str);

        while (m1.find()) {
            groupTokenList.add(m1.group());
        }
        //System.out.println("string groups:" + groupTokenList);
        return groupTokenList;
    }

    // extract alpha-numeric tokens from the group of tokens
    // 66241E, 58E, 70E, 79E
    public List<String> getTokens(String group) {
        List<String> tokenList = new ArrayList<>();
        Pattern p1 = Pattern.compile("\\w+");
        Matcher m1 = p1.matcher(group);
        while (m1.find()) {
            tokenList.add(m1.group());
        }
        //System.out.println("tokens from each group: " + tokenList);
        return tokenList;
    }

    // converts 66241E, 58E, 70E, 79E into
    // 66241E, 66258E, 66270E, 66279E, 67860E, 67888E
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
    
    /**
     * reads an input file and creates map 
     * @param inPathName input path name e.g FJpriceList.csv
     * @return LinkedHashMap 12345/6/7; 10,11,12,13
     */
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
                String emptyBeg = currentLine.replaceAll("^,", " ,"); //replace ,12345/6 into " ",12345
                //System.out.println(emptyBeg);
                String emptyMid = emptyBeg.replaceAll(",,", ", ,");   //replace 12345,,54321 into 12345," ",54321
                //System.out.println(emptyMid);
                ls = new Scanner(emptyMid);
                ls.useDelimiter(",");
                List<String> tmpList = new ArrayList<>();
                while (ls.hasNext()){
                    tmpList.add(ls.next());
                }
                // new is empty (carry over is non-empty)
                if (tmpList.get(0).equals(" ")) {
                    String covr = tmpList.get(1); //get carry over
                    tmpList.remove(0);
                    tmpList.add(0, covr); // add carry over to first column
                }
                // cary over is non-empty (marge new with carry over)
                else if (!tmpList.get(1).equals(" ")){
                    String newPlusCovr = tmpList.get(0) + " " + tmpList.get(1);
                    tmpList.remove(0);
                    tmpList.add(0, newPlusCovr); // add new+carryOver to first column
                }
                String rawCodes = tmpList.get(0);
                tmpList.remove(0);
                cdPrMap.put(rawCodes, tmpList); //12345/6/7; 10,11,12,13
            }
            //printPriceListMap(cdPrMap);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return cdPrMap;
    }
    
    
    
    public void printPriceListMap(Map<String, List<String>> prListMap){
        Map<String, List<String>> prListMapa = new LinkedHashMap<>();
        prListMapa = prListMap;
        List<String> tempList = new ArrayList();
        for(String codes : prListMapa.keySet()){
            tempList = prListMapa.get(codes);
            System.out.println(codes + " " + tempList);
        }
        System.out.println();
    }
    
    @Override
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
        //System.out.println("headers: " + headers);
        return headers;
    }
}
