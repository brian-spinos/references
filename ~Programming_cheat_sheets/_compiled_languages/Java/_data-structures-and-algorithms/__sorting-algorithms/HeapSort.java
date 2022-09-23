package com.example;

import java.util.Arrays;

class HeapSort {
    private int[] array;

    public HeapSort(int[] array) {
        this.array = array;
    }

    public void sort() {
        if (array.length < 2) return;

        // Create heap
        for (int i = 0; i < array.length; i++) {
            bubbleUp(i);
        }

        // Swap highest values to end or array
        for (int i = 0; i < array.length; i++) {
            int currLastIndex = array.length - 1 - i;
            swap(0, currLastIndex);
            bubbleDown(currLastIndex);
        }
    }

    //
    // Helpers
    //

    private int PI(int i){ return (i-1)/2;}
    private int LI(int i){ return 2 * i + 1;}
    private int RI(int i){ return 2 * i + 2;}
    private boolean hasP(int i){ return i > 0;}
    private boolean hasL(int i, int size){ return LI(i) < size;}
    private boolean hasR(int i, int size){ return RI(i) < size;}
    private int PV(int i){ return array[PI(i)]; }
    private int LV(int i){ return array[LI(i)]; }
    private int RV(int i){ return array[RI(i)]; }

    private void bubbleUp(int i) {
        while (i >= 0 && hasP(i) && PV(i) < array[i]) {
            swap(PI(i), i);
            i = PI(i);
        }
    }

    // currLastIndex will NOT be touched/swapped
    private void bubbleDown(int currLastIndex) {
        int i = 0;

        while (hasL(i, currLastIndex)) {
            int gci = LI(i);
            if (hasR(i, currLastIndex) && RV(i) > LV(i)) {
                gci = RI(i);
            }

            if (array[i] < array[gci]) {
                swap(i, gci);
                i = gci;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

public class HeapSortExample {
    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 3, 7, 2, 8, 1, 0, 9};
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        HeapSort heapSort = new HeapSort(arr);
        heapSort.sort();
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}

/* OUTPUT

Arrays.toString(arr) = [5, 4, 6, 3, 7, 2, 8, 1, 0, 9]
Arrays.toString(arr) = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

*/
