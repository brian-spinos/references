package com.example;

import java.util.Arrays;

class QuickSort {
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    // recursive
    private static void sort(int[] array, int start, int end) {
        if (start >= end) return;
        int sortedPosition = partition(array, start, end);
        sort(array, start, sortedPosition - 1);
        sort(array, sortedPosition + 1, end);
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low;

        // j will scan from left to right, looking for smaller then pivot elements
        // and swapping with i
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (array[j] < pivot) {
                swap(array, i, j);
                i++; // move i into position, for future swaps!
            }
        }

        // swap arr[i] and arr[high] (or pivot)
        // because the element after i if the first of the second partition (elements higher than pivot)
        swap(array, i, high);

        // because i is now in the first element of the second partition (elements greater than the pivot)
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

public class QuickSortExample {
    public static void main(String[] args) {
        int[] array = new int[]{
                5, 6, 4, 7, 3, 8, 2, 9, 1, 0
        };
        QuickSort.sort(array);
        System.out.println(Arrays.toString(array));
        //=> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
