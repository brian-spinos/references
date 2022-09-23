#import<iostream>
using namespace std;

//
//------------------------------------------------------- Comments
// $ g++ disjoint-sets.cpp -o disjoint-sets; ./disjoint-sets;
//

//
//------------------------------------------------------- DisjointSet
//

class DisjointSet{
private:
  int size;
  int * array;
public:
  DisjointSet(int capacity);
  ~DisjointSet(void);
  void join(int i, int j);
  int find(int i);
  void print(void);
};

DisjointSet::DisjointSet(int size){
  // cout << "Creating DisjointSet" << endl;
  this->size = size;
  this->array = new int[size];
  for(int i=0; i<size;i++){
    this->array[i] = -1; // initialize array
  }
}

DisjointSet::~DisjointSet(void){
  cout << "Deleting DisjointSet" << endl;
}

// Using join by rank
void DisjointSet::join(int i, int j){
  // cout << "------------------------------- DisjointSet::join called..." << endl;
  int parentIdx1 = find(i);
  int parentIdx2 = find(j);

  if (parentIdx1 == parentIdx2) {
    cout << "ALREADY_TOGETHER: " << i << ", " << j << endl << endl;
    return;
  }
  int rank1 = array[parentIdx1];
  int rank2 = array[parentIdx2];

  if (rank1 > rank2) {
    // 2 has "greater" rank
    array[parentIdx2] += array[parentIdx1];
    array[parentIdx1] = parentIdx2;
  }else{
    // 1 has "greater" rank
    array[parentIdx1] += array[parentIdx2];
    array[parentIdx2] = parentIdx1;
  }
}

// Using path compression
int DisjointSet::find(int i){
  // cout << "DisjointSet::find called..." << endl;
  int currIdx = i;
  while(array[currIdx] >= 0){
    currIdx = array[currIdx];
  }
  int rootIdx = currIdx;

  currIdx = i;
  int parentIdx;
  while(array[currIdx] >= 0){
    parentIdx = array[currIdx];
    array[currIdx] = rootIdx;
    currIdx = parentIdx;
  }
  return rootIdx;
}

void DisjointSet::print(void){
  cout
  << "DisjointSet@" << this
  << "{size=" << size
  << ", array=[" << endl;
  for(int i=0;i<size;i++){
    cout << "  " << i << " -> " << array[i] << endl;
  }
  cout << "]}" << endl;
}

//
//------------------------------------------------------- Main function
//

int main(void){
  DisjointSet * disjointSet = new DisjointSet(10);
  disjointSet->join(3,0);
  disjointSet->join(3,1);
  disjointSet->join(3,2);
  disjointSet->join(3,4);

  disjointSet->join(5,6);
  disjointSet->join(5,7);
  disjointSet->join(5,8);
  disjointSet->join(5,9);

  cout << "FIND(1): " << disjointSet->find(1) << endl; // 3
  cout << "FIND(4): " << disjointSet->find(4) << endl; // 3
  cout << "FIND(6): " << disjointSet->find(5) << endl; // 5
  cout << "FIND(8): " << disjointSet->find(8) << endl; // 5

  disjointSet->join(1,2); // ALREADY_TOGETHER: 1, 2
  disjointSet->print();
  return 0;
}

/* OUTPUT

FIND(1): 3
FIND(4): 3
FIND(6): 5
FIND(8): 5
ALREADY_TOGETHER: 1, 2

DisjointSet@0x7f982bc01700{size=10, array=[
  0 -> 3
  1 -> 3
  2 -> 3
  3 -> -5
  4 -> 3
  5 -> -5
  6 -> 5
  7 -> 5
  8 -> 5
  9 -> 5
]}

*/
