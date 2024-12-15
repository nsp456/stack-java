package com.example.stack;

public class CustomStack {
    private int[] stackArray;
    private int top;
    private int maxSize;

    public CustomStack(int size) {
        this.maxSize = size;
        this.stackArray = new int[size];
        this.top = -1;
    }

    public void push(int value) {
        if (top >= maxSize - 1) {
            throw new StackOverflowException("Stack is full");
        }
        stackArray[++top] = value;
    }

    public Integer pop() {
        if (top < 0) {
            throw new StackUnderflowException("Stack is empty");
        }
        return stackArray[top--];
    }

    public Integer peek() {
        if (top < 0) {
            throw new StackUnderflowException("Stack is empty");
        }
        return stackArray[top];
    }

    public int[] getStack() {
        int[] currentStack = new int[top + 1];
        System.arraycopy(stackArray, 0, currentStack, 0, top + 1);
        return currentStack;
    }

    public void setSize(int newSize) {
        if (newSize < top + 1) {
            throw new IllegalArgumentException("New size cannot be less than the current stack size");
        }
        int[] newStackArray = new int[newSize];
        System.arraycopy(stackArray, 0, newStackArray, 0, top + 1);
        this.stackArray = newStackArray;
        this.maxSize = newSize;
    }

    public int getSize() {
        return this.maxSize;
    }
}