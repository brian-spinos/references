#import<iostream>
#import<queue>
#import<stack>
using namespace std;

//
//-------------------------------- Notes
// $ g++ graph.cpp -o graph; ./graph;
// - Graph with adjacency list
//

//
//-------------------------------- Node
//

class Node {
private:
  int key;
  Node * next;
public:
  Node(int key);
  ~Node(void);
  int getKey(void);
  Node * getNext(void);
  void setNext(Node * node);
};

Node::Node(int key){
  this->key = key;
  next = NULL;
}

Node::~Node(void){
  cout << "Deleting Node@" << this << endl;
}

int Node::getKey(void){
  return key;
}

Node * Node::getNext(void){
  return next;
}

void Node::setNext(Node * node){
  next = node;
}

//
//-------------------------------- Graph
//

class Graph {
private:
  int size;
  Node ** array; // array of Node*
public:
  Graph(int size);
  ~Graph(void);
  void connect(int k1, int k2);
  void BFS(int key);
  void DFS(int key);
  void print(void);
};

Graph::Graph(int size){
  this->size = size;
  array = new Node*[size]; // Allocate space
  for(int i=0;i<size;i++){
    array[i] = NULL; // Initialize
  }
}

Graph::~Graph(void){
  cout << "Deleting Graph@" << this << endl;
}

void Graph::print(void){
  cout << "Graph@" << this;
  cout << "{array=[" << endl;
  for(int i=0;i<size;i++){
    cout << "  " << i << " -> " << endl;
    Node * node = array[i];
    while(node != NULL){
      cout << "        " << "Node@" << node << "{key=" << node->getKey() << "}"  << " -> " << endl;
      node = node->getNext();
    }
  }
  cout << "]}" << endl;
}

void Graph::connect(int k1, int k2){
  Node * currNode = array[k1];
  Node * node2 = new Node(k2);

  // The head nodes are lazily created
  if (currNode == NULL) {
    Node * node1 = new Node(k1);
    array[k1] = node1;
    node1->setNext(node2);
    return;
  }

  while(currNode->getNext() != NULL && currNode->getKey() != k2){
    currNode = currNode->getNext();
  }
  currNode->setNext(node2);
}

void Graph::BFS(int key){
  cout << "BFS: " << endl;
  queue<Node*> queue;
  Node * currNode = array[key];
  queue.push(currNode);

  while(!queue.empty()){
    // Go to node's correct index
    int index = queue.front()->getKey();

    // Since the head is lazily created,
    // we might need to create it here
    if (array[index] == NULL) {
      array[index] = new Node(index);
    }

    currNode = array[index];
    queue.pop();

    // Print currentNode
    cout << currNode->getKey() << ", ";

    // Add connections to queue
    while(currNode->getNext() != NULL){
      currNode = currNode->getNext();
      queue.push(currNode);
    }
  }
  cout << endl << endl;
}

void Graph::DFS(int key){
  cout << "DFS: " << endl;
  stack<Node*> stack;
  Node * currNode = array[key];
  stack.push(currNode);

  while(!stack.empty()){
    // Go to node's correct index
    int index = stack.top()->getKey();

    // Since the head is lazily created,
    // we might need to create it here
    if (array[index] == NULL) {
      array[index] = new Node(index);
    }

    currNode = array[index];
    stack.pop();

    // Print currNode
    cout << currNode->getKey() << ", ";

    // Add connections to the stack
    while(currNode->getNext() != NULL){
      currNode = currNode->getNext();
      stack.push(currNode);
    }
  }
  cout << endl << endl;
}

//
//-------------------------------- Main function
//

int main(void){
  Graph * graph = new Graph(15);
  // Close connections to Node 1
  graph->connect(1,2);
  graph->connect(1,3);
  graph->connect(1,4);
  graph->connect(1,5);
  graph->connect(1,6);
  graph->connect(1,7);
  graph->connect(1,8);

  // long list starting on Node 8
  graph->connect(8,10);
  graph->connect(10,11);
  graph->connect(11,12);
  graph->connect(12,13);
  graph->connect(13,14);

  // Should print close connections first
  graph->BFS(1);

  // Should go deep first
  graph->DFS(1);

  graph->print();
  return 0;
}


/* OUTPUT

BFS:
1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14,

DFS:
1, 8, 10, 11, 12, 13, 14, 7, 6, 5, 4, 3, 2,

Graph@0x7fc4b3c01700{array=[
  0 ->
  1 ->
        Node@0x7fc4b3c017a0{key=1} ->
        Node@0x7fc4b3c01790{key=2} ->
        Node@0x7fc4b3c017b0{key=3} ->
        Node@0x7fc4b3c017c0{key=4} ->
        Node@0x7fc4b3c017d0{key=5} ->
        Node@0x7fc4b3c017e0{key=6} ->
        Node@0x7fc4b3c017f0{key=7} ->
        Node@0x7fc4b3c01800{key=8} ->
  2 ->
        Node@0x7fc4b3c018c0{key=2} ->
  3 ->
        Node@0x7fc4b3c018d0{key=3} ->
  4 ->
        Node@0x7fc4b3c018e0{key=4} ->
  5 ->
        Node@0x7fc4b3c018f0{key=5} ->
  6 ->
        Node@0x7fc4b3c01900{key=6} ->
  7 ->
        Node@0x7fc4b3c01910{key=7} ->
  8 ->
        Node@0x7fc4b3c01820{key=8} ->
        Node@0x7fc4b3c01810{key=10} ->
  9 ->
  10 ->
        Node@0x7fc4b3c01840{key=10} ->
        Node@0x7fc4b3c01830{key=11} ->
  11 ->
        Node@0x7fc4b3c01860{key=11} ->
        Node@0x7fc4b3c01850{key=12} ->
  12 ->
        Node@0x7fc4b3c01880{key=12} ->
        Node@0x7fc4b3c01870{key=13} ->
  13 ->
        Node@0x7fc4b3c018a0{key=13} ->
        Node@0x7fc4b3c01890{key=14} ->
  14 ->
        Node@0x7fc4b3c01920{key=14} ->
]}

*/
