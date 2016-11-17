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
public class RandomIntGenerator2 {
  private final int min;
  private final int max;

  private RandomIntGenerator2(int min, int max) {
    this.min = min;
    this.max = max;
  }

  public static RandomIntGenerator2 between(int min, int max) {
    return new RandomIntGenerator2(min, max);
  }

  public static RandomIntGenerator2 biggerThan(int min) {
    return new RandomIntGenerator2(min, Integer.MAX_VALUE);
  }

  public static RandomIntGenerator2 smallerThan(int max) {
    return new RandomIntGenerator2(Integer.MIN_VALUE, max);
  }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
  
  
}