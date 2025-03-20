package Recursion;

/**
 * 0-1 Knapsack
 * Given a knapsack which can hold s pounds of items, and a set of items with
weight w1, w2, ... wn. Return whether we can pick specific items so that their total
weight s.
 * Example Input:
 * s = 20;
 * w = [14, 8, 7, 5, 3];
 * Example Output:
 * True; (8, 7, 5)
 */

public class Knapsack {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Knapsack knapsackResult = new Knapsack();
    boolean result = knapsackResult.knapsack(20, new int[]{14, 8, 7, 5, 3});
    System.out.println(result);
  }

  public boolean knapsack(int s, int[] weights) {
    return knapsack(s, weights, 0);
  }

  public boolean knapsack(int s, int[] weights, int index) {
    // knapsack is full
    if(s == 0) {
      return true;
    }

    // knapsack is empty or no more items to add
    if(s < 0 || index == weights.length) {
      return false;
    }

    // either add the item or skip it
    return knapsack(s - weights[index], weights, index + 1) || knapsack(s, weights, index + 1);
  }
}
