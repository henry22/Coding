public class ArrayQueuePractice<E> {
  private CycleArray<E> arr;

  public ArrayQueuePractice() {
    arr = new CycleArray<>();
  }

  public void push(E e) {
    arr.addLast(e);
  }

  public void pop() {
    arr.removeFirst();
  }

  public E peek() {
    return arr.getFirst();
  }

  public int size() {
    return arr.size();
  }
}
