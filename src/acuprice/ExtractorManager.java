/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acuprice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UPatryk
 */
public class ExtractorManager extends Extractor {
    private List<String> templatesList = new ArrayList<>();
    
    public ExtractorManager(){
        
    }
    
    public void StringToCodes(String str){
        List<String> groupTokens = getGroupTokens(str);
        for (String grToken : groupTokens) {
            List<String> tokenNum = getTokens(grToken);
            List<String> templList = createCodes(tokenNum);
            for (String eachToken : templList) {
                this.templatesList.add(eachToken);
            }
        }
    }
    
    public List<String> getCodes(){
        return this.templatesList;
    }
}
