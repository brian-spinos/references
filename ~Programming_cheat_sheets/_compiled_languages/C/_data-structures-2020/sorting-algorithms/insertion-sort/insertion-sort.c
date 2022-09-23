//
// Insertion sort
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define length(x) (sizeof(x)/sizeof(x[0]))

//
// Functions
//

void swap(int a[], int i1, int i2){
  int temp = a[i1];
  a[i1] = a[i2];
  a[i2] = temp;
}

void printArray(int a[], int size){
  printf("Array@%p [", a);
  for(int i=0;i<size;i++){
    if (i<size-1) {
      printf("%d, ", a[i]);
    }else{
      printf("%d", a[i]);
    }
  }
  printf("]\n");
}

void insertionSort(int a[], int size){
  if (size < 1) return;
  for(int i=1;i<size;i++){
    int currentValue = a[i];
    for(int j=(i-1);j>=0;j--){
      if (j==0) { // SPECIAL CASE, (no more left elements)
        if (currentValue < a[j]) {
          a[j+1] = a[j]; // move to right
          a[0] = currentValue;
        }else{
          a[j+1] = currentValue;
          break;
        }
      }else{
        if (currentValue < a[j]) {
          a[j+1] = a[j]; // move to right
        }else{
          a[j+1] = currentValue;
          break;
        }
      }
    }
  }
}

//
// Main function
//

int main(){
  int arr[] = {
    50,5,90,4,80,6,70,3,60,7,2,40,8,30,1,20,9,10,0
  };

  insertionSort(arr, length(arr));
  printArray(arr, length(arr));
  return 0;
}

/* OUTPUT

Array@0x7ffee9c37a70 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90]

*/
