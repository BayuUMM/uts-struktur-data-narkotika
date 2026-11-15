package id.ac.umm.strukdat;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class RiwayatStackTest {


    @Test
    void testPushAndSize() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        stack.push("Bayu");
        stack.push("Yusuf");


        assertEquals(2, stack.size());
    }


    @Test
    void testPopLifo() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        stack.push("Data 1");
        stack.push("Data 2");


        assertEquals("Data 2", stack.pop());
        assertEquals("Data 1", stack.pop());
        assertTrue(stack.isEmpty());
    }


    @Test
    void testPeek() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        stack.push("Bayu");


        assertEquals("Bayu", stack.peek());
        assertEquals(1, stack.size());
    }


    @Test
    void testIsEmpty() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        assertTrue(stack.isEmpty());


        stack.push("Yusuf");


        assertFalse(stack.isEmpty());
    }


    @Test
    void testClear() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        stack.push("Bayu");
        stack.push("Yusuf");
        stack.clear();


        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertTrue(stack.validateInvariant());
    }


    @Test
    void testResize() {
        RiwayatStack<Integer> stack = new RiwayatStack<>();


        for (int i = 1; i <= 25; i++) {
            stack.push(i);
        }


        assertEquals(25, stack.size());
        assertEquals(25, stack.peek());
        assertTrue(stack.validateInvariant());
    }


    @Test
    void testPopEmptyStack() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        assertThrows(
                IllegalStateException.class,
                stack::pop
        );
    }


    @Test
    void testPeekEmptyStack() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        assertThrows(
                IllegalStateException.class,
                stack::peek
        );
    }


    @Test
    void testPushNull() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        assertThrows(
                IllegalArgumentException.class,
                () -> stack.push(null)
        );
    }


    @Test
    void testInvariant() {
        RiwayatStack<String> stack = new RiwayatStack<>();


        stack.push("Data A");
        stack.push("Data B");


        assertTrue(stack.validateInvariant());
    }
}