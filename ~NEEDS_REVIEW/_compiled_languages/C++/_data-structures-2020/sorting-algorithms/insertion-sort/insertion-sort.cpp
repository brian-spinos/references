#import<iostream>
using namespace std;

//
//------------------------------------------------- Notes
// $ g++ insertion-sort.cpp -o insertion-sort; ./insertion-sort;
//

//
//------------------------------------------------- InsertionSort
//

class InsertionSort {
private:
public:
  static void sort(int arr[], int size);
  static void print(int arr[], int size);
  static void swap(int arr[], int i1, int i2);
};

void InsertionSort::sort(int arr[], int size){
  for(int i=1;i<size;i++){
    int selectedValue = arr[i];
    bool noInsertion = true;
    for(int j=i-1;j>=0;j--){
      if (arr[j] > selectedValue) {
        arr[j+1] = arr[j]; // shift right
      }else{
        arr[j+1] = selectedValue;
        noInsertion = false;
        break;
      }
    }
    if (noInsertion) {
      // if j reached 0, and no break:
      // it means that we need to use index 0
      arr[0] = selectedValue;
    }
  }
}

void InsertionSort::print(int arr[], int size){
  for(int i=0;i<size;i++){
    cout << arr[i] << ", ";
  }
  cout << endl;
}

void InsertionSort::swap(int arr[], int i1, int i2){
  int temp = arr[i1];
  arr[i1] = arr[i2];
  arr[i2] = temp;
}

//
//------------------------------------------------- Main function
//

int main(void){
  int arr[] = {5,4,6,3,7,2,8,1,0,9};
  int len = sizeof(arr)/sizeof(arr[0]);
  InsertionSort::sort(arr, len);
  InsertionSort::print(arr, len);
  return 0;
}

/* OUTPUT

0, 1, 2, 3, 4, 5, 6, 7, 8, 9,

*/
