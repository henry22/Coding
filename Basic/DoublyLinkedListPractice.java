import java.util.NoSuchElementException;

public class DoublyLinkedListPractice<E> {
  // 虛擬頭尾節點
  private final Node<E> head, tail;
  private int size;

  // 雙鏈表節點
  private static class Node<E> {
    E val;
    Node<E> next;
    Node<E> prev;

    Node(E val) {
      this.val = val;
    }
  }

  // 構造函數初始化虛擬頭尾節點
  public DoublyLinkedListPractice() {
    this.head = new Node<>(null);
    this.tail = new Node<>(null);
    head.next = tail;
    tail.prev = head;
    this.size = 0;
  }

  // ***** 增 *****
  public void addLast(E e) {
    Node<E> newNode = new Node<>(e);
    Node<E> temp = tail.prev;
    // temp <-> newNode
    temp.next = newNode;
    newNode.prev = temp;
    // temp <-> newNode <-> tail
    newNode.next = tail;
    tail.prev = newNode;
    size++;
  }

  public void addFirst(E e) {
    Node<E> newNode = new Node<>(e);
    Node<E> temp = head.next;
    // newNode  <-> temp
    temp.prev = newNode;
    newNode.next = temp;
    // head <-> newNode <-> temp
    head.next = newNode;
    newNode.prev = head;
    size++;
  }

  public void add(int index, E element) {
    checkPositionIndex(index);

    if(index == size) {
      addLast(element);
      return;
    }

    // 找到 index 對應的 Node
    Node<E> p = getNode(index);
    // temp <-> p
    Node<E> temp = p.prev;
    // 新要插入的 Node
    Node<E> newNode = new Node<>(element);
    // temp <-> newNode <-> p
    p.prev = newNode;
    temp.next = newNode;

    newNode.prev = temp;
    newNode.next = p;

    size++;
  }

  // ***** 刪 *****
  public E removeFirst() {
    if(size < 1) {
      throw new NoSuchElementException();
    }
    // 虛擬節點的存在是我們不用考慮空指針的問題
    // head <-> deletedNode <-> temp
    Node<E> deletedNode = head.next;
    Node<E> temp = deletedNode.next;
    // head <-> temp
    head.next = temp;
    temp.prev = head;

    E val = deletedNode.val;
    // delete deletedNode
    deletedNode.prev = null;
    deletedNode.next = null;
    size--;

    return val;
  }

  public E removeLast() {
    if(size < 1) {
      throw new NoSuchElementException();
    }
    // temp <-> deletedNode <-> tail
    Node<E> deletedNode = tail.prev;
    Node<E> temp = tail.prev.prev;
    // temp <-> tail
    tail.prev = temp;
    temp.next = tail;

    E val = deletedNode.val;
    // delete deletedNode
    deletedNode.prev = null;
    deletedNode.next = null;
    size--;

    return val;
  }

  public E remove(int index) {
    checkElementIndex(index);
    // 找到 index 對應的 Node
    Node<E> p = getNode(index);
    // prev <-> p <-> next
    Node<E> prev = p.prev;
    Node<E> next = p.next;
    // prev <-> next
    prev.next = next;
    next.prev = prev;

    E val = p.val;
    // delete p
    p.prev = null;
    p.next = null;
    size--;

    // 返回被刪除節點的值
    return val;
  }

  // **** 查 ****
  public E get(int index) {
    checkElementIndex(index);
    // 找到 index 對應的 Node
    Node<E> p = getNode(index);

    return p.val;
  }

  public E getFirst() {
    if(size < 1) {
      throw new NoSuchElementException();
    }

    return head.next.val;
  }

  public E getLast() {
    if(size < 1) {
      throw new NoSuchElementException()
    }

    return tail.prev.val;
  }

  // **** 改 ****
  public E set(int index, E val) {
    checkElementIndex(index);
    // 找到 index 對應的 Node
    Node<E> p = getNode(index);
    E oldVal = p.val;
    p.val = val;

    return oldVal;
  }

  // ***** 其他工具函數 *****
  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private Node<E> getNode(int index) {
    checkElementIndex(index);
    Node<E> p = head.next;
    // TODO: 可以優化，通過 index 判斷從 head 還是 tail 開始遍歷
    for(int i = 0; i < index; i++) {
      p = p.next;
    }

    return p;
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
    System.out.println("size = " + size);
    for(Node<E> p = head.next; p != tail; p = p.next) {
      System.out.print(p.val + " <-> ");
    }
    System.out.println("null");
    System.out.println();
  }

  public static void main(String[] args) {
    DoublyLinkedListPractice<Integer> doublyLinkedList = new DoublyLinkedListPractice<>();
    doublyLinkedList.addLast(1);
    doublyLinkedList.addLast(2);
    doublyLinkedList.addLast(3);
    doublyLinkedList.addFirst(0);
    doublyLinkedList.add(2, 100);
    doublyLinkedList.display();
  }
}
