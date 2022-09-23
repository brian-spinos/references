package com.example;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

import static com.example.Color.RED;
import static com.example.Color.BLACK;

enum Color {RED, BLACK}

/**
 * TODO:
 * - RBNode and NilNode should implement Node
 * - Check for mirror cases
 * - add deletion code
 * - for verification: https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 */
class InsertionFixer {
    public static void fix(RBTree t, Node node) {
        System.out.println("INFO::1007 - Starting fix for node: " + node);
        getCaseAndFix(t, node);
    }

    public static void getCaseAndFix(RBTree t, Node node) { // recursive
        int c = getCase(t, node);
        if (c == -1) return; // base case
        System.out.println("INFO::1002 - Got case #" + c);

        if (c == 1) {
            fixCase1(t, node); // red root (FINAL case)
        } else if (c == 2) {
            fixCase2(t, node); // red uncle
            Node g = getGrandParent(t, node);
            getCaseAndFix(t, g); // recurse on G
        } else if (c == 3) {
            fixCase3(t, node); // black uncle - triangle
            // getCaseAndFix(t,node); -- will be used in the method above
        } else if (c == 4) {
            fixCase4(t, node);  // black uncle - line (FINAL case)
        }
    }

    /**
     * Root is RED
     * Solution: color it BLACK
     */
    public static void fixCase1(RBTree t, Node node) {
        node.setColor(BLACK);
    }

    /**
     * Uncle is RED
     * Solution: recolor P,U,G
     */
    public static void fixCase2(RBTree t, Node node) {
        // TODO: check if we have P,U,G
        recolor(t, getParent(t, node));
        recolor(t, getUncle(t, node));
        recolor(t, getGrandParent(t, node));
    }

    /**
     * Uncle is BLACK (triangle)
     */
    public static void fixCase3(RBTree t, Node node) {
        Node p = getParent(t, node);
        if (isLeftChild(t, node)) {
            rightRotate(t, p);
        } else {
            leftRotate(t, p);
        }
        getCaseAndFix(t, p);
    }

    /**
     * Uncle is BLACK (line)
     */
    public static void fixCase4(RBTree t, Node node) {
        Node p = getParent(t, node);
        Node g = getGrandParent(t, node);
        recolor(t, p);
        recolor(t, g);
        if (isLeftChild(t, node)) {
            rightRotate(t, g);
        } else {
            leftRotate(t, g);
        }
    }

    // Helper methods

    public static void leftRotate(RBTree t, Node a) {
        System.out.println("INFO::1003 - LeftRotate node: " + a.getKey());
        Node p = a.getParent();
        Node b = a.getRight();
        Node c = b.getLeft();

        b.setLeft(a);
        a.setRight(c);

        if (p == null) {
            // a WAS ROOT!
            t.setRoot(b);
        } else {
            if (isLeftChild(t, a)) {
                p.setLeft(b);
            } else {
                p.setRight(b);
            }
        }

        c.setParent(a);
        a.setParent(b);
        b.setParent(p);
    }

    public static void rightRotate(RBTree t, Node a) {
        System.out.println("INFO::1004 - RightRotate node: " + a.getKey());
        Node p = a.getParent();
        Node b = a.getLeft();
        Node c = b.getRight();

        b.setRight(a);
        a.setLeft(c);

        if (p == null) {
            // a WAS ROOT!
            t.setRoot(b);
        } else {
            if (isLeftChild(t, a)) {
                p.setLeft(b);
            } else {
                p.setRight(b);
            }
        }

        c.setParent(a);
        a.setParent(b);
        b.setParent(p);
    }

    // mirror, nil
    public static boolean isRoot(RBTree t, Node node) {
        return t.getRoot().equals(node);
    }

    // mirror, nil
    public static boolean isRed(RBTree t, Node node) {
        return node.getColor().equals(RED);
    }

    // mirror, nil
    public static boolean isBlack(RBTree t, Node node) {
        return node.getColor().equals(BLACK);
    }

    // mirror, nil
    private static boolean hasGrandParent(RBTree t, Node node) {
        if (hasParent(t, node)) {
            Node p = getParent(t, node);
            return hasParent(t, p);
        }
        return false;
    }

    // mirror, nil
    private static Node getGrandParent(RBTree t, Node node) {
        Node p = getParent(t, node);
        return getParent(t, p);
    }

    // mirror, nil
    public static boolean hasParent(RBTree t, Node node) {
        return node.getParent() != null;
    }

    // mirror, nil
    public static Node getParent(RBTree t, Node node) {
        return node.getParent();
    }


    // mirror, nil
    public static boolean hasUncle(RBTree t, Node node) {
        if (!hasGrandParent(t, node)) return false;
        Node p = getParent(t, node);
        Node g = getParent(t, p);
        if (g != null) return true; // if has G, it has U, guaranteed!
        return false;
    }

    // mirror, nil
    public static Node getUncle(RBTree t, Node node) {
        //if (!hasUncle(t,node)) return null;
        Node p = getParent(t, node);
        Node g = getParent(t, p);

        if (isLeftChild(t, p)) {
            return g.getRight();
        } else {
            return g.getLeft();
        }
    }

    // mirror, nil
    public static void recolor(RBTree t, Node node) {
        System.out.println("INFO::1005 - Recoloring node: " + node.getKey() + " from original color: " + node.getColor());
        if (node.getColor() == RED) {
            node.setColor(BLACK);
        } else {
            node.setColor(RED);
        }
    }

    // mirror, nil
    // TODO: maybe this is used just on DELETION...
    public static void swapColor(RBTree t, Node n1, Node n2) {
        Color temp = n1.getColor();
        n1.setColor(n2.getColor());
        n2.setColor(temp);
    }

    // mirror, nil
    public static boolean isLeftChild(RBTree t, Node node) {
        return node.getParent().getLeft() == node;
    }

    // TODO: maybe remove this method and use !isLeftChild()
    // mirror, nil
    public static boolean isRightChild(RBTree t, Node node) {
        return node.getParent().getRight() == node;
    }

    // has black uncle
    // mirror, nil
    private static boolean isLineCase(RBTree t, Node node) {
        Node u = getUncle(t, node);
        if (isLeftChild(t, node)) {
            return isRightChild(t, u);
        } else {
            return isLeftChild(t, u);
        }
    }

    // has black uncle
    // mirror, nil
    private static boolean isTriangleCase(RBTree t, Node node) {
        Node u = getUncle(t, node);
        if (isLeftChild(t, node)) {
            return isLeftChild(t, u);
        } else {
            return isRightChild(t, u);
        }
    }

    public static int getCase(RBTree t, Node node) {
        if (isRoot(t, node) && isRed(t, node)) {
            return 1;
        } else if (hasUncle(t, node)) {
            if (isRed(t, getUncle(t, node))) {
                return 2;
            } else {
                // Black uncle assumed

                Node u = getUncle(t, node);
                if (isRed(t, u)) throw new RuntimeException("ERROR::1001 - Uncle should NOT be red here");

                if (isTriangleCase(t, node)) {
                    return 3;
                } else if (isLineCase(t, node)) {
                    return 4;
                }
            }
        }
        return -1; // no case
    }
}

class DeletionFixer {
    public static void fix(RBTree rbTree, Node node) {
        System.out.println("fixing: " + node);
    }
}


class Node {
    private int key;
    private Node left;
    private Node right;
    private Node parent;
    private Color color;
    private boolean isNil;

    public Node() {
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node(int key) {
        this.key = key;
        parent = null;
        left = null;
        right = null;
        color = null;
        isNil = false;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isNil() {
        return isNil;
    }

    public void setNil(boolean nil) {
        isNil = nil;
    }

    @Override
    public String toString() {
        if (this.isNil()) {
            return "Node{" +
                    "color=" + color +
                    ", parent=" + (parent == null ? "NULL" : parent.getKey()) +
                    '}';
        }
        return "Node{" +
                "key=" + key +
                ", left=" + (left.isNil() ? "nil" : left.getKey()) +
                ", right=" + (right.isNil() ? "nil" : right.getKey()) +
                ", color=" + color +
                ", parent=" + (parent == null ? "NULL" : parent.getKey()) +
                //", isNil=" + isNil +
                '}';
    }
}

class RBTree {
    private Node root;

    public RBTree() {
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node createNode(int key) {
        Node node = new Node(key);
        node.setColor(RED);

        Node nil1 = new Node(0);
        nil1.setNil(true);
        nil1.setColor(BLACK);
        nil1.setParent(node);

        Node nil2 = new Node(0);
        nil2.setNil(true);
        nil2.setColor(BLACK);
        nil2.setParent(node);

        node.setLeft(nil1);
        node.setRight(nil2);

        System.out.println("INFO::1006 - CreateNode node: " + node);
        return node;
    }

    public void insert(int key) throws Exception {
        System.out.println("INFO::1001 - Inserting node: " + key);
        Node node = createNode(key);
        if (root == null) {
            root = node;
            InsertionFixer.fix(this, node);
            return;
        }

        Node currNode = root;
        Node parent = null;

        while (!currNode.isNil()) {
            parent = currNode;
            if (key > currNode.getKey()) {
                currNode = currNode.getRight();
            } else if (key < currNode.getKey()) {
                currNode = currNode.getLeft();
            } else {
                throw new Exception("INFO::1000 : Duplicate key");
            }
        }

        if (key > parent.getKey()) {
            parent.setRight(node);
            node.setParent(parent);
            InsertionFixer.fix(this, node);
        } else {
            parent.setLeft(node);
            node.setParent(parent);
            InsertionFixer.fix(this, node);
        }
    }

    public Node find(int key) { // TODO: add logs
        Node currNode = root;

        while (!currNode.isNil()) {
            if (currNode.getKey() == key) return currNode;
            if (key > currNode.getKey()) {
                currNode = currNode.getRight();
            } else if (key < currNode.getKey()) {
                currNode = currNode.getLeft();
            } else {
                return null; // No dups
            }
        }
        return null;
    }

    public void delete(int key) {
        // TODO: add code
        //DeletionFixer.fix(this, node);
    }

    public void inOrder() {
        inOrder(root);
        System.out.println("");
    }

    public void inOrder(Node currNode) { // recursive
        if (currNode.isNil()) return;
        inOrder(currNode.getLeft());
        System.out.print(currNode.getKey() + ", ");
        inOrder(currNode.getRight());
    }

    public void BFS() { // queue
        Queue<Node> queue = new LinkedList<>();
        // add, remove (exception)
        // offer, poll (no exception)
        queue.add(root);

        while (!queue.isEmpty()) {
            Node n = queue.remove();
            System.out.print(n.getKey() + ", ");
            if (!n.getLeft().isNil()) queue.add(n.getLeft());
            if (!n.getRight().isNil()) queue.add(n.getRight());
        }
        System.out.println("");
    }

    public void DFS() { // stack
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.print(n.getKey() + ", ");
            if (!n.getLeft().isNil()) stack.push(n.getLeft());
            if (!n.getRight().isNil()) stack.push(n.getRight());
        }
        System.out.println("");
    }

    public boolean isValid() {
        try {
            validateBlackRoot();
            noDoubleRed();
            int res = validateBlackHeight(root);
            System.out.println("INFO::1008 - RBTree is valid (black height is: " + res + ")");
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private int validateBlackHeight(Node n) throws RuntimeException {
        if (n.isNil()) return 1;
        int validateBlackHeight_L = validateBlackHeight(n.getLeft());
        int validateBlackHeight_R = validateBlackHeight(n.getRight());
        if (validateBlackHeight_L == validateBlackHeight_R) {
            if (n.getColor() == BLACK) {
                return validateBlackHeight_L + 1;
            } else {
                return validateBlackHeight_L;
            }
        } else {
            throw new RuntimeException("ERROR::1002 - Black height property is violated");
        }
    }

    private void validateBlackRoot() throws RuntimeException {
        if (root.getColor() != BLACK)
            throw new RuntimeException("ERROR::1003 - Root is NOT BLACK");
    }

    private void noDoubleRed() throws RuntimeException {
        // TODO: add code
    }
}

public class RBTreeExample {
    public static void main(String[] args) {
        RBTree rbTree = new RBTree();

        try {
            rbTree.insert(1);
            rbTree.insert(2);
            rbTree.insert(3);
            rbTree.insert(4);
            rbTree.insert(5);
            rbTree.insert(6);
            rbTree.insert(7);
            rbTree.insert(8);
            rbTree.insert(9);
            rbTree.insert(10);
            rbTree.insert(11);
            rbTree.insert(12);

            //rbTree.find(4).setColor(RED); // ERROR::1003 - Root is NOT BLACK
            //rbTree.find(8).setColor(RED); // ERROR::1002 - Black height property is violated
            System.out.println("rbTree.isValid() = " + rbTree.isValid());


            System.out.println("rbTree.getRoot() = " + rbTree.getRoot());
            System.out.println("rbTree.find(5) = " + rbTree.find(5));
            System.out.println("rbTree.find(100) = " + rbTree.find(100));

            System.out.println("\ninOrder:");
            rbTree.inOrder();

            System.out.println("\nBFS:");
            rbTree.BFS();

            System.out.println("\nDFS:");
            rbTree.DFS();

            System.out.println("END");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

/* OUTPUT

INFO::1001 - Inserting node: 1
INFO::1006 - CreateNode node: Node{key=1, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=1, left=nil, right=nil, color=RED, parent=NULL}
INFO::1002 - Got case #1
INFO::1001 - Inserting node: 2
INFO::1006 - CreateNode node: Node{key=2, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=2, left=nil, right=nil, color=RED, parent=1}
INFO::1001 - Inserting node: 3
INFO::1006 - CreateNode node: Node{key=3, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=3, left=nil, right=nil, color=RED, parent=2}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 2 from original color: RED
INFO::1005 - Recoloring node: 1 from original color: BLACK
INFO::1003 - LeftRotate node: 1
INFO::1001 - Inserting node: 4
INFO::1006 - CreateNode node: Node{key=4, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=4, left=nil, right=nil, color=RED, parent=3}
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 3 from original color: RED
INFO::1005 - Recoloring node: 1 from original color: RED
INFO::1005 - Recoloring node: 2 from original color: BLACK
INFO::1002 - Got case #1
INFO::1001 - Inserting node: 5
INFO::1006 - CreateNode node: Node{key=5, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=5, left=nil, right=nil, color=RED, parent=4}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 4 from original color: RED
INFO::1005 - Recoloring node: 3 from original color: BLACK
INFO::1003 - LeftRotate node: 3
INFO::1001 - Inserting node: 6
INFO::1006 - CreateNode node: Node{key=6, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=6, left=nil, right=nil, color=RED, parent=5}
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 5 from original color: RED
INFO::1005 - Recoloring node: 3 from original color: RED
INFO::1005 - Recoloring node: 4 from original color: BLACK
INFO::1001 - Inserting node: 7
INFO::1006 - CreateNode node: Node{key=7, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=7, left=nil, right=nil, color=RED, parent=6}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 6 from original color: RED
INFO::1005 - Recoloring node: 5 from original color: BLACK
INFO::1003 - LeftRotate node: 5
INFO::1001 - Inserting node: 8
INFO::1006 - CreateNode node: Node{key=8, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=8, left=nil, right=nil, color=RED, parent=7}
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 7 from original color: RED
INFO::1005 - Recoloring node: 5 from original color: RED
INFO::1005 - Recoloring node: 6 from original color: BLACK
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 4 from original color: RED
INFO::1005 - Recoloring node: 2 from original color: BLACK
INFO::1003 - LeftRotate node: 2
INFO::1001 - Inserting node: 9
INFO::1006 - CreateNode node: Node{key=9, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=9, left=nil, right=nil, color=RED, parent=8}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 8 from original color: RED
INFO::1005 - Recoloring node: 7 from original color: BLACK
INFO::1003 - LeftRotate node: 7
INFO::1001 - Inserting node: 10
INFO::1006 - CreateNode node: Node{key=10, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=10, left=nil, right=nil, color=RED, parent=9}
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 9 from original color: RED
INFO::1005 - Recoloring node: 7 from original color: RED
INFO::1005 - Recoloring node: 8 from original color: BLACK
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 6 from original color: RED
INFO::1005 - Recoloring node: 2 from original color: RED
INFO::1005 - Recoloring node: 4 from original color: BLACK
INFO::1002 - Got case #1
INFO::1001 - Inserting node: 11
INFO::1006 - CreateNode node: Node{key=11, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=11, left=nil, right=nil, color=RED, parent=10}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 10 from original color: RED
INFO::1005 - Recoloring node: 9 from original color: BLACK
INFO::1003 - LeftRotate node: 9
INFO::1001 - Inserting node: 12
INFO::1006 - CreateNode node: Node{key=12, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=12, left=nil, right=nil, color=RED, parent=11}
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 11 from original color: RED
INFO::1005 - Recoloring node: 9 from original color: RED
INFO::1005 - Recoloring node: 10 from original color: BLACK
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 8 from original color: RED
INFO::1005 - Recoloring node: 6 from original color: BLACK
INFO::1003 - LeftRotate node: 6
INFO::1008 - RBTree is valid (black height is: 4)
rbTree.isValid() = true
rbTree.getRoot() = Node{key=4, left=2, right=8, color=BLACK, parent=NULL}
rbTree.find(5) = Node{key=5, left=nil, right=nil, color=BLACK, parent=6}
rbTree.find(100) = null

inOrder:
1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,

BFS:
4, 2, 8, 1, 3, 6, 10, 5, 7, 9, 11, 12,

DFS:
4, 8, 10, 11, 12, 9, 6, 7, 5, 2, 3, 1,
END

 */
