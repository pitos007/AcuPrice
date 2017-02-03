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
        
        //MasterFileReaderApp mfr = new MasterFileReaderApp();
            //mfr.getHeaders();
        //mfr.readAndConvertApp();
            //mfr.printMap(mfr.getMasterFileMap());
        
        //MFWriterApp mfw = new MFWriterApp();
        //mfw.writeMPLApp(mfr.getMasterFileMap());
        
        MasterFileReaderSh mfrs = new MasterFileReaderSh();
        mfrs.readAndConvertSh();
        mfrs.printMap(mfrs.getMasterFileMap());
        
        MFWriterSh mfws = new MFWriterSh();
        mfws.writeMPLSh(mfrs.getMasterFileMap());
    }
}
