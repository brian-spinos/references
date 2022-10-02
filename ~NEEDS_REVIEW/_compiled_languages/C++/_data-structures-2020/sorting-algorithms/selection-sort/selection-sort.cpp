#import<iostream>
using namespace std;

//
//--------------------------------------------- Notes
// $ g++ selection-sort.cpp -o selection-sort; ./selection-sort;
//

//
//--------------------------------------------- SelectionSort
//

class SelectionSort {
private:
public:
  static void sort(int arr[], int size);
  static void print(int arr[], int size);
  static void swap(int arr[], int i1, int i2);
  static int min(int arr[], int i1, int i2);
};

void SelectionSort::sort(int arr[], int size){
  for(int i=0;i<size;i++){
    int min = i;
    for(int j=i;j<size;j++){ // J starts at I -- find min
      min = SelectionSort::min(arr, min, j); // compare min with J
    }
    swap(arr, i, min);
  }
}

void SelectionSort::print(int arr[], int size){
  for(int i=0;i<size;i++){
    cout << arr[i] << ", ";
  }
  cout << endl;
}

void SelectionSort::swap(int arr[], int i1, int i2){
  int temp = arr[i1];
  arr[i1] = arr[i2];
  arr[i2] = temp;
}

int SelectionSort::min(int arr[], int i1, int i2){
  return arr[i1] < arr[i2] ? i1 : i2;
}

//
//--------------------------------------------- Main function
//

int main(void){
  int arr[] = {5,4,6,3,7,2,8,1,0,9};
  int size = sizeof(arr)/sizeof(arr[0]);
  SelectionSort::print(arr, size);
  SelectionSort::sort(arr, size);
  SelectionSort::print(arr, size);
  return 0;
}

/* OUTPUT:

5, 4, 6, 3, 7, 2, 8, 1, 0, 9,
0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 

*/
