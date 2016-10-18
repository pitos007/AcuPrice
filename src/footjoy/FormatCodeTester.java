/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footjoy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author UPatryk
 */
public class FormatCodeTester {
    public static void main(String[] args) {
        String[] strArr = {"63332XL", "55555D", "12315Q", "633", "63332XXL",
        "63332e-xl", "633", "63332XL", "54321", "54321H", "54321A", "54321S"
        };
        for (String eachStrArr : strArr) {
            formatCode(eachStrArr);
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
        Matcher matcher = p1.matcher(ncd);
        if (!matcher.find()) {
            matcher.reset();
            matcher = p2.matcher(ncd);
            if (!(matcher.find())&&(ncd.length() > 4)) {
                ncd = ncd.substring(0,5);
            }
        }
        return ncd;
    }
}
