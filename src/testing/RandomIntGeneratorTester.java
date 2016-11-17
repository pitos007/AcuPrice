/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

/**
 *
 * @author upatryk
 */
public class RandomIntGeneratorTester {
    public static void main(String[] args) {
        RandomIntGenerator1 rig1 = new RandomIntGenerator1(5, 10);
        System.out.println(rig1.getMin() + " " + rig1.getMax());
        
        rig1 = new RandomIntGenerator1(5);
        System.out.println(rig1.getMin());
        
        // no constructor for RandomIntGenerator1(int max)
       
        System.out.println("-------------------------------");
        
        
        RandomIntGenerator2 rig2 = RandomIntGenerator2.between(5, 10);
        System.out.println(rig2.getMin() + " " + rig2.getMax());
        
        rig2 = RandomIntGenerator2.smallerThan(10);
        System.out.println(rig2.getMax());
        
        rig2 = RandomIntGenerator2.biggerThan(6);
        System.out.println(rig2.getMin());
    }
}
