/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.util.List;
import java.util.Map;

/**
 *
 * @author UPatryk
 */
public interface Writer {
    public void updateOutFile();
    public void updateOutFile(List<String> list);
    public void updateOutFile(Map<String, List<String>> map);
    public String createFileName();
    public String createFileName(String fileName);
}
