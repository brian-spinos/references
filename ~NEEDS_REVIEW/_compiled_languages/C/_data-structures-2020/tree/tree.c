#include<stdio.h>
#include<stdlib.h>

//
// Struct
//

typedef struct Node{
  int key;
  struct Node * left;
  struct Node * right;
} Node;

typedef struct Tree{
  Node * root;
} Tree;

//
// Function declarations
//
void printNode(Node * node);

//
// Functions
//

Tree * createTree(){
  Tree * tree;
  tree = (Tree*)calloc(1, sizeof(Tree));
  tree->root = NULL;
  return tree;
}

Node * createNode(int key){
  Node * node = (Node*)malloc(sizeof(Node));
   node->key = key;
   node->left = NULL;
   node->right = NULL;
   return node;
}

void printNode(Node * node){
  if (node == NULL) {
    printf("NULL Node\n");
    return;
  }
  if (node->left == NULL) {
    if (node->right == NULL) {
      // both null
      printf("Node{key=%d, left=NULL, right=NULL}\n", node->key);
    }else{
      // only left null
      printf("Node{key=%d, left=NULL, right=%i}\n", node->key, node->right->key);
    }
  }else{
    if (node->right == NULL) {
      // only right null
      printf("Node{key=%d, left=%d, right=NULL}\n", node->key, node->left->key);
    }else{
      // both not null
      printf("Node{key=%d, left=%d, right=%d}\n", node->key, node->left->key, node->right->key);

    }
  }
}

void add(Tree ** _tree, int key){
  Tree * tree = *_tree; // unwrap
  Node * node = createNode(key);

  if (tree->root == NULL) {
    tree->root = node;
    return;
  }

  Node * currentNode = tree->root;
  Node * parent = NULL;
  while (currentNode != NULL) {
    if (key > currentNode->key) {
      parent = currentNode;
      currentNode = currentNode->right;
    }else{
      parent = currentNode;
      currentNode = currentNode->left;
    }
  }

  if (key > parent->key) {
    parent->right = node;
  }else{
    parent->left = node;
  }
}

void inOrder(Node * currentNode){
  if (currentNode == NULL) {
    return;
  }
  inOrder(currentNode->left);
  printNode(currentNode);
  inOrder(currentNode->right);
}

// inOrder needs to be declared before this function
void print(Tree * tree){
  inOrder(tree->root);
}

//
// Main function
//

int main(){
    Tree * tree = createTree();
    add(&tree, 50);
    add(&tree, 25);
    add(&tree, 75);
    add(&tree, 10);
    add(&tree, 30);

    print(tree);
    //printNode(find(3));

    return 0;
}

/* OUTPUT

Node{key=10, left=NULL, right=NULL}
Node{key=25, left=10, right=30}
Node{key=30, left=NULL, right=NULL}
Node{key=50, left=25, right=75}
Node{key=75, left=NULL, right=NULL}

*/
