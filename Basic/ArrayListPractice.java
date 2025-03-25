import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayListPractice {
  public static void main(String[] args) {
    int n = 10;

    ArrayList<Integer> nums = new ArrayList<>(Collections.nCopies(n, 0));
    ArrayList<Integer> nums2 = new ArrayList<>(Arrays.asList(1, 3, 5));

    System.out.println(nums.isEmpty()); // false
    System.out.println(nums.size()); // 10
    nums.add(20);
    System.out.println(nums.size()); // 11
    System.out.println(nums.get(nums.size() - 1)); // 20
    nums.remove(nums.size() - 1);
    System.out.println(nums.size()); // 10
    nums.set(0, 11);
    System.out.println(nums.get(0)); // 11
    nums.add(3, 99);;
    nums.remove(2);
    Collections.swap(nums, 0, 1);
    for(int num : nums) {
      System.out.print(num + " "); // 0 11 99 0 0 0 0 0 0 0
    }

    System.out.println();
  }
}