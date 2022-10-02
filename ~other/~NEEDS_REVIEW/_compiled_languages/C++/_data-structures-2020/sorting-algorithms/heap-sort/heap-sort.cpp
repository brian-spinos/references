#import<iostream>
using namespace std;

//
//------------------------------------------------- Notes
// $ g++ heap-sort.cpp -o heap-sort; ./heap-sort;
//

//
//------------------------------------------------- HeapSort
//

class HeapSort {
private:
  static void swap(int arr[], int i1, int i2);
  static void heapify(int arr[], int size);
  static int getPI(int i);
  static int getLI(int i);
  static int getRI(int i);
  static int getPV(int arr[], int i);
  static int getLV(int arr[], int i);
  static int getRV(int arr[], int i);
  static bool hasP(int i);
  static bool hasL(int size, int i);
  static bool hasR(int size, int i);
  static void bubbleUp(int arr[], int i);
  static void bubbleDown(int arr[], int size, int i);
public:
  static void print(int arr[], int size);
  static void sort(int arr[], int size);
};

void HeapSort::print(int arr[], int size){
  for(int i=0;i<size;i++){
    cout << arr[i] << ", ";
  }
  cout << endl;
}

void HeapSort::sort(int arr[], int size){
  heapify(arr, size);
  for(int i=0;i<size;i++){
    swap(arr, 0, (size -1 -i));
    bubbleDown(arr, size, (size -1 -i));
  }
}

void HeapSort::swap(int arr[], int i1, int i2){
  int temp = arr[i1];
  arr[i1] = arr[i2];
  arr[i2] = temp;
}

void HeapSort::heapify(int arr[], int size){
  for(int i=0;i<size;i++){
    bubbleUp(arr, i);
  }
}

//
//------------------------------------------------- Helpers
//

int HeapSort::getPI(int i){ return (i-1)/2; }
int HeapSort::getLI(int i){ return 2 * i + 1; }
int HeapSort::getRI(int i){ return 2 * i + 2; }
int HeapSort::getPV(int arr[], int i){ return arr[getPI(i)]; }
int HeapSort::getLV(int arr[], int i){ return arr[getLI(i)]; }
int HeapSort::getRV(int arr[], int i){ return arr[getRI(i)]; }
bool HeapSort::hasP(int i){ return getPI(i) >= 0; }
bool HeapSort::hasL(int size, int i){ return getLI(i) < size; }
bool HeapSort::hasR(int size, int i){ return getRI(i) < size; }

void HeapSort::bubbleUp(int arr[], int i){
  int currIdx = i;
  while(currIdx > 0 && hasP(currIdx) && getPV(arr, currIdx) < arr[currIdx]){
    swap(arr, currIdx, getPI(currIdx));
    currIdx = getPI(currIdx);
  }
}

// int i - should NOT be touched/modified
void HeapSort::bubbleDown(int arr[], int size, int i){
  int currIdx = 0;
  while(hasL(size, currIdx) && getLI(currIdx) < i){
    int gci = getLI(currIdx);
    if (
      hasR(size, currIdx) &&
      getRV(arr, currIdx) > getLV(arr, currIdx) &&
      getRI(currIdx) < i
    ) {
      gci = getRI(currIdx);
    }
    if (arr[currIdx] < arr[gci]) {
      swap(arr, currIdx, gci);
      currIdx = gci;
    }else{
      break;
    }
  }
}

//
//------------------------------------------------- Main function
//

int main(void){
  int arr[] = {5,4,6,3,7,2,8,1,0,9};
  int size = sizeof(arr)/sizeof(arr[0]);
  HeapSort::print(arr, size);
  HeapSort::sort(arr, size);
  HeapSort::print(arr, size);
  return 0;
}

/* OUTPUT

5, 4, 6, 3, 7, 2, 8, 1, 0, 9,
0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 

*/
