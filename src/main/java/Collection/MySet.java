package Collection;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MySet<E> extends AbstractSet<E> implements Set<E> {

    private HashMap<E, Object> map;



    private static final Object object = new Object();

    public MySet() {
        map = new HashMap<>();
    }

    @SneakyThrows
    public MySet(int capacty) {
        if (capacty < 0)
            throw new IllegalArgumentException();
        map = new HashMap<>(capacty);
    }

    @SneakyThrows
    public MySet(int capacty, float loadFactor) {
        if (capacty < 0 || loadFactor < 0)
            throw new IllegalArgumentException();
        map = new HashMap<>(capacty, loadFactor);
    }

    @SneakyThrows
    public MySet(Collection<? extends E> collection)
    {
        if (collection == null)
            throw new NullPointerException();
        int capacity = Math.max(16, (int)(collection.size()/.75f)+1);
        this.map = new HashMap<>(capacity);
        for (E e: collection){
            map.put(e, object);
        }
    }

    public int size() {
        return map.size();
    }


    public boolean isEmpty() {
        return map.isEmpty();
    }


    public boolean contains(Object o) {
        return map.containsKey(o);
    }


    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }


    public boolean add(E e) {
        return map.put(e, object) == null;
    }


    public boolean remove(Object o) {
        return map.remove(o) == object;
    }

    @SneakyThrows
    public Object clone() throws InternalError // clonnable
    {

        MySet hashSet;
        try {
            hashSet = (MySet) super.clone();
            hashSet.map = (HashMap) map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return hashSet;
    }


    public void clear() {
        map.clear();
    }
}
