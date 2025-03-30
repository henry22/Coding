public class CycleArray<T> {
  private T[] arr;
  private int start;
  private int end;
  private int count;
  private int size;

  public CycleArray() {
    this(1);
  }

  @SuppressWarnings("unchecked")
  public CycleArray(int size) {
    this.size = size;
    // 因為 Java 不支持直接創建泛型數組，所以這裡使用了類型轉換
    this.arr = (T[]) new Object[size];
    // start 指向第一個有效元素的索引，閉區間
    this.start = 0;
    // 切記 end 是一個開區間
    // 即 end 指向最後一個有效元素的下一個位置索引
    this.end = 0;
    this.count = 0;
  }

  // 自動擴縮容輔助函數
  @SuppressWarnings("unchecked")
  private void resize(int newSize) {
    // 創建新的數組
    T[] newArr = (T[]) new Object[newSize];
    // 將舊數組的元素複製到新數組中
    for(int i = 0; i < count; i++) {
      newArr[i]  = arr[(start + i) % size];
    }
    arr = newArr;
    // 重置 start 和 end 指針
    start = 0;
    end = count;
    size = newSize;
  }

  // 在數組頭部添加元素，時間複雜度 O(1)
  public void addFirst(T val) {
    // 當數組滿時，擴容為原來的兩倍
    if(isFull()) {
      resize(size * 2);
    }
    // 因為 start 是閉區間，所以先左移，再賦值
    start = (start - 1 + size) % size;
    arr[start] = val;
    count++;
  }

  // 刪除數組頭部元素，時間複雜度 O(1)
  public void removeFirst() {
    if(isEmpty()) {
      throw new IllegalStateException("Array is empty");
    }
    // 因為 start 是閉區間，所以先賦值，再右移
    arr[start] = null;
    start = (start + 1) % size;
    count--;
    // 如果數組元素數量減少到原大小的四分之一，則減小數組大小為一半
    if(count > 0 && count == size / 4) {
      resize(size / 2);
    }
  }

  // 在數組尾部添加元素，時間複雜度 O(1)
  public void addLast(T val) {
    if(isFull()) {
      resize(size * 2);
    }
    // 因為 end 是開區間，所以先賦值，再右移
    arr[end] = val;
    end = (end + 1) % size;
    count++;
  }

  // 刪除數組尾部元素，時間複雜度 O(1)
  public void removeLast() {
    if(isEmpty()) {
      throw new IllegalStateException("Array is empty");
    }
    // 因為 end 是開區間，所以先左移，再賦值
    end = (end - 1 + size) % size;
    arr[end] = null;
    count--;
    // 縮容
    if(count > 0 && count == size / 4) {
      resize(size / 2);
    }
  }

  // 獲取數組頭部元素，時間複雜度 O(1)
  public T getFirst() {
    if(isEmpty()) {
      throw new IllegalStateException("Array is empty");
    }
    return arr[start];
  }

  // 獲取數組尾部元素，時間複雜度 O(1)
  public T getLast() {
    if(isEmpty()) {
      throw new IllegalStateException("Array is empty");
    }
    // end 是開區間，指向的是下一個元素的位置，所以要減1
    return arr[(end - 1 + size) % size];
  }

  public boolean isFull() {
    return count == size;
  }

  public int size() {
    return count;
  }

  public boolean isEmpty() {
    return count == 0;
  }
}
