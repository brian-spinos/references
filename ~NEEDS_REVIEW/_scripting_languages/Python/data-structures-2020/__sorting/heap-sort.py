class HeapSort:
    def __init__(self, array):
        self.array = array

    def sort(self):
        self.heapify()
        self.swapToEnd()

    # Private

    def heapify(self):
        for i in range(1, len(self.array)):
            self.bubbleUp(i)

    def bubbleUp(self, i):
        currIdx = i
        while self.hasP(currIdx) and self.PV(currIdx) < self.array[currIdx]:
            self.swap(currIdx, self.PI(currIdx))
            currIdx = self.PI(currIdx)

    def swapToEnd(self):
        arrLen = len(self.array)
        for i in range(arrLen-1):
            currLastIdx = arrLen-1-i
            self.swap(0, currLastIdx)
            self.bubbleDown(currLastIdx)

    def bubbleDown(self, currLastIdx):
        currIdx = 0
        while self.hasL(currIdx, currLastIdx):
            cmi = self.LI(currIdx)
            if self.hasR(currIdx, currLastIdx) and self.RV(currIdx) > self.LV(currIdx):
                cmi = self.RI(currIdx)
            if self.array[currIdx] < self.array[cmi]:
                self.swap(currIdx, cmi)
                currIdx = cmi
            else:
                break

    def swap(self, i, j):
        self.array[i], self.array[j] = self.array[j], self.array[i]

    # Helpers

    def hasP(self, i):
        return i > 0

    def hasL(self, i, j): # j is exclusive
        return self.LI(i) < j

    def hasR(self, i, j): # j is exclusive
        return self.RI(i) < j

    def PI(self, i):
        return (i-1)//2

    def LI(self, i):
        return 2*i+1

    def RI(self, i):
        return 2*i+2

    def PV(self, i):
        return self.array[self.PI(i)]

    def LV(self, i):
        return self.array[self.LI(i)]

    def RV(self, i):
        return self.array[self.RI(i)]

if __name__ == "__main__":
    print("start")
    arr = [5,4,6,3,7,2,8,1,0,9]
    heapSort = HeapSort(arr)
    heapSort.sort()
    print(arr)
    # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
