package com.example;

import java.util.Arrays;

class SelectionSort {
  public static void sort(int[] arr){
      for(int i=0; i<arr.length-1;i++){ // we don't do it for the last item
          int minIndex = i;

          // get min
          for (int j=i; j<arr.length;j++){
              if (arr[j] < arr[minIndex]){
                  minIndex = j;
              }
          }
          swap(arr, i, minIndex);
      }
  }

  private static void swap(int[] arr, int a, int b){
      int temp = arr[a];
      arr[a] = arr[b];
      arr[b] = temp;
  }
}

public class SelectionSortExample {
    public static void main(String[] args) {
        int[] arr = {
                5,6,4,7,3,8,2,9,1,0
        };

        SelectionSort.sort(arr);

        System.out.println(Arrays.toString(arr));
        //=> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
