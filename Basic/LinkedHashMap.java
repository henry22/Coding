import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinkedHashMap<K, V> {
  private static class Node<K, V> {
    K key;
    V val;
    Node<K, V> next, prev;

    Node(K key, V val) {
      this.key = key;
      this.val = val;
    }
  }

  private final Node<K, V> head, tail;
  private final HashMap<K, Node<K, V>> map = new HashMap<>();

  public LinkedHashMap() {
    head = new Node<>(null, null);
    tail = new Node<>(null, null);
    head.next = tail;
    tail.prev = head;
  }

  public V get(K key) {
    Node<K, V> node = map.get(key);

    if(node != null) {
      return node.val;
    }

    return null;
  }

  public void put(K key, V val) {
    // 若為新插入的節點，則同時插入鏈表和map
    if(!map.containsKey(key)) {
      // 插入新的Node
      Node<K, V> node = new Node<>(key, val);
      addLastNode(node);
      map.put(key, node);
      return;
    }
    // 若存在，則替換之前的val
    map.get(key).val = val;
  }

  public void remove(K key) {
    // 若key不存在，直接返回
    if(!map.containsKey(key)) {
      return;
    }
    // 若key存在，則需要同時在哈希表和鏈表中刪除
    Node<K, V> node = map.get(key);
    map.remove(key);
    removeNode(node);
  }

  public boolean containsKey(K key) {
    return map.containsKey(key);
  }

  public List<K> keys() {
    List<K> keyList = new ArrayList<>();

    for(Node<K, V> p = head.next; p != tail; p = p.next) {
      keyList.add(p.key);
    }

    return keyList;
  }

  public int size() {
    return map.size();
  }

  private void addLastNode(Node<K, V> x) {
    Node<K, V> temp = tail.prev;
    // temp <-> tail

    x.next = tail;
    x.prev = temp;
    // temp <- x -> tail

    temp.next = x;
    tail.prev = x;
    // temp <-> x <-> tail
  }

  private void removeNode(Node<K, V> x) {
    Node<K, V> prev = x.prev;
    Node<K, V> next = x.next;
    // prev <-> x <-> next

    prev.next = next;
    next.prev = prev;
    // prev <-> next

    x.next = null;
    x.prev = null;
  }

  public static void main(String[] args) {
    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

    map.put("a", 1);
    map.put("b", 2);
    map.put("c", 3);
    map.put("d", 4);
    map.put("e", 5);

    System.out.println(map.keys()); // [a, b, c, d, e]
    map.remove("c");
    System.out.println(map.keys()); // [a, b, d, e]
  }
}
