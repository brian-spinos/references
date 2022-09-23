//
// Min Heap
//

#import<stdio.h>
#import<stdlib.h>
#import<string.h>

#define TRUE 1
#define FALSE 0

//
// Structs
//

typedef struct Heap {
  int capacity;
  int size; // works as a 'next available index'
  int * array; // size will vary
} Heap;

//
// Helpers
//

int getPI(int idx){ return (idx - 1) / 2; }
int getLI(int idx){ return 2 * idx + 1; }
int getRI(int idx){ return 2 * idx + 2; }
// ATTENTION: does not work with idx 0: hasP(0) == TRUE, which SHOULD BE FALSE
//            check if (idx > 0) first
int hasP(int idx){ return getPI(idx) >= 0; }
int hasL(Heap * heap, int idx){ return getLI(idx) < heap->size; }
int hasR(Heap * heap, int idx){ return getRI(idx) < heap->size; }
int getPV(Heap * heap, int idx){ return heap->array[getPI(idx)]; }
int getLV(Heap * heap, int idx){ return heap->array[getLI(idx)]; }
int getRV(Heap * heap, int idx){ return heap->array[getRI(idx)]; }

void swap(Heap * heap, int i1, int i2){
  int temp = heap->array[i1];
  heap->array[i1] = heap->array[i2];
  heap->array[i2] = temp;
}

void bubbleUp(Heap * heap){
  int idx = heap->size; // last index

  while(idx > 0){
    if (hasP(idx) == TRUE && getPV(heap, idx) > heap->array[idx]) {
      swap(heap, getPI(idx), idx);
      idx = getPI(idx);
    }else{
      return;
    }
  }
}

void bubbleDown(Heap * heap){
  int idx = 0; // first index

  while(hasL(heap, idx) == TRUE){
    // get child idx that has min value:
    int sci = getLI(idx);
    if (hasR(heap, idx) == TRUE && getRV(heap, idx) < getLV(heap, idx)) {
      sci = getRI(idx);
    }

    if (heap->array[idx] > heap->array[sci]) {
      swap(heap, idx, sci);
      idx = sci;
    }else{
      return;
    }
  }
}

//
// Functions
//

Heap * createHeap(int capacity){
  Heap * heap = (Heap *)malloc(sizeof(Heap));
  heap->capacity = capacity;
  heap->size = 0;
  heap->array = (int *)malloc(sizeof(int) * capacity);
  return heap;
}

int getIncreasedCapacity(int capacity){
  double cap = (double) capacity;
  double inc = 1.5;
  int result = cap * inc;
  return result;
}

void copyArrayFromToWithCount(int * a1, int * a2, int count){
  for(int i=0;i<count;i++){
    a2[i] = a1[i];
  }
  free(a1);
}

void ensureCapacity(Heap * heap){
  if (heap->size == heap->capacity) {
    // add 1/2 to array capacity:
    int newCapacity = getIncreasedCapacity(heap->capacity);
    printf("newCapacity: %d\n", newCapacity);
    int * array2 = (int *)malloc(sizeof(int) * newCapacity);
    copyArrayFromToWithCount(heap->array, array2, heap->size);
    heap->array = array2;
    heap->capacity = newCapacity;
  }
}

void adjustCapacity(Heap * heap){
  if (heap->size < (heap->capacity / 4)) {
    // half array capacity, if only 1/4 used
    int newCapacity = heap->capacity / 2;
    printf("newCapacity: %d\n", newCapacity);
    int * array2 = (int *)malloc(sizeof(int) * newCapacity);
    copyArrayFromToWithCount(heap->array, array2, heap->size);
    heap->array = array2;
    heap->capacity = newCapacity;
  }
}

// add to bottom, bubble up
void add(Heap * heap, int item){
  printf("add: %d\n", item);
  ensureCapacity(heap);
  heap->array[heap->size] = item;
  bubbleUp(heap);
  heap->size++;
}

// add last to top, bubble down
int rem(Heap * heap){
  int * arr = heap->array;

  int top = arr[0];
  arr[0] = arr[heap->size - 1];
  bubbleDown(heap);
  heap->size--;
  adjustCapacity(heap);
  printf("rem: %d\n", top);
  return top;
}

int peek(Heap * heap){
  int top = heap->array[0];
  printf("\n\npeek: %d\n\n", top);
  return top;
}

//
// Main function
//

int main(){
  Heap * heap = createHeap(10);
  for(int i=60;i>10;i--){
    add(heap, i);
  }

  peek(heap);

  for(int i=60;i>10;i--){
    rem(heap);
  }

  return 0;
}


/* OUTPUT

add: 60
add: 59
add: 58
add: 57
add: 56
add: 55
add: 54
add: 53
add: 52
add: 51
add: 50
newCapacity: 15
add: 49
add: 48
add: 47
add: 46
add: 45
newCapacity: 22
add: 44
add: 43
add: 42
add: 41
add: 40
add: 39
add: 38
newCapacity: 33
add: 37
add: 36
add: 35
add: 34
add: 33
add: 32
add: 31
add: 30
add: 29
add: 28
add: 27
newCapacity: 49
add: 26
add: 25
add: 24
add: 23
add: 22
add: 21
add: 20
add: 19
add: 18
add: 17
add: 16
add: 15
add: 14
add: 13
add: 12
add: 11
newCapacity: 73


peek: 11

rem: 11
rem: 12
rem: 13
rem: 14
rem: 15
rem: 16
rem: 17
rem: 18
rem: 19
rem: 20
rem: 21
rem: 22
rem: 23
rem: 24
rem: 25
rem: 26
rem: 27
rem: 28
rem: 29
rem: 30
rem: 31
rem: 32
rem: 33
rem: 34
rem: 35
rem: 36
rem: 37
rem: 38
rem: 39
rem: 40
rem: 41
rem: 42
newCapacity: 36
rem: 43
rem: 44
rem: 45
rem: 46
rem: 47
rem: 48
rem: 49
rem: 50
rem: 51
newCapacity: 18
rem: 52
rem: 53
rem: 54
rem: 55
rem: 56
newCapacity: 9
rem: 57
rem: 58
newCapacity: 4
rem: 59
newCapacity: 2
rem: 60

*/
