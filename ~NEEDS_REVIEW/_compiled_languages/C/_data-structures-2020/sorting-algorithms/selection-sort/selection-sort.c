//
// Selection sort
//

// gcc main.c -o main; ./main

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define length(x) (sizeof(x)/sizeof(x[0]))

//
// Functions
//

void printArray(int a[], int size){
  printf("Array@%p [", a);
  for(int i=0;i<size;i++){
    if (i < size-1) {
      printf("%d, ", a[i]);
    }else{
      printf("%d", a[i]);

    }
  }
  printf("]\n");
}

void swap(int a[], int i1, int i2){
  int temp = a[i1];
  a[i1] = a[i2];
  a[i2] = temp;
}

void selectionSort(int a[], int size){
  int idx1 = 0; // left-most element
  int idx2 = 0; // current min
  for(int i=0;i<size-1;i++){ // We dont need to do the last i
    idx2 = i;
    for(int j=i;j<size;j++){ // j=i
      if (a[j] < a[idx2]) {
        idx2 = j;
      }
    }
    swap(a, idx1, idx2);
    idx1++;
  }
}

//
// Main function
//

int main(){
  int arr[] = {
    5,4,6,3,7,2,8,1,9,0
  };

  selectionSort(arr, length(arr));
  printArray(arr, length(arr));

  return 0;
}

/* OUTPUT

Array@0x7ffee21c0aa0 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

*/
