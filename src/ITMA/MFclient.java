/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITMA;

import java.io.IOException;

/**
 *
 * @author upatryk
 */
public class MFclient {
    public static void main(String[] args) throws IOException {
        
        MasterFileReader mfr = new MasterFileReader();
        //mfr.getHeaders();
        mfr.readAndConvert();
        mfr.printMap(mfr.getMasterFileMap());
        
        MFWriter mfw = new MFWriter();
        mfw.writeMPLApp(mfr.getMasterFileMap());
    }
}
