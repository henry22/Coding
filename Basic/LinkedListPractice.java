import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListPractice {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
    System.out.println(list.isEmpty()); // false
    System.out.println(list.size()); // 5
    list.addFirst(0);
    list.addLast(6);
    System.out.println(list.getFirst() + " " + list.getLast()); // 0 6
    list.removeFirst();
    list.removeLast();
    list.add(2, 99);
    list.remove(1);

    for(int val : list)  {
      System.out.print(val + " "); // 1 99 3 4 5
    }
    System.out.println();
  }
}