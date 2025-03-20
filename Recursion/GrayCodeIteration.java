package Recursion;

import java.util.List;
import java.util.ArrayList;

/**
 * Gray Code
 * The gray code is a binary numeral system where two successive values differ in
only one bit.
 * Given a non-negative integer n representing the total number of bits in the code,
print the sequence of gray code. A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]
 */
public class GrayCodeIteration {

  /**
   * @param args
   */
  public static void main(String[] args) {
    GrayCodeIteration grayCodeIteration = new GrayCodeIteration();
    List<Integer> result = grayCodeIteration.grayCode(3);
    for(int i : result)  {
      System.out.print(i + " ");
    }
  }

  public List<Integer> grayCode(int n) {
    List<Integer> result = new ArrayList<>();
    result.add(0);

    for(int i = 0; i < n; i++) {
      int size = result.size();
      int k = 1 << i;
      for(int j = size - 1; j >= 0; j--) {
        result.add(result.get(j) + k);
      }
    }

    return result;
  }
}
