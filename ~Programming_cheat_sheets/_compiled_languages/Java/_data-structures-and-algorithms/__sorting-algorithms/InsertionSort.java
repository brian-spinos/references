// TODO: review this code
package com.example;

import java.util.Arrays;

class InsertionSort {
    public static void sort(int[] arr) {
        if (arr.length == 1) return;

        for (int i = 1; i < arr.length; i++) {
            int iVal = arr[i]; // need to save this, or else it will be overwritten
            boolean isInsertion = false; // special case that no insertion was made
            for (int j = (i - 1); j >= 0; j--) {
                if (arr[j] > iVal) {
                    arr[j + 1] = arr[j]; // move to the right
                } else {
                    isInsertion = true;
                    arr[j + 1] = iVal; // arr[j++] is vacant, use it
                    break; // no need to go further in this iteration
                }
            }
            if (!isInsertion)
                arr[0] = iVal; // in case all j's were grater than i
        }
    }
}

public class InsertionSortExample {
    public static void main(String[] args) {
        int[] arr = {
                5, 6, 4, 7, 3, 8, 2, 9, 1, 0
        };
        InsertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        //=> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
