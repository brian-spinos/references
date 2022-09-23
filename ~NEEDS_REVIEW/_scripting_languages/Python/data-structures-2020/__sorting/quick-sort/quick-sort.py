import array

class QuickSort:
    def __init__(self, arr):
        self.array = arr

    def sort(self):
        self.quickSort(0, len(arr)-1)

    def quickSort(self, left, right): # right is inclusive
        if left >= right: return
        p = self.partition(left, right)
        self.quickSort(left, p-1)
        self.quickSort(p+1, right)

    def partition(self, left, right): # right is inclusive
        wall = left
        pivot = self.array[right]
        for i in range(left, right+1):
            if self.array[i] < pivot:
                self.swap(i, wall)
                wall+=1
        self.swap(wall, right)
        return wall

    def swap(self, i, j):
        self.array[i], self.array[j] = self.array[j], self.array[i]


if __name__ == "__main__":
    arr = array.array('i', [5,4,6,3,7,2,8,1,0,9])
    quickSort = QuickSort(arr)
    quickSort.sort()
    print(arr)
    # array('i', [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
