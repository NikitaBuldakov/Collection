import Collection.MyList;
import Collection.MySet;
import Exceptions.MyIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.*;

public class MySetTest {
    private Set<String> set;
    private MyList nullList;

    @Before
    public void setUp(){
        set = new MySet<String>();
    }

    @Test
    public void testSetInit(){
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testAddObject(){
        set.add("Samantha");
        assertTrue(set.contains("Samantha"));
    }

    @Test
    public void testRemoveElement(){
        set.add("Samantha");
        set.remove("Samantha");
        assertFalse(set.contains("Samantha"));
    }

    @Test
    public void testClearSet(){
        set.add("Samantha");
        set.add("Vanessa");
        set.add("Karol");
        set.add("Ricky");

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testInvalidCapacity(){
        assertThrows(IllegalArgumentException.class, ()-> set = new MySet<String>(-1));
        assertThrows(IllegalArgumentException.class, ()-> set = new MySet<String>(-1, -4));
    }

    @Test
    public void testNullableCollection(){
        assertThrows(NullPointerException.class, ()-> set = new MySet<String>(nullList));
    }

}
