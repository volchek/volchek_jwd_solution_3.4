package by.tc.module3.task3_4.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ListIterator;

public class LinkedListTest {

	@Test
	public void shouldCreateEmptyList() {
		LinkedList<Integer> list = new LinkedList<>();
	}

	@Test
	public void shouldAddElement() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(3);
		list.add(5);
		list.add(7);
		assertThat(list.size(), equalTo(3));
	}

	@Test
	public void shouldGetElementByIndex() {
		LinkedList<Integer> list = makeExample();
		assertThat(list.get(0), equalTo(3));
		assertThat(list.get(1), equalTo(15));
		assertThat(list.get(2), equalTo(10));
		assertThat(list.get(3), equalTo(20));
	}

	@Test
	public void shouldGetIndexByElement() {
		LinkedList<Integer> list = makeExample();
		assertThat(list.indexOf(3), equalTo(0));
		assertThat(list.indexOf(15), equalTo(1));
		assertThat(list.indexOf(10), equalTo(2));
		assertThat(list.indexOf(20), equalTo(3));
	}

	@Test
	public void shouldGetLastIndexByElement() {
		LinkedList<Integer> list = makeExample();
		list.add(3);
		assertThat(list.lastIndexOf(3), equalTo(4));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldFailOnInvalidIndex() {
		LinkedList<Integer> list = makeExample();
		list.get(4);
	}

	@Test
	public void shouldCheckContainment() {
		LinkedList<Integer> list = makeExample();
		assertThat(list.contains(15), equalTo(true));
		assertThat(list.contains(16), equalTo(false));
	}

	@Test
	public void shouldRemoveElementByValue() {
		LinkedList<Integer> list = makeExample();
		list.remove((Object) 15);
		assertThat(list.get(0), equalTo(3));
		assertThat(list.get(1), equalTo(10));
		assertThat(list.get(2), equalTo(20));
	}

	@Test
	public void shouldRemoveFirstElementByValue() {
		LinkedList<Integer> list = makeExample();
		list.remove((Object) 3);
		assertThat(list.get(0), equalTo(15));
		assertThat(list.get(1), equalTo(10));
		assertThat(list.get(2), equalTo(20));
	}

	@Test
	public void shouldRemoveLastElementByValue() {
		LinkedList<Integer> list = makeExample();
		list.remove((Object) 20);
		assertThat(list.get(0), equalTo(3));
		assertThat(list.get(1), equalTo(15));
		assertThat(list.get(2), equalTo(10));
	}

	@Test
	public void shouldRemoveElementByIndex() {
		LinkedList<Integer> list = makeExample();
		list.remove(2);
		assertThat(list.get(0), equalTo(3));
		assertThat(list.get(1), equalTo(15));
		assertThat(list.get(2), equalTo(20));

		list.remove(0);
		assertThat(list.get(0), equalTo(15));
		assertThat(list.get(1), equalTo(20));

		list.remove(1);
		assertThat(list.get(0), equalTo(15));
		assertThat(list.size(), equalTo(1));

	}

	@Test
	public void shouldCheckNextIterator() {
		LinkedList<Integer> list = createList();

		ListIterator<Integer> it = list.listIterator(0);
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
		LinkedList<Integer> list = createList();

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
		LinkedList<Integer> list = createList();

		ListIterator<Integer> it = list.listIterator(0);
		for (int i = 0; i < list.size(); i++) {
			assertThat(it.nextIndex(), equalTo(i));
		}
	}

	@Test
	public void shouldCheckPrevIndexIterator() {
		LinkedList<Integer> list = createList();

		ListIterator<Integer> it = list.listIterator(2);
		for (int i = 2; i >= 0; i--) {
			assertThat(it.previousIndex(), equalTo(i));
		}
	}

	@Test
	public void shouldTraverseList() {
		LinkedList<Integer> list = createList();
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

	@Test
	public void shouldReturnIndexes() {
		LinkedList<Integer> list = createList();
		list.add(25);

		ListIterator<Integer> it = list.listIterator(0);
		int i = 0;
		while (it.hasNext()) {
			assertThat(i, equalTo(it.nextIndex()));
			i++;
		}

		i = 3;
		it = list.listIterator(3);
		while (it.hasPrevious()) {
			assertThat(i, equalTo(it.previousIndex()));
			i--;
		}
	}

	private LinkedList<Integer> createList() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(3);
		list.add(5);
		list.add(10);
		return list;
	}

	private LinkedList<Integer> makeExample() {
		return new LinkedList<Integer>() {
			{
				add(3);
				add(15);
				add(10);
				add(20);
			}
		};
	}

}
