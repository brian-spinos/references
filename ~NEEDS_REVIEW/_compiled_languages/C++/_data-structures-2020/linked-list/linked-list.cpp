#include <iostream>
#include <string>
#include <cstring>
using namespace std;

/*
 * ------------------------------------------------ Comments
 * $ g++ linked-list.cpp -o linked-list; ./linked-list
 */

/*
 * ------------------------------------------------ Node
 */

class Node {
private:
  string key;
  string value;
  Node * next;
public:
  Node(string key, string value);
  ~Node(void);
  string getKey(void);
  void setKey(string key);
  string getValue(void);
  void setValue(string value);
  Node * getNext(void);
  void setNext(Node * node);
}; // because, C++

Node::Node(string key, string value){
  this->key = key;
  this->value = value;
  this->next = NULL;
}

Node::~Node(void){
  cout << "Deleting Node " << this->key << endl;
}

string Node::getValue(void){
  return this->value;
}

string Node::getKey(void){
  return this->key;
}

void Node::setValue(string value){
  this->value = value;
}

void Node::setKey(string key){
  this->key = key;
}

Node * Node::getNext(void){
  return this->next;
}

void Node::setNext(Node * node){
  this->next = node;
}

/*
 * ------------------------------------------------ LinkedList
 */

class LinkedList{
private:
  Node * head;
  Node * tail;
  int size;
public:
  LinkedList(void);
  ~LinkedList(void);
  Node * getHead(void);
  void setHead(Node * node);
  Node * getTail(void);
  void setTail(Node * node);
  void add(string key, string value);
  void add(Node * node);
  Node * removeHead(void);
  Node * removeTail(void);
  int getSize(void);
  void print(void);
};

LinkedList::LinkedList(void){
  cout << "Creating LinkedList" << endl;
  this->size = 0;
}

LinkedList::~LinkedList(void){
  cout << "Deleting LinkedList" << endl;
}

Node * LinkedList::getHead(void){
  return this->head;
}

void LinkedList::setHead(Node * node){
  cout << "LinkedList::setHead called..." << endl;
  if (this->head == NULL) { // 0 nodes
    this->head = node;
    this->tail = node;
    this->size++;
    return;
  }

  node->setNext(this->head);
  this->head = node;
  this->size++;
}

Node * LinkedList::getTail(void){
  return this->tail;
}

void LinkedList::setTail(Node * node){
  cout << "LinkedList::setTail called..." << endl;
  if (this->tail == NULL) { // 0 nodes
    this->head = node;
    this->tail = node;
    this->size++;
    return;
  }

  this->tail->setNext(node);
  this->tail = node;
  this->size++;
}

void LinkedList::add(string key, string value){
  //cout << "LinkedList::add called..." << endl;
  Node * node = new Node(key, value);
  this->add(node);
  return;
}

void LinkedList::add(Node * node){
  //cout << "LinkedList::add called..." << endl;
  if (this->head == NULL) { // zero nodes
    this->head = node;
    this->tail = node;
    this->size++;
    return;
  }

  if (this->head == this->tail) { // one node
    this->head->setNext(node);
    this->tail = node;
    this->size++;
    return;
  }

  this->tail->setNext(node); // 2+ nodes
  this->tail = node;
  this->size++;
  return;
}

Node * LinkedList::removeHead(void){
  cout << "LinkedList::removeHead called..." << endl;
  if (this->head == NULL) {
    return NULL;
  }

  if (this->size == 1) {
    Node * head = this->head;
    this->head = NULL;
    this->tail = NULL;
    this->size--;
    return head;
  }

  Node * head = this->head;
  Node * next = this->head->getNext();
  this->head = next;
  this->size--;
  return head;
}

// TODO: finish
Node * LinkedList::removeTail(void){
  return NULL;

  // if (this->head == NULL) {
  //   /* code */
  //   return;
  // }
  //
  // if (this->head == this->tail) { // 1 node
  //   Node * tail = this->tail;
  //   this->head == NULL;
  //   this->tail == NULL;
  //   this->size--;
  //   return tail;
  // }
  //
  // Node * tail = this->tail;
  // this->head == NULL;
  // this->tail == NULL;
  // this->size--;
  // return tail;
}

// void LinkedList::print(void){
//   cout << "LinkedList@" << this
//     << "{size=" << this->size
//     << ", head=" << this->head
//     << ", tail=" << this->tail
//     << "}"
//     << endl;
//     return;
// }

// (k1) -> (k2) -> (k3) ->
void LinkedList::print(void){
  Node * currentNode = this->head;
  while(currentNode != NULL){
    cout << "("
    << currentNode->getKey()
    << ") -> ";
    currentNode = currentNode->getNext();
  }
  cout << endl;
}

int LinkedList::getSize(void){
  cout << "Size: " << this->size << endl;;
  return this->size;
}

/*
 * ------------------------------------------------ Main function
 */

int main(void){
  LinkedList * linkedList = new LinkedList();
  linkedList->add("k1", "v1");
  linkedList->add("k2", "v2");
  linkedList->add("k3", "v3");
  linkedList->add("k4", "v4");
  linkedList->add("k5", "v5");
  linkedList->print();
  linkedList->removeHead();
  linkedList->removeHead();
  linkedList->removeHead();
  linkedList->removeTail(); // Needs to be DLL
  linkedList->print();

  linkedList->setHead(new Node("k0", "v0"));
  linkedList->print();

  linkedList->setTail(new Node("k6", "v6"));
  linkedList->print();

  linkedList->getSize();
  return 0;
}

/* OUTPUT

Creating LinkedList
(k1) -> (k2) -> (k3) -> (k4) -> (k5) ->
LinkedList::removeHead called...
LinkedList::removeHead called...
LinkedList::removeHead called...
(k4) -> (k5) ->
LinkedList::setHead called...
(k0) -> (k4) -> (k5) ->
LinkedList::setTail called...
(k0) -> (k4) -> (k5) -> (k6) ->
Size: 4

*/
