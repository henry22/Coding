import java.util.LinkedList;
import java.util.List;

public class LinearProbingHashMapSpecialValueMarking<K, V> {
  private static class KVNode<K, V> {
    K key;
    V val;

    KVNode(K key, V val) {
      this.key = key;
      this.val = val;
    }
  }

  // 被刪除的KVNode的占位符
  private final KVNode<K, V> DUMMY = new KVNode<>(null, null);

  // 真正存儲鍵值對的table數組
  private KVNode<K, V>[] table;
  // HashMap中的鍵值對個數
  private int size;
  // 默認的初始化容量
  private static final int INIT_CAP = 4;

  public LinearProbingHashMapSpecialValueMarking() {
    this(INIT_CAP);
  }

  public LinearProbingHashMapSpecialValueMarking(int cap) {
    size = 0;
    table = (KVNode<K, V>[]) new KVNode[cap];
  }

  // **** 增/改 ****

  // 添加 key -> val 鍵值對
  // 如果鍵 key 已存在，則將值修改為val
  public void put(K key, V val) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    // 負載因子默認設為0.75，超過則擴容
    if(size >= table.length * 0.75) {
      resize(table.length * 2);
    }

    int index = getKeyIndex(key);

    if(index != -1) {
      // key 已存在，修改對應的val
      table[index].val = val;
      return;
    }

    // key不存在
    KVNode<K, V> x = new KVNode<>(key, val);
    // 在table中找一個空位或者占位符，插入
    index = hash(key);

    while(table[index] != null && table[index] != DUMMY) {
      index = (index + 1) % table.length;
    }

    table[index] = x;
    size++;
  }

  // **** 刪 ****

  // 刪除key和對應的val，並返回val
  // 若key不存在，則返回null
  public void remove(K key) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    // 縮容
    if(size < table.length / 8) {
      resize(table.length / 2);
    }

    int index = getKeyIndex(key);

    if(index == -1) {
      // key不存在，不需要remove
      return;
    }

    // 開始remove
    // 直接用占位符表示刪除
    table[index] = DUMMY;
    size--;
  }

  // **** 查 ****

  // 返回key對應的val
  // 如果key不存在，則返回null
  public V get(K key) {
    if(key == null) {
      throw new IllegalArgumentException("key is null");
    }

    int index = getKeyIndex(key);

    if(index == -1) {
      return null;
    }

    return table[index].val;
  }

  public boolean containsKey(K key) {
    return getKeyIndex(key) != -1;
  }

  public List<K> keys() {
    LinkedList<K> keys = new LinkedList<>();

    for(KVNode<K, V> entry : table) {
      if(entry != null && entry != DUMMY) {
        keys.addLast(entry.key);
      }
    }

    return keys;
  }

  public int size() {
    return size;
  }

  // 對key進行線性探查，返回一個索引
  // 根據keys[i]是否為null判斷是否找到對應的key
  private int getKeyIndex(K key) {
    int step = 0;
    for(int i = hash(key); table[i] != null; i = (i + 1) % table.length) {
      step++;
      //防止死循環
      if(step > table.length) {
        // 這裡可以觸發一次resize，把標記為刪除的占位符清理掉
        resize(table.length);
        return -1;
      }

      KVNode<K, V> entry = table[i];

      // 遇到占位符直接跳過
      if(entry == DUMMY) {
        continue;
      }

      if(entry.key.equals(key)) {
        return i;
      }
    }

    return -1;
  }

  // 哈希函數，將鍵印射到table的索引
  // [0, table.length - 1]
  private int hash(K key) {
    // int: 0000 0000 0000 ... 0000
    //    : 0111 1111 1111 ... 1111
    return (key.hashCode() & 0x7fffffff) % table.length;
  }

  private void resize(int cap) {
    LinearProbingHashMapSpecialValueMarking<K, V> newMap = new LinearProbingHashMapSpecialValueMarking<>(cap);

    for(KVNode<K, V> entry : table) {
      if(entry != null && entry != DUMMY) {
        newMap.put(entry.key, entry.val);
      }
    }

    this.table = newMap.table;
  }

  public static void main(String[] args) {
    LinearProbingHashMapSpecialValueMarking<Integer, Integer> map = new LinearProbingHashMapSpecialValueMarking<>();

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
