package by.tc.module3.task3_4.list;

import java.util.Collection;
import java.util.List;

public abstract class MyAbstractList<T> implements List<T> {

    protected int size;

    protected void checkIndexValidity(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    protected abstract int generalizedIndexOf(Object o, boolean stopOnFirstMatch);

    @Override
    public int indexOf(Object o) {
        return generalizedIndexOf(o, true);
    }

    @Override
    public int lastIndexOf(Object o) {
        return generalizedIndexOf(o, false);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean status = true;
        for (Object o: c) {
            if (!contains(o)) {
                status = false;
                break;
            }
        }
        return status;
    }

    @Override
    public abstract boolean add(T o);

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean status = false;
        for (T elem: c) {
            add(elem);
            status = true; // as .add(elem) always changes the list
        }
        return status;
    }

    @Override
    public abstract T remove(int index);

    @Override
    public boolean remove(Object o) {
        boolean status = false;
        int index = indexOf((T) o);
        if (index != -1) {
            remove(index);
            status = true;
        }
        return status;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean status = false;
        for (Object o: c) {
            status |= remove((T) o);
        }
        return status;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
