package Recursion;

class FactorialRecursion {
  /**
   * @param args
   */
  public static void main(String[] args) {
    FactorialRecursion factorialRecursion = new FactorialRecursion();
    int result = factorialRecursion.factorial(5, 1);
    System.out.println(result);
  }

  public int factorial(int n, int total) {
    if(n == 1) {
      return total;
    }

    return factorial(n - 1, n * total);
  }
}