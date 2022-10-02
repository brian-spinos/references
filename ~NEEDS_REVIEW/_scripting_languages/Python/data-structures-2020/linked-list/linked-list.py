# Linked list

# ------------------------------ Node
class Node:
    def __init__(self, key):
        self.key = key
        self.next = None

# ------------------------------ LinkedList
class LinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def add(self, key):
        node = Node(key)
        if self.head == None:
            self.head = node
            self.tail = node
            return

        self.tail.next = node
        self.tail = node

    def show(self):
        currentNode = self.head
        while(currentNode != None):
            print(currentNode.key)
            currentNode = currentNode.next

# ------------------------------ Main
linkedList = LinkedList()
linkedList.add(1)
linkedList.add(2)
linkedList.add(3)
linkedList.add(4)
linkedList.add(5)
linkedList.add(6)

linkedList.show()
