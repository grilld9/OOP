package ru.nsu.fit.maksimenkov.tree;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class of tree node (maybe root).
 *
 * @param <T> type of data.
 */
public class Node<T> implements Iterable<Node<T>> {

  private Node<T> parent;

  private enum SearchType {
    DFS,
    BFS
  }

  private SearchType searchType;

  public void setDfs() {
    searchType = SearchType.DFS;
  }

  public void setBfs() {
    searchType = SearchType.BFS;
  }

  public void setParent(Node<T> parentToSet) {
    parent = parentToSet;
  }

  public Node<T> getParent() {
    return parent;
  }

  /**
   * value in node.
   */
  public T value;

  /**
   * flag for catching exception.
   */
  private int modCount;

  public int getModCount() {
    return modCount;
  }

  public void setModCount(int count) {
    modCount = count;
    Node<T> nextParent = getParent();
    while (nextParent != null) {
      nextParent.setModCount(count);
      nextParent = nextParent.getParent();
    }
  }

  /**
   * list of children of this node.
   */
  public ArrayList<Node<T>> children = new ArrayList<>();

  /**
   * method of adding some element to this node.
   *
   * @param element we need to add.
   * @return new node of curr node.
   */
  public Node<T> add(T element) {
    if (this.value == null) {
      this.value = element;
      setModCount(this.getModCount() + 1);
      return this;
    } else {
      Node<T> node = new Node<>();
      node.setParent(this);
      node.setModCount(this.getModCount() + 1);
      node.value = element;
      this.children.add(node);
      return node;
    }
  }

  /**
   * method of adding some element to some node.
   *
   * @param node node for adding new element.
   * @param element some element.
   * @throws ConcurrentModificationException .
   */
  public void add(Node<T> node, T element) {
    if (node.value == null) {
      node.value = element;
      node.setModCount(node.getModCount() + 1);
    } else {
      Node<T> newNode = new Node<>();
      newNode.value = element;
      node.children.add(newNode);
      newNode.setParent(node);
      newNode.setModCount(node.getModCount() + 1);
    }
  }

  /**
   *  removes some element in this tree.
   *
   * @param node node to remove.
   *
   * @throws NoSuchElementException in case no element with this adress.
   * @throws ConcurrentModificationException .
   */
  public void remove(Node<T> node) throws NoSuchElementException {
    this.setModCount(node.getModCount() + 1);
    if (!children.remove(node)) {
      throw new NoSuchElementException();
    }
  }

  public Iterator<Node<T>> iterator() {
    if (searchType == SearchType.DFS) {
      return new DepthFirstSearchIterator<>(this);
    } else {
      return new BreadthFirstIterator<>(this);
    }
  }
}



