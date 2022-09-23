package com.example;

import java.util.Arrays;

class ResizeableArray<T> {
  private int size;
  private T[] arr;
  private static int DEFAULT_SIZE = 10;
  private int lastIndex = 0;

  @SuppressWarnings("unchecked")
  public ResizeableArray() {
    this.size = DEFAULT_SIZE;
    arr = (T[]) new Object[DEFAULT_SIZE];
  }

  @SuppressWarnings("unchecked")
  public ResizeableArray(int size) {
    this.size = size;
    arr = (T[]) new Object[size];
  }

  public T get(int index) throws Exception {
    if (index >= size) {
      throw new Exception("INDEX_OUT_OF_BOUNDS");
    }
    return arr[index];
  }

  public void set(int index, T value) throws Exception {
    if (index >= size) {
      throw new Exception("Index out of bounds");
    }
    arr[index] = value;
  }

  public T add(T value) throws Exception {
    if (lastIndex >= size) {
      resizeArray();
    }
    return arr[lastIndex++] = value;
  }

  @Override
  public String toString() {
    return "Array{" +
            "size=" + size +
            ", arr=" + Arrays.toString(arr) +
            ", lastIndex=" + lastIndex +
            '}';
  }

  //
  // Private
  //

  @SuppressWarnings("unchecked")
  private void resizeArray() {
    System.out.println("resizeArray...");
    size = arr.length * 2;
    T[] arr2 = (T[]) new Object[size];
    for (int i = 0; i < arr.length; i++) {
      arr2[i] = arr[i];
    }
    arr = arr2;
  }
}


public class ResizeableArrayExample {
  public static void main(String[] args) {

    ResizeableArray<Integer> arr = new ResizeableArray<>(5);
    try {
      arr.add(0);
      arr.add(10);
      arr.add(20);
      arr.add(30);
      arr.add(40);
      arr.add(50);
      arr.add(60);
      arr.add(70);
      arr.add(80);
      arr.add(90);
      arr.add(100);
      arr.add(110);

      arr.set(7, 7070);

      //arr.set(999, 999); // Index out of bounds

      System.out.println("GET-7: " + arr.get(7));
      System.out.println("arr: " + arr);
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }
}

/* OUTPUT

resizeArray...
resizeArray...
GET-7: 7070
arr: Array{size=20, arr=[0, 10, 20, 30, 40, 50, 60, 7070, 80, 90, 100, 110, null, null, null, null, null, null, null, null], lastIndex=12}
*/
