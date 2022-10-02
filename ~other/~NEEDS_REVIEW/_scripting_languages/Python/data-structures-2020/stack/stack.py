# Stack

# ------------------------------ Node
class Node:
    def __init__(self, key):
        self.key = key
        self.next = None

# ------------------------------ Stack
class Stack:
    def __init__(self):
        self.head = None

    def push(self, key):
        node = Node(key)
        if self.head == None:
            self.head = node
            return

        node.next = self.head
        self.head = node

    def pop(self):
        if self.head == None:
            return

        self.head = self.head.next

    def show(self):
        currentNode = self.head
        while(currentNode != None):
            print(currentNode.key)
            currentNode = currentNode.next

# ------------------------------ Main
linkedList = Stack()
linkedList.push(1)
linkedList.push(2)
linkedList.push(3)
linkedList.push(4)
linkedList.push(5)
linkedList.push(6)


linkedList.pop()
linkedList.pop()
linkedList.pop()

linkedList.show()
