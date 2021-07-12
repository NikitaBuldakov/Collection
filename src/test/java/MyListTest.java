import Collection.MyList;
import Exceptions.MyIllegalArgumentException;
import Exceptions.MyIndexOutOfBoundsException;
import Exceptions.MyNullPointerException;
import com.sun.source.tree.NewArrayTree;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class MyListTest {

    private List<String> list;

    private ArrayList<String> arrayList;

    private ArrayList<String> nullableCollection;

    @Before
    public void setUp(){
        list = new MyList<String>();

        arrayList = new ArrayList<>();

        arrayList.add(0, "Karol");
        arrayList.add(1, "Vanessa");
        arrayList.add(2, "Amanda");
    }

    @Test
    public void testListInit(){
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testInvalidCapacity(){
        assertThrows(MyIllegalArgumentException.class, ()-> list = new MyList<String>(-1));
    }

    @Test
    public void testInitWithNullableCollection(){
        assertThrows(NullPointerException.class, ()-> list = new MyList<String>(nullableCollection));
    }

    @Test
    public void testAddElements(){
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Karol", list.get(0));
        assertEquals("Vanessa", list.get(1));
        assertEquals("Amanda", list.get(2));

        list.add(1, "Mariana");

        assertEquals("Karol", list.get(0));
        assertEquals("Mariana", list.get(1));
        assertEquals("Vanessa", list.get(2));
        assertEquals("Amanda", list.get(3));

        assertEquals(4, list.size());
    }

    @Test
    public void testAddElementNull(){
        assertThrows(MyNullPointerException.class, ()-> list.add(0, null));
        assertThrows(NullPointerException.class, ()-> list.add(null));
    }

    @Test
    public void testSetElementNull(){
        list.add(0, "Kheyla");
        assertThrows(MyNullPointerException.class, ()-> list.set(0, null));
    }

    @Test
    public void testSetElement(){
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        list.set(1, "Livia");

        assertEquals("Karol", list.get(0));
        assertEquals("Livia", list.get(1));
        assertEquals("Amanda", list.get(2));
    }

    @Test
    public void testRemoveElement(){
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Amanda", list.remove(2));
        assertEquals(2, list.size());
    }

    @Test
    public void testAddNullableCollection(){
        assertThrows(MyNullPointerException.class, ()-> list.addAll(nullableCollection));
        assertThrows(MyNullPointerException.class, ()-> list.addAll(0, nullableCollection));
    }

    @Test
    public void testInvalidArgumentForAddingCollection(){
        assertThrows(MyIndexOutOfBoundsException.class, ()-> list.addAll(-1, arrayList));
    }

    @Test
    public void testRemoveByIllegalIndex(){
        assertThrows(MyIndexOutOfBoundsException.class, ()-> list.remove(-1));
    }

    @Test
    public void testRemoveWithEmptyList(){
        assertThrows(MyIndexOutOfBoundsException.class, ()-> list.remove(0));
    }

    @Test
    public void testContainsElement(){
        list.add("Karol");
        assertTrue(list.contains("Karol"));
    }

    @Test
    public void testInitWithCollection(){

        list = new MyList<>(arrayList);

        assertTrue(list.containsAll(arrayList));
        assertEquals(arrayList.size(), list.size());
    }

    @Test
    public void testAddAllCollection(){

        list.addAll(arrayList);

        assertTrue(list.containsAll(arrayList));
        assertEquals(list.size(), arrayList.size());

        list.addAll(2, arrayList);

        assertEquals(list.size(), arrayList.size()*2);
        assertEquals("Karol", list.get(0));
        assertEquals("Karol", list.get(2));
        assertEquals("Vanessa", list.get(1));
        assertEquals("Amanda", list.get(4));
        assertEquals("Vanessa", list.get(3));
        assertEquals("Amanda", list.get(5));
    }

    @Test
    public void testClear(){

        list.addAll(arrayList);

        list.clear();

        assertEquals(list.size(), 0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIndexOf(){

        list.addAll(arrayList);
        list.addAll(2, arrayList);

        assertEquals(list.indexOf("Vanessa"), 1);
        assertEquals(list.lastIndexOf("Karol"),2);

    }

    @Test
    public void testSubList(){

        list.addAll(arrayList);
        list.addAll(2, arrayList);

        List<String> newList = list.subList(2, 5);

        assertEquals("Karol", newList.get(0));
        assertEquals("Vanessa", newList.get(1));
        assertEquals("Amanda", newList.get(2));
    }

    @Test
    public void testSubListWithIllegalIndexes()
    {
        assertThrows(IndexOutOfBoundsException.class, ()-> list.subList(-2, 200));
        assertThrows(IllegalArgumentException.class, ()-> list.subList(15, 2));
    }

    @Test
    public void testRetainALL(){

        list.addAll(arrayList);

        List<String> newList = new MyList<String>();

        List<String> expectedList = new MyList<String>();

        expectedList.add("Karol");
        expectedList.add("Vanessa");

        newList.add("Karol");
        newList.add("Piter");
        newList.add("Vanessa");
        newList.add("Samantha");

        list.retainAll(newList);

        assertArrayEquals(list.toArray(), expectedList.toArray());
    }

    @Test
    public void testRemoveAll(){

        list.addAll(arrayList);
        list.removeAll(arrayList);
        assertTrue(list.isEmpty());

    }

    @Test
    public void testContainsALl(){
        list.addAll(arrayList);
        assertTrue(list.containsAll(arrayList));
    }

    @Test
    public void testContainsRemoveRetainAllWithNullableCollection(){
        assertThrows(NullPointerException.class, ()-> list.containsAll(nullableCollection));
        assertThrows(NullPointerException.class, ()-> list.removeAll(nullableCollection));
        assertThrows(NullPointerException.class, ()-> list.retainAll(nullableCollection));
    }

    @Test
    public void testToArray(){

        list.addAll(arrayList);

        Object[] ar1 = list.toArray();

        assertEquals("Karol", ar1[0]);
        assertEquals("Vanessa", ar1[1]);
        assertEquals("Amanda",ar1[2]);

        String[] ar2 = new String[list.size()];
        ar2 = list.toArray(ar2);

        assertEquals("Karol", ar2[0]);
        assertEquals("Vanessa", ar2[1]);
        assertEquals("Amanda",ar2[2]);

    }

    @Test
    public void testGetElement(){
        list.add("Karol");

        assertEquals(list.get(0), "Karol");
    }

}
