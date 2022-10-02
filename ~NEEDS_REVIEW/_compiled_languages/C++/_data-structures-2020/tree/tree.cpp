#import<iostream>
using namespace std;

//
//---------------------------------------- Comments
// $ g++ tree.cpp -o tree; ./tree;
//
// TODO: addRecursive()
//

//
//---------------------------------------- Node
//

class Node {
private:
  int key;
  Node * left;
  Node * right;
public:
  Node(int key);
  ~Node(void);
  int getKey(void);
  Node * getRight(void);
  Node * getLeft(void);
  void setRight(Node * node);
  void setLeft(Node * node);
  void print(void);
};

Node::Node(int key){
  this->key = key;
  this->left = NULL;
  this->right = NULL;
}

Node::~Node(void){
  cout << "Deleting Node" << endl;
}

int Node::getKey(void){
  return key;
}

Node * Node::getRight(void){
  return right;
}

Node * Node::getLeft(void){
  return left;
}

void Node::setLeft(Node * node){
  left = node;
}

void Node::setRight(Node * node){
  right = node;
}

void Node::print(void){
  cout
  << "Node@" << this
  << "{key=" << key
  << ", left=" << left
  << ", right=" << right
  << "}" << endl;
}

//
//---------------------------------------- Tree
//

class Tree {
private:
  Node * root;
public:
  Tree(void);
  ~Tree(void);
  void add(int key);
  void addIteratively( Node * node);
  Node * addRecursively(Node * root, Node * node);
  void inOrder(void);
  void inOrder(Node * node);
  Node * getRoot(void);
};

Tree::Tree(void){
  this->root = NULL;
}

Tree::~Tree(void){
  cout << "Deleting Tree" << endl;
}

void Tree::add(int key){
  Node * node = new Node(key);
  // addIteratively(node);
  root = addRecursively(root, node);
}

void Tree::addIteratively(Node * node){
  // cout << "Tree::addIteratively called..." << endl;
  if (root == NULL) {
    root = node;
    return;
  }

  int key = node->getKey();
  Node * curr = root;
  Node * parent = NULL;

  while(curr != NULL){
    parent = curr;
    if (key > curr->getKey()) {
      curr = curr->getRight();
    }else{
      curr = curr->getLeft();
    }
  }

  if (key > parent->getKey()) {
    parent->setRight(node);
  }else{
    parent->setLeft(node);
  }
}

void Tree::inOrder(Node * node){
  if (node == NULL) return;
  inOrder(node->getLeft());
  cout << node->getKey() << endl;
  inOrder(node->getRight());
}

void Tree::inOrder(void){
  inOrder(root);
}

Node * Tree::getRoot(void){
  return root;
}

Node * Tree::addRecursively(Node * root, Node * node){
  // cout << "Tree::addRecursively called..." << endl;

  if (root == NULL) {
    // This happens only once, when root is null
    return node;
  }

  if (node->getKey() > root->getKey()) {
    root->setRight(
      addRecursively(root->getRight(), node)
    );
  }else{
    root->setLeft(
      addRecursively(root->getLeft(), node)
    );
  }

  // Return root, because that is what we are returning
  // to the initial caller:
  // root = addRecursively(root, node);
  return root;
}

//
//---------------------------------------- Main function
//

int main(void){
  Tree * tree = new Tree();
  tree->add(5);
  tree->add(4);
  tree->add(6);
  tree->add(3);
  tree->add(7);
  tree->add(2);
  tree->add(1);
  tree->add(8);
  tree->add(9);
  tree->add(0);
  tree->inOrder();

  return 0;
}

/* OUTPUT

0
1
2
3
4
5
6
7
8
9

*/
