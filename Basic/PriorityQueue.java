import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<T> {
  private T[] heap;
  private int size;
  private final Comparator<? super T> comparator;

  @SuppressWarnings("unchecked")
  public PriorityQueue(int capacity, Comparator<? super T> comparator) {
    heap = (T[]) new Object[capacity];
    size = 0;
    this.comparator = comparator;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  // 父節點的索引
  private int parent(int node) {
    return (node - 1) / 2;
  }

  // 左子節點的索引
  private int left(int node) {
    return node * 2 + 1;
  }

  // 右子節點的索引
  private int right(int node) {
    return node * 2 + 2;
  }

  // 交換數組的兩個元素
  private void swap(int i, int j) {
    T temp = heap[i];
    heap[i]  = heap[j];;
    heap[j]  = temp;
  }

  // 查，返回堆頂元素，時間複雜度 O(1)
  public T peek() {
    if(isEmpty()) {
      throw new NoSuchElementException("Priority queue underflow");
    }

    return heap[0];
  }

  // 增，向堆中插入一個元素，時間複雜度 O(logN)
  public void push(T x) {
    // 擴容
    if(size == heap.length) {
      resize(2 * heap.length);;
    }
    // 把新元素追加到最後
    heap[size] = x;
    // 然後上浮到正確位置
    swim(size);
    size++;
  }

  // 刪，刪除堆頂元素，時間複雜度 O(logN)
  public T pop() {
    if(isEmpty())  {
      throw new NoSuchElementException("Priority queue underflow");
    }

    T res = heap[0];;
    // 把堆底元素放到堆頂
    swap(0, size - 1);
    // 避免對象游離
    heap[size - 1] = null;
    size--;
    // 然後下沉到正確位置
    sink(0);;
    // 縮容
    if((size > 0) && (size == heap.length / 4)) {
      resize(heap.length / 2);
    }

    return res;
  }

  // 上浮操作，時間複雜度是樹高 O(logN)
  private void swim(int node) {
    while(node > 0 && comparator.compare(heap[parent(node)], heap[node]) > 0) {
      swap(parent(node), node);
      node = parent(node);
    }
  }

  // 下沉操作，時間複雜度是樹高 O(logN)
  private void sink(int node) {
    while(left(node) < size || right(node) < size) {
      // 比較自己和左右子節點，看看誰最小
      int min = node;

      if(left(node) < size && comparator.compare(heap[left(node)], heap[min]) < 0) {
        min = left(node);
      }

      if(right(node) < size && comparator.compare(heap[right(node)], heap[min]) < 0) {
        min = right(node);
      }

      if(min == node) {
        break;
      }

      // 如果左右子節點中有比自己小的，就交換
      swap(node, min);
      node = min;
    }
  }

  // 調整堆的大小
  @SuppressWarnings("unchecked")
  private void resize(int capacity) {
    assert capacity > size;
    T[] temp = (T[]) new Object[capacity];

    for(int i = 0; i < size; i++) {
      temp[i] = heap[i];
    }

    heap = temp;
  }

  public static void main(String[] args) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(3, Comparator.naturalOrder());
    pq.push(3);
    pq.push(1);
    pq.push(4);
    pq.push(1);
    pq.push(5);
    pq.push(9);
    // 1 1 3 4 5 9
    while(!pq.isEmpty()) {
      System.out.print(pq.pop() + " ");
    }
  }
}
