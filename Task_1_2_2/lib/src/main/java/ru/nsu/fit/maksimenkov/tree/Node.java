package ru.nsu.fit.maksimenkov.tree;

import java.util.List;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class of tree node (maybe root).
 *
 * @param <T> type of data.
 */
public class Node<T> implements Iterable<Node<T>> {

  /**
   * list of children of this node.
   */
  private List<Node<T>> children;

  /**
   * value in node.
   */
  private T value;

  private Node<T> parent;

  private SearchType searchType;

  private int modCount;

  public void addChild(Node<T> child){
    children.add(child);
  }

  public int getChildrenCount() {
    return children.size();
  }

  public Node<T> getChild(int i) {
    return children.get(i);
  }

  public Iterator<Node<T>> getChildrenIterator() {
    return children.iterator();
  }

  public void setParent(Node<T> parentToSet) {
    parent = parentToSet;
    modCount = parent.modCount;
  }

  public Node<T> getParent() {
    return parent;
  }

  public void setMode(SearchType searchTypeToSet){
    searchType = searchTypeToSet;
  }


  public int getModCount() {
    return modCount;
  }

  public void incModCount() {
    modCount++;
    Node<T> nextParent = getParent();
    if (nextParent != null) {
      nextParent.incModCount();
    }
  }

  /**
   * method of adding some element to this node.
   *
   * @param element we need to add.
   * @return new node of curr node.
   */
  public Node<T> add(T element) {
    if (this.value == null) {
      this.value = element;
      incModCount();
      return this;
    } else {
      Node<T> node = new Node<>();
      node.setParent(this);
      node.incModCount();
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
      node.incModCount();
    } else {
      Node<T> newNode = new Node<>();
      newNode.value = element;
      node.children.add(newNode);
      newNode.setParent(node);
      newNode.incModCount();
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
    if (!children.remove(node)) {
      throw new NoSuchElementException();
    } else {
      incModCount();
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



