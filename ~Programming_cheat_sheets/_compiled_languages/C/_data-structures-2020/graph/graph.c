#
#
#  UNFINISHED...
# 
#

#include<stdio.h>
#include<stdlib.h>
#include "queue.h"
// graph ajd


//
// Structs
//

typedef struct TreeNode {
  int key;
  struct TreeNode * next;
} TreeNode;

typedef struct Graph {
  TreeNode ** array; // array of pointers is **
} Graph;




//
// Functions
//

Graph * createGraph(int size){
  Graph * graph = (Graph*)malloc(sizeof(Graph));
  graph->array = (TreeNode**)malloc(sizeof(TreeNode) * size); // allocate memory for array of pointers
  return graph;
}

TreeNode * createTreeNode(int key){
  TreeNode * node = (TreeNode*)malloc(sizeof(TreeNode));
  node->key = key;
  node->next = NULL;
  return node;
}

void connect(Graph ** _graph, int a, int b, int w){
  Graph * graph = *_graph; // unwrap
  TreeNode * node = createTreeNode(b);
  if (graph->array[a] == NULL) {
    graph->array[a] = node;
    return;
  }

  TreeNode * currentTreeNode = graph->array[a];
  TreeNode * prev = NULL;
  while(currentTreeNode != NULL){
    prev = currentTreeNode;
    currentTreeNode = currentTreeNode->next;
  }
  prev->next = node;
}

void printTreeNode(TreeNode * node){
  if (node == NULL) {
    printf("NULL TreeNode");
  }
  else if(node->next == NULL){
    printf("TreeNode{key=%d, next=NULL}\n", node->key);
  }else{
    printf("TreeNode{key=%d, next=%d}\n", node->key, node->next->key);
  }
}

void printByIndex(Graph ** _graph, int index){
  Graph * graph = *_graph; // unwrap
  TreeNode * currentTreeNode = graph->array[index];

  while(currentTreeNode != NULL){
    printTreeNode(currentTreeNode);
    currentTreeNode = currentTreeNode->next;
  }
}

void bfs(Graph ** _graph, int start){
  Graph * graph = *_graph; // unwrap
  Queue * queue = createQueue();

  TreeNode * currentTreeNode = graph->array[start];
  add(&queue, currentTreeNode);

  while(!isEmpty(&queue)){
    n = rem(&queue);
    printTreeNode(n);
    n = n->next;
    while(n != NULL){
      add(&queue, n);
      n = n->next;
    }
  }
}

//
// Main function
//

int main(){
    Graph * graph = createGraph(10);
    connect(&graph, 1,2, 2000);
    connect(&graph, 1,6, 6000);
    connect(&graph, 2,3, 3000);
    connect(&graph, 3,4, 4000);
    connect(&graph, 4,5, 5000);

    //printByIndex(&graph, 1);

    bfs(&graph, 1);

    return 0;
}


/*
generics:
struct Foo {
  int key;
  void* data;
}

Foo f = {
  .key = 123,
  .data = malloc(sizeof(XXX))
}

((int *)f.data)[0] = 123;



*/
