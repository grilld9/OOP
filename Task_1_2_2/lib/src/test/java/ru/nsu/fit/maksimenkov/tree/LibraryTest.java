/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.maksimenkov.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
  @Test
  void addNewNodeTest() {
    Node<String> tree = new Node<String>();
    tree.add("A");
    tree.add("B");
    tree.add("AB");
    BreadthFirstIterator<String> bfs = new BreadthFirstIterator<>(tree);
    Assertions.assertEquals("A", bfs.next());
    Assertions.assertEquals("B", bfs.next());
    Assertions.assertEquals("AB", bfs.next());
  }
}
