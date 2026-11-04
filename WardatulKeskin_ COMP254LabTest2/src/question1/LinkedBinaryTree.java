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
package question1;

import java.util.Stack;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	// ---------------- nested Node class ----------------
	/** Nested static class for a binary tree node. */
	protected static class Node<E> implements Position<E> {
		private E element; // an element stored at this node
		private Node<E> parent; // a reference to the parent node (if any)
		private Node<E> left; // a reference to the left child (if any)
		private Node<E> right; // a reference to the right child (if any)

		/**
		 * Constructs a node with the given element and neighbors.
		 *
		 * @param e          the element to be stored
		 * @param above      reference to a parent node
		 * @param leftChild  reference to a left child node
		 * @param rightChild reference to a right child node
		 */
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}

		// get and set methods
		//
		// accessor methods
		public E getElement() {
			return element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public Node<E> getRight() {
			return right;
		}

		// update methods
		public void setElement(E e) {
			element = e;
		}

		public void setParent(Node<E> parentNode) {
			parent = parentNode;
		}

		public void setLeft(Node<E> leftChild) {
			left = leftChild;
		}

		public void setRight(Node<E> rightChild) {
			right = rightChild;
		}
	} // ----------- end of nested Node class -----------

	/** Factory function to create a new node storing element e. */
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		return new Node<E>(e, parent, left, right);
	}

	// LinkedBinaryTree instance variables
	/** The root of the binary tree */
	protected Node<E> root = null; // root of the tree

	/** The number of nodes in the binary tree */
	private int size = 0; // number of nodes in the tree

	// constructor
	/** Construts an empty binary tree. */
	public LinkedBinaryTree() {
	} // constructs an empty binary tree

	// nonpublic utility
	/**
	 * Verifies that a Position belongs to the appropriate class, and is not one
	 * that has been previously removed. Note that our current implementation does
	 * not actually verify that the position belongs to this particular list
	 * instance.
	 *
	 * @param p a Position (that should belong to this tree)
	 * @return the underlying Node instance for the position
	 * @throws IllegalArgumentException if an invalid position is detected
	 */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getParent() == node) // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	// accessor methods (not already implemented in AbstractBinaryTree)
	/**
	 * Returns the number of nodes in the tree.
	 * 
	 * @return number of nodes in the tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the root Position of the tree (or null if tree is empty).
	 * 
	 * @return root Position of the tree (or null if tree is empty)
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/**
	 * Returns the Position of p's parent (or null if p is root).
	 *
	 * @param p A valid Position within the tree
	 * @return Position of p's parent (or null if p is root)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	/**
	 * Returns the Position of p's left child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the left child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	/**
	 * Returns the Position of p's right child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the right child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	// update methods supported by this class
	/**
	 * Places element e at the root of an empty tree and returns its new Position.
	 *
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty())
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Creates a new left child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p the Position to the left of which the new element is inserted
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * @throws IllegalArgumentException if p already has a left child
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	/**
	 * Creates a new right child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p the Position to the right of which the new element is inserted
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 * @throws IllegalArgumentException if p already has a right child
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}

	/**
	 * Replaces the element at Position p with element e and returns the replaced
	 * element.
	 *
	 * @param p the relevant Position
	 * @param e the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	/**
	 * Attaches trees t1 and t2, respectively, as the left and right subtree of the
	 * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
	 *
	 * @param p  a leaf of the tree
	 * @param t1 an independent tree whose structure becomes the left child of p
	 * @param t2 an independent tree whose structure becomes the right child of p
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * @throws IllegalArgumentException if p is not a leaf
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (isInternal(p))
			throw new IllegalArgumentException("p must be a leaf");
		size += t1.size() + t2.size();
		if (!t1.isEmpty()) { // attach t1 as left subtree of node
			t1.root.setParent(node); // the node becomes parent of the root of left subtree
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if (!t2.isEmpty()) { // attach t2 as right subtree of node
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}

	/**
	 * Removes the node at Position p and replaces it with its child, if any.
	 *
	 * @param p the relevant Position
	 * @return element that was removed
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 * @throws IllegalArgumentException if p has two children.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if (child != null)
			child.setParent(node.getParent()); // child's grandparent becomes its parent
		if (node == root)
			root = child; // child becomes root
		else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		E temp = node.getElement();
		node.setElement(null); // help garbage collection
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node); // our convention for defunct node
		return temp;
	}

	/* Wardatul Keskin | 301294696 */

	public static void main(String[] args) {
		// create and populate a linked binary tree
		LinkedBinaryTree lbt = new LinkedBinaryTree();
		Position<String> root = lbt.addRoot("ICET");

		//
		Position<String> softwarePosition = lbt.addLeft(root, "Software");
		Position<String> networkingPosition = lbt.addRight(root, "Networking");
		Position<String> set = lbt.addLeft(softwarePosition, "SET");
		Position<String> ig = lbt.addRight(softwarePosition, "IG");

		//
		printPreorder(lbt);
		parenthesize(lbt, root);
		System.out.println("\n\nSum of Depths (Iterative): " + lbt.sumOfDepthsIterative());

		// Time the execution of sumOfDepthsIterative
		long startTime = System.nanoTime();

		int depthSum = lbt.sumOfDepthsIterative();

		long endTime = System.nanoTime();
		long durationNano = endTime - startTime;
		double durationMillis = durationNano / 1_000_000.0;

		// Print result and timing
		System.out.printf("Time Elapsed: %.4f ms\n", durationMillis);

		// Printing the linear testing output of sum Depth method
		benchmarkSumOfDepths();
	}

	/* Sum of depths using iterative */
	/* The Big O notation of this method is O(n) */
	public int sumOfDepthsIterative() {
		if (isEmpty())
			return 0;

		int totalDepth = 0;
		Stack<DepthNode<E>> stack = new Stack<>();
		stack.push(new DepthNode<>(root, 0));

		while (!stack.isEmpty()) {
			DepthNode<E> current = stack.pop();
			Position<E> p = current.node;
			int depth = current.depth;
			totalDepth += depth;

			Position<E> right = right(p);
			Position<E> left = left(p);
			if (right != null)
				stack.push(new DepthNode<>(right, depth + 1));
			if (left != null)
				stack.push(new DepthNode<>(left, depth + 1));
		}

		return totalDepth;
	}

	/* Testing the linear time */
	public static void benchmarkSumOfDepths() {
		int[] sizes = { 5, 100, 10_000, 100_000, 1_000_000 };

		System.out.printf("%-15s%-15s%n", "Tree Size (n)", "Time (ms)");
		for (int size : sizes) {
			LinkedBinaryTree<Integer> tree = buildSkewedTree(size); // or buildBalancedTree

			long start = System.nanoTime();
			tree.sumOfDepthsIterative();
			long end = System.nanoTime();

			double duration = (end - start) / 1_000_000.0; // in ms
			System.out.printf("%-15d%-15.4f%n", size, duration);
		}
	}

	/* Helper of SkewedTree for worst case scenario */
	private static LinkedBinaryTree<Integer> buildSkewedTree(int n) {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		if (n <= 0)
			return tree;

		Position<Integer> root = tree.addRoot(1);
		Position<Integer> current = root;

		for (int i = 2; i <= n; i++) {
			current = tree.addLeft(current, i); // adds to left, creating a chain
		}

		return tree;
	}

	/** Helper class for stack node with depth. */
	private static class DepthNode<E> {
		Position<E> node;
		int depth;

		DepthNode(Position<E> node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}

	/* Print height use postorder traversal */
	public static <E> int printSubtreeHeights(Tree<E> T, Position<E> p) { // Complexity O(n)
		int height = 0;

		// Recurse through children (postorder traversal)
		for (Position<E> c : T.children(p)) { // base case: when p is leaf (no children)
			int childHeight = printSubtreeHeights(T, c); // recursive call to collect child's height
			height = Math.max(height, 1 + childHeight); // update height
		}

		// Print the element and computed height
		System.out.println(p.getElement() + ": height = " + height);

		return height;
	}

	/** Prints parenthesized representation of subtree of T rooted at p. */
	public static <E> void parenthesize(Tree<E> T, Position<E> p) {
		System.out.print(p.getElement());
		if (T.isInternal(p)) {
			boolean firstTime = true;
			for (Position<E> c : T.children(p)) {
				System.out.print((firstTime ? " (" : ", ")); // determine proper punctuation
				firstTime = false; // any future passes will get comma
				parenthesize(T, c); // recur on child
			}
			System.out.print(")");
		}
	}

	//
	public static <E> void printPreorder(AbstractTree<E> T) {
		for (Position<E> p : T.preorder())
			System.out.println(p.getElement());
	}//

} // ----------- end of LinkedBinaryTree class -----------
