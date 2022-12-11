package ru.nsu.fit.maksimenkov.tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Node<Type> {
  public Type value;
  public ArrayList<Node<Type>> children = new ArrayList<>();

  public Node<Type> add(Type element) {
    Node<Type> node = new Node<>();
    node.value = element;
    this.children.add(node);
    return this;
  }

  public void add(Node<Type> node, Type element){
    Node<Type> newNode = new Node<>();
    newNode.value = element;
    node.children.add(newNode);
  }

  public Node<Type> remove(Node<Type> node) throws NoSuchElementException {
    if (children.remove(node)) {
      return this;
    } else {
      throw new NoSuchElementException();
    }
  }
}

