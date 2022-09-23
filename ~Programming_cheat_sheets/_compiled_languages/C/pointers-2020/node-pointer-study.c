#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct Node {
  int key;
  // int intArray[3]; // works
  struct Node * next; // pointer to Node
  struct Node * arrayOfNodePointers[3]; // also array of node pointers
  struct Node ** containerForArrayOfNodePointers; // array of node pointers
  // struct Node array[]; // error:  array has incomplete element type 'struct Node' ???
  struct Node * arrayOfNodePointersFlexible[]; // THIS NEEDS TO BE AT THE END!!! because it can grow :)
  // struct Node * arrayOfNodePointersFlexible2[]; // DOES NOT WORK... We can only have 1 flwxible array at the end of a struct :)
} Node;

Node * createNode(int key){
  Node * node = (Node*)malloc(sizeof(Node));
  node->key = key;
  return node;
}

int main(){
  printf("OK\n\n");

  // INITIAL NODE:
  Node * MY_NODE = createNode(1);
  Node * n0 = createNode(2);
  Node * n1 = createNode(3);
  Node * n2 = createNode(4);
  Node * n3 = createNode(5);
  Node * n4 = createNode(6);
  Node * n5 = createNode(7);
  Node * n6 = createNode(8);
  Node * n7 = createNode(9);
  Node * n8 = createNode(10);

  MY_NODE->next = n0;

  MY_NODE->arrayOfNodePointers[0] = n1;
  MY_NODE->arrayOfNodePointers[1] = n2;
  MY_NODE->arrayOfNodePointers[2] = n3;
  // MY_NODE->arrayOfNodePointers[3] = n4; // error

  Node * arrayOfNodePointers2[3]; // array of Node pointers
  arrayOfNodePointers2[0] = n5;
  arrayOfNodePointers2[1] = n6;
  arrayOfNodePointers2[2] = n7;
  // arrayOfNodePointers2[3] = n8; // ERROR

  MY_NODE->containerForArrayOfNodePointers = MY_NODE->arrayOfNodePointers; // THIS ALSO WORKS!
  MY_NODE->containerForArrayOfNodePointers = arrayOfNodePointers2;

  return 0;
}
