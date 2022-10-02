#import<iostream>
using namespace std;

//
//---------------------------------------------------- Notes
// $ g++ bubble-sort.cpp -o bubble-sort; ./bubble-sort;
//

//
//---------------------------------------------------- Bubble sort
//

class BubbleSort {
private:
public:
  static void sort(int arr[], int size);
  static void swap(int arr[], int i1, int i2);
  static void print(int arr[], int size);
};

//  When you pass an array to a function, it decays into a pointer
// to the first element, at which point knowledge of its size is lost.
void BubbleSort::sort(int arr[], int size){
  if (size < 2)return;
  for(int i=0;i<size-1;i++){ // do until prev to last
    bool noSwap = true;
    for(int j=0;j<(size-1-i);j++){ // go until the prev to last - i
      if (arr[j] > arr[j+1]) {
        swap(arr, j, j+1);
        noSwap = false;
      }
    }
    if (noSwap) return;
  }
}

void BubbleSort::swap(int arr[], int i1, int i2){
  int temp = arr[i1];
  arr[i1] = arr[i2];
  arr[i2] = temp;
}

void BubbleSort::print(int arr[], int size){
  for(int i=0;i<size;i++){
    cout << arr[i] << ", ";
  }
  cout << endl;

}

//
//---------------------------------------------------- Main function
//

int main(void){
  int arr[] = {5,4,6,3,7,2,8,1,0,9};
  int size = sizeof(arr)/sizeof(arr[0]);
  BubbleSort::print(arr, size);
  BubbleSort::sort(arr, size);
  BubbleSort::print(arr, size);
  return 0;
}

/* OUTPUT

5, 4, 6, 3, 7, 2, 8, 1, 0, 9,
0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 

*/
