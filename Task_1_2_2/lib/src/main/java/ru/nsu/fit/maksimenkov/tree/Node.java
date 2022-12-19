package ru.nsu.fit.maksimenkov.tree;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Class of tree node (maybe root).
 *
 * @param <T> type of data.
 */
public class Node<T> {

  /**
   * value in node.
   */
  public T value;

  /**
   * flag for catching exception.
   */
  public boolean isIteratorWorks = false;

  /**
   * list of children of this node.
   */
  public ArrayList<Node<T>> children = new ArrayList<>();

  /**
   * method of adding some element to this node.
   *
   * @param element we need to add.
   * @return new node of curr node.
   * @throws ConcurrentModificationException .
   */
  public Node<T> add(T element) throws  ConcurrentModificationException {
    if (isIteratorWorks) {
      throw new ConcurrentModificationException();
    }
    if (this.value == null) {
      this.value = element;
      return this;
    } else {
      Node<T> node = new Node<>();
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
  public void add(Node<T> node, T element) throws ConcurrentModificationException {
    if (node.isIteratorWorks) {
      throw new ConcurrentModificationException();
    }
    if (node.value == null) {
      node.value = element;
    } else {
      Node<T> newNode = new Node<>();
      newNode.value = element;
      node.children.add(newNode);
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
  public void remove(Node<T> node) throws NoSuchElementException, ConcurrentModificationException {
    if (isIteratorWorks) {
      throw new ConcurrentModificationException();
    }
    if (!children.remove(node)) {
      throw new NoSuchElementException();
    }
  }
}

