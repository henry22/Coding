import java.util.Queue;
import java.util.LinkedList;

public class QueuePractice {
  public static void main(String[] args) {
    Queue<Integer> q = new LinkedList<>();

    q.offer(10);
    q.offer(20);
    q.offer(30);

    System.out.println(q.isEmpty()); // false
    System.out.println(q.size()); // 3
    System.out.println(q.peek()); // 10

    q.poll();

    System.out.println(q.peek()); // 20
  }
}