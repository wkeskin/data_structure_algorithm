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
package com.exercise3.wardatul.keskin;

/**
 * Realization of a FIFO queue as an adaptation of a SinglyLinkedList.
 * All operations are performed in constant time.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @see SinglyLinkedList
 */
public class LinkedQueue<E> implements Queue<E> {

  /** The primary storage for elements of the queue */
  private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty  list

  /** Constructs an initially empty queue. */
  public LinkedQueue() { }                  // new queue relies on the initially empty list

  /**
   * Returns the number of elements in the queue.
   * @return number of elements in the queue
   */
  @Override
  public int size() { return list.size(); }

  /**
   * Tests whether the queue is empty.
   * @return true if the queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() { return list.isEmpty(); }

  /**
   * Inserts an element at the rear of the queue.
   * @param element  the element to be inserted
   */
  @Override
  public void enqueue(E element) { list.addLast(element); }

  /**
   * Returns, but does not remove, the first element of the queue.
   * @return the first element of the queue (or null if empty)
   */
  @Override
  public E first() { return list.first(); }

  /**
   * Removes and returns the first element of the queue.
   * @return element removed (or null if empty)
   */
  @Override
  public E dequeue() { return list.removeFirst(); }

  /** Produces a string representation of the contents of the queue.
   *  (from front to back). This exists for debugging purposes only.
   */
  public String toString() {
    return list.toString();
  }
  /*Concatenate method*/
  public void concatenate(LinkedQueue<E> Q2) {
	    if (Q2.isEmpty()) return; // Nothing to concatenate

	    list.concatenate(Q2.list); // Use the SinglyLinkedList method

	    Q2.list = new SinglyLinkedList<>(); // Reset Q2 to be empty
	}
  /*Wardatul Keskin | 301294696*/
  public static void main(String[] args) {
	  
	  /*Case 1: Both have elements*/
      LinkedQueue<String> Q1 = new LinkedQueue<>();
      LinkedQueue<String> Q2 = new LinkedQueue<>();

      // Add elements to Q1
      Q1.enqueue("Toronto");
      Q1.enqueue("Vancouver");
      Q1.enqueue("Montreal");

      // Add elements to Q2
      Q2.enqueue("Calgary");
      Q2.enqueue("Ottawa");
      Q2.enqueue("Edmonton");

      System.out.println("Q1: " + Q1);
      System.out.println("Q2: " + Q2);

      // Concatenate Q2 into Q1
      Q1.concatenate(Q2);

      System.out.println("Q1 after concatenation: " + Q1);
      System.out.println("Q2 after concatenation: " + Q2);
      System.out.println(Q2.isEmpty());
      System.out.println("--------------------------------\n");
      
      // **Case 2: Q1 is empty, Q2 has cities**
      Q1 = new LinkedQueue<>(); // Reset Q1 to empty
      Q2.enqueue("Calgary");
      Q2.enqueue("Ottawa");
      Q2.enqueue("Edmonton");

      System.out.println("Before Concatenation (Case 2):");
      System.out.println("Q1: " + Q1);
      System.out.println("Q2: " + Q2);

      Q1.concatenate(Q2);

      System.out.println("After Concatenation (Case 2):");
      System.out.println("Q1: " + Q1);
      System.out.println("Q2: " + Q2);
      System.out.println("--------------------------------\n");


      // **Case 3: Both Q1 and Q2 are empty**
      Q1 = new LinkedQueue<>(); // Reset Q1
      Q2 = new LinkedQueue<>(); // Reset Q2

      System.out.println("Before Concatenation (Case 3):");
      System.out.println("Q1: " + Q1);
      System.out.println("Q2: " + Q2);

      Q1.concatenate(Q2);

      System.out.println("After Concatenation (Case 3):");
      System.out.println("Q1: " + Q1);
      System.out.println("Q2: " + Q2);
      System.out.println("--------------------------------\n");
  }
}
