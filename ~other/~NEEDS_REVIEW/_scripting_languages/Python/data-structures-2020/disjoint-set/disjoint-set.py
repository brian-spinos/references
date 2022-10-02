#------------------------------ Disjoint-Sets

class DisjointSet:
    def __init__(self, capacity):
        self.list = [-1] * capacity

    # Union by rank
    def union(self, n1, n2):
        r1 = self.find(n1)
        r2 = self.find(n2)

        if self.list[r1] <= self.list[r2]:
            self.list[r1] += self.list[r2]
            self.list[r2] = r1
        else:
            self.list[r2] += self.list[r1]
            self.list[r1] = r2

    # path compression
    def find(self, n1):
        current = n1
        while self.list[current] >= 0:
            current = self.list[current]
        root = current
        current = n1

        while self.list[current] >= 0:
            parent = self.list[current]
            self.list[current] = root
            current = parent
        return root

    def show(self):
        print(self.list)

#------------------------------ Main
ds = DisjointSet(20)
ds.union(1,2)
ds.union(2,3)
ds.union(3,4)
ds.union(4,5)

ds.union(6,7)
ds.union(7,8)
ds.union(8,9)
ds.union(9,10)

print("FIND 5: " + str(ds.find(5))) # 1
print("FIND 3: " + str(ds.find(3))) # 1

print("FIND 9: " + str(ds.find(9))) # 6
print("FIND 7: " + str(ds.find(7))) # 6

ds.show()

'''OUTPUT

FIND 5: 1
FIND 3: 1
FIND 9: 6
FIND 7: 6
[-1, -5, 1, 1, 1, 1, -5, 6, 6, 6, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1]

'''
