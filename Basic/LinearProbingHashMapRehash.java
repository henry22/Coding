import java.util.LinkedList;
import java.util.List;

// rehash 方式實現的線性探查哈希表
public class LinearProbingHashMapRehash<K, V> {
  private static class KVNode<K, V> {
    K key;
    V val;

    KVNode(K key, V val) {
      this.key = key;
      this.val = val;
    }
  }

  // 真正存儲鍵值對的數組
  private KVNode<K, V>[] table;
  // HashMap中的鍵值對個數
  private int size;
  // 默認的初始化容量
  private static final int INIT_CAP = 4;

  public LinearProbingHashMapRehash() {
    this(INIT_CAP);
  }

  public LinearProbingHashMapRehash(int initCapacity) {
    size = 0;
    table = (KVNode<K, V>[]) new KVNode[initCapacity];
  }

  // **** 增/改 ****
  public void put(K key, V val) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    // 我們把負載因子默認設為0.75，超過則擴容
    if(size >= table.length * 0.75) {
      resize(table.length * 2);
    }

    int index = getKeyIndex(key);

    // key 已存在，修改對應的val
    if(table[index] != null) {
      table[index].val = val;
      return;
    }

    // key不存在，在空衛插入
    table[index] = new KVNode<>(key, val);
    size++;
  }

  // **** 刪 ****
  // 刪除key和對應的val
  public void remove(K key) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    // 縮容，當負載因子小於0.125時，縮容
    if(size <= table.length / 8) {
      resize(table.length / 4);
    }

    int index = getKeyIndex(key);

    if(table[index] == null) {
      // key不存在，不需要remove
      return;
    }

    // 開始remove
    table[index] = null;
    size--;
    // 保持元素連續性，進行rehash
    index = (index + 1) % table.length;
    for(; table[index] != null; index = (index + 1) % table.length) {
      KVNode<K, V> entry = table[index];
      table[index] = null;
      // 這裡減一，因為put裡面又會加一
      size--;
      put(entry.key, entry.val);
    }
  }

  // **** 查 ****
  // 返回key對應的val，如果key不存在，則返回null
  public V get(K key) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    int index = getKeyIndex(key);

    if(table[index] == null) {
      return null;
    }

    return table[index].val;
  }

  // 返回所有key (順序不固定)
  public List<K> keys() {
    LinkedList<K> keys = new LinkedList<>();

    for(KVNode<K, V> entry : table) {
      if(entry != null) {
        keys.addLast(entry.key);
      }
    }

    return keys;
  }

  // **** 其他工具函數 ****
  public int size() {
    return size;
  }

  // 哈希函數，將鍵印射到table的索引
  // [0, table.length - 1]
  private int hash(K key) {
    // int: 0000 0000 0000 ... 0000
    //    : 0111 1111 1111 ... 1111
    return (key.hashCode() & 0x7fffffff) % table.length;
  }

  // 對key進行線性探查，返回一個索引
  // 如果key不存在，返回的就是下一個為null的索引，可用於插入
  private int getKeyIndex(K key) {
    int index;

    for(index = hash(key); table[index] != null; index = (index + 1) % table.length) {
      if(table[index].key.equals(key)) {
        return index;
      }
    }

    return index;
  }

  private void resize(int newCap) {
    LinearProbingHashMapRehash<K, V> newMap = new LinearProbingHashMapRehash<>(newCap);

    for(KVNode<K, V> entry : table) {
      if(entry != null) {
        newMap.put(entry.key, entry.val);
      }
    }

    this.table = newMap.table;
  }

  public static void main(String[] args) {
    LinearProbingHashMapRehash<Integer, Integer> map = new LinearProbingHashMapRehash<>();
    map.put(1, 1);
    map.put(2, 2);
    map.put(10, 10);
    map.put(20, 20);
    map.put(30, 30);
    map.put(3, 3);

    System.out.println(map.get(1)); // 1
    System.out.println(map.get(2)); // 2
    System.out.println(map.get(20)); // 20

    map.put(1, 100);
    System.out.println(map.get(1)); // 100

    map.remove(20);
    System.out.println(map.get(20)); // null
    System.out.println(map.get(30)); // 30
  }
}
