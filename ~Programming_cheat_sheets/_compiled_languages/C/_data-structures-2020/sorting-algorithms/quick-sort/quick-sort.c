//
// Quick sort
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define length(x) (sizeof(x)/sizeof(x[0]))

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

// Last item is pivot
int partition(int a[], int li, int ri){
  int pivotIndex = ri;
  int i=li;
  for(int j=li;j<=ri;j++){
    if (a[j] < a[pivotIndex]) {
      swap(a,i,j);
      i++;
    }
  }
  swap(a,pivotIndex,i);
  return i;
}

// Has a helper function called partition
void quickSort(int a[], int li, int ri){
  if (li >= ri) return;
  int idx = partition(a, li, ri);
  quickSort(a, li, idx-1);
  quickSort(a, idx+1, ri);
}

//
// Main function
//

int main(){
  int arr[] = { 0,4,6,5,7,2,8,1,9,3 };
  quickSort(arr, 0, length(arr)-1);
  printArray(arr, length(arr));
  return 0;
}

/* OUTPUT

Array@0x7ffee1bf5a90 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

*/
