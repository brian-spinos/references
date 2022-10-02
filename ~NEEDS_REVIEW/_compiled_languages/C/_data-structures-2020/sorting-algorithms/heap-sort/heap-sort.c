//
// Heap sort
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// BE CAREFUL, if these are wrong, they can do a lot of damage
#define length(x) (sizeof(x)/sizeof(x[0]))
#define getPI(i) ((i - 1) / 2)
#define getLI(i) ((2 * i) + 1)
#define getRI(i) ((2 * i) + 2)
#define getPV(a, i) (a[getPI(i)])
#define getLV(a, i) (a[getLI(i)])
#define getRV(a, i) (a[getRI(i)])
#define hasP(a, i) (i != 0 && a[getPI(i)] >= 0)
#define hasL(i, size) (getLI(i) < size)
#define hasR(i, size) (getRI(i) < size)

//
// Functions
//

void printArray(int a[], int size){
  printf("Array@%p [", a);
  for(int i=0;i<size;i++){
    if (i < size-1) printf("%d, ", a[i]);
    else printf("%d", a[i]);
  }
  printf("]\n");
}

void swap(int a[], int i1, int i2){
  int temp = a[i1];
  a[i1] = a[i2];
  a[i2] = temp;
}

void bubbleDownUntil(int a[], int size){
  int idx = 0;
  while (hasL(idx, size)) {
    int gci = getLI(idx);
    if (hasR(idx, size) && getRV(a, idx) > getLV(a, idx)) {
      gci = getRI(idx);
    }

    if (a[gci] > a[idx]){
      swap(a, gci, idx);
      idx = gci;
    }else{
      break;
    }
  }
}

void bubbleUp(int a[], int idx){
  while (hasP(a, idx) && getPV(a, idx) < a[idx]) {
    swap(a, getPI(idx), idx);
    idx = getPI(idx);
  }
}

// We build max heap, then swap index 0 with current-last
void heapSort(int a[], int size){
  for(int i=1;i<size;i++){
    bubbleUp(a, i);
  }

  for(int i=(size-1);i>=1;i--){
    swap(a, 0, i);
    bubbleDownUntil(a, i); // las-index (i) should NOT be touched!
  }
}

//
// Main function
//

int main(){
  int arr[] = {
    5,4,6,3,7,2,8,1,9,0
  };

  heapSort(arr, length(arr));
  printArray(arr, length(arr));
  return 0;
}

/* OUTPUT

Array@0x7ffeee8a7aa0 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

*/
