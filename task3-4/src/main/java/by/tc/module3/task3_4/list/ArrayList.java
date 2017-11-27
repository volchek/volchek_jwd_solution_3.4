package by.tc.module3.task3_4.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<T> extends MyAbstractList<T> {

	class MyArrayListIterator<V> implements ListIterator<T> {

		int index;

		MyArrayListIterator(int index) {
			this.index = index;
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			T value = (T) data[index];
			stepForward();
			return value;
		}

		@Override
		public int nextIndex() {
			stepForward();
			return index;
		}

		@Override
		public boolean hasPrevious() {
			return !isEmpty() && index >= 0;
		}

		@Override
		public T previous() {
			T value = (T) data[index];
			stepBehind();
			return value;
		}

		@Override
		public int previousIndex() {
			stepBehind();
			return index + 1;
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException(); // currently not
														// implemented
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException(); // currently not
														// implemented
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(); // currently not
														// implemented
		}

		private void stepForward() {
			index++;
		}

		private void stepBehind() {
			index--;
		}

	};

	private static final int INITIAL_CAPACITY = 8;
	private static final int RESIZE_FACTOR = 2;

	private T[] data;
	private int capacity;

	public ArrayList() {
		clear();
	}

	@Override
	public void clear() {
		this.data = (T[]) new Object[INITIAL_CAPACITY];
		this.capacity = INITIAL_CAPACITY;
		this.size = 0;
	}

	@Override
	public boolean add(T o) {
		resizeUp();
		data[size] = o;
		size++;
		return true;
	}

	@Override
	public void add(int index, T element) {
		checkIndexValidity(index);
		resizeUp();
		T bufferNew = element;
		T bufferOld;
		for (int i = index; i < size; i++) {
			bufferOld = data[index];
			data[index] = bufferNew;
			bufferNew = bufferOld;
		}
		data[size] = bufferNew;
		size++;
	}

	@Override
	public T get(int index) {
		checkIndexValidity(index);
		return data[index];
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		boolean status = false;
		for (T elem : c) {
			add(index, elem);
			index++;
			status = true;
		}
		return status;
	}

	@Override
	public T remove(int index) {
		checkIndexValidity(index);
		T value = get(index);
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		data[size - 1] = null;
		size--;
		resizeDown();
		return value;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean status = false;
		int i = 0;
		while (i < size) {
			if (!c.contains(data[i])) {
				remove(i); // this implies size--
				status = true;
			} else {
				i++;
			}
		}
		return status;
	}

	@Override
	public T set(int index, T element) {
		checkIndexValidity(index);
		T buffer = data[index];
		data[index] = element;
		return buffer;
	}

	@Override
	public T[] toArray(Object[] a) {
		int aSize = a.length;
		if (aSize < size) {
			a = new Object[size];
		}
		for (int i = 0; i < size; i++) {
			a[i] = data[i];
		}
		if (size < aSize) {
			a[size] = null;
		}
		return (T[]) a;
	}

	@Override
	public T[] toArray() {
		return Arrays.copyOf(data, size);
	}

	@Override
	public ListIterator<T> iterator() {
		return listIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new MyArrayListIterator<T>(0);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		checkIndexValidity(index);
		return new MyArrayListIterator<T>(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException(); // currently not implemented
	}

	@Override
	protected int generalizedIndexOf(Object o, boolean stopOnFirstMatch) {
		throw new UnsupportedOperationException();
	}

	private void resizeUp() {
		if (size == capacity) {
			capacity *= RESIZE_FACTOR;
			data = Arrays.copyOf(data, capacity);
		}
	}

	private void resizeDown() {
		if (size < capacity / RESIZE_FACTOR) {
			capacity /= RESIZE_FACTOR;
			data = Arrays.copyOf(data, capacity);
		}
	}

}
