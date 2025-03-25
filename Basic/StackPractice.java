import java.util.Stack;

public class StackPractice {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();

    stack.push(10);
    stack.push(20);
    stack.push(30);

    System.out.println(stack.isEmpty()); // false
    System.out.println(stack.size()); // 3
    System.out.println(stack.peek()); // 30

    stack.pop();
    System.out.println(stack.peek()); // 20
  }
}