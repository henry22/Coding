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

public class GrayCodeRecursion {

  /**
   * @param args
   */
  public static void main(String[] args) {
    GrayCodeRecursion grayCodeRecursion = new GrayCodeRecursion();
    List<Integer> result = grayCodeRecursion.grayCode(3);

    for(int i : result)  {
      System.out.print(i + " ");
    }
  }

  public List<Integer> grayCode(int n) {
    List<Integer> result = new ArrayList<>();
    helper(n, result);

    return result;
  }

  public void helper(int n, List<Integer> result) {
    if(n == 0)  {
      result.add(0);
      return;
    }

    helper(n - 1, result);
    int size = result.size();
    int k = 1 << (n-1);

    for(int i = size - 1; i >= 0; i--)  {
      result.add(result.get(i) + k);
    }

    return;
  }
}
