/**
 * DisjointSet
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct DisjointSet {
  int size;
  int * array; // pointer to int array
} DisjointSet;

DisjointSet * createDisjointSet(int size){
  DisjointSet * ds = (DisjointSet *)malloc(sizeof(DisjointSet));
  ds->size = size;

  // allocate memory for array of ints
  ds->array = (int *)malloc(sizeof(int) * size);

  // init array:
  for(int i=0;i<size;i++){
    ds->array[i] = -1;
  }

  return ds;
}

void printDisjointSet(DisjointSet * ds){
  printf("DisjointSet@%p{size=%d, array=[\n", ds, ds->size);
  for (int i=0;i<ds->size;i++){
    printf("  %d -> [%d]\n", i, ds->array[i]);
  }
  printf("]}\n");
}

/**
 * Use path compression, three pointers:
 * - current node
 * - root
 * - parent of current (because if we set current node to point to root, we loose the parent)
 */
int find(DisjointSet * ds, int a){
  printf("============================= find(%d) INIT\n", a);
  int initialIndex = a;
  int currentIndex = a;
  int rootIndex;

  // Find root
  while(ds->array[currentIndex] >= 0){
    printf("INFO_2007 (round #1 - find root) currentIndex: %d\n", currentIndex);
    currentIndex = ds->array[currentIndex];
  }

  rootIndex = currentIndex;
  printf("INFO_2010 found root: %d\n", rootIndex);

  // Now make all descendents point to root
  currentIndex = initialIndex;
  while(ds->array[currentIndex] >= 0){ // while is NOT root
    printf("INFO_2011 (round #2 - set to root) currentIndex: %d\n", currentIndex);

    int parent = ds->array[currentIndex];
    printf("INFO_2009 -- parent is %d\n", parent);

    printf("INFO_2008 -- setting arr[%d] = %d (ROOT)\n", currentIndex, rootIndex);
    ds->array[currentIndex] = rootIndex;
    currentIndex = parent;
  }

  printf("============================= find(%d) == %d (RESULT)\n", a, rootIndex);
  return rootIndex;
}

// use union by rank
void unite(DisjointSet * ds, int a, int b){
  printf("\n\n============================= unite(%d, %d) INIT\n", a, b);

  // Get the roots FIRST
  int ar = find(ds, a);
  int br = find(ds, b);
  printf("============ ROOTS: (%d, %d)\n", ar, br);

  // Then get root ranks
  int rank1 = ds->array[ar];
  int rank2 = ds->array[br];
  printf("============ RANKS: (%d, %d)\n", rank1, rank2);

  /**
   * Figure out who has higher rank
   * then connect, increase rank
   */
  if (rank1 < 0) {
    if (rank2 < 0) {
      // a has some rank, b has some rank
      if (rank1 == rank2){
        printf("INFO_1001\n");
        ds->array[ar] = ds->array[ar] + ds->array[br];
        ds->array[br] = ar;
      }else if (rank1 < rank2) {
        // a has HIGHER RANK
        printf("INFO_1002\n");
        ds->array[ar] = ds->array[ar] + ds->array[br];
        ds->array[br] = ar;
      }else{
        // b has HIGHER RANK
        printf("INFO_1003\n");
        ds->array[br] = ds->array[ar] + ds->array[br];
        ds->array[ar] = br;
      }
    }else{
      // a has some rank, b has no rank
      printf("INFO_1004\n");
      ds->array[br] = ar;
      ds->array[ar]--;
    }
  }else{
    if (rank2 < 0) {
      // a has no rank,  b has some rank
      printf("INFO_1005\n");
      ds->array[ar] = br;
      ds->array[br]--;
    }else{
      // a has no rank, b has no rank
      printf("INFO_1006\n");
      ds->array[ar] = br;
      ds->array[br]--;
    }
  }
  printDisjointSet(ds);
}

int main(){
  DisjointSet * ds = createDisjointSet(15);
  printDisjointSet(ds);

  unite(ds, 0,1);
  unite(ds, 1,2);
  unite(ds, 2,3);
  unite(ds, 3,4);

  unite(ds, 5,6);
  unite(ds, 6,7);
  unite(ds, 7,8);
  unite(ds, 8,9);
  unite(ds, 9,10);
  unite(ds, 10,11);

  unite(ds, 12,13);
  unite(ds, 13,14);

  unite(ds, 3,8);

  unite(ds, 14,3);

  find(ds, 14);

  printDisjointSet(ds);

  return 0;
}

/* OUTPUT

DisjointSet@0x7fec00c01700{size=15, array=[
  0 -> [5]
  1 -> [0]
  2 -> [0]
  3 -> [5]
  4 -> [0]
  5 -> [-15]
  6 -> [5]
  7 -> [5]
  8 -> [5]
  9 -> [5]
  10 -> [5]
  11 -> [5]
  12 -> [5]
  13 -> [12]
  14 -> [5]
]}

*/
