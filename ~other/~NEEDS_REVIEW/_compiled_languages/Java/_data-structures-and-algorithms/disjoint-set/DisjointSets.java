package com.example;

import java.util.Arrays;

class DisjointSet {
    private int[] array;

    public DisjointSet(int size) {
        this.array = new int[size];
        Arrays.fill(array, -1);
    }

    /**
     * Using union by rank
     */
    public void union(int i1, int i2) {
        int r1 = find(i1);
        int r2 = find(i2);

        if (r1 < r2) {
            array[r1] += array[r2]; // Get total rank
            array[r2] = r1; // r2 becomes child of r1
        } else if (r2 < r1) {
            array[r2] += array[r1];
            array[r1] = r2;
        } else {
            array[r1] += array[r2];
            array[r2] = r1;
        }
    }

    /**
     * Using path compression
     */
    public int find(int i) {
        int root;
        int currentIdx = i;
        int currentParent;
        while (array[currentIdx] >= 0) {
            currentIdx = array[currentIdx];
        }
        root = currentIdx;
        currentIdx = i;

        while (array[currentIdx] >= 0) {
            currentParent = array[currentIdx];
            array[currentIdx] = root;
            currentIdx = currentParent;
        }

        return root;
    }

    public void makeSet(int i) {
        // TODO: Needs child array
    }

    @Override
    public String toString() {
        return "DisjointSet{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}

public class DisjointSetExample {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(10);
        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(3, 4);
        ds.union(4, 5);

        ds.union(6, 7);
        ds.union(7, 8);
        ds.union(8, 9);

        System.out.println("ds.find(5) = " + ds.find(5)); // 1
        System.out.println("ds.find(3) = " + ds.find(3)); // 1

        System.out.println("ds.find(5) = " + ds.find(5)); // 1
        System.out.println("ds.find(9) = " + ds.find(9)); // 6

        System.out.println(ds.toString());
    }
}

/* OUTPUT

ds.find(5) = 1
ds.find(3) = 1
ds.find(5) = 1
ds.find(9) = 6
DisjointSet{array=[-1, -5, 1, 1, 1, 1, -4, 6, 6, 6]}

*/
