//
// Merge sort
//

// gcc recursion.c -o recursion; ./recursion

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define length(x) (sizeof(x)/sizeof(x[0]))

//
// Functions
//

void printArrayOLD(int a[], int size){
  printf("Array@%p [\n", a);
  for(int i=0;i<size;i++){
    printf("  %d -> %d\n", i, a[i]);
  }
  printf("]\n");
}

void printArray(int a[], int size){
  printf("Array@%p [", a);
  for(int i=0;i<size;i++){
    if (i < size - 1){
      printf("%d, ", a[i]);
    }else{
      printf("%d", a[i]);
    }
  }
  printf("]\n");
}

// make copy of array left and right, then update the original array
void merge(int a[], int li, int mi, int ri){
  // printf("merge: %p, li: %d, mi: %d, ri: %d\n", a, li, mi, ri);
  int length1 = mi - li + 1;
  int length2 = ri - mi;
  int la[length1];
  int ra[length2];
  for (int i=0;i<length1;i++) la[i] = a[li + i];
  for (int i=0;i<length2;i++) ra[i] = a[mi + 1 + i];

  // printArray(la, length(la));
  // printArray(ra, length(ra));

  int idx1=0;
  int idx2=0;
  int idx3=li; // <------- INDEX starts at LEFT_INDEX

  while(idx1 < length1 && idx2 < length2){
    if (la[idx1] < ra[idx2]) {
      a[idx3] = la[idx1];
      idx1++;
    }else{
      a[idx3] = ra[idx2];
      idx2++;
    }
    idx3++;
  }

  // One of the array might have items left over, copy them
  while (idx1 < length1) {
    a[idx3] = la[idx1];
    idx1++;
    idx3++;
  }

  while (idx2 < length2) {
    a[idx3] = ra[idx2];
    idx2++;
    idx3++;
  }
}

// mergeSort left, right, then merge(left, right)
void mergeSort(int a[], int li, int ri){
  // printf("========= mergeSort: %p, li: %d, ri: %d\n", a, li, ri);
  if (li >= ri) return;

  int mi = li + ((ri - li) / 2);
  mergeSort(a, li, mi);
  mergeSort(a, mi+1, ri);
  merge(a, li, mi, ri);
}

//
// Main function
//

int main(){
  int arr[] = {
    5,4,6,3,7,2,8,1,9,0
    // 345,635,884,645,112,423,534,645,756,886,908,457,175,825,141,478,468,864,345,364,746,835
  };

  mergeSort(arr, 0, length(arr)-1);
  printArray(arr, length(arr));
  return 0;
}

/* OUTPUT

Array@0x7ffee43f4a90 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

*/
