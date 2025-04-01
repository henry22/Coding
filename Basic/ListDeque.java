import java.util.LinkedList;

public class ListDeque<E> {
  private LinkedList<E> list = new LinkedList<>();

  // 從隊頭插入元素，時間複雜度 O(1)
  void addFirst(E e) {
    list.addFirst(e);
  }

  // 從隊尾插入元素，時間複雜度 O(1)
  void addLast(E e) {
    list.addLast(e);
  }

  // 從隊頭刪除元素，時間複雜度 O(1)
  E removeFirst() {
    return list.removeFirst();
  }

  // 從隊尾刪除元素，時間複雜度 O(1)
  E removeLast() {
    return list.removeLast();
  }

  // 查看隊頭元素，時間複雜度 O(1)
  E peekFirst() {
    return list.getFirst();
  }

  // 查看隊尾元素，時間複雜度 O(1)
  E peekLast() {
    return list.getLast();
  }

  public static void main(String[] args) {
    ListDeque<Integer> deque = new ListDeque<>();

    deque.addFirst(1);
    deque.addFirst(2);
    deque.addLast(3);
    deque.addLast(4);

    System.out.println(deque.removeFirst()); // 2
    System.out.println(deque.removeLast()); // 4
    System.out.println(deque.peekFirst()); // 1
    System.out.println(deque.peekLast()); // 3
  }
}
