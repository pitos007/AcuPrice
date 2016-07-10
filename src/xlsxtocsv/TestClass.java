/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsxtocsv;

import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        String str = "66241E/58E/70E/79E; 67860E/88E; 67901E/02E";
        str = str.replaceAll("[A-Za-z]", "");
        System.out.println(str);
    }
}
