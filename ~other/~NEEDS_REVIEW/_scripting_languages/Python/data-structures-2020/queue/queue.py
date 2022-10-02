# Queue

# ------------------------------ Node
class Node:
    def __init__(self, key):
        self.key = key
        self.next = None

# ------------------------------ Queue
class Queue:
    def __init__(self):
        self.head = None
        self.tail = None

    def enqueue(self, key):
        node = Node(key)
        if self.head == None:
            self.head = node
            self.tail = node
            return

        self.tail.next = node
        self.tail = node

    def dequeue(self):
        if self.head == None:
            return

        node = self.head
        self.head = self.head.next

        if self.head == None:
            self.tail = Node 
        return node

    def show(self):
        currentNode = self.head
        while(currentNode != None):
            print(currentNode.key)
            currentNode = currentNode.next

# ------------------------------ Main
linkedList = Queue()
linkedList.enqueue(1)
linkedList.enqueue(2)
linkedList.enqueue(3)
linkedList.enqueue(4)
linkedList.enqueue(5)
linkedList.enqueue(6)

linkedList.dequeue()
linkedList.dequeue()
linkedList.dequeue()

linkedList.show()
