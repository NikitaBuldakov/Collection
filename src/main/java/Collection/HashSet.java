package Collection;


import lombok.Builder;
import lombok.SneakyThrows;
import lombok.With;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class HashSet<E> extends AbstractSet<E> implements Set<E> {

    private HashMap<E, Object> map;



    private static final Object object = new Object();

    public HashSet() {
        map = new HashMap<>();
    }

    public HashSet(int capacty) {
        map = new HashMap<>(capacty);
    }

    public HashSet(int capacty, float loadFactor) {
        map = new HashMap<>(capacty, loadFactor);
    }

    public HashSet(Collection<? extends E> collection)
    {
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
    public Object clone() throws InternalError
    {

        HashSet hashSet;
        try {
            hashSet = (HashSet) super.clone();
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
