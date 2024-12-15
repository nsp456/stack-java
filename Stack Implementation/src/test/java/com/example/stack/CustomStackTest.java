package com.example.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomStackTest {

    @Test
    void testPush() {
        CustomStack stack = new CustomStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThrows(StackOverflowException.class, () -> stack.push(4));
    }

    @Test
    void testPop() {
        CustomStack stack = new CustomStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertThrows(StackUnderflowException.class, stack::pop);
    }

    @Test
    void testPeek() {
        CustomStack stack = new CustomStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.peek());
        stack.pop();
        assertEquals(2, stack.peek());
        stack.pop();
        assertEquals(1, stack.peek());
        stack.pop();
        assertThrows(StackUnderflowException.class, stack::peek);
    }

    @Test
    void testGetStack() {
        CustomStack stack = new CustomStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int[] expected = {1, 2, 3};
        assertArrayEquals(expected, stack.getStack());
    }

    @Test
    void testSetSize() {
        CustomStack stack = new CustomStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.setSize(5);
        stack.push(4);
        stack.push(5);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, stack.getStack());
        assertThrows(IllegalArgumentException.class, () -> stack.setSize(2));
    }
}