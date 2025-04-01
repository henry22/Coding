public class ArrayDeque<E> {
  private CycleArray<E> arr = new CycleArray<>();

  // 從隊頭插入元素，時間複雜度 O(1)
  void addFirst(E e) {
    arr.addFirst(e);
  }

  // 從隊尾插入元素，時間複雜度 O(1)
  void addLast(E e) {
    arr.addLast(e);
  }

  // 從隊頭刪除元素，時間複雜度 O(1)
  void removeFirst() {
    arr.removeFirst();
  }

  // 從隊尾刪除元素，時間複雜度 O(1)
  void removeLast() {
    arr.removeLast();
  }

  // 查看隊頭元素，時間複雜度 O(1)
  E peekFirst() {
    return arr.getFirst();
  }

  // 查看隊尾元素，時間複雜度 O(1)
  E peekLast() {
    return arr.getLast();
  }
}
