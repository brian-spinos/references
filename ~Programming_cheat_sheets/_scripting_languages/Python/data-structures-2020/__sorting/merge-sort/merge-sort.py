class MergeSort:
    def __init__(self, array):
        self.array = array

    def sort(self):
        return self.mergeSort(self.array)

    def mergeSort(self, arr):
        if len(arr) < 2:
            return arr
        mid = len(arr) // 2
        arr1 = self.mergeSort(arr[:mid])
        arr2 = self.mergeSort(arr[mid:])
        return self.merge(arr1, arr2)

    def merge(self, arr1, arr2):
        arr3 = []
        while(arr1 != None and arr2 != None and len(arr1) > 0 and len(arr2) > 0):
            if arr1[0] <= arr2[0]:
                arr3.append(arr1.pop(0))
            else:
                arr3.append(arr2.pop(0))

        while(arr1 != None and len(arr1) > 0):
            arr3.append(arr1.pop(0))

        while(arr2 != None and len(arr2) > 0):
            arr3.append(arr2.pop(0))
        return arr3

if __name__ == "__main__":
    arr = [5,4,6,3,7,2,8,1,0,9]
    mergeSort = MergeSort(arr)
    arr = mergeSort.sort()
    print(arr)
    # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
