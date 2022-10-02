// STATUS: I created this myself, after understanding the concept a little bit


/*
==================================================================================
============================== WARNING: NEEDS TESTING ============================
==================================================================================
*/



/*

========== RBT insertion:

What about triangle case? (Left Child or Right Child, or Right Child of Left Child)
    - Rotate the Parent, then proceed to the following cases

If Uncle is Black:
    - Means that the tree is too heavy on the Parent's side,
      so a rotation towards the Uncle is necessary.
      1. Rotate Grandparent towards the Uncle
      2. Recolor original Parent and Grandparent

If Uncle is Red:
    - Means that the tree is heavy on the Parent's side, BUT
      not enough to need a rotation, so
      1. Recolor original Parent, Grandparent and Uncle
      2. Mark current node to Grandparent and check for violations

*/


/*
 RBT

4 scenarios:

Z is root -- color B
z.U is R -- recolor (P, GP, U), and check GP for violations
z.U B (triangle) -- rotate z.P , check child for violations??? in the direction it was rotated ???
z.U B (line) -- rotate  z.GP (to the opposite direction of Z) , and recolor original P and GP


new nodes are RED

violation: consecutive RED nodes

=================================

p = n.p
g = n.p.p
isPR = (p == g.r) ? true : false
u = isPR ? g.l : g.r
isNR = (n == p.r) ? true : false
isGPR = (GP == GP.p.r) ? true : false

if(isPR && isNR) // RR
if(!isPR && !isNR) // LL
if(isPR && !isNR) // RL
if(!isPR && isNR) // LR

if(isPR){
  if(isNR){
    // RR
    leftRotate(GP)
  }else{
    // RL
    rightRotate(P)
    leftRotate(GP);
  }
}else{
  if(isNR){
    // LR
    leftRotate(P)
    rightRotate(GP);
  }else{
    // LL
    rightRotate(GP)
  }
}

=================================
*/


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * NOTES:
 * - ISSUE: not updating parent-child pointers while having double-rotation cases
 * - remember to update the root pointer and set it to black <-------
 * - remember to update parent pointers <-----
 * - update output
 * - rotate only on black uncle?
 * - on red uncle - only recolor?
 * - need to check if I am coloring correctly
 */

enum Color { RED, BLACK }

class Node{
    int key;
    Color color;
    Node parent, left, right;

    Node(int key, Color color){
        this.key = key;
        this.color = color;
    }

    @Override
    public String toString(){
        /**
         * Node{key=1, color=BLACK, parent=3, left=-1, right=2}
         */
        String format = "Node{key=%s, color=%s, parent=%s, left=%s, right=%s}";
        String colorString =  color == Color.RED ? "RED" : "BLACK";
        int parentKey = parent == null ? -1 : parent.key;
        int leftKey = left == null ? -1 : left.key;
        int rightKey = right == null ? -1 : right.key;
        return String.format(
                format,
                key,
                colorString,
                parentKey,
                leftKey,
                rightKey
        );
    }
}

class RBT{
    Node root;
    Node nullNode = new Node(-1, Color.BLACK);

    void insert(int key){
        Node nn = new Node(key, Color.RED);
        nn.left = nullNode;
        nn.right = nullNode;

        if(root == null){
            root = nn;
            nn.color = Color.BLACK;
            return;
        }

        // ...normal BST insertion algorithm here...
        Node c = root;
        Node leaf = null;

        while(c != nullNode){ // c != null WILL NOT WORK, because we still have nullNodes right? :)
            leaf = c;

            if (key > c.key)
                c = c.right;
            else if (key < c.key)
                c = c.left;
            else
                return; // no dups
        }

        if (key > leaf.key)
            leaf.right = nn;
        if (key < leaf.key)
            leaf.left = nn;

        nn.parent = leaf; // attach parent to new node!
        insertFix(nn);
    }

    void insertFix(Node c){
        while(c.parent != null && c.parent.color == Color.RED){ // main source of violation: RED->RED
            Node p = c.parent;
            Node g = c.parent.parent;
            boolean isPR = p == g.right;
            Node u = isPR ? g.left : g.right;
            boolean isNR = c == p.right;
            //boolean isGPR = (g == g.parent.right) ? true : false;
            boolean isUR = u.color == Color.RED;

            /**
             * Uncle is RED (re-color u, p, g)
             */
            if (isUR) {
                g.color = Color.RED;
                p.color = Color.BLACK;
                u.color = Color.BLACK;
                c = g;
                continue; // stops everything and goes back to the top of the while loop
            }

            /**
             * Uncle is BLACK (rotate, re-color p, g)
             */

            g.color = Color.RED;
            p.color = Color.BLACK;

            if(isPR && isNR){ // RR
                leftRotate(g);
                break; // we still need to assign the root?
            }
            if(!isPR && !isNR){ // LL
                rightRotate(g);
                break; // we still need to assign the root?
            }
            if(isPR && !isNR){ // RL
                rightRotate(p); // TODO: check if parent-child pointers are updated
                leftRotate(g);
                break; // we still need to assign the root?
            }
            if(!isPR && isNR){ // LR
                leftRotate(p); // TODO: check if parent-child pointers are updated
                rightRotate(g);
                break; // we still need to assign the root?
            }
        }

        if (c == root){ // while going up the tree, we may encounter the root, so we need to prevent errors like roo.parent or root.parent.parent :)
            root.color = Color.BLACK;
            return;
        }

        if (c.parent != null){
            if (c == c.parent.right && c.parent.left == root){
                // c is right child
                root = c.parent;
                root.color = Color.BLACK;
                return;
            }

            if (c == c.parent.left && c.parent.right == root){
                // c is left child
                root = c.parent;
                root.color = Color.BLACK;
                return;
            }
        }else{
            // c.parent IS NULL, meaning SHOULD BE ROOT, but either its left or right child is set to ROOT
            // lets update to the TRUE ROOT!
            root = c;
            root.color = Color.BLACK;
        }
    }

    Node leftRotate(Node a){
        Node x = a.parent;

        int xStatus = 0;
        if (x != null)
            if (x.right == a) xStatus = 1; // x has right child
            else xStatus = -1; // x has left child

        //DEBUG:
        if (x == null) System.out.println("INFO_002");

        Node b = a.right;
        Node c = b.left;

        b.left = a;
        a.right = c;

        c.parent = a;
        a.parent = b;
        b.parent = x;

        if (x != null)
            if (xStatus == 1) x.right = b;
            else x.left = b; // -1

        return b;
    }

    Node rightRotate(Node a){
        Node x = a.parent;

        int xStatus = 0;
        if (x != null)
            if (x.right == a) xStatus = 1; // x has right child
            else xStatus = -1; // x has left child

        //DEBUG:
        if (x == null) System.out.println("INFO_001");

        Node b = a.left;
        Node c = b.right;

        b.right = a;
        a.left = c;

        c.parent = a;
        a.parent = b;
        b.parent = x;

        if (x != null)
            if (xStatus == 1) x.right = b;
            else x.left = b; // -1

        return b;
    }

    /**
     * if we pass a tree with root == null, we need to handle that
     */
    void inOrder(Node c){
        if (c == nullNode || c == null) return; // no left  // c == null WILL NOT WORK, we are checking for nullNode right? :)
        inOrder(c.left);
        System.out.println(c);
        inOrder(c.right);
    }

    public Node find(int key){
        if (root == null) return null; // not found

        Node currentNode = root;

        while(currentNode != null){
            if (key == currentNode.key)
                return currentNode;
            else if (key > currentNode.key)
                currentNode = currentNode.right;
            else
                currentNode = currentNode.left;
        }
        return null; // none found
    }

    public void delete(int k){
        Node nodeToDelete = find(k);

        if (nodeToDelete.left == nullNode){
            if (nodeToDelete.right == nullNode){
                // no left child, no right
                handleZeroChildren(nodeToDelete);
            }else{
                // no left child, has right
                handleOneChild(nodeToDelete);
            }
        }else{
            if (nodeToDelete.right == nullNode){
                // has left child, no right
                handleOneChild(nodeToDelete);
            }else{
                // has left child, has right
                handleWithTwoChildren(nodeToDelete);
            }
        }
    }

    /**
     * Directly delete Node instance, without a search
     * reason: if we set some other parent node to the same value
     * (in case of deletion) the normal find(int k) method will find the parent, not the leaft node
     */
    public void delete(Node nodeToDelete){
        if (nodeToDelete.left == nullNode){
            if (nodeToDelete.right == nullNode){
                // no left child, no right
                handleZeroChildren(nodeToDelete);
            }else{
                // no left child, has right
                handleOneChild(nodeToDelete);
            }
        }else{
            if (nodeToDelete.right == nullNode){
                // has left child, no right
                handleOneChild(nodeToDelete);
            }else{
                // has left child, has right
                handleWithTwoChildren(nodeToDelete);
            }
        }
    }

    private void handleWithTwoChildren(Node nodeToDelete) {
        Node successor = getMin(nodeToDelete.right);
        nodeToDelete.key = successor.key;

        /**
         * TODO: create a delete method on a Node, not an int,
         * so it does not need to search for it
         */
        delete(successor);
    }

    public Node getMin(Node n){
        Node c = n;
        while(c.left != nullNode){
            c=c.left;
        }
        return c;
    }

    /**
     * TODO: organize LL RR LR RL
     */
    private void handleOneChild(Node nodeToDelete) {
        if(nodeToDelete.left != nullNode){
            // has left
            if (nodeToDelete == root){
                nodeToDelete.left.color = Color.BLACK;
                nodeToDelete.left.parent = nullNode;
            }
            else if(isLeftChild(nodeToDelete)){ // LL
                nodeToDelete.parent.left = nodeToDelete.left;
                nodeToDelete.left.color = Color.BLACK;
                nodeToDelete.left.parent = nodeToDelete.parent;
            }else{ // RL
                nodeToDelete.parent.right = nodeToDelete.left;
                nodeToDelete.left.color = Color.BLACK;
                nodeToDelete.left.parent = nodeToDelete.parent;
            }
            if(root == nodeToDelete) root = nodeToDelete.left;
        }else{
            // has right
            if (nodeToDelete == root){
                nodeToDelete.right.color = Color.BLACK;
                nodeToDelete.right.parent = nullNode;
            }
            else if(isLeftChild(nodeToDelete)){ // LR
                nodeToDelete.parent.left = nodeToDelete.right;
                nodeToDelete.right.color = Color.BLACK;
                nodeToDelete.right.parent = nodeToDelete.parent;
            }else{ // RR
                nodeToDelete.parent.right = nodeToDelete.right;
                nodeToDelete.right.color = Color.BLACK;
                nodeToDelete.right.parent = nodeToDelete.parent;
            }
            if(root == nodeToDelete) root = nodeToDelete.right;
        }
    }

    /**
     * Don't forget to set the parent
     */
    private void handleZeroChildren(Node nodeToDelete) {
        if (nodeToDelete == root){
            root = null;
            return;
        }
        if(nodeToDelete.color == Color.RED){
            if(isLeftChild(nodeToDelete)){
                nodeToDelete.parent.left = nodeToDelete.right;
                nodeToDelete.right.parent = nodeToDelete.parent;
            }else{
                nodeToDelete.parent.right = nodeToDelete.right;
                nodeToDelete.right.parent = nodeToDelete.parent;
            }
        }else{
            if(isLeftChild(nodeToDelete)){
                nodeToDelete.parent.left = nodeToDelete.right;
                nodeToDelete.right.parent = nodeToDelete.parent;
                handleDoubleBlack(nodeToDelete.parent.left);
            }else{
                nodeToDelete.parent.right = nodeToDelete.right;
                nodeToDelete.right.parent = nodeToDelete.parent;
                handleDoubleBlack(nodeToDelete.parent.right);
            }
        }

        if(root == nodeToDelete) root = null;
    }

    private void handleDoubleBlack(Node doubleBlack) {
        Node s = getSibling(doubleBlack);
        if(s.color == Color.RED){
            case1(doubleBlack);
        }else{
            if(isLeftChild(doubleBlack)){
                if (s.right.color == Color.RED){
                    case4(doubleBlack);
                }else{
                    if (s.left.color == Color.RED){
                        case3(doubleBlack);
                    }else{
                        case2(doubleBlack);
                    }
                }
            }else{
                if (s.left.color == Color.RED){
                    case4(doubleBlack);
                }else{
                    if (s.right.color == Color.RED){
                        case3(doubleBlack);
                    }else{
                        case2(doubleBlack);
                    }
                }
            }
        }
    }

    private void case4(Node doubleBlack) {
        Node s = getSibling(doubleBlack);
        if (isLeftChild(doubleBlack)){
            s.color = doubleBlack.parent.color;
            doubleBlack.parent.color = Color.BLACK;
            s.right.color = Color.BLACK;
            leftRotate(doubleBlack.parent);
        }else{
            s.color = doubleBlack.parent.color;
            doubleBlack.parent.color = Color.BLACK;
            s.left.color = Color.BLACK;
            rightRotate(doubleBlack.parent);
        }
        if (doubleBlack.parent == root) root = s;
    }

    private void case3(Node doubleBlack) {
        Node s = getSibling(doubleBlack);
        if (isLeftChild(doubleBlack)){
            s.color = Color.RED;
            s.left.color = Color.BLACK;
            rightRotate(s);
            case4(doubleBlack);
        }else{
            s.color = Color.RED;
            s.right.color = Color.BLACK;
            leftRotate(s);
            case4(doubleBlack);
        }
    }

    private void case2(Node doubleBlack) {
        getSibling(doubleBlack).color = Color.RED;
        doubleBlack.parent.color = Color.BLACK;
    }

    private void case1(Node doubleBlack) {
        Node s = getSibling(doubleBlack);
        if (isLeftChild(doubleBlack)){
            s.color = Color.BLACK;
            doubleBlack.parent.color = Color.RED;
            leftRotate(doubleBlack.parent);
        }else{
            s.color = Color.BLACK;
            doubleBlack.parent.color = Color.RED;
            rightRotate(doubleBlack.parent);
        }
        if (doubleBlack.parent == root) root = s;
        handleDoubleBlack(doubleBlack);
    }

    public Node getSibling(Node n){
        return n.parent.left == n ? n.parent.right : n.parent.left;
    }
    public boolean isLeftChild(Node n){
        return n.parent.left == n;
    }
}

class Main {
    public static void testEqual(Object o1, Object o2, String msg)  throws Exception {
        if (!o1.equals(o2)){
            throw new Exception("ERROR: " + msg);
        }
    }

    public static void main(String[] args) throws Exception {
        RBT t;
        String res, treeState;


        //
        //================================================================== TESTING 1,2,3,4,5,6 case:
        //

        res = "";
        treeState = "";
        t = new RBT();

        //============== insert 1
        t.insert(1);
        res = String.format("%s", t.root);
        treeState = "Node{key=1, color=BLACK, parent=-1, left=-1, right=-1}";
        testEqual(res, treeState, "FAIL");


        //============== insert 2
        t.insert(2);
        res = String.format("%s\n%s\n", t.root, t.root.right);
        treeState =
                "Node{key=1, color=BLACK, parent=-1, left=-1, right=2}\n" +
                        "Node{key=2, color=RED, parent=1, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 3
        t.insert(3);
        res = String.format("%s\n%s\n%s\n", t.root.left, t.root, t.root.right);
        treeState =
                "Node{key=1, color=RED, parent=2, left=-1, right=-1}\n" +
                        "Node{key=2, color=BLACK, parent=-1, left=1, right=3}\n" +
                        "Node{key=3, color=RED, parent=2, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 4
        t.insert(4);
        res = String.format("%s\n%s\n%s\n%s\n", t.root.left, t.root, t.root.right, t.root.right.right);
        treeState =
                "Node{key=1, color=BLACK, parent=2, left=-1, right=-1}\n" +
                        "Node{key=2, color=BLACK, parent=-1, left=1, right=3}\n" +
                        "Node{key=3, color=BLACK, parent=2, left=-1, right=4}\n" +
                        "Node{key=4, color=RED, parent=3, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 5
        t.insert(5);
        res = String.format("%s\n%s\n%s\n%s\n%s\n", t.root.left, t.root, t.root.right, t.root.right.left, t.root.right.right);
        treeState =
                "Node{key=1, color=BLACK, parent=2, left=-1, right=-1}\n" +
                        "Node{key=2, color=BLACK, parent=-1, left=1, right=4}\n" +
                        "Node{key=4, color=BLACK, parent=2, left=3, right=5}\n" +
                        "Node{key=3, color=RED, parent=4, left=-1, right=-1}\n" +
                        "Node{key=5, color=RED, parent=4, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 6
        t.insert(6);
        res = String.format("%s\n%s\n%s\n%s\n%s\n%s\n", t.root.left, t.root, t.root.right, t.root.right.left, t.root.right.right, t.root.right.right.right);
        treeState =
                "Node{key=1, color=BLACK, parent=2, left=-1, right=-1}\n" +
                        "Node{key=2, color=BLACK, parent=-1, left=1, right=4}\n" +
                        "Node{key=4, color=RED, parent=2, left=3, right=5}\n" +
                        "Node{key=3, color=BLACK, parent=4, left=-1, right=-1}\n" +
                        "Node{key=5, color=BLACK, parent=4, left=-1, right=6}\n" +
                        "Node{key=6, color=RED, parent=5, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");






        // adding extra random items:
        Integer[] intArray = { 7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.forEach(t::insert);

        //t.inOrder(t.root);

        //
        //================================================================== TESTING RL case:
        //

        //String res, treeState;
        res = "";
        treeState = "";
        t = new RBT();

        //============== insert 10
        t.insert(10);
        res = String.format("%s", t.root);
        treeState = "Node{key=10, color=BLACK, parent=-1, left=-1, right=-1}";
        testEqual(res, treeState, "...");


        //============== insert 20
        t.insert(20);
        res = String.format("%s\n%s\n", t.root, t.root.right);
        treeState =
                "Node{key=10, color=BLACK, parent=-1, left=-1, right=20}\n" +
                        "Node{key=20, color=RED, parent=10, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 15
        t.insert(15);
        res = String.format("%s\n%s\n%s\n", t.root.left, t.root, t.root.right);
        treeState =
                "Node{key=10, color=RED, parent=15, left=-1, right=-1}\n" +
                        "Node{key=15, color=BLACK, parent=-1, left=10, right=20}\n" +
                        "Node{key=20, color=BLACK, parent=15, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");



        //
        //================================================================== TESTING LR case:
        //

        res = "";
        treeState = "";
        t = new RBT();

        //============== insert 20
        t.insert(20);
        res = String.format("%s", t.root);
        treeState = "Node{key=20, color=BLACK, parent=-1, left=-1, right=-1}";
        testEqual(res, treeState, "...");


        //============== insert 10
        t.insert(10);
        res = String.format("%s\n%s\n", t.root.left, t.root);
        treeState =
                "Node{key=10, color=RED, parent=20, left=-1, right=-1}\n" +
                        "Node{key=20, color=BLACK, parent=-1, left=10, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 5
        t.insert(5);
        res = String.format("%s\n%s\n%s\n", t.root.left, t.root, t.root.right);
        treeState =
                "Node{key=5, color=RED, parent=10, left=-1, right=-1}\n" +
                        "Node{key=10, color=BLACK, parent=-1, left=5, right=20}\n" +
                        "Node{key=20, color=RED, parent=10, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //
        //================================================================== TESTING RR case:
        //

        res = "";
        treeState = "";
        t = new RBT();

        //============== insert 1
        t.insert(1);
        res = String.format("%s", t.root);
        treeState = "Node{key=1, color=BLACK, parent=-1, left=-1, right=-1}";
        testEqual(res, treeState, "...");


        //============== insert 2
        t.insert(2);
        res = String.format("%s\n%s\n", t.root, t.root.right);
        treeState =
                "Node{key=1, color=BLACK, parent=-1, left=-1, right=2}\n" +
                        "Node{key=2, color=RED, parent=1, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 3
        t.insert(3);
        res = String.format("%s\n%s\n%s\n", t.root.left, t.root, t.root.right);
        treeState =
                "Node{key=1, color=RED, parent=2, left=-1, right=-1}\n" +
                        "Node{key=2, color=BLACK, parent=-1, left=1, right=3}\n" +
                        "Node{key=3, color=RED, parent=2, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");

        //
        //================================================================== TESTING LL case:
        //

        res = "";
        treeState = "";
        t = new RBT();

        //============== insert 3
        t.insert(3);
        res = String.format("%s", t.root);
        treeState = "Node{key=3, color=BLACK, parent=-1, left=-1, right=-1}";
        testEqual(res, treeState, "...");


        //============== insert 2
        t.insert(2);
        res = String.format("%s\n%s\n", t.root.left, t.root);
        treeState =
                "Node{key=2, color=RED, parent=3, left=-1, right=-1}\n" +
                        "Node{key=3, color=BLACK, parent=-1, left=2, right=-1}\n";
        testEqual(res, treeState, "...");


        //============== insert 1
        t.insert(1);
        res = String.format("%s\n%s\n%s\n", t.root.left, t.root, t.root.right);
        treeState =
                "Node{key=1, color=RED, parent=2, left=-1, right=-1}\n" +
                        "Node{key=2, color=BLACK, parent=-1, left=1, right=3}\n" +
                        "Node{key=3, color=RED, parent=2, left=-1, right=-1}\n";
        testEqual(res, treeState, "...");


        //
        //================================================================== Deletion
        //

        for (int i=1; i<10; i++){
            t.insert(i);
        }

        for (int i=1; i<10; i++){
            System.out.println("================= Delete " + i);
            t.delete(i);
            t.inOrder(t.root);
        }

        //
        //==================================================================
        //
    }
}

/* OUTPUT:

================= Delete 1
Node{key=2, color=BLACK, parent=4, left=-1, right=3}
Node{key=3, color=RED, parent=2, left=-1, right=-1}
Node{key=4, color=BLACK, parent=-1, left=2, right=6}
Node{key=5, color=BLACK, parent=6, left=-1, right=-1}
Node{key=6, color=RED, parent=4, left=5, right=8}
Node{key=7, color=RED, parent=8, left=-1, right=-1}
Node{key=8, color=BLACK, parent=6, left=7, right=9}
Node{key=9, color=RED, parent=8, left=-1, right=-1}
================= Delete 2
Node{key=3, color=BLACK, parent=4, left=-1, right=-1}
Node{key=4, color=BLACK, parent=-1, left=3, right=6}
Node{key=5, color=BLACK, parent=6, left=-1, right=-1}
Node{key=6, color=RED, parent=4, left=5, right=8}
Node{key=7, color=RED, parent=8, left=-1, right=-1}
Node{key=8, color=BLACK, parent=6, left=7, right=9}
Node{key=9, color=RED, parent=8, left=-1, right=-1}
================= Delete 3
INFO_002
Node{key=4, color=BLACK, parent=6, left=-1, right=5}
Node{key=5, color=RED, parent=4, left=-1, right=-1}
Node{key=6, color=BLACK, parent=-1, left=4, right=8}
Node{key=7, color=RED, parent=8, left=-1, right=-1}
Node{key=8, color=BLACK, parent=6, left=7, right=9}
Node{key=9, color=RED, parent=8, left=-1, right=-1}
================= Delete 4
Node{key=5, color=BLACK, parent=6, left=-1, right=-1}
Node{key=6, color=BLACK, parent=-1, left=5, right=8}
Node{key=7, color=RED, parent=8, left=-1, right=-1}
Node{key=8, color=BLACK, parent=6, left=7, right=9}
Node{key=9, color=RED, parent=8, left=-1, right=-1}
================= Delete 5
INFO_002
Node{key=6, color=BLACK, parent=8, left=-1, right=7}
Node{key=7, color=RED, parent=6, left=-1, right=-1}
Node{key=8, color=BLACK, parent=-1, left=6, right=9}
Node{key=9, color=BLACK, parent=8, left=-1, right=-1}
================= Delete 6
Node{key=7, color=BLACK, parent=8, left=-1, right=-1}
Node{key=8, color=BLACK, parent=-1, left=7, right=9}
Node{key=9, color=BLACK, parent=8, left=-1, right=-1}
================= Delete 7
Node{key=8, color=BLACK, parent=-1, left=-1, right=9}
Node{key=9, color=RED, parent=8, left=-1, right=-1}
================= Delete 8
Node{key=9, color=BLACK, parent=-1, left=-1, right=-1}
================= Delete 9

*/



/* OUTPUT:  (incorrect, needs to be updated...)

                   3B
              /         \
           1B             6B
         /    \         /     \
               2R      4B        8B
              /  \    / \      /   \
                        5R    7R     9R
                        / \  / \    / \


Node{key=1, color=BLACK, parent=3, left=-1, right=2}
Node{key=2, color=RED, parent=1, left=-1, right=-1}
Node{key=3, color=BLACK, parent=-1, left=1, right=6}
Node{key=4, color=BLACK, parent=6, left=-1, right=5}
Node{key=5, color=RED, parent=4, left=-1, right=-1}
Node{key=6, color=BLACK, parent=3, left=4, right=8}
Node{key=7, color=RED, parent=8, left=-1, right=-1}
Node{key=8, color=BLACK, parent=6, left=7, right=9}
Node{key=9, color=RED, parent=8, left=-1, right=-1}

*/
