package com.example;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

import static com.example.Color.RED;
import static com.example.Color.BLACK;

enum Color {RED, BLACK}

/**
 * <code>
 * TODO:
 * - extract the common code to yet another util (like isRed, leftRotate,...)
 * - on deletion:
 *     - check the case when node is root
 *     - BUG: when node does not have a parent and we call 'isLeftChild(t, n)' we get NPE
 * - check if red/red violation before checking case
 * - add logs to deletion -- with detail
 * - add testing
 *
 * - RBNode and NilNode should implement Node
 * - Check for mirror cases
 * - for verification: https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 *     - unfortunately the website above uses predecessor in deletion,
 *       so the tree might not look the same as this one
 */

class InsertionFixer {
    public static void fix(RedBlackTree t, Node node) {
        System.out.println("INFO::1007 - Starting fix for node: " + node);
        getCaseAndFix(t, node);
    }

    // TODO: check if red/red violation before checking case
    public static void getCaseAndFix(RedBlackTree t, Node node) { // recursive
        int c = getCase(t, node);
        if (c == -1) return; // base case
        System.out.println("INFO::1002 - Got case #" + c);

        if (c == 1) {
            fixCase1(t, node); // Red root (FINAL case)
        } else if (c == 2) {
            fixCase2(t, node); // Red uncle (NOT final case)
            Node g = getGrandParent(t, node);
            getCaseAndFix(t, g); // recurse on G
        } else if (c == 3) {
            fixCase3(t, node); // black uncle - triangle (NOT final case)
            // getCaseAndFix(t,node); -- will be used in the method above
        } else if (c == 4) {
            fixCase4(t, node);  // black uncle - line (FINAL case)
        }
    }

    /**
     * Root is RED
     * Solution: color it BLACK
     */
    public static void fixCase1(RedBlackTree t, Node node) {
        node.setColor(BLACK);
    }

    /**
     * Uncle is RED
     * Solution: recolor P,U,G
     */
    public static void fixCase2(RedBlackTree t, Node node) {
        // TODO: check if we have P,U,G
        recolor(t, getParent(t, node));
        recolor(t, getUncle(t, node));
        recolor(t, getGrandParent(t, node));
    }

    /**
     * Uncle is BLACK (triangle)
     */
    public static void fixCase3(RedBlackTree t, Node node) {
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
    public static void fixCase4(RedBlackTree t, Node node) {
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

    public static void leftRotate(RedBlackTree t, Node a) {
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

    public static void rightRotate(RedBlackTree t, Node a) {
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

    public static boolean isRoot(RedBlackTree t, Node node) {
        return t.getRoot().equals(node);
    }

    public static boolean isRed(RedBlackTree t, Node node) {
        return node.getColor().equals(RED);
    }

    public static boolean isBlack(RedBlackTree t, Node node) {
        return node.getColor().equals(BLACK);
    }

    private static boolean hasGrandParent(RedBlackTree t, Node node) {
        if (hasParent(t, node)) {
            Node p = getParent(t, node);
            return hasParent(t, p);
        }
        return false;
    }

    private static Node getGrandParent(RedBlackTree t, Node node) {
        Node p = getParent(t, node);
        return getParent(t, p);
    }

    public static boolean hasParent(RedBlackTree t, Node node) {
        return node.getParent() != null;
    }

    public static Node getParent(RedBlackTree t, Node node) {
        return node.getParent();
    }

    public static boolean hasUncle(RedBlackTree t, Node node) {
        if (!hasGrandParent(t, node)) return false;
        Node p = getParent(t, node);
        Node g = getParent(t, p);
        if (g != null) return true; // if has G, it has U, guaranteed!
        return false;
    }

    public static Node getUncle(RedBlackTree t, Node node) {
        //if (!hasUncle(t,node)) return null;
        Node p = getParent(t, node);
        Node g = getParent(t, p);

        if (isLeftChild(t, p)) {
            return g.getRight();
        } else {
            return g.getLeft();
        }
    }

    public static void recolor(RedBlackTree t, Node node) {
        System.out.println("INFO::1005 - Recoloring node: " + node.getKey() + " from original color: " + node.getColor());
        if (node.getColor() == RED) {
            node.setColor(BLACK);
        } else {
            node.setColor(RED);
        }
    }

    public static boolean isLeftChild(RedBlackTree t, Node node) {
        return node.getParent().getLeft() == node;
    }

    // TODO: maybe remove this method and use !isLeftChild()
    public static boolean isRightChild(RedBlackTree t, Node node) {
        return node.getParent().getRight() == node;
    }

    // has black uncle
    private static boolean isLineCase(RedBlackTree t, Node node) {
        Node u = getUncle(t, node);
        if (isLeftChild(t, node)) {
            return isRightChild(t, u);
        } else {
            return isLeftChild(t, u);
        }
    }

    // has black uncle
    private static boolean isTriangleCase(RedBlackTree t, Node node) {
        Node u = getUncle(t, node);
        if (isLeftChild(t, node)) {
            return isLeftChild(t, u);
        } else {
            return isRightChild(t, u);
        }
    }

    public static int getCase(RedBlackTree t, Node node) {
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

/**
 * <code>
 * Deleting a node:
 * when deleting a node, we first check how many nil children it has,
 * there are 3 cases:
 *     - node has 2 nil children
 *     - node has 1 nil child
 *     - node has 0 nil children
 * The issue arises when both nodes are black (the node to be deleted and the node that will replace it)
 * because that alters the 'black height' property, in that case, we do the RedBlackTree fixup for deletion
 *
 *
 *
 * RedBlackTree deletion cases:
 *
 * If node has 2 nil children:
 *     - replace node with node.right (nil) --> if node WAS B: FIXUP(x) // x is node.right
 * If node has 1 nil child:
 *     - replace node with non-nil child --> if node WAS B: FIXUP(x) // x is the non-nil child
 * If node has 0 nil children: (using successor case)
 *     - find successor
 *     - swap payload of node and successor
 *     - proceed to delete successor:
 *         - if successor has 2 nil children: replace it with successor.right  --> if successor WAS B: FIXUP(x) // x is successor.right
 *         - if successor has 1 nil child: replace successor with non-nil child
 *             --> if successor WAS B and x was B: FIXUP(x) // x is the non-nil child
 *             --> if successor WAS B and x is RED, color x black // x is successor.right
 */
class DeletionUtil {
    public static void delete(RedBlackTree t, Node node) {
        System.out.println("INFO::1009 - Deleting node: " + node);
        int c = getCase(t, node);

        if (c == 1) {
            fixCase1(t, node);
        } else if (c == 2) {
            fixCase2(t, node);
        } else if (c == 3) {
            fixCase3(t, node);
        }
    }

    // Check how many nil children node has:
    public static int getCase(RedBlackTree t, Node node) {
        if (node.getLeft().isNil()) {
            if (node.getRight().isNil()) {
                return 1; // node has 2 nil children
            } else {
                return 2; // node has 1 nil child (left)
            }
        } else {
            if (node.getRight().isNil()) {
                return 2; // node has 1 nil child (right)
            } else {
                return 3; // node has 0 nil children
            }
        }
    }

    // If node has 2 nil children:
    //     - replace node with node.right (nil) --> if node WAS B: FIXUP(x) // x is node.right
    public static void fixCase1(RedBlackTree t, Node node) {
        if (t.getRoot().equals(node)) {
            t.setRoot(null);
            return; // tree should be totally empty
        }
        Node p = node.getParent();
        Node x = node.getRight();
        Color nodeColor = node.getColor();


        if (isLeftChild(t, node)) {
            p.setLeft(x);
        } else {
            p.setRight(x);
        }
        x.setParent(p);

        if (nodeColor == BLACK) { // nil is already black, so no need to check its color
            DeletionFixer.fix(t, x);
        }
    }

    // If node has 1 nil child:
    //     - replace node with non-nil child --> if node WAS B: FIXUP(x) // x is the non-nil child
    public static void fixCase2(RedBlackTree t, Node node) {
        Node p = node.getParent();
        Color nodeColor = node.getColor();
        Node x;

        if (node.getLeft().isNil()) {
            x = node.getRight();
        } else {
            x = node.getLeft();
        }

        if (t.getRoot().equals(node)) {
            t.setRoot(x);
            x.setParent(p); // null parent
            x.setColor(BLACK);
            return;
        }

        if (isLeftChild(t, node)) {
            p.setLeft(x);
        } else {
            p.setRight(x);
        }
        x.setParent(p);

        if (nodeColor == BLACK && x.getColor() == BLACK) {
            DeletionFixer.fix(t, x);
        } else if (nodeColor == BLACK && x.getColor() == RED) {
            x.setColor(BLACK);
        }
        // There will be no red/red case here (that would be a violation)
        // in red/black case: no need to change anything at this point
    }

    /**
     * <code>
     * If node has 0 nil children: (using successor case)
     *     - find successor
     *     - swap payload of node and successor
     *     - proceed to delete successor:
     *         - if successor has 2 nil children: replace it with successor.right  --> if successor WAS B: FIXUP(x) // x is successor.right
     *         - if successor has 1 nil child: replace successor with non-nil child
     *             --> if successor WAS B AND x was B: FIXUP(x) // x is the non-nil child
     *             --> if successor WAS B AND x is RED, color x black // x is successor.right
     */
    public static void fixCase3(RedBlackTree t, Node node) { // TODO: see if we need these variables
        Node successor = getSuccessor(t, node);

        // copy payload from successor to node
        int key = successor.getKey();
        node.setKey(key);

        int c = getCase(t, successor);

        if (c == 1) {
            fixCase1(t, successor);
        } else if (c == 2) {
            fixCase2(t, successor);
        }
        // Will not have case3 (0 nil children)
    }

    //
    // Helpers
    //

    private static boolean isLeftChild(RedBlackTree t, Node node) {
        return node.getParent().getLeft() == node;
    }

    private static Node getSuccessor(RedBlackTree t, Node node) {
        Node currNode = node.getRight();

        while (!currNode.getLeft().isNil()) {
            currNode = currNode.getLeft();
        }

        return currNode;
    }
}


/**
 * <code>
 * node is the DOUBLE_BLACK
 *
 * cases:
 *
 * case #0:
 *     - node is RED
 * case #1:
 *     - Sibling is RED
 * case #2A:
 *     - PR SB IB OB
 * case #2B:
 *     - all black
 * case #3:
 *     - SB IR OB
 * case #4A:
 *     - PR SB OB
 * case #4B:
 *     - PB SB OR
 */
class DeletionFixer {
    public static void fix(RedBlackTree t, Node node) {
        System.out.println("INFO::1009 - Starting fix for node: " + node);
        getCaseAndFix(t, node);
    }

    public static void getCaseAndFix(RedBlackTree t, Node node) {
        int c = getCase(t, node);

        if (c == 0) {
            fixCase0(t, node);
        } else if (c == 1) {
            fixCase1(t, node);
        } else if (c == 21) {
            fixCase21(t, node);
        } else if (c == 22) {
            fixCase22(t, node);
        } else if (c == 3) {
            fixCase3(t, node);
        } else if (c == 41) {
            fixCase41(t, node);
        } else if (c == 42) {
            fixCase42(t, node);
        }
    }

    public static int getCase(RedBlackTree t, Node node) {

        // If root is red, color black ??? is this that case ???
        // TODO: need to check this
        if (node.equals(t.getRoot())) { // Root
            System.out.println("INFO::5000 - found RBT deletion fixup case 0");
            return 0;
        }

        if (isLeftChild(t, node)) {
            Node p = node.getParent();
            Node s = p.getRight();
            Node leftNephew = s.getLeft();
            Node rightNephew = s.getRight();

            if (node.getColor() == RED) {
                System.out.println("INFO::6000 - found RBT deletion fixup case 0");
                return 0;
            } else if (s.getColor() == RED) {
                System.out.println("INFO::5001 - found RBT deletion fixup case 1");
                return 1;
            } else if (s.getColor() == BLACK && leftNephew.getColor() == BLACK && rightNephew.getColor() == BLACK) {
                if (p.getColor() == RED) {
                    System.out.println("INFO::5021 - found RBT deletion fixup case 21");
                    return 21;
                } else {
                    System.out.println("INFO::5022 - found RBT deletion fixup case 22");
                    return 22;
                }
            } else if (s.getColor() == BLACK && leftNephew.getColor() == RED && rightNephew.getColor() == BLACK) { // correct
                System.out.println("INFO::5003 - found RBT deletion fixup case 3");
                return 3;
            } else if (s.getColor() == BLACK && rightNephew.getColor() == RED) {
                if (p.getColor() == RED) {
                    System.out.println("INFO::5041 - found RBT deletion fixup case 41");
                    return 41;
                } else {
                    System.out.println("INFO::5042 - found RBT deletion fixup case 42");
                    return 42;
                }
            }
        } else {
            Node p = node.getParent();
            Node s = p.getLeft();
            Node leftNephew = s.getLeft();
            Node rightNephew = s.getRight();

            if (node.getColor() == RED) {
                System.out.println("INFO::5000 - found RBT deletion fixup case 0");
                return 0;
            } else if (s.getColor() == RED) {
                System.out.println("INFO::5001 - found RBT deletion fixup case 1");
                return 1;
            } else if (s.getColor() == BLACK && leftNephew.getColor() == BLACK && rightNephew.getColor() == BLACK) {
                if (p.getColor() == RED) {
                    System.out.println("INFO::5021 - found RBT deletion fixup case 21");
                    return 21;
                } else {
                    System.out.println("INFO::5022 - found RBT deletion fixup case 22");
                    return 22;
                }
            } else if (s.getColor() == BLACK && leftNephew.getColor() == BLACK && rightNephew.getColor() == RED) { // correct
                System.out.println("INFO::5003 - found RBT deletion fixup case 3");
                return 3;
            } else if (s.getColor() == BLACK && leftNephew.getColor() == RED) {
                if (p.getColor() == RED) {
                    System.out.println("INFO::5041 - found RBT deletion fixup case 41");
                    return 41;
                } else {
                    System.out.println("INFO::5042 - found RBT deletion fixup case 42");
                    return 42;
                }
            }
        }

        throw new RuntimeException("ERROR::1009 - INVALID CASE");
    }

    private static void fixCase0(RedBlackTree t, Node node) {
        node.setColor(BLACK);
    }

    private static void fixCase1(RedBlackTree t, Node node) {
        if (isLeftChild(t, node)) {
            Node p = node.getParent();
            Node s = p.getRight();
            swapColor(t, p, s);
            leftRotate(t, p);
            getCaseAndFix(t, node); // That is it!
        } else {
            Node p = node.getParent();
            Node s = p.getLeft();
            swapColor(t, p, s);
            rightRotate(t, p);
            getCaseAndFix(t, node); // That is it!
        }
    }

    private static void fixCase21(RedBlackTree t, Node node) {
        if (isLeftChild(t, node)) {
            Node p = node.getParent();
            Node s = p.getRight();
            swapColor(t, p, s); // DONE!!!
        } else {
            Node p = node.getParent();
            Node s = p.getLeft();
            swapColor(t, p, s); // DONE!!!
        }
    }

    private static void fixCase22(RedBlackTree t, Node node) {
        if (isLeftChild(t, node)) {
            Node p = node.getParent();
            Node s = p.getRight();
            s.setColor(RED);
            getCaseAndFix(t, p); // That is it!
        } else {
            Node p = node.getParent();
            Node s = p.getLeft();
            s.setColor(RED);
            getCaseAndFix(t, p); // That is it!
        }
    }

    private static void fixCase3(RedBlackTree t, Node node) {
        if (isLeftChild(t, node)) {
            Node p = node.getParent();
            Node s = p.getRight();
            Node innerNephew = s.getLeft();
            swapColor(t, innerNephew, s);
            rightRotate(t, s);
            getCaseAndFix(t, innerNephew); // That is it!
        } else {
            Node p = node.getParent();
            Node s = p.getLeft();
            Node innerNephew = s.getRight();
            swapColor(t, innerNephew, s);
            leftRotate(t, s);
            getCaseAndFix(t, innerNephew); // That is it!
        }
    }

    private static void fixCase41(RedBlackTree t, Node node) {
        if (isLeftChild(t, node)) {
            Node p = node.getParent();
            Node s = p.getRight();
            Node outerNephew = s.getRight();
            swapColor(t, p, s);
            outerNephew.setColor(BLACK);
            leftRotate(t, p);
            // DONE
        } else {
            Node p = node.getParent();
            Node s = p.getLeft();
            Node outerNephew = s.getLeft();
            swapColor(t, p, s);
            outerNephew.setColor(BLACK);
            rightRotate(t, p);
            // DONE
        }
    }

    private static void fixCase42(RedBlackTree t, Node node) {
        if (isLeftChild(t, node)) {
            Node p = node.getParent();
            Node s = p.getRight();
            Node outerNephew = s.getRight();
            outerNephew.setColor(BLACK);
            leftRotate(t, p);
            // DONE
        } else {
            Node p = node.getParent();
            Node s = p.getLeft();
            Node outerNephew = s.getLeft();
            outerNephew.setColor(BLACK);
            rightRotate(t, p);
            // DONE
        }
    }


    //
    // Helpers
    //

    public static boolean isLeftChild(RedBlackTree t, Node node) {
        return node.getParent().getLeft() == node;
    }

    public static void swapColor(RedBlackTree t, Node n1, Node n2) {
        Color temp = n1.getColor();
        n1.setColor(n2.getColor());
        n2.setColor(temp);
    }

    public static void leftRotate(RedBlackTree t, Node a) {
        System.out.println("INFO::1013 - LeftRotate node: " + a.getKey());
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

    public static void rightRotate(RedBlackTree t, Node a) {
        System.out.println("INFO::1014 - RightRotate node: " + a.getKey());
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

class RedBlackTree {
    private Node root;

    public RedBlackTree() {
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node createNode(int key) {
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

        System.out.println("rbTree.getRoot() = " + getRoot());
        System.out.println("rbTree.isValid() = " + isValid());
    }

    public Node find(int key) {
        System.out.println("INFO::3000 - finding: " + key);
        Node currNode = root;

        while (!currNode.isNil()) {
            if (currNode.getKey() == key) {
                System.out.println("INFO::3001 - FOUND: " + currNode);
                return currNode;
            }
            if (key > currNode.getKey()) {
                currNode = currNode.getRight();
            } else if (key < currNode.getKey()) {
                currNode = currNode.getLeft();
            } else {
                System.out.println("INFO::3002 - DUPLICATE KEY: " + key);
                return null; // No dups
            }
        }

        System.out.println("INFO::3003 - Node not found: " + key);
        return null;
    }

    public void delete(int key) {
        System.out.println("INFO::2000 - deleting: " + key);
        Node node = find(key);
        DeletionUtil.delete(this, node);

        System.out.println("rbTree.getRoot() = " + getRoot());
        System.out.println("rbTree.isValid() = " + isValid());
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
            if (root == null) return true;
            validateBlackRoot();
            noDoubleRed(root);
            int res = validateBlackHeight(root); // DOES NOT HANDLE root == null case
            System.out.println("INFO::1008 - RedBlackTree is valid (black height is: " + res + ")");
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

    // TODO: need to test this code
    private void noDoubleRed(Node node) throws RuntimeException {
        if (node.isNil()) return;

        if (node.getColor() == RED && (node.getLeft().getColor() == RED || node.getRight().getColor() == RED)) {
            throw new RuntimeException("ERROR::4000 - noDoubleRed violation, node: " + node);
        }

        noDoubleRed(node.getLeft());
        noDoubleRed(node.getRight());
    }
}

public class RedBlackTreeExample {
    public static void main(String[] args) {
        RedBlackTree rbTree = new RedBlackTree();

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


            rbTree.delete(1);
            rbTree.delete(2);
            rbTree.delete(3);
            rbTree.delete(4);
            rbTree.delete(5);
            rbTree.delete(6);
            rbTree.delete(7);
            rbTree.delete(8);
            rbTree.delete(9);
            rbTree.delete(10);
            rbTree.delete(11);
            rbTree.delete(12);


//            rbTree.find(5);
//            rbTree.find(100);

//            System.out.println("\ninOrder:");
//            rbTree.inOrder();
//
//            System.out.println("\nBFS:");
//            rbTree.BFS();
//
//            System.out.println("\nDFS:");
//            rbTree.DFS();
//

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
rbTree.getRoot() = Node{key=1, left=nil, right=2, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 2)
rbTree.isValid() = true
INFO::1001 - Inserting node: 3
INFO::1006 - CreateNode node: Node{key=3, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=3, left=nil, right=nil, color=RED, parent=2}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 2 from original color: RED
INFO::1005 - Recoloring node: 1 from original color: BLACK
INFO::1003 - LeftRotate node: 1
rbTree.getRoot() = Node{key=2, left=1, right=3, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 2)
rbTree.isValid() = true
INFO::1001 - Inserting node: 4
INFO::1006 - CreateNode node: Node{key=4, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=4, left=nil, right=nil, color=RED, parent=3}
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 3 from original color: RED
INFO::1005 - Recoloring node: 1 from original color: RED
INFO::1005 - Recoloring node: 2 from original color: BLACK
INFO::1002 - Got case #1
rbTree.getRoot() = Node{key=2, left=1, right=3, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::1001 - Inserting node: 5
INFO::1006 - CreateNode node: Node{key=5, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=5, left=nil, right=nil, color=RED, parent=4}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 4 from original color: RED
INFO::1005 - Recoloring node: 3 from original color: BLACK
INFO::1003 - LeftRotate node: 3
rbTree.getRoot() = Node{key=2, left=1, right=4, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::1001 - Inserting node: 6
INFO::1006 - CreateNode node: Node{key=6, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=6, left=nil, right=nil, color=RED, parent=5}
INFO::1002 - Got case #2
INFO::1005 - Recoloring node: 5 from original color: RED
INFO::1005 - Recoloring node: 3 from original color: RED
INFO::1005 - Recoloring node: 4 from original color: BLACK
rbTree.getRoot() = Node{key=2, left=1, right=4, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::1001 - Inserting node: 7
INFO::1006 - CreateNode node: Node{key=7, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=7, left=nil, right=nil, color=RED, parent=6}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 6 from original color: RED
INFO::1005 - Recoloring node: 5 from original color: BLACK
INFO::1003 - LeftRotate node: 5
rbTree.getRoot() = Node{key=2, left=1, right=4, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
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
rbTree.getRoot() = Node{key=4, left=2, right=6, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::1001 - Inserting node: 9
INFO::1006 - CreateNode node: Node{key=9, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=9, left=nil, right=nil, color=RED, parent=8}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 8 from original color: RED
INFO::1005 - Recoloring node: 7 from original color: BLACK
INFO::1003 - LeftRotate node: 7
rbTree.getRoot() = Node{key=4, left=2, right=6, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
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
rbTree.getRoot() = Node{key=4, left=2, right=6, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 4)
rbTree.isValid() = true
INFO::1001 - Inserting node: 11
INFO::1006 - CreateNode node: Node{key=11, left=nil, right=nil, color=RED, parent=NULL}
INFO::1007 - Starting fix for node: Node{key=11, left=nil, right=nil, color=RED, parent=10}
INFO::1002 - Got case #4
INFO::1005 - Recoloring node: 10 from original color: RED
INFO::1005 - Recoloring node: 9 from original color: BLACK
INFO::1003 - LeftRotate node: 9
rbTree.getRoot() = Node{key=4, left=2, right=6, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 4)
rbTree.isValid() = true
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
rbTree.getRoot() = Node{key=4, left=2, right=8, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 4)
rbTree.isValid() = true
INFO::2000 - deleting: 1
INFO::3000 - finding: 1
INFO::3001 - FOUND: Node{key=1, left=nil, right=nil, color=BLACK, parent=2}
INFO::1009 - Deleting node: Node{key=1, left=nil, right=nil, color=BLACK, parent=2}
INFO::1009 - Starting fix for node: Node{color=BLACK, parent=2}
INFO::5022 - found RBT deletion fixup case 22
INFO::5042 - found RBT deletion fixup case 42
INFO::1013 - LeftRotate node: 4
rbTree.getRoot() = Node{key=8, left=4, right=10, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 4)
rbTree.isValid() = true
INFO::2000 - deleting: 2
INFO::3000 - finding: 2
INFO::3001 - FOUND: Node{key=2, left=nil, right=3, color=BLACK, parent=4}
INFO::1009 - Deleting node: Node{key=2, left=nil, right=3, color=BLACK, parent=4}
rbTree.getRoot() = Node{key=8, left=4, right=10, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 4)
rbTree.isValid() = true
INFO::2000 - deleting: 3
INFO::3000 - finding: 3
INFO::3001 - FOUND: Node{key=3, left=nil, right=nil, color=BLACK, parent=4}
INFO::1009 - Deleting node: Node{key=3, left=nil, right=nil, color=BLACK, parent=4}
INFO::1009 - Starting fix for node: Node{color=BLACK, parent=4}
INFO::5001 - found RBT deletion fixup case 1
INFO::1013 - LeftRotate node: 4
INFO::5021 - found RBT deletion fixup case 21
rbTree.getRoot() = Node{key=8, left=6, right=10, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 4)
rbTree.isValid() = true
INFO::2000 - deleting: 4
INFO::3000 - finding: 4
INFO::3001 - FOUND: Node{key=4, left=nil, right=5, color=BLACK, parent=6}
INFO::1009 - Deleting node: Node{key=4, left=nil, right=5, color=BLACK, parent=6}
rbTree.getRoot() = Node{key=8, left=6, right=10, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 4)
rbTree.isValid() = true
INFO::2000 - deleting: 5
INFO::3000 - finding: 5
INFO::3001 - FOUND: Node{key=5, left=nil, right=nil, color=BLACK, parent=6}
INFO::1009 - Deleting node: Node{key=5, left=nil, right=nil, color=BLACK, parent=6}
INFO::1009 - Starting fix for node: Node{color=BLACK, parent=6}
INFO::5022 - found RBT deletion fixup case 22
INFO::5022 - found RBT deletion fixup case 22
INFO::5000 - found RBT deletion fixup case 0
rbTree.getRoot() = Node{key=8, left=6, right=10, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::2000 - deleting: 6
INFO::3000 - finding: 6
INFO::3001 - FOUND: Node{key=6, left=nil, right=7, color=BLACK, parent=8}
INFO::1009 - Deleting node: Node{key=6, left=nil, right=7, color=BLACK, parent=8}
rbTree.getRoot() = Node{key=8, left=7, right=10, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::2000 - deleting: 7
INFO::3000 - finding: 7
INFO::3001 - FOUND: Node{key=7, left=nil, right=nil, color=BLACK, parent=8}
INFO::1009 - Deleting node: Node{key=7, left=nil, right=nil, color=BLACK, parent=8}
INFO::1009 - Starting fix for node: Node{color=BLACK, parent=8}
INFO::5001 - found RBT deletion fixup case 1
INFO::1013 - LeftRotate node: 8
INFO::5021 - found RBT deletion fixup case 21
rbTree.getRoot() = Node{key=10, left=8, right=11, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::2000 - deleting: 8
INFO::3000 - finding: 8
INFO::3001 - FOUND: Node{key=8, left=nil, right=9, color=BLACK, parent=10}
INFO::1009 - Deleting node: Node{key=8, left=nil, right=9, color=BLACK, parent=10}
rbTree.getRoot() = Node{key=10, left=9, right=11, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::2000 - deleting: 9
INFO::3000 - finding: 9
INFO::3001 - FOUND: Node{key=9, left=nil, right=nil, color=BLACK, parent=10}
INFO::1009 - Deleting node: Node{key=9, left=nil, right=nil, color=BLACK, parent=10}
INFO::1009 - Starting fix for node: Node{color=BLACK, parent=10}
INFO::5042 - found RBT deletion fixup case 42
INFO::1013 - LeftRotate node: 10
rbTree.getRoot() = Node{key=11, left=10, right=12, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 3)
rbTree.isValid() = true
INFO::2000 - deleting: 10
INFO::3000 - finding: 10
INFO::3001 - FOUND: Node{key=10, left=nil, right=nil, color=BLACK, parent=11}
INFO::1009 - Deleting node: Node{key=10, left=nil, right=nil, color=BLACK, parent=11}
INFO::1009 - Starting fix for node: Node{color=BLACK, parent=11}
INFO::5022 - found RBT deletion fixup case 22
INFO::5000 - found RBT deletion fixup case 0
rbTree.getRoot() = Node{key=11, left=nil, right=12, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 2)
rbTree.isValid() = true
INFO::2000 - deleting: 11
INFO::3000 - finding: 11
INFO::3001 - FOUND: Node{key=11, left=nil, right=12, color=BLACK, parent=NULL}
INFO::1009 - Deleting node: Node{key=11, left=nil, right=12, color=BLACK, parent=NULL}
rbTree.getRoot() = Node{key=12, left=nil, right=nil, color=BLACK, parent=NULL}
INFO::1008 - RedBlackTree is valid (black height is: 2)
rbTree.isValid() = true
INFO::2000 - deleting: 12
INFO::3000 - finding: 12
INFO::3001 - FOUND: Node{key=12, left=nil, right=nil, color=BLACK, parent=NULL}
INFO::1009 - Deleting node: Node{key=12, left=nil, right=nil, color=BLACK, parent=NULL}
rbTree.getRoot() = null
rbTree.isValid() = true
END

*/
