import java.util.LinkedList;
import java.util.List;

public class ChainingHashMap<K, V> {
  // 拉鏈法使用的單鏈表節點，存儲 key-value 對
  private static class KVNode<K, V> {
    K key;
    V value;
    // 因為我們使用了內置的 LinkedList 類，所以不用 next 指針
    // 不用我們自己實現鏈表的邏輯
    KVNode(K key, V val) {
      this.key = key;
      this.value = val;
    }
  }

  // 哈希表的底層數組，每個數組元素是一個鏈表，鏈表中每個節點是 KVNode 存儲鍵值對
  private LinkedList<KVNode<K, V>>[] table;

  // 哈希表中存入的鍵值對個數
  private int size;
  // 底層數組的初始容量
  private static final int INIT_CAP = 4;

  public ChainingHashMap() {
    this(INIT_CAP);
  }

  public ChainingHashMap(int initCapacity) {
    size = 0;
    // 保證底層數組的容量至少爲 1，因為 hash 函數中有求餘運算，避免出現除以 0 的情況
    initCapacity = Math.max(initCapacity, 1);
    // 初始化哈希表
    table = (LinkedList<KVNode<K, V>>[]) new LinkedList[initCapacity];
    for(int i = 0; i < table.length; i++) {
      table[i] = new LinkedList<>();
    }
  }

  // **** 增/改 ****

  // 添加 key -> val 鍵值對
  // 如果鍵 key 已存在，則將值修改為 val
  public void put(K key, V val) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    LinkedList<KVNode<K, V>> list = table[hash(key)];
    // 如果 key 之前存在，則修改對應的 val
    for(KVNode<K, V> node : list) {
      if(node.key.equals(key)) {
        node.value = val;
        return;
      }
    }
    // 如果 key 之前不存在，則插入，size 增加
    list.add(new KVNode<>(key, val));
    size++;

    // 如果元素數量超過了負載因子，進行擴容
    if(size >= table.length * 0.75) {
      resize(table.length * 2);
    }
  }

  // **** 刪 ****

  // 刪除 key 和對應的 val
  public void remove(K key) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    LinkedList<KVNode<K, V>> list = table[hash(key)];
    // 如果 key 存在，則刪除，size 減少
    for(KVNode<K, V> node : list) {
      if(node.key.equals(key)) {
        list.remove(node);
        size--;

        // 縮容，當負載因子小於 0.125 時，縮容
        if(size <= table.length / 8) {
          resize(table.length / 4);
        }

        return;
      }
    }
  }

  // **** 查 ****

  // 返回 key 對應的 val，如果 key 不存在，則返回 null
  public V get(K key) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    LinkedList<KVNode<K, V>> list = table[hash(key)];

    for(KVNode<K, V> node : list) {
      if(node.key.equals(key)) {
        return node.value;
      }
    }

    return null;
  }

  // 返回所有 key
  public List<K> keys() {
    List<K> keys = new LinkedList<>();
    for(LinkedList<KVNode<K, V>> list : table) {
      for(KVNode<K, V> node : list) {
        keys.add(node.key);
      }
    }

    return keys;
  }

  // **** 其他工具函數 ****
  public int size() {
    return size;
  }

  // 哈希函數，將鍵映射到 table 的索引
  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % table.length;
  }

  private void resize(int newCap) {
    // 構造一個新的 HashMap
    // 避免 newCap 為 0，造成求模運算產生除以 0 的異常
    newCap = Math.max(newCap, 1);
    ChainingHashMap<K, V> newMap = new ChainingHashMap<>(newCap);
    // 窮舉當前 HashMap 中的所有鍵值對
    for(LinkedList<KVNode<K, V>> list : table) {
      for(KVNode<K, V> node : list) {
        // 將鍵值對轉移到新的 HashMap 中
        newMap.put(node.key, node.value);
      }
    }
    // 將當前 HashMap 的底層 table 換掉
    this.table = newMap.table;
  }

  public static void main(String[] args) {
    ChainingHashMap<Integer, Integer> map = new ChainingHashMap<>();

    map.put(1, 1);
    map.put(2, 2);
    map.put(3, 3);
    System.out.println(map.get(1)); // 1
    System.out.println(map.get(2)); // 2

    map.put(1, 100);
    System.out.println(map.get(1)); // 100

    map.remove(2);
    System.out.println(map.get(2)); // null
    // [1, 3] (順序可能不同)
    System.out.println(map.keys());

    map.remove(1);
    map.remove(2);
    map.remove(3);
    System.out.println(map.get(1)); // null
  }
}
