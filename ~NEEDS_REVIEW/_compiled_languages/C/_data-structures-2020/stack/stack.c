#include <stdio.h>
#include <stdlib.h>

//
// Structs
//

typedef struct Node {
  int key;
  struct Node * next;
} Node;

typedef struct Stack {
  struct Node * top;
} Stack;

//
// functions
//

Node * createNode(int key){
  Node * node = (Node*)malloc(sizeof(Node));
  node->key = key;
  //node->next = NULL;
  return node;
}

void printNode(Node * node){
  if (node->next == NULL) {
    printf("Node{key=%i, next=NULL}\n", node->key);
    return;
  }
  printf("Node{key=%i, next=%i}\n", node->key, node->next->key);
}

Stack * createStack(){
  Stack * stack = (Stack*)malloc(sizeof(Stack));
  stack->top = NULL;
  return stack;
}

void add(Stack ** _stack, int key){
  Stack * stack = *_stack; // unwrap
  Node * node = createNode(key);
  if (stack->top == NULL) {
    stack->top = node;
    return;
  }

  node->next = stack->top;
  stack->top = node;
}

Node * rem(Stack ** _stack){
  Stack * stack = *_stack; // unwrap
  if (stack->top == NULL) {
    return NULL; // empty
  }

  Node * node = stack->top;
  stack->top = stack->top->next;
  return node;
}

void print(Stack * stack){
  Node * currentNode = stack->top;

  while (currentNode != NULL) {
    printNode(currentNode);
    currentNode = currentNode->next;
  }
}

/**
 * MAIN METHOD
 */
int main(){
  Stack * stack = createStack();
  add(&stack, 1);
  add(&stack, 2);
  add(&stack, 3);
  add(&stack, 4);
  add(&stack, 5);

  print(stack);

  printf("========== REMOVING:\n");

  printNode(rem(&stack));
  printNode(rem(&stack));
  printNode(rem(&stack));

  return 0;
}

/* OUTPUT:

Node{key=5, next=4}
Node{key=4, next=3}
Node{key=3, next=2}
Node{key=2, next=1}
Node{key=1, next=NULL}
========== REMOVING:
Node{key=5, next=4}
Node{key=4, next=3}
Node{key=3, next=2}

*/
