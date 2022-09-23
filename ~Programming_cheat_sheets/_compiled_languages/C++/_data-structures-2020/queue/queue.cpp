#import <iostream>
using namespace std;

/*
 * -------------------------------------------------- Comments
 * $ g++ queue.cpp -o queue; ./queue;
 */

/*
 * -------------------------------------------------- Queue
 */

class Node {
private:
  int key;
  // TODO: string value;
  Node * next;
public:
  Node(int key);
  ~Node(void);
  void setNext(Node * node);
  Node * getNext(void);
  void print(void);
};

Node::Node(int key){
  // cout << "Creating Node" << endl;
  this->key = key;
  this->next = NULL;
}

Node::~Node(void){
  cout << "Deleting Node" << endl;
}

void Node::setNext(Node * node){
  this->next = node;
}

Node * Node::getNext(void){
  return this->next;
}

void Node::print(void){
  cout << "Node@" << this
  << "{key=" << this->key
  << ", value=" << "?"
  << ", next=" << this->next
  << "}" << endl;
}

/*
 * -------------------------------------------------- Queue
 */

class Queue {
private:
  Node * head;
  Node * tail;
  int size;
public:
  Queue(void);
  ~Queue(void);
  void add(int n);
  Node * remove(void);
  Node * getHead(void);
  Node * getTail(void);
  void setHead(Node * node);
  void setTail(Node * node);
  void print(void);
};

Queue::Queue(void){
  // cout << "Creating Queue" << endl;
  this->size = 0;
}

Queue::~Queue(void){
  cout << "Deleting Queue" << endl;
}

void Queue::add(int key){
  // cout << "Queue::add called with " << key << endl;
  Node * node = new Node(key);

  if (this->head == NULL) { // 0 nodes
    this->head = node;
    this->tail = node;
    this->size++;
    return;
  }

  if (this->head == this->tail) { // 1 node
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

Node * Queue::remove(void){
  if (this->head == NULL) { // 0 nodes
    cout<< "\nNothing to remove" << endl;
    cout << endl;
    return NULL;
  }

  if (this->head == this->tail) { // 1 node
    Node * head = this->head;
    this->head = NULL;
    this->tail = NULL;
    cout<< "\nRemoving:" << endl;
    head->print();
    cout << endl;
    this->size--;
    return head;
  }

  Node * head = this->head;     // 2+ nodes
  Node * next = this->head->getNext();
  this->head = next;
  cout<< "\nRemoving:" << endl;
  head->print();
  cout << endl;
  this->size--;
  return head;
}

void Queue::print(void){
  cout << "Queue@" << this
  << "{size=" << this->size
  << ", head=" << this->head
  << ", tail=" << this->tail
  << "}" << endl;
}

/*
 * -------------------------------------------------- Main function
 */

int main(void){
  Queue * queue = new Queue();
  queue->print();

  queue->add(1);
  queue->add(2);
  queue->add(3);
  queue->print();

  queue->remove();
  queue->remove();
  queue->remove();
  queue->print();

  return 0;
}

/* OUTPUT

Queue@0x7ff10a401700{size=0, head=0x0, tail=0x0}
Queue@0x7ff10a401700{size=3, head=0x7ff10a401720, tail=0x7ff10a401740}

Removing:
Node@0x7ff10a401720{key=1, value=?, next=0x7ff10a401730}


Removing:
Node@0x7ff10a401730{key=2, value=?, next=0x7ff10a401740}


Removing:
Node@0x7ff10a401740{key=3, value=?, next=0x0}

Queue@0x7ff10a401700{size=0, head=0x0, tail=0x0}

*/
