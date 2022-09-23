#import<iostream>
using namespace std;

//
//------------------------------------------------- Notes
// $ g++ merge-sort.cpp -o merge-sort; ./merge-sort;
//

//
//------------------------------------------------- MergeSort
//

class MergeSort {
private:
  static void swap(int arr[], int i1, int i2);
  static void merge(int arr[], int i1, int mid, int i2);
  static void mergeSort(int arr[], int i1, int i2); // recursive
public:
  static void sort(int arr[], int size);
  static void print(int arr[], int size);
};

void MergeSort::sort(int arr[], int size){
  mergeSort(arr,0, size-1);
}

void MergeSort::mergeSort(int arr[], int i1, int i2){
  if (i1 >= i2) return;
  int mid = (i1 + i2) / 2;
  mergeSort(arr,i1, mid);
  mergeSort(arr,mid+1, i2);
  merge(arr, i1, mid+1, i2);
}

void MergeSort::merge(int arr[], int i1, int mid, int i2){
  int size1 = mid - i1;
  int size2 = i2 - (mid - 1);
  int * arr1 = new int[size1];
  int * arr2 = new int[size2];
  copy(arr + i1, arr + mid, arr1);
  copy(arr + mid, arr + (i2 + 1), arr2);
  int idx1 = 0;
  int idx2 = 0;
  int idx3 = i1;

  while(idx1 < size1 && idx2 < size2){
    if(arr1[idx1] < arr2[idx2]){
      arr[idx3] = arr1[idx1];
      idx1++;
    }else{
      arr[idx3] = arr2[idx2];
      idx2++;
    }
    idx3++;
  }

  while(idx1 < size1){
    arr[idx3] = arr1[idx1];
    idx1++;
    idx3++;
  }

  while(idx2 < size2){
    arr[idx3] = arr2[idx2];
    idx2++;
    idx3++;
  }
}

void MergeSort::print(int arr[], int size){
  for(int i=0;i<size;i++){
    cout << arr[i] << ", ";
  }
  cout << endl;
}

void MergeSort::swap(int arr[], int i1, int i2){
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
  MergeSort::print(arr, size);
  MergeSort::sort(arr, size);
  MergeSort::print(arr, size);
  return 0;
}

/* OUTPUT

5, 4, 6, 3, 7, 2, 8, 1, 0, 9,
0, 1, 2, 3, 4, 5, 6, 7, 8, 9,

*/
