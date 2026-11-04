/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.exercise1.wardatul.keskin;

/**
 * A basic doubly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class DoublyLinkedList<E> {

  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  private static class Node<E> {

    /** The element stored at this node */
    private E element;               // reference to the element stored at this node

    /** A reference to the preceding node in the list */
    private Node<E> prev;            // reference to the previous node in the list

    /** A reference to the subsequent node in the list */
    private Node<E> next;            // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
    }

    // public accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node<E> getPrev() { return prev; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Update methods
    /**
     * Sets the node's previous reference to point to Node n.
     * @param p    the node that should precede this one
     */
    public void setPrev(Node<E> p) { prev = p; }

    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
    
  } //----------- end of nested Node class -----------

  // instance variables of the DoublyLinkedList
  /** Sentinel node at the beginning of the list */
  private Node<E> header;                    // header sentinel

  /** Sentinel node at the end of the list */
  private Node<E> trailer;                   // trailer sentinel

  /** Number of elements in the list (not including sentinels) */
  private int size = 0;                      // number of elements in the list

  /** Constructs a new empty list. */
  public DoublyLinkedList() {
    header = new Node<>(null, null, null);      // create header
    trailer = new Node<>(null, header, null);   // trailer is preceded by header
    header.setNext(trailer);                    // header is followed by trailer
  }

  // public accessor methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list.
   * @return element at the front of the list (or null if empty)
   */
  public E first() {
    if (isEmpty()) return null;
    return header.getNext().getElement();   // first element is beyond header
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {
    if (isEmpty()) return null;
    return trailer.getPrev().getElement();    // last element is before trailer
  }

  // public update methods
  /**
   * Adds an element to the front of the list.
   * @param e   the new element to add
   */
  public void addFirst(E e) {
    addBetween(e, header, header.getNext());    // place just after the header
  }

  /**
   * Adds an element to the end of the list.
   * @param e   the new element to add
   */
  public void addLast(E e) {
    addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(header.getNext());             // first element is beyond header
  }

  /**
   * Removes and returns the last element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeLast() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(trailer.getPrev());            // last element is before trailer
  }

  // private update methods
  /**
   * Adds an element to the linked list in between the given nodes.
   * The given predecessor and successor should be neighboring each
   * other prior to the call.
   *
   * @param predecessor   node just before the location where the new element is inserted
   * @param successor     node just after the location where the new element is inserted
   */
  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    // create and link a new node
    Node<E> newest = new Node<>(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
  }

  /**
   * Removes the given node from the list and returns its element.
   * @param node    the node to be removed (must not be a sentinel)
   */
  private E remove(Node<E> node) {
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = header.getNext();
    while (walk != trailer) {
      sb.append(walk.getElement());
      walk = walk.getNext();
      if (walk != trailer)
        sb.append(", ");
    }
    sb.append(")");
    return sb.toString();
  }
//----------- Wardatul Keskin | 301294696 -----------
//main method
  public static void main(String[] args)
  {
	//create and populate a doubly linked L1 (red fruits)
	  DoublyLinkedList<String> L1 = new DoublyLinkedList<String>();
	  // Adding first and last list
	  L1.addFirst("Strawberry");
	  L1.addFirst("Pomegrant");
	  L1.addFirst("Cranberry");
	  L1.addLast("Raspberry");
	  L1.addLast("Cherry");
	  L1.addLast("Red grape");
	  System.out.println("L1 original list: "+L1);
	  
	  Node<String> node1 = L1.header.getNext(); // Node for "Cranberry"
	  Node<String> node2 = L1.trailer.getNext().getNext(); // Node for "Red grape"
	  
	  L1.swapTwoNodes(node1, node2);
	  System.out.println("List after Swapping Strawberry and Red grape:\n"+ L1);
	  
	  /* Swapping the null node and non null node
	  Node<String> node3 = null;
	  L1.swapTwoNodes(node1, node3);
	  System.out.println("Swapping null node and non null node:\n"+L1);
	  
	  // Swapping the same node
	  Node<String> node4 = node2;
	  L1.swapTwoNodes(node2, node4);
	  System.out.println("Swapping the same node:\n"+L1);
	  
	  // Swapping both null node
	  Node<String> node5 = node3;
	  L1.swapTwoNodes(node5, node3);
	  System.out.println("Swapping both null nodes:\n"+L1);?*/
  }
  
  private void swapTwoNodes(Node<E> node1, Node<E> node2) {
	  // Checking invalid cases when node are the same or null
	  if (node1 == node2 || node1 == null || node2 == null) return; 
	  
	  // Defining references for both nodes
	  Node<E> node1Prev = node1.getPrev();
	  Node<E> node1Next = node1.getNext();
	  Node<E> node2Prev = node2.getPrev();
	  Node<E> node2Next = node2.getNext();
	  
	  // Adjusting node1 and node2 neighbors
	  if (node1Prev != null) node1Prev.setNext(node2);  // Update node1Prev to point to node2
	  else header.setNext(node2);  // If node1 is the first node, update header
	  if (node1Next != null) node1Next.setPrev(node2);  // Update node1Next to point to node2
	  if (node2Prev != null) node2Prev.setNext(node1);  // Update node2Prev to point to node1
	  else header.setNext(node1);  // If node2 is the first node, update header
	  if (node2Next != null) node2Next.setPrev(node1);  // Update node2Next to point to node1
	  
	  // Swapping the pointers of node1 and node2
	  node1.setPrev(node2Prev);
	  node1.setNext(node2Next);
	  node2.setPrev(node1Prev);
	  node2.setNext(node1Next);
	  
	  // Updating trailer
	  if (trailer.getPrev() == node1) trailer.setPrev(node2);  // If node1 was last, update trailer
	  else if (trailer.getPrev() == node2) trailer.setPrev(node1);  // If node2 was last, update trailer
	    
	  }
	  
} //----------- Wardatul Keskin | 301294696 -----------
