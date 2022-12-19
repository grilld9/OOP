package ru.nsu.fit.maksimenkov.tree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

/**
 * Breadth First Search Iterator.
 *
 * @param <T> type of data.
 */
public class BreadthFirstIterator<T> implements Iterator<T> {
  private Set<Node<T>> visited = new HashSet<>();
  private Queue<Node<T>> queue = new LinkedList<>();
  private Node<T> node;

  /**
   * initialization method.
   *
   * @param t tree collection.
   */
  public BreadthFirstIterator(Node<T> t) {
    this.node = t;
    this.queue.add(t);
    this.visited.add(t);
    t.isIteratorWorks = true;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasNext() {
    return !this.queue.isEmpty();
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Node<T> next = queue.remove();
    for (Node<T> neighbor : next.children) {
      if (!this.visited.contains(neighbor)) {
        this.queue.add(neighbor);
        this.visited.add(neighbor);
      }
    }
    return next.value;
  }
}