# Graph: adjacency list

'''TODO
- python array of objects does not exist ???
'''
class Node:
    def __init__(self, key, weight):
        self.key = key
        self.weight = weight
        self.next = None

    def __str__(self):
        return "Node(key={key})".format(key=self.key)

class Graph:
    def __init__(self, capacity):
        self.array = [None] * capacity

    def connect(self, i, j):
        head = self.array[i]
        newNode = Node(j, None)

        if head == None:
            self.array[i] = newNode
            return

        prev = None
        while head != None:
            prev = head
            if head.key == j:
                return
            head = head.next
        prev.next = newNode

    def isConnected(self, i, j):
        head = self.array[i]
        while head != None:
            if head.key == j:
                return True
            head = head.next
        return False

    def DFS(self, i):
        stack = [] # append, pop
        stack.append(i)
        while stack != []:
            idx = stack.pop()
            print("{idx} ".format(idx=idx)),
            head = self.array[idx]
            while head != None:
                if head != None:
                    stack.append(head.key)
                head = head.next
        print("")

    def BFS(self, i):
        stack = [] # append, pop(0)
        stack.append(i)
        while stack != []:
            idx = stack.pop(0)
            print("{idx} ".format(idx=idx)),
            head = self.array[idx]
            while head != None:
                if head != None:
                    stack.append(head.key)
                head = head.next
        print("")

    def show(self):
        print("Graph(array=[")
        for i in range(len(self.array)):
            head = self.array[i]
            print("  [{i}]".format(i=i)),
            if head == None:
                print("None".format(i=i)),
                next
            while head != None:
                print("{node} -> ".format(i=i, node=head)),
                head = head.next
            print("")
        print("])")

if __name__ == "__main__":
    graph = Graph(10)
    graph.connect(1,2)
    graph.connect(1,3)
    graph.connect(1,4)

    graph.connect(4,5)
    graph.connect(5,6)
    graph.connect(6,7)
    graph.connect(7,8)
    graph.connect(8,9)

    graph.show()
    graph.DFS(1) # 1  4  5  6  7  8  9  3  2
    graph.BFS(1) # 1  2  3  4  5  6  7  8  9
    print(graph.isConnected(1,2)) # True

'''OUTPUT

Graph(array=[
  [0] None
  [1] Node(key=2) ->  Node(key=3) ->  Node(key=4) ->
  [2] None
  [3] None
  [4] Node(key=5) ->
  [5] Node(key=6) ->
  [6] Node(key=7) ->
  [7] Node(key=8) ->
  [8] Node(key=9) ->
  [9] None
])
1  4  5  6  7  8  9  3  2
1  2  3  4  5  6  7  8  9
True

'''
