package by.tc.module3.task3_4.list;

import org.junit.Test;

import by.tc.module3.task3_4.list.ArrayList.MyArrayListIterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.ListIterator;

public class ArrayListTest {

	@Test
	public void shouldCreateEmptyList() {
		ArrayList<Integer> list = new ArrayList<>();
		assertThat(list.size(), equalTo(0));
	}

	@Test
	public void shouldAddElement() {
		ArrayList<Integer> list = createList();
		assertThat(list.size(), equalTo(3));
	}

	@Test
	public void shouldAddManyElements() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			list.add(i);
		}
		assertThat(list.size(), equalTo(15));
	}

	@Test
	public void shouldAddElementByIndex() {
		ArrayList<Integer> list = createList();

		list.add(0, 4);
		assertThat(list.size(), equalTo(4));
		assertThat(list.get(0), equalTo(4));

		list.add(3, 4);
		assertThat(list.size(), equalTo(5));
		assertThat(list.get(3), equalTo(4));
	}

	@Test
	public void shouldAddAll() {
		ArrayList<Integer> list = createList();

		List<Integer> addition = new LinkedList<Integer>();
		addition.add(6);
		addition.add(7);

		list.addAll(1, addition);
		assertThat(list.size(), equalTo(5));
		assertThat(list.get(0), equalTo(3));
		assertThat(list.get(1), equalTo(5));
		assertThat(list.get(2), equalTo(10));
		assertThat(list.get(3), equalTo(6));
		assertThat(list.get(4), equalTo(7));
	}

	@Test
	public void shouldRemoveElement() {
		ArrayList<Integer> list = createList();

		list.remove(1);
		assertThat(list.size(), equalTo(2));
		assertThat(list.get(1), equalTo(10));

		list.remove(1);
		assertThat(list.size(), equalTo(1));
		assertThat(list.get(0), equalTo(3));

		list.remove(0);
		assertThat(list.size(), equalTo(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldFailOnInvalidIndex() {
		ArrayList<Integer> list = createList();
		list.get(4);
	}

	@Test
	public void shouldSetNewValue() {
		ArrayList<Integer> list = createList();

		list.set(1, 42);
		assertThat(list.get(0), equalTo(3));
		assertThat(list.get(1), equalTo(42));
		assertThat(list.get(2), equalTo(10));
	}

	@Test
	public void shouldCheckNextIterator() {
		ArrayList<Integer> list = createList();

		ListIterator<Integer> it = list.iterator();
		if (it.hasNext()) {
			int value = it.next();
			assertThat(value, equalTo(3));
		}

		if (it.hasNext()) {
			int value = it.next();
			assertThat(value, equalTo(5));
		}

		if (it.hasNext()) {
			int value = it.next();
			assertThat(value, equalTo(10));
		}

		assertThat(it.hasNext(), equalTo(false));
	}

	@Test
	public void shouldCheckPrevIterator() {
		ArrayList<Integer> list = createList();

		ListIterator<Integer> it = list.listIterator(2);

		if (it.hasPrevious()) {
			int value = it.previous();
			assertThat(value, equalTo(10));
		}

		if (it.hasPrevious()) {
			int value = it.previous();
			assertThat(value, equalTo(5));
		}

		if (it.hasPrevious()) {
			int value = it.previous();
			assertThat(value, equalTo(3));
		}

		assertThat(it.hasPrevious(), equalTo(false));
	}

	@Test
	public void shouldCheckNextIndexIterator() {
		ArrayList<Integer> list = createList();

		ListIterator<Integer> it = list.listIterator(0);
		for (int i = 0; i < list.size(); i++) {
			assertThat(it.nextIndex(), equalTo(i + 1));
		}
	}

	@Test
	public void shouldCheckPrevIndexIterator() {
		ArrayList<Integer> list = createList();

		ListIterator<Integer> it = list.listIterator(2);
		for (int i = 2; i >= 0; i--) {
			assertThat(it.previousIndex(), equalTo(i));
		}
	}

	@Test
	public void shouldTraverseList() {
		ArrayList<Integer> list = createList();
		list.add(25);

		ListIterator<Integer> it = list.listIterator(0);
		int i = 0;
		while (it.hasNext()) {
			assertThat(list.get(i), equalTo(it.next()));
			i++;
		}

		i = 3;
		it = list.listIterator(3);
		while (it.hasPrevious()) {
			assertThat(list.get(i), equalTo(it.previous()));
			i--;
		}
	}

	private ArrayList<Integer> createList() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(5);
		list.add(10);
		return list;
	}

}
