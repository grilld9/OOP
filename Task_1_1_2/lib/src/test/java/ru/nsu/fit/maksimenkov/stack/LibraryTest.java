/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.maksimenkov.stack;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class LibraryTest {

  void printStack(Stack stack){
    int n = stack.count();
    int[] arr = new int[n];
    for (int i = 1, j = 0; i <= n; i++, j++){
      arr[j] = stack.arr[i];
    }
    System.out.println(Arrays.toString(arr));
  }
  @Test
  void test1() {
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    Stack stack3 = new Stack();
    stack1.push(2);
    printStack(stack1);
    stack1.push(7);
    printStack(stack1);
    stack2.push(4);
    stack2.push(8);
    stack1.pushStack(stack2);
    printStack(stack1);

    int x = stack1.pop();
    printStack(stack1);
    stack3 = stack1.popStack(2);
    printStack(stack1);
    System.out.println(stack1.count());
  }
}