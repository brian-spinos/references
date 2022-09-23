import sys

class Node():
    RED = 1
    BLACK = 0
    def __init__(self, data):
        self.data = data
        self.parent = None
        self.left = None
        self.right = None
        self.color = self.RED

class RedBlackTree():
    RED = 1
    BLACK = 0

    def __init__(self):
        self.NIL = Node(0)
        self.NIL.color = self.BLACK
        self.NIL.left = None
        self.NIL.right = None
        self.root = self.NIL

    def left_rotate(self, x):
        y = x.right
        x.right = y.left
        if y.left != self.NIL:
            y.left.parent = x

        y.parent = x.parent
        if x.parent == None:
            self.root = y
        elif x == x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left = x
        x.parent = y

    def right_rotate(self, x):
        y = x.left
        x.left = y.right
        if y.right != self.NIL:
            y.right.parent = x

        y.parent = x.parent
        if x.parent == None:
            self.root = y
        elif x == x.parent.right:
            x.parent.right = y
        else:
            x.parent.left = y
        y.right = x
        x.parent = y

    def insert(self, key):
        node = Node(key)
        node.parent = None
        node.data = key
        node.left = self.NIL
        node.right = self.NIL
        node.color = self.RED # new node must be red

        y = None
        x = self.root

        while x != self.NIL:
            y = x
            if node.data < x.data:
                x = x.left
            else:
                x = x.right

        # y is parent of x
        node.parent = y
        if y == None:
            self.root = node
        elif node.data < y.data:
            y.left = node
        else:
            y.right = node

        # if new node is a root node, simply return
        if node.parent == None:
            node.color = self.BLACK
            return

        # if the grandparent is None, simply return
        if node.parent.parent == None:
            return

        self.__fix_insert(node)

    def get_root(self):
        return self.root

    # delete the node from the tree
    def delete_node(self, data):
        self.__delete_node_helper(self.root, data)

    def show(self):
        self.__print_helper(self.root, "", True)

    def inorder(self):
        self.__in_order_helper(self.root)

    # search the tree for the key k
    # and return the corresponding node
    def find(self, k):
        return self.__find(self.root, k)

    def __in_order_helper(self, node):
        if node != self.NIL:
            self.__in_order_helper(node.left)
            sys.stdout.write(str(node.data) + " ")
            self.__in_order_helper(node.right)

    def __find(self, node, key):
        if node == self.NIL or key == node.data:
            return node

        if key < node.data:
            return self.__find(node.left, key)
        return self.__find(node.right, key)

    def __fix_delete(self, x):
        while x != self.root and x.color == self.BLACK:
            if x == x.parent.left:
                s = x.parent.right
                if s.color == self.RED:
                    # case 3.1
                    s.color = self.BLACK
                    x.parent.color = self.RED
                    self.left_rotate(x.parent)
                    s = x.parent.right

                if s.left.color == self.BLACK and s.right.color == self.BLACK:
                    # case 3.2
                    s.color = self.RED
                    x = x.parent
                else:
                    if s.right.color == self.BLACK:
                        # case 3.3
                        s.left.color = self.BLACK
                        s.color = self.RED
                        self.right_rotate(s)
                        s = x.parent.right

                    # case 3.4
                    s.color = x.parent.color
                    x.parent.color = self.BLACK
                    s.right.color = self.BLACK
                    self.left_rotate(x.parent)
                    x = self.root
            else:
                s = x.parent.left
                if s.color == self.RED:
                    # case 3.1
                    s.color = self.BLACK
                    x.parent.color = self.RED
                    self.right_rotate(x.parent)
                    s = x.parent.left

                if s.left.color == self.BLACK and s.right.color == self.BLACK:
                    # case 3.2
                    s.color = self.RED
                    x = x.parent
                else:
                    if s.left.color == self.BLACK:
                        # case 3.3
                        s.right.color = self.BLACK
                        s.color = self.RED
                        self.left_rotate(s)
                        s = x.parent.left

                    # case 3.4
                    s.color = x.parent.color
                    x.parent.color = self.BLACK
                    s.left.color = self.BLACK
                    self.right_rotate(x.parent)
                    x = self.root
        x.color = self.BLACK

    #
    #
    #
    def __rb_transplant(self, u, v):
        if u.parent == None:
            self.root = v
        elif u == u.parent.left:
            u.parent.left = v
        else:
            u.parent.right = v
        v.parent = u.parent

    def __delete_node_helper(self, node, key):
        # find the node containing key
        z = self.NIL
        while node != self.NIL:
            if node.data == key:
                z = node

            if node.data <= key:
                node = node.right
            else:
                node = node.left

        if z == self.NIL:
            print "Couldn't find key in the tree"
            return

        y = z
        y_original_color = y.color
        if z.left == self.NIL:
            x = z.right
            self.__rb_transplant(z, z.right)
        elif (z.right == self.NIL):
            x = z.left
            self.__rb_transplant(z, z.left)
        else:
            y = self.__minimum(z.right)
            y_original_color = y.color
            x = y.right
            if y.parent == z:
                x.parent = y
            else:
                self.__rb_transplant(y, y.right)
                y.right = z.right
                y.right.parent = y

            self.__rb_transplant(z, y)
            y.left = z.left
            y.left.parent = y
            y.color = z.color
        if y_original_color == self.BLACK:
            self.__fix_delete(x)

    def __fix_insert(self, k):
        while k.parent.color == self.RED:
            if k.parent == k.parent.parent.right:
                u = k.parent.parent.left # uncle
                if u.color == self.RED:
                    # case 3.1
                    u.color = self.BLACK
                    k.parent.color = self.BLACK
                    k.parent.parent.color = self.RED
                    k = k.parent.parent
                else:
                    if k == k.parent.left:
                        # case 3.2.2
                        k = k.parent
                        self.right_rotate(k)
                    # case 3.2.1
                    k.parent.color = self.BLACK
                    k.parent.parent.color = self.RED
                    self.left_rotate(k.parent.parent)
            else:
                u = k.parent.parent.right # uncle

                if u.color == self.RED:
                    # mirror case 3.1
                    u.color = self.BLACK
                    k.parent.color = self.BLACK
                    k.parent.parent.color = self.RED
                    k = k.parent.parent
                else:
                    if k == k.parent.right:
                        # mirror case 3.2.2
                        k = k.parent
                        self.left_rotate(k)
                    # mirror case 3.2.1
                    k.parent.color = self.BLACK
                    k.parent.parent.color = self.RED
                    self.right_rotate(k.parent.parent)
            if k == self.root:
                break
        self.root.color = self.BLACK

    def __print_helper(self, node, indent, last):
        if node != self.NIL:
            sys.stdout.write(indent)
            if last:
                sys.stdout.write("|----")
                indent += "     "
            else:
                sys.stdout.write("|----")
                indent += "|    "

            RED_LABEL = " R"
            BLACK_LABEL = ""
            s_color = RED_LABEL if node.color == self.RED else BLACK_LABEL
            print str(node.data) + s_color
            self.__print_helper(node.right, indent, False)
            self.__print_helper(node.left, indent, True)

    # find the node with the minimum key
    def __minimum(self, node):
        while node.left != self.NIL:
            node = node.left
        return node

    def successor(self, x):
        # if the right subtree is not None,
        # the successor is the leftmost node in the
        # right subtree
        if x.right != self.NIL:
            return self.__minimum(x.right)

        # else it is the lowest ancestor of x whose
        # left child is also an ancestor of x.
        y = x.parent
        while y != self.NIL and x == y.right:
            x = y
            y = y.parent
        return y

if __name__ == "__main__":
    rbt = RedBlackTree()
    for i in [5,4,6,3,7,2,8,1,0,9]:
        rbt.insert(i)
    rbt.delete_node(5)
    rbt.show()
    rbt.inorder() # 0 1 2 3 4 6 7 8 9

    print("")
    x = rbt.find(0)
    print(x)

'''OUTPUT

|----6
     |----8 R
     |    |----9
     |    |----7
     |----3 R
          |----4
          |----1
               |----2 R
               |----0 R

'''
