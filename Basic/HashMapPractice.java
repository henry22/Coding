import java.util.HashMap;
import java.util.Map;

public class HashMapPractice {
  public static void main(String[] args) {
    HashMap<Integer, String>  hashmap = new HashMap<>();

    hashmap.put(1, "one");
    hashmap.put(2, "two");
    hashmap.put(3, "three");

    System.out.println(hashmap.isEmpty()); // false
    System.out.println(hashmap.size()); // 3

    if(hashmap.containsKey(2)) {
      System.out.println("Key 2 -> " + hashmap.get(2)); // Key 2 -> two
    } else {
      System.out.println("Key 2 not found");
    }

    System.out.println(hashmap.get(4)); // null
    System.out.println(hashmap.getOrDefault(4, "defaultVal")); // defaultVal
    hashmap.put(4, "four");
    System.out.println(hashmap.get(4)); // four
    hashmap.remove(3);

    if(hashmap.containsKey(3)) {
      System.out.println("Key 3 -> " + hashmap.get(3));
    } else {
      System.out.println("Key 3 not found"); // Key 3 not found
    }

    for(Map.Entry<Integer, String> pair : hashmap.entrySet()) {
      System.out.println(pair.getKey() + " -> " + pair.getValue()); // 1 -> one, 2 -> two, 4 -> four
    }
  }
}