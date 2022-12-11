package ru.nsu.fit.maksimenkov.tree;
import java.util.*;

public class BreadthFirstIterator<T> implements Iterator<T> {
  private Set<Node<T>> visited = new HashSet<>();
  private Queue<Node<T>> queue = new LinkedList<>();
  private Node<T> node;

  public BreadthFirstIterator(Node<T> t) {
    this.node = t;
    this.queue.add(t);
    this.visited.add(t);
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
    if (!hasNext()) throw new NoSuchElementException();
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