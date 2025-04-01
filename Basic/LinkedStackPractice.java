import java.util.LinkedList;

// 用鏈表作為底層數據結構實現棧
public class LinkedStackPractice<E> {
  private final LinkedList<E> list = new LinkedList<>();

  // 向棧頂插入元素，時間複雜度 O(1)
  void push(E e) {
    list.addLast(e);
  }

  // 從棧頂彈出元素，時間複雜度 O(1)
  E pop() {
    return list.removeLast();
  }

  // 查看棧頂元素，時間複雜度 O(1)
  E peek() {
    return list.getLast();
  }

  // 返回棧中的元素個數，時間複雜度 O(1)
  int size() {
    return list.size();
  }

  public static void main(String[] args) {
    LinkedStackPractice<Integer> stack = new LinkedStackPractice<>();

    stack.push(10);
    stack.push(20);
    stack.push(30);

    System.out.println(stack.peek()); // 30
    System.out.println(stack.pop()); // 30
    System.out.println(stack.peek()); // 20

    stack.pop();
    System.out.println(stack.peek()); // 10
  }
}