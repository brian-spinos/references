#include <iostream>
#include <string>
#include <cstring>
using namespace std;


// Linked list


//----------------------------------------------------
class Node{
    public:
        Node * prev;
        Node * next;

        string getValue(void);
        void setValue(string value);

        int getKey(void);
        void setKey(int key);

        Node(int key, string value);
        ~Node(void);
    private:
        int key;
        string value;
        
    protected:
};

Node::Node(int key, string value){
    this->key = key;
    this->value = value;
    this->prev = NULL;
    this->next = NULL;
}

Node::~Node(void){
    cout << "Deleting node with key: " << this->key << endl;

    this->key = 0;
    this->value = ""; // You cannot assign NULL or 0 to a C++ std::string object, because the object is not a pointer.
    this->prev = NULL;
    this->next = NULL;
}

string Node::getValue(void){
    return this->value;
}

void Node::setValue(string value){
    this->value = value;
}

int Node::getKey(void){
    return this->key;
}

void Node::setKey(int key){
    this->key = key;
}



//----------------------------------------------------
class LinkedList{
    public:
        int _count; // we cant have fields with the same name as functions
        void append(int key, string value);
        void prepend(int key, string value);
        void displayLinkedList(void);
        void removeAtIndex(int index);
        int count(void);

        LinkedList(void);
        ~LinkedList(void);
    private:
        
        Node * head;
        Node * tail;
    protected:
};

LinkedList::LinkedList(void){
    this->_count = 0;
    this->head = NULL;
    this->tail = NULL;
}

LinkedList::~LinkedList(void){
    cout << "Deleting LinkedList..." << endl;

    Node * currentNode = this->head;
    Node * nextNode = NULL;

    // Also delete all nodes
    while(currentNode){
        nextNode = currentNode->next;
        delete currentNode;
        currentNode = nextNode;
    }
}

void LinkedList::append(int key, string value){
    // create node
    Node * node = new Node(key, value);

    // check if there is a head
    if(this->head){
        node->next = this->head;
        this->head->prev = node;
        this->head = node;
        this->_count++;
    }else{
        this->head = node;
        this->tail = node;
        this->_count++;
    }
}

void LinkedList::prepend(int key, string value){
    // create node
    Node * node = new Node(key, value);

    // check if there is a tail
    if(this->tail){
        node->prev = this->tail;
        this->tail->next = node;
        this->tail = node;
        this->_count++;
    }else{
        this->head = node;
        this->tail = node;
        this->_count++;
    }
}

void LinkedList::displayLinkedList(void){
    Node * currentNode = this->head;
    while(currentNode){
        cout << currentNode->getKey() << " " << currentNode->getValue() << endl;
        currentNode = currentNode->next;
    }
}

void LinkedList::removeAtIndex(int index){
    cout << "removeAtIndex()" << endl;
    Node * currentNode = this->head;

    Node * prevNode = NULL;
    Node * nextNode = NULL;

    int _index = 0;
    while(currentNode){

        cout << "---> " << index << endl;


        if(_index == index){
            cout << "===> " << index << endl;
            // delete node
            prevNode = currentNode->prev;
            nextNode = currentNode->next;

            delete currentNode;
             this->_count--;

            prevNode->next = nextNode;
            nextNode->prev = prevNode;
        }
        // cout << currentNode->getValue() << endl;
        currentNode = currentNode->next;
        _index++;
    }
}

int LinkedList::count(void){
    cout << "Count: " << this->_count << endl;
    return this->_count;
}


//----------------------------------------------------

int main(void){ 

    LinkedList * list = new LinkedList();
    list->prepend(1, "Brian");
    list->prepend(2, "Erich");
    list->prepend(3, "Rick");
    list->prepend(4, "Sandra");
    list->prepend(5, "Ana");
    list->prepend(6, "Bea");

    list->append(0, "Dude");

    list->removeAtIndex(1);

    list->count();
    list->displayLinkedList();

    delete list;

    return 0;
}
