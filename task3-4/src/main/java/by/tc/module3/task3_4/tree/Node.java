package by.tc.module3.task3_4.tree;

class Node<T extends Comparable<T>> {

	private T value;
	private Node<T> left;
	private Node<T> right;

	Node() {}

	Node(T value) {
		this.value = value;
	}

	void setValue(T value) {
		this.value = value;
	}

	T getValue() {
		return value;
	}

	void setLeft(Node<T> left) {
		this.left = left;
	}

	Node<T> getLeft() {
		return left;
	}

	void setRight(Node<T> right) {
		this.right = right;
	}

	Node<T> getRight() {
		return right;
	}
}
