package by.tc.module3.task3_4.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T> extends MyAbstractList<T> {

	class MyLinkedListIterator<V> implements ListIterator<T> {

		private ListNode<T> position;
		private int index;

		MyLinkedListIterator(ListNode<T> position, int index) {
			this.position = position;
			this.index = index;
		}

		@Override
		public boolean hasNext() {
			return position != null;
		}

		@Override
		public T next() {
			T value = position.getData();
			stepForward();
			return value;
		}

		@Override
		public int nextIndex() {
			int ind = index;
			stepForward();
			return ind;
		}

		@Override
		public boolean hasPrevious() {
			return position != null;
		}

		@Override
		public T previous() {
			T value = position.getData();
			stepBehind();
			return value;
		}

		@Override
		public int previousIndex() {
			int prevIndex = index;
			stepBehind();
			return prevIndex;
		}

		@Override
		public void set(T elem) {
			throw new UnsupportedOperationException(); // currently not
														// implemented
		}

		@Override
		public void add(T elem) {
			throw new UnsupportedOperationException(); // currently not
														// implemented
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(); // currently not
														// implemented
		}

		private void stepForward() {
			position = position.getNext();
			index++;
		}

		private void stepBehind() {
			position = position.getPrev();
			index--;
		}
	}

	private ListNode<T> head;
	private ListNode<T> tail;

	public LinkedList() {
		clear();
	}

	public LinkedList(ListNode<T> head, ListNode<T> tail, int size) {
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	@Override
	public T[] toArray(Object[] a) {
		int aSize = a.length;
		if (aSize < size) {
			a = new Object[size];
		}
		ListNode<T> it = head;
		int index = 0;
		while (it != null) {
			a[index] = it.getData();
			it = it.getNext();
			index++;
		}
		if (index < aSize) {
			a[index] = null;
		}
		return (T[]) a;
	}

	@Override
	public T[] toArray() {
		return toArray(new Object[size]);
	}

	@Override
	public T get(int index) {
		return getNode(index).getData();
	}

	@Override
	public boolean add(T o) {
		ListNode<T> node = new ListNode<T>(o);
		if (isEmpty()) {
			head = node;
			tail = node;
		} else {
			tail.setNext(node);
			node.setPrev(tail);
			tail = node;
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, T element) {
		if (index == size) {
			add(element);
		} else {
			checkIndexValidity(index);
			ListNode<T> it = getNode(index);
			addAfterNode(it, element);
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		checkIndexValidity(index);
		boolean status = false;
		ListNode<T> it = getNode(index);
		for (T elem : c) {
			addAfterNode(it, elem);
			it = it.getNext();
			status = true;
		}
		return status;
	}

	@Override
	public T remove(int index) {
		checkIndexValidity(index);

		ListNode<T> node = getNode(index);
		T value = removeNode(node);
		if (index == 0) {
			head = node.getNext();
		}
		if (index == (size - 1)) {
			tail = tail.getPrev();
		}

		return value;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean status = false;
		ListNode<T> it = head;
		while (it != null) {
			T elem = it.getData();
			if (c.contains(elem)) {
				removeNode(it);
				status = true;
			}
			it = it.getNext();
		}
		return status;
	}

	@Override
	public T set(int index, T element) {
		checkIndexValidity(index);
		ListNode<T> it = getNode(index);
		T oldValue = it.getData();
		it.setData(element);
		return oldValue;
	}

	@Override
	public Iterator<T> iterator() {
		return listIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new MyLinkedListIterator<T>(head, 0);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		checkIndexValidity(index);
		return new MyLinkedListIterator<T>(getNode(index), index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		checkIndexValidity(fromIndex);
		checkIndexValidity(toIndex);
		// As specified in the interface documentation, this method doesn't make
		// a copy
		LinkedList<T> subList = new LinkedList<T>(getNode(fromIndex), getNode(toIndex).getPrev(), // exclusive
				toIndex - fromIndex);
		return subList;
	}

	@Override
	protected int generalizedIndexOf(Object o, boolean stopOnFirstMatch) {
		int index = -1;
		int iterIndex = 0;
		ListNode<T> it = head;
		while (it != null) {
			if (it.getData().equals((T) o)) {
				index = iterIndex;
				if (stopOnFirstMatch) {
					break;
				}
			}
			it = it.getNext();
			iterIndex++;
		}
		return index;
	}

	private ListNode<T> getNode(int index) {
		checkIndexValidity(index);
		ListNode<T> it = head;
		int iterIndex = 0;
		while (iterIndex != index) {
			it = it.getNext();
			iterIndex++;
		}
		return it;
	}

	private void addAfterNode(ListNode<T> it, T element) {
		ListNode<T> nextNode = new ListNode<T>(it);
		it = new ListNode<T>(element);
		it.setPrev(nextNode.getPrev());
		it.setNext(nextNode);
		nextNode.setPrev(it);
		size++;
	}

	private T removeNode(ListNode<T> node) {
		ListNode<T> nextNode = node.getNext();
		ListNode<T> prevNode = node.getPrev();
		if (nextNode != null) {
			nextNode.setPrev(prevNode);
		}
		if (prevNode != null) {
			prevNode.setNext(nextNode);
		}
		size--;
		return node.getData();
	}

}