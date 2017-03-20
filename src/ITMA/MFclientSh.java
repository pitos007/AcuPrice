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
public class MFclientSh {
    public static void main(String[] args) throws IOException {

        MasterFileReaderSh mfrs = new MasterFileReaderSh();
        mfrs.readAndConvertSh();
        mfrs.printMap(mfrs.getMasterFileMap());
        
        MFWriterSh mfws = new MFWriterSh();
        mfws.writeMPLSh(mfrs.getMasterFileMap());
    }
}
