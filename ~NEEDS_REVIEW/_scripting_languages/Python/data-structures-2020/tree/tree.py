# Tree

#------------------------------ Node
class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None

    def __str__(self):
        if  self == None:
            return
        LK = None
        RK = None
        if self.left != None:
            LK = self.left.key
        if self.right != None:
            RK = self.right.key
        return "Node(key={key}, left={LK}, right={RK})"\
            .format(key=self.key, LK=LK, RK=RK)

#------------------------------ Tree
class Tree:
    def __init__(self):
        self.root = None

    def insert(self, key):
        node = Node(key)
        if self.root == None:
            self.root = node
            return

        currentNode = self.root
        currentParent = None

        while(currentNode != None):
            currentParent = currentNode
            if key > currentNode.key:
                currentNode = currentNode.right
            elif key < currentNode.key:
                currentNode = currentNode.left
            else:
                return # no dups

        if key > currentParent.key:
            currentParent.right = node
        else:
            currentParent.left = node

    def inorder(self):
        self._inorder(self.root)

    def _inorder(self, node):
        if node == None:
            return
        self._inorder(node.left)
        print(node)
        self._inorder(node.right)

#------------------------------ Main
tree = Tree()
tree.insert(100)
tree.insert(50)
tree.insert(25)
tree.insert(75)
tree.insert(150)

tree.inorder()

""" OUTPUT
Node(key=25, left=None, right=None)
Node(key=50, left=25, right=75)
Node(key=75, left=None, right=None)
Node(key=100, left=50, right=150)
Node(key=150, left=None, right=None)
"""
