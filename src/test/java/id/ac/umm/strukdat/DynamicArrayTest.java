
package id.ac.umm.strukdat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void testAddAndGet() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Bayu");
        array.add("Yusuf");

        assertEquals(2, array.size());
        assertEquals("Bayu", array.get(0));
        assertEquals("Yusuf", array.get(1));
    }

    @Test
    void testInsert() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Bayu");
        array.add("Yusuf");
        array.insert(1, "Abdan");

        assertEquals("Abdan", array.get(1));
        assertEquals(3, array.size());
    }

    @Test
    void testRemove() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Bayu");
        array.add("Yusuf");

        String removed = array.remove(0);

        assertEquals("Bayu", removed);
        assertEquals("Yusuf", array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void testSet() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Bayu");

        String oldValue = array.set(0, "Yusuf");

        assertEquals("Bayu", oldValue);
        assertEquals("Yusuf", array.get(0));
    }

    @Test
    void testSearch() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Bayu");
        array.add("Yusuf");

        assertEquals(1, array.search("Yusuf"));
        assertEquals(2, array.getLangkahTerakhir());
        assertEquals(-1, array.search("Abdan"));
    }

    @Test
    void testResize() {
        DynamicArray<Integer> array = new DynamicArray<>();

        for (int i = 0; i < 15; i++) {
            array.add(i);
        }

        assertEquals(15, array.size());
        assertEquals(14, array.get(14));
        assertTrue(array.validateInvariant());
    }

    @Test
    void testClear() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Bayu");
        array.add("Yusuf");
        array.clear();

        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
        assertTrue(array.validateInvariant());
    }

    @Test
    void testContains() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Bayu");

        assertTrue(array.contains("Bayu"));
        assertFalse(array.contains("Yusuf"));
    }

    @Test
    void testInvalidIndex() {
        DynamicArray<String> array = new DynamicArray<>();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> array.get(0)
        );
    }
}
