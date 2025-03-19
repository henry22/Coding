package Recursion;

public class FactorialIteration {

  /**
   * @param args
   */
  public static void main(String[] args) {
    FactorialIteration factorialIteration = new FactorialIteration();
    int result = factorialIteration.factorial(5);
    System.out.println(result);
  }

  public int factorial(int n) {
    int total = 1;

    for(int i = 1; i <= n; i++) {
      total *= i;
    }

    return total;
  }
}
