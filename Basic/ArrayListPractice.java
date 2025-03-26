import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListPractice<E> {
  // 真正儲存數據的底層數組
  private E[] data;
  // 記錄當前元素個數
  private int size;
  // 默認初始容量
  private static final int INIT_CAP = 1;

  public ArrayListPractice() {
    this(INIT_CAP);
  }

  public ArrayListPractice(int initCapacity) {
    data = (E[]) new Object[initCapacity];
    size = 0;
  }

  // 增
  public void addLast(E e) {
    int cap = data.length;
    // 看 data 數組容量夠不夠
    if(size == cap) {
      resize(2 * cap);
    }
    // 在尾部插入元素
    data[size] = e;
    size++;
  }

  public void add(int index, E e) {
    // 檢查索引越界
    checkPositionIndex(index);

    int cap = data.length;
    // 看 data 數組容量夠不夠
    if(size == cap) {
      resize(2 * cap);
    }

    // 搬移數據 data[index..] -> data[index+1..]
    // 給新元素騰出位置
    for(int i = size - 1; i >= index; i--) {
      data[i + 1] = data[i];
    }

    // 插入新元素
    data[index] = e;
    size++;
  }

  public void addFirst(E e) {
    add(0, e);
  }

  // 刪
  public E removeLast() {
    if(size == 0) {
      throw new NoSuchElementException();
    }

    int cap =data.length;
    // 可以縮容，節約空間
    if(size == cap / 4) {
      resize(cap / 2);
    }

    E deletedVal = data[size - 1];
    // 刪除最後一個元素
    // 必須給最後一個元素置為 null，否則會有內存泄漏
    data[size - 1] = null;
    size--;

    return deletedVal;
  }

  public E remove(int index) {
    // 檢查索引越界
    checkElementIndex(index);

    int cap = data.length;
    // 可以縮容，節約空間
    if(size == cap / 4) {
      resize(cap / 2);
    }

    E deletedVal = data[index];

    // 搬移數據 data[index+1..] -> data[index..]
    for(int i = index + 1; i < size; i++) {
      data[i - 1] = data[i];
    }

    data[size - 1] = null;
    size--;

    return deletedVal;
  }

  public E removeFirst() {
    return remove(0);
  }

  // 查
  public E get(int index) {
    // 檢查索引越界
    checkElementIndex(index);

    return data[index];
  }

  // 改
  public E set(int index, E element) {
    // 檢查索引越界
    checkElementIndex(index);
    // 修改數據
    E oldVal = data[index];
    data[index] = element;
    return oldVal;
  }

  // 工具方法
  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  // 將 data 的容量改為 newCap
  private void resize(int newCap) {
    E[] temp = (E[]) new Object[newCap];

    for(int i = 0; i < size; i++) {
      temp[i] = data[i];
    }

    data = temp;
  }

  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }

  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
  }

  // 檢查 index 索引位置是否可以存在元素
  private void checkElementIndex(int index) {
    if(!isElementIndex(index)) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  // 檢查 index 索引位置是否可以添加元素
  private void checkPositionIndex(int index) {
    if(!isPositionIndex(index)) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  private void display() {
    System.out.println("size = " + size + " cap = " + data.length);
    System.out.println(Arrays.toString(data));
  }

  public static void main(String[] args) {
    // 初始容量設置為 3
    ArrayListPractice<Integer> arr = new ArrayListPractice<>(3);

    // 添加 5 個元素
    for(int i = 1; i <= 5; i++) {
      arr.addLast(i);
    }

    arr.remove(3);
    arr.add(1, 9);
    arr.addFirst(100);

    int val = arr.removeLast();

    for(int i = 0; i < arr.size(); i++) {
      System.out.println(arr.get(i));
    }
  }
}