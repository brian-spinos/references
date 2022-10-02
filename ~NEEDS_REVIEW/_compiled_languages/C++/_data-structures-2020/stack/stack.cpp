#import<iostream>
using namespace std;

/*
 * -------------------------------------------------- Comments
 * $ g++ stack.cpp -o stack; ./stack
 */

/*
 * -------------------------------------------------- Node
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
 * -------------------------------------------------- Stack
 */

 class Stack {
 private:
   Node * top;
   int size;
 public:
   Stack(void);
   ~Stack(void);
   void push(int n);
   Node * pop(void);
   void print(void);
 };

 Stack::Stack(void){
   // cout << "Creating Stack" << endl;
   this->size = 0;
 }

 Stack::~Stack(void){
   cout << "Deleting Stack" << endl;
 }

 void Stack::push(int key){
   // cout << "Stack::push called with " << key << endl;
   Node * node = new Node(key);

   if (this->top == NULL) { // 0 nodes
     this->top = node;
     this->size++;
     return;
   }

   node->setNext(this->top); // 1+ nodes
   this->top = node;
   this->size++;
   return;
 }

 Node * Stack::pop(void){
   if (this->top == NULL) { // 0 nodes
     cout<< "\nNothing to pop" << endl;
     cout << endl;
     return NULL;
   }

   Node * top = this->top;     // 1+ nodes
   Node * next = this->top->getNext();
   this->top = next;
   cout<< "\nRemoving:" << endl;
   top->print();
   cout << endl;
   this->size--;
   return top;
 }

 void Stack::print(void){
   cout << "Stack@" << this
   << "{size=" << this->size
   << ", top=" << this->top
   << "}" << endl;
 }

/*
 * -------------------------------------------------- Main function
 */
int main(void){
  Stack * stack = new Stack();
  stack->print();

  stack->push(1);
  stack->push(2);
  stack->push(3);
  stack->print();

  stack->pop();
  stack->pop();
  stack->pop();
  stack->print();


  return 0;
}

/* OUTPUT

Stack@0x7ff065401700{size=0, top=0x0}
Stack@0x7ff065401700{size=3, top=0x7ff065401730}

Removing:
Node@0x7ff065401730{key=3, value=?, next=0x7ff065401720}


Removing:
Node@0x7ff065401720{key=2, value=?, next=0x7ff065401710}


Removing:
Node@0x7ff065401710{key=1, value=?, next=0x0}

Stack@0x7ff065401700{size=0, top=0x0}

*/
