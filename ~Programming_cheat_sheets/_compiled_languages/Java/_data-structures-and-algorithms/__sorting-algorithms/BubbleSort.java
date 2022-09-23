package com.example;

import java.util.Arrays;

class BubbleSort {
    private int[] array;

    public BubbleSort(int[] array) {
        this.array = array;
    }

    public void sort() {
        for (int i = 0; i < array.length; i++) {
            boolean noSwap = true;
            for (int j = 0; j < array.length - i - 1; j++) { // minus 1 so we can do array[j+1]
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    noSwap = false;
                }
            }
            // if we go through the whole array and no swaps, then it is already sorted
            if (noSwap) return;
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

public class BubbleSortExample {
    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 3, 7, 2, 8, 1, 0, 9};
        BubbleSort bubbleSort = new BubbleSort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        bubbleSort.sort();
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}

/* OUTPUT

Arrays.toString(arr) = [5, 4, 6, 3, 7, 2, 8, 1, 0, 9]
Arrays.toString(arr) = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

*/
