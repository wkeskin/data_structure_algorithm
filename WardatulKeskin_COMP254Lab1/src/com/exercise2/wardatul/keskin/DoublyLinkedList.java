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
package com.exercise2.wardatul.keskin;

/**
 * A basic doubly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */

//---------------------- implementing cloning interface --------------------
public class DoublyLinkedList<E> implements Cloneable{
    public DoublyLinkedList<E> clone() throws CloneNotSupportedException {
        // Create other list
        DoublyLinkedList<E> other = (DoublyLinkedList<E>) super.clone();

        // Reinitialize header, trailer, and size
        other.header = new Node<>(null, null, null);
        other.trailer = new Node<>(null, other.header, null);
        other.header.setNext(other.trailer);
        other.size = 0;

        // Traverse the original list and clone each node
        Node<E> walk = this.header.getNext(); // Start at the first actual node
        while (walk != this.trailer) {
            other.addLast(walk.getElement()); // Add each element to the new list
            walk = walk.getNext();
        }
        

        return other;
    }

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
	  // Showing the List
	  System.out.println(L1);
	  // Removing first and last list
	  L1.removeFirst();
	  L1.removeLast();
	  // Showing the members of L1 after removal
	  System.out.println("New List of L1 after removal");
	  System.out.println("L1: "+L1);
  //create and populate a doubly linked L2 (red vegetables)
	  DoublyLinkedList<String> L2 = new DoublyLinkedList<String>();
	  // Populating the list by adding first and last list
	  L2.addFirst("Red pepper");
	  L2.addFirst("Beetroot");
	  L2.addLast("Sweet potato");
	  L2.addLast("Tomato");
	  // Showing the members of L2
	  System.out.println("L2: "+L2);

  //concatenating two doubly linked lists L1 and L2, into a single list L
	  DoublyLinkedList<String> L = concatenate(L1,L2);
	  System.out.println("L: "+L);
	  System.out.println("First element of L: "+L.first());
	  System.out.println("Last element of L: "+L.last());
	  L1.addFirst("Avocado");
	  System.out.println("L1: "+L1);
	  System.out.println("L2: "+L2);
 // Concatenating of empty List
	  DoublyLinkedList<String> L3 = new DoublyLinkedList<String>();
	  System.out.println("L3: "+L3);
	  DoublyLinkedList<String> L4 = new DoublyLinkedList<String>();
	  System.out.println("L4: "+L4);
	  DoublyLinkedList<String> L5 = concatenate(L3,L4);
	  System.out.println("L5: "+L5);
	  DoublyLinkedList<String> L6 = concatenate(L1,L4);
	  System.out.println("L1 + L4: "+L6);
	  DoublyLinkedList<String> L7 = concatenate(L3,L2);
	  System.out.println("L3 + L2: "+L7);
	  System.out.println("First element of L3 + L2: "+L7.first());
	  System.out.println("Last element of L3 + L2: "+L7.last());
  }

// First concatenating method = use addLast method
  /*private static DoublyLinkedList<String> concatenate(DoublyLinkedList<String> l1, DoublyLinkedList<String> l2) {
	// Creating new list (l) to hold the result
	DoublyLinkedList<String> l = new DoublyLinkedList<>();
      
	 // Adding elements from l1 into the l
    Node<String> walk = l1.header.getNext(); // Start at the first node of l1
    while (walk != l1.trailer) { // Traverse/walking until the end of l1 node or stop before the l1's trailer
        l.addLast(walk.getElement()); // Add each node to the new list (l)
        walk = walk.getNext();
    }

    // Adding elements from l2 into the l
    walk = l2.header.getNext(); // Start at the first node of l2
    while (walk != l2.trailer) { // Traverse/walking until the end of l2 node or stop before the l2's trailer
        l.addLast(walk.getElement()); // Add each node to the new list (l)
        walk = walk.getNext();
    }
    
	return l ;	
  }*/

// Second concatenating method = use temporary variable with manual deep cloning
  /*private static DoublyLinkedList<String> concatenate(DoublyLinkedList<String> l1, DoublyLinkedList<String> l2) {
	    // Create a new empty list
	    DoublyLinkedList<String> l = new DoublyLinkedList<>();

	    // Clone nodes from l1 into the new list l

	    Node<String> walk = l1.header.getNext(); // Start at the first node of l1
	    Node<String> pointer = l.header; // Start at the header of the new list, this is used as temporary variable to mark the current node
	    while (walk != l1.trailer) { // Traverse until the end of l1
	        Node<String> newNode = new Node<>(walk.getElement(), pointer, null); // Create a clone of the current node
	        pointer.setNext(newNode); // Link the new node to the new list
	        pointer = newNode; // Move the pointer to the current node
	        walk = walk.getNext(); // Move to the next node in l1
	    }

	    // Clone nodes from l2 into the new list l
	    walk = l2.header.getNext(); // Start at the first node of l2
	    while (walk != l2.trailer) { // Traverse until the end of l2
	        Node<String> newNode = new Node<>(walk.getElement(), pointer, null); // Create a clone of the current node
	        pointer.setNext(newNode); // Link the new node to the new list
	        pointer = newNode; // Move the the pointer to the current node
	        walk = walk.getNext(); // Move to the next node in l2
	    }

	    // Set the trailer of the new list to the final node
	    pointer.setNext(l.trailer);
	    l.trailer.setPrev(pointer);

	    // Update the size of the new list
	    l.size = l1.size + l2.size;

	    return l;
	}*/
//Between First and Second, the second method will be faster because it doesn't repeatedly call addLast method
//but, second method will slightly consume more memory due to manually cloning node, and break the encapsulation
  
// Third concatenating method = use clonable interface 
  private static DoublyLinkedList<String> concatenate(DoublyLinkedList<String> l1, DoublyLinkedList<String> l2) {
	    try {
	        // Clone the first list (l1)
	        DoublyLinkedList<String> l = l1.clone();

	        // Clone the second list (l2) and append its elements to the first list
	        Node<String> walk = l2.header.getNext(); // Start at the first node of l2
	        while (walk != l2.trailer) { // Traverse until the end of l2
	            l.addLast(walk.getElement()); // Add each node to the new list (l)
	            walk = walk.getNext(); // Move to the next node
	        }

	        return l;
	    } catch (CloneNotSupportedException e) {
	        throw new AssertionError("Cloning not supported, but it should be!");
	    }
	}

} //----------- Wardatul Keskin | 301294696 -----------
