package ru.nsu.fit.maksimenkov.tree;

import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Depth First Search iterator.
 *
 * @param <T> type of data.
 *
 */
public class DepthFirstSearchIterator<T>  implements Iterator<Node<T>> {
  private final Set<Node<T>> visited = new HashSet<>();
  private Deque<Iterator<Node<T>>> stack;
  private final Node<T> node;
  private Node<T> next;


  /**
   * Depth First Search iterator.
   *
   * @param g root of tree.
   *
   */
  public DepthFirstSearchIterator(Node<T> g) {
    stack.push(g.getChildrenIterator());
    node = g;
    next = g;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasNext() {
    return this.next != null;
  }

  @Override
  public Node<T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    try {
      visited.add(next);
      return next;
    } finally {
      advance();
    }
  }

  private void advance() throws ConcurrentModificationException {
    Iterator<Node<T>> neighbors = stack.peek();
    do {
      while (!neighbors.hasNext()) {
        stack.pop();
        if (stack.isEmpty()) {
          next = null;
          return;
        }
        neighbors = stack.peek();
      }
      next = neighbors.next();
      if (next.getModCount() < node.getModCount()) {
        throw new ConcurrentModificationException();
      }
    } while (visited.contains(next));
    stack.push(node.getChildrenIterator());
  }
}