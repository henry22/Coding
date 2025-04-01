import java.util.ArrayList;

// 用數組作為底層數據結構實現棧
public class ArrayStackPractice<E> {
  private ArrayList<E> list = new ArrayList<>();

  // 向棧頂加入元素，時間複雜度 O(1)
  public void push(E e) {
    list.add(e);
  }

  // 從棧頂彈出元素，時間複雜度 O(1)
  public E pop() {
    return list.remove(list.size() - 1);
  }

  // 查看棧頂元素，時間複雜度 O(1)
  public E peek() {
    return list.get(list.size() - 1);
  }

  // 返回棧中的元素個數，時間複雜度 O(1)
  public int size() {
    return list.size();
  }
}
