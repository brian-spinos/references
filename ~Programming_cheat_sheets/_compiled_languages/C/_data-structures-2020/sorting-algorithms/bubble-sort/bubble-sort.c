
//
// Bubble sort
//

// gcc bubble-sort.c -o bubble-sort; ./bubble-sort

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define length(x)  (sizeof(x) / sizeof((x)[0]))
#define TRUE 1
#define FALSE 0

//
// Functions
//

void swap(int a[], int i1, int i2){
  int temp = a[i1];
  a[i1] = a[i2];
  a[i2] = temp;
}

// Loop n-1 times on both loops
// Improvements:
// - last item will be sorted
// - stop when no swaps
void bubbleSort(int a[], int size){
  for(int i=0;i<size-1;i++){
    printf("i: %d\n", i);
    int noSwaps = TRUE;
    for(int j=0;j<size-1 -i;j++){ // last item will be sorted
      printf("    j: %d\n", j);
      int idx1 = j;
      int idx2 = j+1;

      if (a[idx1] > a[idx2]) {
        swap(a, idx1, idx2);
        noSwaps = FALSE;
      }
    }
    if (noSwaps) {
      printf("NO SWAPS\n");
      return; // stop when no swaps
    }
  }
}

void printArray(int a[], int size){
  for(int i=0;i<size;i++){
    printf("%d\n", a[i]);
  }
}

//
// Main function
//

int main(){
  int numbers[] = {
    5,4,6,3,7,2,8,1,9,0
    // 1,0,2,3,4,5,6,7,8,9 // only 1 swap
    // 0,1,2,3,4,5,6,7,8,9 // no swaps
  };

  bubbleSort(numbers, length(numbers));
  printArray(numbers, length(numbers));

  return 0;
}
