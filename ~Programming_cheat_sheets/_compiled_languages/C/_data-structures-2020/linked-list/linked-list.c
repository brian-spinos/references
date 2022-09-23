#include <stdio.h>
#include <stdlib.h>

//
// Structs
//

typedef struct Node {
    int key;
    struct Node * next;
 } Node;

typedef struct LinkedList{
  struct Node * head;
  struct Node * tail;
} LinkedList;

//
// functions
//

Node * createNode(int key){
  Node * node = (Node*)malloc(sizeof(Node));
  node->key = key;
  return node;
}

void printNode(Node * node){
  if (node->next == NULL) {
    printf("Node{key=%i, next=NULL}\n", node->key);
    return;
  }
  printf("Node{key=%i, next=%i}\n", node->key, node->next->key);
}

LinkedList * createLinkedList(){
  LinkedList * linkedList = (LinkedList*)malloc(sizeof(LinkedList));
  linkedList->head = NULL;
  linkedList->tail = NULL;
  return linkedList;
}

void add(LinkedList ** _linkedList, int key){
    LinkedList * linkedList = (*_linkedList); // unwrap double-pointer
    Node * node = createNode(key);
    // printNode(node);

    if (linkedList->head == NULL) {
      // printf("HEAD BEFORE: %p\n", linkedList->head); // 0x0
      // printf("TAIL BEFORE: %p\n", linkedList->tail); // 0x0
      linkedList->head = node;
      linkedList->tail = node;
      // printf("HEAD AFTER: %p\n", linkedList->head); // 0x7faaa1401760
      // printf("TAIL AFTER: %p\n", linkedList->tail); // 0x7faaa1401760
      return;
    }
    linkedList->tail->next = node;
    linkedList->tail = node;
    // printf("TAIL AFTER #2: %i\n", linkedList->tail->key); // 0x7faaa1401760
}

void print(LinkedList ** linkedList){
  Node * currentNode = (*linkedList)->head;
  while(currentNode != NULL){
    printNode(currentNode);
    currentNode = currentNode->next;
  }
}

/**
 * MAIN METHOD
 */
int main(){
    LinkedList * linkedList = createLinkedList();
    add(&linkedList, 1);
    add(&linkedList, 2);
    add(&linkedList, 3);
    add(&linkedList, 4);
    add(&linkedList, 5);
    add(&linkedList, 6);
    add(&linkedList, 7);
    add(&linkedList, 8);
    add(&linkedList, 9);
    add(&linkedList, 10);

    print(&linkedList);
    return 0;
}


/* OUTPUT:

Node{key=1, next=2}
Node{key=2, next=3}
Node{key=3, next=4}
Node{key=4, next=5}
Node{key=5, next=6}
Node{key=6, next=7}
Node{key=7, next=8}
Node{key=8, next=9}
Node{key=9, next=10}
Node{key=10, next=NULL}

*/
