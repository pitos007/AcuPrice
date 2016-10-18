/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titleist;

/**
 *
 * @author UPatryk
 */
public abstract class TFileManager {
    private final String inPathName = "C:\\Users\\upatryk\\Documents\\NetBeansProjects\\AcuPrice\\src\\FileContainer\\TpriceList.csv";
    private final String outPathName = "C:\\Users\\upatryk\\Documents\\NetBeansProjects\\AcuPrice\\src\\FileContainer\\";

    public TFileManager() {
    }

    
    public String getInPathName() {
        return inPathName;
    }

    public String getOutPathName() {
        return outPathName;
    }

    

}
