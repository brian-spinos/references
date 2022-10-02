//
// Pointer study - with functions
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// int ALPHABET_COUNT = 26;
#define ALPHABET_COUNT 26

//
// Structs
//

typedef struct Node {
  int isWord;
  struct Node * array[ALPHABET_COUNT]; // DONT use double pointer, we loose the size
} Node;

Node * createNode(){
  Node * node = (Node*)malloc(sizeof(Node*));
  node->isWord = 0; // false
  for(int i=0;i<ALPHABET_COUNT;i++){
    node->array[i] = NULL;
  }

  return node;
}

void printNode(Node * node){
  printf("Node@%p{isWord=%d, array=[", node, node->isWord);

  for(int i=0;i<ALPHABET_COUNT;i++){
    if (node->array[i] == NULL) {
      printf(". ");
    }else{
      printf("%p ", node->array[i]);
    }
  }
  printf("]}\n\n");
}

void func1(Node node){ // we get a struct copy
  printf("func1(Node node) called...\n");

  // changes is performed on the copy, not on the original
  node.isWord = 1010;

  // since it is a copy, it has another address
  printf("Node@%p{isWord=%d, array=[...]}", &node, node.isWord);
  printf("  <------ DIFF ADDRESS !!!\n\n");
}

void func2(Node * node){ // we can change the struct we point to :)
  printf("func2(Node * node) called...\n");

  // node->isWord = 2020;
  (*node).isWord = 2020;

  // printf("Node@%p{isWord=%d, array=[...]}\n\n", node, node->isWord);
  printf("Node@%p{isWord=%d, array=[...]}\n\n", node, (*node).isWord);
}

void func3(Node ** node){  // we can change the struct we point to :)
  printf("func3(Node ** node) called...\n");

  // (*node)->isWord = 3030;
  (**node).isWord = 3030;

  // printf("Node@%p{isWord=%d, array=[...]}\n\n", (*node), (*node)->isWord);
  printf("Node@%p{isWord=%d, array=[...]}\n\n", (&**node), (**node).isWord);
}

//
// Main function
//

int main(){

  // struct:
  Node nodeOLD = *createNode();
  nodeOLD.isWord = 1111;
  nodeOLD.array[10] = createNode();
  nodeOLD.array[20] = createNode();
  nodeOLD.array[25] = createNode();
  Node node = nodeOLD; // copy is done by using same types


  printf("ORIGINAL STRUCT:\n");
  printNode(&node);

  // method will actually receive a copy of the struct, with diff address
  func1(node);

  // struct (changed?):
  printf("ORIGINAL STRUCT (changed by func1? NO):\n");
  printNode(&node);

  Node * npOLD = &node;
  Node * np = npOLD;
  func2(np); // passing a node pointer


  // struct (changed?):
  printf("ORIGINAL STRUCT (changed by func2? YES):\n");
  printNode(&node);


  Node ** nppOLD = &np;
  Node ** npp = nppOLD;
  func3(npp);

  // struct (changed?):
  printf("ORIGINAL STRUCT (changed by func3? YES):\n");
  printNode(&node);



  printf("=======================\n\n");

  //
  // How to create new copy, and same copy
  //
  Node * original = createNode();
  printf("original:\n");
  printNode(original);

  Node * copy = original;
  printf("copy (BUT SAME ADDRESS, so same struct):\n");
  printNode(copy);


 // change newCode: it DONE change original
  printf("changing copy (CHANGES BOTH original and copy)...\n\n");
  copy->array[10] = createNode();


  printf("original again:\n");
  printNode(original);

  printf("copy again:\n");
  printNode(copy);




  printf("=======================\n\n");
  Node * n1 = createNode();
  n1->isWord = 1111;
  n1->array[10] = createNode();
  printf("n1:\n");
  printNode(n1);

  Node * n2 = createNode();
  n2->isWord = 2222;
  n2->array[20] = createNode();
  printf("n2:\n");
  printNode(n2);

  Node * n3 = createNode();
  n3->isWord = 3333;
  n3->array[25] = createNode();
  printf("n3:\n");
  printNode(n3);

  // We need to allocate memory for it, so we dont get error: "Bus error: 10"
  // which seems to mean that we dont have enough memory in the variable to hold
  // what is being assigned to it...
  Node * currentNode = NULL;

  currentNode = n1;
  printf("currentNode (pointing to n1):\n");
  printNode(currentNode);

  currentNode = n2;
  printf("currentNode (pointing to n2):\n");
  printNode(currentNode);

  currentNode = n3;
  printf("currentNode (pointing to n3):\n");
  printNode(currentNode);



  printf("n1:\n");
  printNode(n1);

  printf("n2:\n");
  printNode(n2);

  printf("n3:\n");
  printNode(n3);

  return 0;
}

/* OUTPUT

ORIGINAL STRUCT:
Node@0x7ffee670f918{isWord=1111, array=[. . . . . . . . . . 0x7fc376401710 . . . . . . . . . 0x7fc376401720 . . . . 0x7fc376401730 ]}

func1(Node node) called...
Node@0x7ffee670f790{isWord=1010, array=[...]}  <------ DIFF ADDRESS !!!

ORIGINAL STRUCT (changed by func1? NO):
Node@0x7ffee670f918{isWord=1111, array=[. . . . . . . . . . 0x7fc376401710 . . . . . . . . . 0x7fc376401720 . . . . 0x7fc376401730 ]}

func2(Node * node) called...
Node@0x7ffee670f918{isWord=2020, array=[...]}

ORIGINAL STRUCT (changed by func2? YES):
Node@0x7ffee670f918{isWord=2020, array=[. . . . . . . . . . 0x7fc376401710 . . . . . . . . . 0x7fc376401720 . . . . 0x7fc376401730 ]}

func3(Node ** node) called...
Node@0x7ffee670f918{isWord=3030, array=[...]}

ORIGINAL STRUCT (changed by func3? YES):
Node@0x7ffee670f918{isWord=3030, array=[. . . . . . . . . . 0x7fc376401710 . . . . . . . . . 0x7fc376401720 . . . . 0x7fc376401730 ]}

=======================

original:
Node@0x7fc376500000{isWord=0, array=[. . . . . . . . . . . . . . . . . . . . . . . . . . ]}

copy (BUT SAME ADDRESS, so same struct):
Node@0x7fc376500000{isWord=0, array=[. . . . . . . . . . . . . . . . . . . . . . . . . . ]}

changing copy (CHANGES BOTH original and copy)...

original again:
Node@0x7fc376500000{isWord=0, array=[. . . . . . . . . . 0x7fc376500010 . . . . . . . . . . . . . . . ]}

copy again:
Node@0x7fc376500000{isWord=0, array=[. . . . . . . . . . 0x7fc376500010 . . . . . . . . . . . . . . . ]}

=======================

n1:
Node@0x7fc376500020{isWord=1111, array=[. . . . . . . . . . 0x7fc376500030 . . . . . . . . . . . . . . . ]}

n2:
Node@0x7fc376500040{isWord=2222, array=[. . . . . . . . . . . . . . . . . . . . 0x7fc376500050 . . . . . ]}

n3:
Node@0x7fc376500060{isWord=3333, array=[. . . . . . . . . . . . . . . . . . . . . . . . . 0x7fc376500070 ]}

currentNode (pointing to n1):
Node@0x7fc376500020{isWord=1111, array=[. . . 0x8ae . . . 0xd05 . . . . . . . . . . . . . . . . . . ]}

currentNode (pointing to n2):
Node@0x7fc376500040{isWord=2222, array=[. . . 0xd05 . . . . . . . . . . . . . . . . . . . . . . ]}

currentNode (pointing to n3):
Node@0x7fc376500060{isWord=3333, array=[. . . . . . . . . . . . . . . . . . . . . . . . . 0x7fc376500070 ]}

n1:
Node@0x7fc376500020{isWord=1111, array=[. . . 0x8ae . . . 0xd05 . . . . . . . . . . . . . . . . . . ]}

n2:
Node@0x7fc376500040{isWord=2222, array=[. . . 0xd05 . . . . . . . . . . . . . . . . . . . . . . ]}

n3:
Node@0x7fc376500060{isWord=3333, array=[. . . . . . . . . . . . . . . . . . . . . . . . . 0x7fc376500070 ]}

*/
