package by.tc.module3.task3_4.tree;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

	private enum Order {
		PRE, IN, POST
	}

	private Node<T> root;
	private int size;

	public BinaryTree() {
	}

	public BinaryTree(T value) {
		root = new Node<T>(value);
		size++;
	}

	public int getSize() {
		return size;
	}

	public void add(T value) {

		Node<T> currentNode = new Node<T>(value);

		if (size == 0) {
			root = currentNode;
		} else {
			addHelper(value, currentNode, root);
		}
		size++;
	}

	public T min() {
		if (size == 0) {
			return null;
		}

		Node<T> currentNode = root;
		while (currentNode.getLeft() != null) {
			currentNode = currentNode.getLeft();
		}
		return currentNode.getValue();
	}

	public T max() {
		if (size == 0) {
			return null;
		}

		Node<T> currentNode = root;
		while (currentNode.getRight() != null) {
			currentNode = currentNode.getRight();
		}
		return currentNode.getValue();
	}

	public boolean find(T value) {
		if (size == 0) {
			return false;
		}

		return findHelper(value, root);
	}

	public boolean remove(T value) {
		if (size == 0) {
			return false;
		}

		if (root.getValue() == value) {
			removeRoot();
			return true;
		}

		MutablePair<Node<T>, Node<T>> startPair = new MutablePair<Node<T>, Node<T>>(null, root);
		Pair<Node<T>, Node<T>> parentAndSon = findTwoNodes(value, startPair);

		Node<T> parent = parentAndSon.getLeft();
		Node<T> son = parentAndSon.getRight();

		if (son == null) {
			return false;
		} else if (son.getLeft() == null && son.getRight() == null) {
			removeLeaf(parent, son, value);
		} else if (son.getLeft() != null && son.getRight() != null) {
			removeInternalNode(parent, son);
		} else if (son.getLeft() == null && son.getRight() != null) {
			removeNodeWithoutLeftSubtree(parent, son);
		} else if (son.getLeft() != null && son.getRight() == null) {
			removeNodeWithoutRightSubtree(parent, son);
		}

		size--;

		return true;
	}

	public List<T> preOrderTraverse() {
		return traverse(Order.PRE);
	}

	public List<T> inOrderTraverse() {
		return traverse(Order.IN);
	}

	public List<T> postOrderTraverse() {
		return traverse(Order.POST);
	}

	private List<T> traverse(Order order) {
		List<T> nodes = new ArrayList<T>();
		if (size == 0) {
			return null;
		}

		switch (order) {
		case PRE:
			preOrderTraversalHelper(root, nodes);
			break;
		case IN:
			inOrderTraversalHelper(root, nodes);
			break;
		case POST:
			postOrderTraversalHelper(root, nodes);
			break;
		default:
			throw new IllegalArgumentException("Invalid traversal order");
		}

		return nodes;
	}

	private void preOrderTraversalHelper(Node<T> currentNode, List<T> nodes) {
		if (currentNode == null) {
			return;
		}

		nodes.add(currentNode.getValue());
		preOrderTraversalHelper(currentNode.getLeft(), nodes);
		preOrderTraversalHelper(currentNode.getRight(), nodes);
	}

	private void inOrderTraversalHelper(Node<T> currentNode, List<T> nodes) {
		if (currentNode == null) {
			return;
		}

		inOrderTraversalHelper(currentNode.getLeft(), nodes);
		nodes.add(currentNode.getValue());
		inOrderTraversalHelper(currentNode.getRight(), nodes);
	}

	private void postOrderTraversalHelper(Node<T> currentNode, List<T> nodes) {
		if (currentNode == null) {
			return;
		}
		postOrderTraversalHelper(currentNode.getLeft(), nodes);
		postOrderTraversalHelper(currentNode.getRight(), nodes);
		nodes.add(currentNode.getValue());
	}

	private void addHelper(T value, Node<T> newNode, Node<T> currentRoot) {

		T rootValue = currentRoot.getValue();

		if (value.compareTo(rootValue) >= ComparisonResult.EQUAL_TO) {
			if (currentRoot.getRight() == null) {
				currentRoot.setRight(newNode);
				return;
			} else {
				addHelper(value, newNode, currentRoot.getRight());
			}
		} else if (value.compareTo(rootValue) == ComparisonResult.LESS_THAN) {
			if (currentRoot.getLeft() == null) {
				currentRoot.setLeft(newNode);
				return;
			} else {
				addHelper(value, newNode, currentRoot.getLeft());
			}
		}
	}

	private boolean findHelper(T value, Node<T> currentNode) {

		if (currentNode == null) {
			return false;
		}

		T currentValue = currentNode.getValue();

		if (value.compareTo(currentValue) == ComparisonResult.EQUAL_TO) {
			return true;
		} else if (value.compareTo(currentValue) == ComparisonResult.LESS_THAN) {
			return findHelper(value, currentNode.getLeft());
		} else {
			return findHelper(value, currentNode.getRight());
		}
	}

	private void removeLeaf(Node<T> parent, Node<T> son, T value) {
		if (parent.getLeft() == son) {
			parent.setLeft(null);
		} else {
			parent.setRight(null);
		}
	}

	private void removeNodeWithoutLeftSubtree(Node<T> parent, Node<T> son) {
		if (parent.getLeft() == son) {
			parent.setLeft(son.getRight());
		} else {
			parent.setRight(son.getRight());
		}
		son.setRight(null);
	}

	private void removeNodeWithoutRightSubtree(Node<T> parent, Node<T> son) {
		if (parent.getLeft() == son) {
			parent.setLeft(son.getLeft());
		} else {
			parent.setRight(son.getLeft());
		}
		son.setLeft(null);
	}

	private void removeInternalNode(Node<T> parent, Node<T> son) {

		Node<T> parentReplaceElement = son;
		Node<T> replaceElement = findReplaceElement(parentReplaceElement);

		son.setValue(replaceElement.getValue());

		if (replaceElement.getRight() != null) {
			parentReplaceElement.setRight(replaceElement.getRight());
			replaceElement.setRight(null);
		} else if (replaceElement.getLeft() != null) {
			parentReplaceElement.setLeft(replaceElement.getLeft());
			replaceElement.setLeft(null);
		} else {
			if (parentReplaceElement.getLeft() == replaceElement) {
				parentReplaceElement.setLeft(null);
			} else {
				parentReplaceElement.setRight(null);
			}
		}
	}

	private void removeRoot() {
		if (size == 1) {
			root = null;
		} else {
			removeInternalNode(null, root);
		}
		size--;
	}

	private Node<T> findReplaceElement(Node<T> parentReplaceElement) {
		Node<T> forReplacedElement = parentReplaceElement;
		Node<T> replaceElement = null;

		if (forReplacedElement.getRight() != null) {
			replaceElement = forReplacedElement.getRight();
			while (replaceElement.getLeft() != null) {
				parentReplaceElement = replaceElement;
				replaceElement = replaceElement.getLeft();
			}
		} else if (forReplacedElement.getLeft() != null) {
			replaceElement = forReplacedElement.getLeft();
			while (replaceElement.getRight() != null) {
				parentReplaceElement = replaceElement;
				replaceElement = replaceElement.getRight();
			}
		}
		return replaceElement;
	}

	private Pair<Node<T>, Node<T>> findTwoNodes(T value, MutablePair<Node<T>, Node<T>> parentAndSon) {

		Node<T> son = parentAndSon.getRight();

		if (son == null) {
			parentAndSon.setLeft(null);
			return parentAndSon;
		}

		T currentValue = son.getValue();

		if (value.compareTo(currentValue) == ComparisonResult.EQUAL_TO) {
			return parentAndSon;
		} else if (value.compareTo(currentValue) == ComparisonResult.LESS_THAN) {
			parentAndSon.setLeft(son);
			parentAndSon.setRight(son.getLeft());
		} else {
			parentAndSon.setLeft(son);
			parentAndSon.setRight(son.getRight());
		}
		return findTwoNodes(value, parentAndSon);
	}

}
