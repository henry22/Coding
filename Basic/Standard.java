public class Standard {
  public static void main(String[] args) {
    int a = 10;

    System.out.println(a); // 10

    System.out.println("Hello" + ", " + "World!"); // Hello, World!

    String s = "abc";;

    System.out.println(s + " " + a); // abc 10

    System.out.printf("%s %d\n", s, a); // abc 10


    if(a > 5) {
      System.out.println("a > 5"); // a > 5
    } else if(a == 5) {
      System.out.println("a == 5");
    } else {
      System.out.println("a < 5");
    }

    for(int i = 0; i < 5; i++) {
      System.out.println(i + " "); // 0 1 2 3 4
    }
    System.out.println();

    int num = 100;

    while(num > 0) {
      System.out.print(num + " "); // 100 50 25 12 6 3 1
      num /= 2;
    }

    System.out.println();
  }
}