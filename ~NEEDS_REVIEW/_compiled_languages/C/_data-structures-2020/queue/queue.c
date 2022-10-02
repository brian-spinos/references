#include <stdio.h>
#include <stdlib.h>

//
// Structs
//

typedef struct Node {
  int key;
  struct Node * next;
} Node;

typedef struct Queue {
  struct Node * head;
  struct Node * tail;
} Queue;

//
// functions
//

Node * createNode(int key){
  Node * node = (Node*)malloc(sizeof(Node));
  node->key = key;
  node->next = NULL;
  return node;
}

void printNode(Node * node){
  if (node->next == NULL) {
    printf("Node{key=%i, next=NULL}\n", node->key);
    return;
  }
  printf("Node{key=%i, next=%i}\n", node->key, node->next->key);
}

Queue * createQueue(){
  Queue * queue = (Queue*)malloc(sizeof(Queue));
  queue->head = NULL;
  queue->tail = NULL;
  return queue;
}

void add(Queue ** _queue, int key){
  Queue * queue = *_queue; // unwrap
  Node * node = createNode(key);
  if (queue->head == NULL) {
    queue->head = node;
    queue->tail = node;
    return;
  }

  queue->tail->next = node;
  queue->tail = node;
}

Node * rem(Queue ** _queue){
  Queue * queue = *_queue; // unwrap
  if (queue->head == NULL) {
    return NULL; // empty
  }

  Node * node = queue->head;

  if (queue->head == queue->tail) {
    queue->head = NULL;
    queue->tail = NULL;
    return node;
  }

  queue->head = queue->head->next;
  return node;
}

void print(Queue * queue){
  Node * currentNode = queue->head;

  while (currentNode != NULL) {
    printNode(currentNode);
    currentNode = currentNode->next;
  }
}

/**
 * MAIN METHOD
 */
int main(){
  Queue * queue = createQueue();
  add(&queue, 1);
  add(&queue, 2);
  add(&queue, 3);
  add(&queue, 4);
  add(&queue, 5);

  print(queue);

  printf("========== REMOVING:\n");

  printNode(rem(&queue));
  printNode(rem(&queue));
  printNode(rem(&queue));

  return 0;
}

/* OUTPUT:

Node{key=1, next=2}
Node{key=2, next=3}
Node{key=3, next=4}
Node{key=4, next=5}
Node{key=5, next=NULL}
========== REMOVING:
Node{key=1, next=2}
Node{key=2, next=3}
Node{key=3, next=4}

*/
