package com.example;

import java.util.Arrays;

class MergeSort {
    public static int[] sort(int[] arr) {
        return mergeSort(arr); // recursive
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr; // if the arr has 1 element, it is already sorted!
        int midIdx = arr.length / 2;
        int[] arr1 = mergeSort(Arrays.copyOfRange(arr, 0, midIdx)); // get left side of the arr
        int[] arr2 = mergeSort(Arrays.copyOfRange(arr, midIdx, arr.length)); // get right side of the arr
        return merge(arr1, arr2);
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int size = arr1.length + arr2.length;
        int[] arr3 = new int[size];
        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;

        while (idx1 < arr1.length && idx2 < arr2.length) {
            if (arr1[idx1] < arr2[idx2]) {
                arr3[idx3] = arr1[idx1];
                idx1++;
            } else {
                arr3[idx3] = arr2[idx2];
                idx2++;
            }
            idx3++;
        }

        if (idx1 < arr1.length) {
            // still items in arr1
            while (idx1 < arr1.length) {
                arr3[idx3] = arr1[idx1];
                idx3++;
                idx1++;
            }
        } else {
            // still items in arr2
            while (idx2 < arr2.length) {
                arr3[idx3] = arr2[idx2];
                idx3++;
                idx2++;
            }
        }
        return arr3;
    }
}

public class MergeSortExample {
    public static void main(String[] args) {
        int[] arr = {
                5, 6, 4, 7, 3, 8, 2, 9, 1, 0
        };
        arr = MergeSort.sort(arr);

        System.out.println(Arrays.toString(arr));
        //=> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
