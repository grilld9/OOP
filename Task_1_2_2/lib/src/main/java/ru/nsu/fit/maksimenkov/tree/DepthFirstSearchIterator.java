package ru.nsu.fit.maksimenkov.tree;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

/**
 * Depth First Search iterator.
 *
 * @param <T> type of data.
 *
 */
public class DepthFirstSearchIterator<T>  implements Iterator<Node<T>> {
  private Set<Node<T>> visited = new HashSet<>();
  private Stack<Iterator<Node<T>>> stack = new Stack<>();
  private Node<T> node;
  private Node<T> next;

  private int modCount;

  public int getModCount() {
    return modCount;
  }

  /**
   * Depth First Search iterator.
   *
   * @param g root of tree.
   *
   */
  public DepthFirstSearchIterator(Node<T> g) {
    this.stack.push(g.children.iterator());
    this.node = g;
    this.next = g;
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
      this.visited.add(this.next);
      return this.next;
    } finally {
      this.advance();
    }
  }

  private void advance() throws ConcurrentModificationException {
    Iterator<Node<T>> neighbors = this.stack.peek();
    do {
      while (!neighbors.hasNext()) {
        this.stack.pop();
        if (this.stack.isEmpty()) {
          this.next = null;
          return;
        }
        neighbors = this.stack.peek();
      }
      this.next = neighbors.next();
      if (this.next.getModCount() < this.node.getModCount()) {
        throw new ConcurrentModificationException();
      }
    } while (this.visited.contains(this.next));
    this.stack.push(this.node.children.iterator());
  }
}