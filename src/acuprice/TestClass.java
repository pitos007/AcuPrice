/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author UPatryk
 */
public class TestClass {
    public static void main(String[] args) {
        String[] stringArray = {"The", "cat", "sat", "on", "the", "mat"};                   //create new array 
        List<String> stringList = Arrays.asList(stringArray);                                       //convert array to list      
        Set<String> stringSet = new TreeSet<>(stringList);                                          //creates new set from array (removes duplicates with updated hashCode?) 
        List<String> stringListTwo = new ArrayList<>(stringSet);                                            //converts set to list 
        String[] stringArrayTwo = stringList.toArray(new String[stringList.size()]);                 //converts List back to array 
        String str = stringArray[0];
        System.out.println(str);
    }
}
