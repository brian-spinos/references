#import<iostream>
using namespace std;

//
//------------------------------------------------- Notes
// $ g++ quick-sort.cpp -o quick-sort; ./quick-sort;
//

//
//------------------------------------------------- QuickSort
//

class QuickSort {
private:
  static void sort(int arr[], int size, int i1, int i2); // recursive
  static void swap(int arr[], int i1, int i2);
  static int partition(int arr[], int i1, int i2);
public:
  static void sort(int arr[], int size);
  static void print(int arr[], int size);
};

void QuickSort::print(int arr[], int size){
  for(int i=0;i<size;i++){
    cout << arr[i] << ", ";
  }
  cout << endl;
}

void QuickSort::sort(int arr[], int size){
  sort(arr, size, 0, size-1);
}

void QuickSort::sort(int arr[], int size, int i1, int i2){
  if (i1 > i2) return;
  int pivot = partition(arr, i1, i2);
  sort(arr, size, i1, pivot-1);
  sort(arr, size, pivot+1, i2);
}

int QuickSort::partition(int arr[], int i1, int i2){
  int pivot = i2;
  int min = i1;
  for(int i=i1;i<=i2;i++){
    if (arr[i] < arr[pivot]) {
      swap(arr, min, i);
      min++;
    }
  }
  swap(arr, min, pivot);
  return min; // the index
}

void QuickSort::swap(int arr[], int i1, int i2){
  int temp = arr[i1];
  arr[i1] = arr[i2];
  arr[i2] = temp;
}

//
//------------------------------------------------- Main function
//

int main(void){
  int arr[] = {5,4,6,3,7,2,8,1,0,9};
  int size = sizeof(arr)/sizeof(arr[0]);
  QuickSort::print(arr, size);
  QuickSort::sort(arr, size);
  QuickSort::print(arr, size);
  return 0;
}

/* OUTPUT

5, 4, 6, 3, 7, 2, 8, 1, 0, 9,
0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 

*/
