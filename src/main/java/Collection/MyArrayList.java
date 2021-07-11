package Collection;

import Exceptions.MyIllegalArgumentException;
import Exceptions.MyIndexOutOfBoundsException;
import Exceptions.MyNullPointerException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class MyArrayList<E> extends AbstractList<E> implements List<E> {

    private final int INT_SIZE = 16;
    private final int RESIZE_RATE = 2;
    private int pointer = 0;
    private Object[] array = new Object[INT_SIZE];

    public MyArrayList(){}

    @SneakyThrows
    public MyArrayList(int size){

        if(size < 0)
            throw new MyIllegalArgumentException();
        pointer = size;
        size = (int) Math.ceil(Math.log(size) / Math.log(2));
        this.array = new Object[size];
    }

    public int size() {
        return pointer;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public boolean contains(Object o) {
        if(indexOf(o) != -1)
            return true;
        return false;
    }

    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < array.length && array[currentIndex] != null;
            }

            @Override
            public E next() {
                return (E) array[currentIndex++];
            }
        };
        return iterator;
    }



    @SneakyThrows
    public boolean add(Object o) {
        if(o.equals(null))
            throw new MyNullPointerException();
        if(pointer >= array.length-1){
            resize(array.length * RESIZE_RATE);
        }
        array[pointer++] = o;
        return true;
    }

    @SneakyThrows
    public boolean remove(Object o) {

        if(indexOf(o) < 0)
            throw new MyIndexOutOfBoundsException();



        for(int i = indexOf(o); i < array.length - 1; i++){
            array[i] = array[i+1];
        }
        array[pointer] = null;
        pointer--;
        if(array.length > INT_SIZE && pointer < array.length / RESIZE_RATE)
            resize(array.length / RESIZE_RATE);
        return true;
    }

    @SneakyThrows
    public boolean addAll(Collection c) {

        if(c == null)
            throw new MyNullPointerException();

        for (Object item: c)
            add(item);

        pointer += c.size();

        return true;
    }

    @SneakyThrows
    public boolean addAll(int index, Collection c) {

        if(c == null)
            throw new MyNullPointerException();

        if(index < 0)
            throw new MyIndexOutOfBoundsException();

        int collectionSize = c.size();

        Object[] tmp = new Object[array.length + collectionSize];

        System.arraycopy(array, 0, tmp, 0, index);
        System.arraycopy(c.toArray(), 0, tmp, index, collectionSize);
        System.arraycopy(array, index, tmp, index + collectionSize, array.length - index);

        array = tmp;
        pointer += collectionSize;

        return false;
    }

    public void clear() {
        for(int i = 0; i < pointer; i++)
            array[i] = null;
        pointer = 0;
    }

    @SneakyThrows
    public E get(int index) {

        if(index < 0)
            throw new MyIndexOutOfBoundsException();

        return (E) array[index];
    }

    @SneakyThrows
    public Object set(int index, Object element) {

        if(index < 0)
            throw new MyIndexOutOfBoundsException();

        if(element == null)
            throw new MyNullPointerException();

        return (E) (array[index] = element);
    }

    @SneakyThrows
    public void add(int index, Object element) {

        if(index < 0)
            throw new MyIndexOutOfBoundsException();

        if(element == null)
            throw new MyNullPointerException();

        if(pointer + 1 >= array.length-1)
            resize(array.length * RESIZE_RATE);

        if(index > pointer)
            index = pointer;

        for(int i = pointer; i >= index; i--)
            array[i + 1] = array[i];

        array[index] = element;
        pointer++;

    }

    @SneakyThrows
    public E remove(int index) {

        Object o = null;

        if(index < 0 || pointer == 0)
            throw new MyIndexOutOfBoundsException();

        o = get(index);
        for(int i = index; i < pointer; i++)
            array[i] = array[i + 1];

        pointer--;
        return (E) o;
    }

    public int indexOf(Object o) {
        for(int i = 0; i < array.length - 1; i++){
            if(o.equals(array[i]))
                return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for(int i = array.length-1; i >= 0; i--){
            if(o.equals(array[i]))
                return i;
        }
        return -1;
    }

    public ListIterator listIterator() {
        return null;
    }

    public ListIterator listIterator(int index) {
        return null;
    }

    @SneakyThrows
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            int temp = fromIndex;
            fromIndex = toIndex;
            toIndex = temp;
        }

        if(fromIndex < 0 || toIndex > array.length-1)
            throw new MyIllegalArgumentException();

        List arrayList = null;
        arrayList = new MyArrayList<E>(toIndex - fromIndex);
        for(int i = fromIndex; i < toIndex; i++)
            arrayList.add(array[i]);

        return arrayList;
    }

    public boolean retainAll(Collection c) {
        boolean check = false;
        for(Object item: c){
            if(contains(item)) {
                add(item);
                check = true;
                pointer++;
            }
        }
        return check;
    }

    public boolean removeAll(Collection c) {
        boolean check = false;
        for(Object item: c){
            if(contains(item)) {
                remove(item);
                check = true;
                pointer--;
            }
        }
        return check;
    }

    public boolean containsAll(Collection c) {
        for(Object item: c){
            if(!contains(item))
                return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {

        if (a.length < pointer)
            return (T[]) Arrays.copyOf(array, pointer, a.getClass());

        System.arraycopy(array, 0, a, 0, pointer);

        if (a.length > pointer)
            a[pointer] = null;

        return a;
    }



    public Object[] toArray() {

        Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
