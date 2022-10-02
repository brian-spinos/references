#------------------------------ Heap
class Heap:
    def __init__(self, capacity):
        self.list = []
        self.capacity = capacity

    def add(self, item):
        print("ADD:    {i}".format(i=item))
        self.list.append(item)
        self.__bubbleUp()

    def remove(self):
        if len(self.list) == 0: return
        item = self.list[0]
        last = self.list[-1]
        self.list[0] = last
        self.__bubbleDown()
        self.list = self.list[:-1] # remove last item
        print("REMOVE: {i}".format(i=item))
        return item

    def peek(self):
        print("PEEK:   {i}".format(i=self.list[0]))
        return self.list[0]

    def show(self):
        print(self.list)

    # Private Methods

    def __PI(self, i): return (i-1)/2
    def __LI(self, i): return 2 * i + 1
    def __RI(self, i): return 2 * i + 2
    def __hasP(self, i): return i > 0
    def __hasL(self, i): return self.__LI(i) < len(self.list)
    def __hasR(self, i): return self.__RI(i) < len(self.list)
    def __PV(self, i): return self.list[self.__PI(i)]
    def __LV(self, i): return self.list[self.__LI(i)]
    def __RV(self, i): return self.list[self.__RI(i)]

    def __bubbleUp(self):
        i = len(self.list) -1
        while self.__hasP(i) and self.__PV(i) > self.list[i]:
            self.__swap(i, self.__PI(i))
            i = self.__PI(i)

    def __bubbleDown(self):
        i = 0
        while self.__hasL(i):
            sci = self.__LI(i)
            if self.__hasR(i) and self.__RV(i) < self.__LV(i):
                sci = self.__RI(i)
            if self.list[i] > self.list[sci]:
                self.__swap(i, sci)
                i = sci
            else:
                return # break

    def __swap(self, i, j):
        self.list[i], self.list[j] = self.list[j], self.list[i]

#------------------------------ Main
heap = Heap(10)

heap.add(5)
heap.add(4)
heap.add(3)
heap.add(2)
heap.add(1)
heap.add(10)
heap.add(9)
heap.add(8)
heap.add(7)
heap.add(6)

heap.show()
heap.peek()

heap.remove()
heap.remove()
heap.remove()
heap.remove()
heap.remove()

heap.show()

heap.remove()
heap.remove()
heap.remove()
heap.remove()
heap.remove()

heap.remove()
heap.show()

'''OUTPUT

ADD:    5
ADD:    4
ADD:    3
ADD:    2
ADD:    1
ADD:    10
ADD:    9
ADD:    8
ADD:    7
ADD:    6
[1, 2, 4, 5, 3, 10, 9, 8, 7, 6]
PEEK:   1
REMOVE: 1
REMOVE: 2
REMOVE: 3
REMOVE: 4
REMOVE: 5
[6, 7, 8, 10, 9]
REMOVE: 6
REMOVE: 7
REMOVE: 8
REMOVE: 9
REMOVE: 10
[]

'''
