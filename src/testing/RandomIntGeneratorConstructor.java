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
public class RandomIntGeneratorConstructor implements RandomGenerator<Integer> {
    private final int min;
    private final int max;
    
    public RandomIntGeneratorConstructor(int min, int max){
        this.min = min;
        this.max = max;
    }
    
    public RandomIntGeneratorConstructor(int min){
        this.min = min;
        this.max = Integer.MAX_VALUE;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    
    
     // error - constructor already defined
//    public Tester5(int max){
//        this.min = Integer.MIN_VALUE;
//        this.max = max;
//    }
    
    @Override
    public Integer next(){
        return 0;
    }
    
}
