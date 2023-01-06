package ru.nsu.fit.maksimenkov.tree;

import java.util.ConcurrentModificationException;
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
public class BreadthFirstIterator<T> implements Iterator<Node<T>> {
  private final Set<Node<T>> visited = new HashSet<>();
  private final Queue<Node<T>> queue = new LinkedList<>();

  private final int modCount;

  /**
   * initialization method.
   *
   * @param t tree collection.
   */
  public BreadthFirstIterator(Node<T> t) {
    this.queue.add(t);
    this.visited.add(t);
    modCount = t.getModCount();
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
  public Node<T> next() throws  ConcurrentModificationException, NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Node<T> next = queue.remove();
    if (next.getModCount() > modCount) {
      throw new ConcurrentModificationException();
    }
    for (int i = 0; i < next.getChildrenCount(); i++) {
      Node<T> neighbor = next.getChild(i);
      if (!this.visited.contains(neighbor)) {
        this.queue.add(neighbor);
        this.visited.add(neighbor);
      }
    }
    return next;
  }
}