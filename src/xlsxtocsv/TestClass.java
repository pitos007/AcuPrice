/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass extends Extractor {
    public static void main(String[] args) {
        String[] strAr = {
"6644E-",
            "abc",
            "CXHHHBHA",
            "CXHAMSTA",
"E-fsfs",
"66439ML",
"66441Q",
"66442A",
"66443D",
"66444H",
"66445W",
"66446S",
"66447XL",
"66448XXL",
"66449B",
"66440R",
"Q66446",
"66442P",
"66447E-s"};
        
        for (String strAr1 : strAr) {
            System.out.println(formatCode(strAr1));
        }
   

    }
    
    public static String formatCode(String cd){
        String ncd = null;
        if (cd.length()>5) {
            ncd = cd.substring(0, 6);
        }
        else{
            ncd = cd;
        }
        Pattern p1 = Pattern.compile("(?i)[qadhrbpe]");
        Pattern p2 = Pattern.compile("(?i)(e-)");
        Matcher m1 = p1.matcher(ncd);
        if (m1.find()) {
            m1.reset();
            m1 = p2.matcher(ncd);
            if(m1.find()){
                return ncd.substring(0, 4);
            }
            else{
                return ncd;
            }
        }
        else{
            return ncd.substring(0, 5);
        }
    }
         
    
}
