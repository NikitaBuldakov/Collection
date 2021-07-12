import Collection.MyList;
import Exceptions.MyIllegalArgumentException;
import Exceptions.MyIndexOutOfBoundsException;
import Exceptions.MyNullPointerException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class MyListTest {

    private List<String> list;


    @Before
    public void setUp(){
        list = new MyList<String>();
    }

    @Test
    public void testListInit(){
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }

    @Test
    public void testInvalidCapacity(){
        assertThrows(MyIllegalArgumentException.class, ()-> list = new MyList<String>(-1));
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

        assertTrue(list.size()==4);
    }

    @Test
    public void testAddElementNull(){
        assertThrows(MyNullPointerException.class, ()-> list.add(0, null));
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
        assertTrue(list.size() == 2);
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
        ArrayList<String> arrayList = new ArrayList();

        arrayList.add(0, "Karol");
        arrayList.add(1, "Vanessa");
        arrayList.add(2, "Amanda");

        list = new MyList<String>(arrayList);

        assertTrue(list.containsAll(arrayList));
        assertEquals(arrayList.size(), list.size());
    }

    @Test
    public void testAddAllCollection(){
        ArrayList<String> arrayList = new ArrayList();

        arrayList.add(0, "Karol");
        arrayList.add(1, "Vanessa");
        arrayList.add(2, "Amanda");

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
        ArrayList<String> arrayList = new ArrayList();

        arrayList.add(0, "Karol");
        arrayList.add(1, "Vanessa");
        arrayList.add(2, "Amanda");

        list.addAll(arrayList);

        list.clear();

        assertEquals(list.size(), 0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIndexOf(){
        ArrayList<String> arrayList = new ArrayList();

        arrayList.add(0, "Karol");
        arrayList.add(1, "Vanessa");
        arrayList.add(2, "Amanda");

        list.addAll(arrayList);
        list.addAll(2, arrayList);

        assertEquals(list.indexOf("Vanessa"), 1);
        assertEquals(list.lastIndexOf("Karol"),2);

    }

    @Test
    public void testSubList(){
        ArrayList<String> arrayList = new ArrayList();

        arrayList.add(0, "Karol");
        arrayList.add(1, "Vanessa");
        arrayList.add(2, "Amanda");

        list.addAll(arrayList);
        list.addAll(2, arrayList);

        List<String> newList = list.subList(2, 5);

        assertEquals(newList, arrayList);
    }


}
