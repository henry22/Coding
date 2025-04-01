import java.util.LinkedList;

// 用鏈表作為底層數據結構實現隊列
public class LinkedQueuePractice<E> {
  private final LinkedList<E> list = new LinkedList<>();

  // 向隊尾插入元素，時間複雜度 O(1)
  void push(E e) {
    list.addLast(e);
  }

  // 從隊頭刪除元素，時間複雜度 O(1)
  E pop() {
    return list.removeFirst();
  }

  // 查看隊頭元素，時間複雜度 O(1)
  E peek() {
    return list.getFirst();
  }

  // 返回隊列中的元素個數，時間複雜度 O(1)
  int size() {
    return list.size();
  }

  public static void main(String[] args) {
    LinkedQueuePractice<Integer> q = new LinkedQueuePractice<>();

    q.push(10);
    q.push(20);
    q.push(30);

    System.out.println(q.peek()); // 10
    System.out.println(q.pop()); // 10
    System.out.println(q.pop()); // 20
    System.out.println(q.peek()); // 30
  }
}