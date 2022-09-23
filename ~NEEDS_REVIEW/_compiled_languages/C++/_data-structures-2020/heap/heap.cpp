#include<iostream>
using namespace std;

/*
 * ------------------------------------------------ Comments
 * $ g++ heap.cpp -o heap; ./heap;
 */

/*
 * ------------------------------------------------ Heap (min)
 */
class Heap {
private:
  int size; // same as "next available index"
  int * array;
  int capacity;
public:
  Heap(int size);
  ~Heap(void);
  void push(int n);
  int peek(void);
  int pop(void);
  int getPI(int i);
  int getLI(int i);
  int getRI(int i);
  bool hasP(int i);
  bool hasL(int i);
  bool hasR(int i);
  int getPV(int i);
  int getLV(int i);
  int getRV(int i);
  void bubbleUp(int i);
  void bubbleDown(void);
  void swap(int i, int j);
  void print(void);
};

Heap::Heap(int capacity){
  cout << "Creating Heap" << endl;
  this->size = 0;
  this->capacity = capacity;
  this->array = new int[capacity];
}

Heap::~Heap(void){
  cout << "Deleting Heap" << endl;
}

void Heap::push(int n){
  cout << "Pushing: " << n << endl;
  // TODO: ensure capacity +50%
  int lastAvailIndex = size;
  array[lastAvailIndex] = n;
  bubbleUp(lastAvailIndex);
  size++;
}

int Heap::peek(void){
  cout << "Peeking: " << this->array[0] << endl;
  return this->array[0];
}

int Heap::pop(void){
  if (size <= 0) {
    cout << "Heap is empty!" << endl;
    // TODO: throw exception when size is zero
    throw 1;
  }
  // TODO: adjust capacity 1/4
  int top = array[0];
  int lastItem = array[size-1];
  array[size-1] = 0; // clean up unused slots
  array[0] = lastItem;
  size--;
  bubbleDown();
  cout << "Popped: " << top << endl;
  return top;
}

/*
 * ------------------------------------------------ Helpers
 */

int Heap::getPI(int i){ return (i-1)/2; }
int Heap::getLI(int i){ return (2*i)+1; }
int Heap::getRI(int i){ return (2*i)+2; }
int Heap::getPV(int i){ return this->array[getPI(i)]; }
int Heap::getLV(int i){ return this->array[getLI(i)]; }
int Heap::getRV(int i){ return this->array[getRI(i)]; }
bool Heap::hasP(int i){ return this->getPI(i) >= 0; } // watch out for i==0
bool Heap::hasL(int i){ return this->getLI(i) < this->size; }
bool Heap::hasR(int i){ return this->getRI(i) < this->size; }

void Heap::bubbleUp(int i){
  while(i != 0 && hasP(i) && getPV(i) > array[i]){
    swap(getPI(i), i);
    i = getPI(i);
  }
}

void Heap::bubbleDown(void){
  int i = 0;
  while(hasL(i)){
    // we assume that the left child has smaller value for now
    int sci = getLI(i);
    if (hasR(i) && getRV(i) < getLV(i)) {
      sci = getRI(i);
    }

    if (array[i] > array[sci]) {
      swap(i, sci);
      i = sci;
    }else{
      return;
    }
  }
}

void Heap::swap(int i, int j){
  int temp = this->array[i];
  this->array[i] = this->array[j];
  this->array[j] = temp;
}

void Heap::print(void){
  cout
  << "Heap@" << this
  << "{size=" << size
  << ", capacity=" << capacity
  << ", array=[" << endl;
  for(int i=0;i<capacity;i++){
    cout << "  " << i << " -> [" << array[i] << "]" << endl;
  }
  cout << "]}" << endl;
}

/*
 * ------------------------------------------------ Main function
 */

int main(void){
  Heap * heap = new Heap(10);
  heap->print();

  heap->push(5);
  heap->push(3);
  heap->push(4);
  heap->push(1);
  heap->push(6);
  heap->push(2);

  heap->print();

  heap->peek();

  heap->pop();
  heap->pop();
  heap->pop();
  heap->pop();

  heap->print();

  return 0;
}

/* OUTPUT

Creating Heap
Heap@0x7fa970c01700{size=0, capacity=10, array=[
  0 -> [0]
  1 -> [0]
  2 -> [0]
  3 -> [0]
  4 -> [0]
  5 -> [0]
  6 -> [0]
  7 -> [0]
  8 -> [0]
  9 -> [0]
]}
Pushing: 5
Pushing: 3
Pushing: 4
Pushing: 1
Pushing: 6
Pushing: 2
Heap@0x7fa970c01700{size=6, capacity=10, array=[
  0 -> [1]
  1 -> [3]
  2 -> [2]
  3 -> [5]
  4 -> [6]
  5 -> [4]
  6 -> [0]
  7 -> [0]
  8 -> [0]
  9 -> [0]
]}
Peeking: 1
Popped: 1
Popped: 2
Popped: 3
Popped: 4
Heap@0x7fa970c01700{size=2, capacity=10, array=[
  0 -> [5]
  1 -> [6]
  2 -> [0]
  3 -> [0]
  4 -> [0]
  5 -> [0]
  6 -> [0]
  7 -> [0]
  8 -> [0]
  9 -> [0]
]}

*/
