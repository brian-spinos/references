package com.example;

import java.util.Arrays;

class Heap {
    private int capacity;
    private int size;
    int[] items;

    public Heap(int capacity) {
        this.capacity = capacity;
        size = 0;
        items = new int[capacity];
    }

    private int LI(int i) { return 2 * i + 1; }
    private int RI(int i) { return 2 * i + 2; }
    private int PI(int i) { return (i - 1) / 2; }
    private boolean hasL(int i) { return LI(i) < size; }
    private boolean hasR(int i) { return RI(i) < size; }
    private boolean hasP(int i) { return i > 0; } // HACK!
    private int LV(int i) { return items[LI(i)]; }
    private int RV(int i) { return items[RI(i)]; }
    private int PV(int i) { return items[PI(i)]; }
    public boolean isEmpty() { return size == 0; }

    private void swap(int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 1.5;
            items = Arrays.copyOf(items, capacity);
        }
    }

    public int peek() throws Exception {
        if (size == 0) throw new Exception("Heap is empty");
        return items[0];
    }

    public int remove() throws Exception {
        if (size == 0) throw new Exception("Heap is empty");
        int item = items[0];
        items[0] = items[size - 1];
        size--;
        bubbleDown();
        return item;
    }

    public void add(int item) {
        ensureCapacity();
        items[size] = item;
        size++;
        bubbleUp();
    }

    private void bubbleUp() {
        int i = size - 1;
        while (hasP(i) && PV(i) > items[i]) {
            swap(PI(i), i);
            i = PI(i);
        }
    }

    private void bubbleDown() {
        int i = 0;
        while (hasL(i)) { // no right child if there is no left child!
            int smallerChildIndex = LI(i); // assumption, will fix it bellow
            if (hasR(i) && RV(i) < LV(i))
                smallerChildIndex = RI(i);

            if (items[i] > items[smallerChildIndex]) {
                swap(i, smallerChildIndex);
                i = smallerChildIndex;
            } else {
                break;
            }
        }
    }
}

public class HeapExample {
    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.add(2);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(5);
        heap.add(0);
        heap.add(9);
        heap.add(4);
        heap.add(3);
        heap.add(1);
        try {
            if (!heap.isEmpty()) {
                System.out.println("peek: " + heap.peek());
            }

            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            System.out.println("min: " + heap.remove());
            //System.out.println("min: " + heap.remove()); // ERROR: Heap is empty
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

/* OUTPUT

peek: 0
min: 0
min: 1
min: 2
min: 3
min: 4
min: 5
min: 6
min: 7
min: 8
min: 9

*/
