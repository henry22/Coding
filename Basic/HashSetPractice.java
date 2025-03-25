import java.util.Arrays;
import java.util.HashSet;

public class HashSetPractice {
  public static void main(String[] args) {
    HashSet<Integer> hashset = new HashSet<>(Arrays.asList(1, 2, 3, 4));
    System.out.println(hashset.isEmpty()); // false
    System.out.println(hashset.size()); // 4

    if(hashset.contains(3)) {
      System.out.println("Element 3 found "); // Element 3 found
    } else {
      System.out.println("Element 3 not found ");
    }

    hashset.add(5);
    hashset.remove(2);

    if(hashset.contains(2)) {
      System.out.println("Element 2 found ");
    } else {
      System.out.println("Element 2 not found "); // Element 2 not found
    }

    for(int element : hashset) {
      System.out.println(element); // 1 3 4 5
    }
  }
}