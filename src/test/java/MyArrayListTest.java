import Collection.MyArrayList;
import Exceptions.MyIllegalArgumentException;
import Exceptions.MyIndexOutOfBoundsException;
import Exceptions.MyNullPointerException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class MyArrayListTest {

    private List<String> list;


    @Before
    public void setUp(){
        list = new MyArrayList<String>();
    }

    @Test
    public void testListInit(){
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }

    @Test
    public void testInvalidCapacity(){
        assertThrows(MyIllegalArgumentException.class, ()-> list = new MyArrayList<String>(-1));
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
}
