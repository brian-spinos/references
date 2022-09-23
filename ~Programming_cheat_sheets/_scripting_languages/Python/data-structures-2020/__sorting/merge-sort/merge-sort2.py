import array

class MergeSort:
    def __init__(self, array):
        self.array = array

    def sort(self):
        self.mergeSort(0, len(self.array)-1)

    def mergeSort(self, left, right): # right is inclusive
        if left == right: return
        mid = (left + right) // 2 # mid belongs to left side
        self.mergeSort(left, mid)
        self.mergeSort(mid+1, right)
        self.merge(left, mid+1, right)

    def merge(self, left, mid, right):
        if left == right: return
        tempArr = array.array('i')
        idx1 = left
        idx2 = mid
        while(idx1 < mid and idx2 <= right):
            if self.array[idx1] <= self.array[idx2]:
                tempArr.append(self.array[idx1])
                idx1+=1
            else:
                tempArr.append(self.array[idx2])
                idx2+=1

        while(idx1 < mid):
            tempArr.append(self.array[idx1])
            idx1+=1

        while(idx2 <= right):
            tempArr.append(self.array[idx2])
            idx2+=1

        for i in range(left,right+1):
            self.array[i] = tempArr[i - left]


if __name__ == "__main__":
    arr = array.array('i',[5,4,90,6,3,80,7,2,8,70,1,60,0,9,50,11])
    mergeSort = MergeSort(arr)
    mergeSort.sort()
    print(arr)
    # array('i', [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 50, 60, 70, 80, 90])
