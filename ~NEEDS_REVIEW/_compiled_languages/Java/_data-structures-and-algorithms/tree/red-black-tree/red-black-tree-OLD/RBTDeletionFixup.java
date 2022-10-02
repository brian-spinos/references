/**
 *
 * ATTENTION: this class is not tested yet...
 *
 */
class RBTDeletionFixup {
    private static Node nullNode = new Node(-1, Color.BLACK);

    public static void deleteSuccessorNode(Node node) {
        if (isRoot(node)) {
            // TODO: handle this
        } else if (hasZeroChildren(node)) {
            handleZeroChildren(node);
        } else if (isBothBlackCase(node)) {
            fixDoubleBlack(node);
        } else {
            fixOneRedCase(node);
        }
    }

    private static boolean isRoot(Node node) {
        return node.parent == null; // TODO: test this
    }

    private static void handleZeroChildren(Node node) {
        // TODO: fix
    }

    private static boolean hasZeroChildren(Node node) {
        return (node.left == nullNode && node.right == nullNode);
    }

    /**
     * We assume that the node has only 1 child
     */
    public static boolean isBothBlackCase(Node node) {
        if (hasLeftChild(node)) {
            return node.left.color == Color.BLACK;
        } else {
            return node.right.color == Color.BLACK;
        }
    }

    // TODO: figure out which direction the child will be...
    public static void fixOneRedCase(Node node) {
        if (isLeftChild(node)){
            node.parent.left = node.left;
            node.left.parent = node.parent;
            node.left.color = Color.BLACK;
        }else{
            node.parent.right = node.right;
            node.right.parent = node.parent;
            node.right.color = Color.BLACK;
        }
    }


    /**
     * We assume node has only 1 child
     */
    public static void fixDoubleBlack(Node node) {
        if (isLeftChild(node)){
            if (hasLeftChild(node)){
                node.parent.left = node.left;
                node.left.parent = node.parent;
                node.left.color = Color.DOUBLE_BLACK;
            }else{
                node.parent.left = node.right;
                node.right.parent = node.parent;
                node.right.color = Color.DOUBLE_BLACK;
            }
        }else{
            if (hasLeftChild(node)){
                node.parent.right = node.left;
                node.left.parent = node.parent;
                node.left.color = Color.DOUBLE_BLACK;
            }else{
                node.parent.right = node.right;
                node.right.parent = node.parent;
                node.right.color = Color.DOUBLE_BLACK;
            }
        }

        Node sibling = getSibling(node);

        if (sibling.color == Color.RED) {
            doubleBlackCase4(node);
        } else {
            if (getInnerNephew(node).color == Color.BLACK && getOuterNephew(node).color == Color.BLACK) {
                doubleBlackCase1(node);
            } else if (getInnerNephew(node).color == Color.RED) {
                doubleBlackCase2(node);
            } else {
                doubleBlackCase3(node);
            }
        }
    }

    public static boolean isLeftChild(Node node) {
        return node.parent.left == node;
    }

    public static boolean hasLeftChild(Node node) {
        return node.left != nullNode;
    }

    public static boolean isRed(Node node) {
        return node.color == Color.RED;
    }

    // TODO: test for nulls, we might be safe if it is NOT the root,
    //  which we take care of in the beginning
    public static Node getSibling(Node node) {
        Node parent = node.parent;
        if (isLeftChild(node)) {
            return parent.right;
        } else {
            return parent.left;
        }
    }

    public static Node getInnerNephew(Node node) {
        Node parent = node.parent;
        if (isLeftChild(node)) {
            return parent.right.left;
        } else {
            return parent.left.right;
        }
    }

    public static Node getOuterNephew(Node node) {
        Node parent = node.parent;
        if (isLeftChild(node)) {
            return parent.right.right;
        } else {
            return parent.left.left;
        }
    }

    public static Node leftRotate(Node a) {
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

    public static Node rightRotate(Node a) {
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

    public static void exchangeColors(Node node1, Node node2) {
        Color temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    /**
     * sibling is black and it has both black children
     */
    public static void doubleBlackCase1(Node node) {
        Node parent = node.parent;
        Node sibling = getSibling(node); // could be left or right

        if (isRed(parent)) {
            parent.color = Color.BLACK;
            sibling.color = Color.RED;
        } else {
            parent.color = Color.DOUBLE_BLACK;
            sibling.color = Color.RED;
        }
    }

    /**
     * sibling is black, and it's inner child is red
     */
    public static void doubleBlackCase2(Node node) {
        Node sibling = getSibling(node); // could be left or right

        if (isLeftChild(node)) {
            sibling.color = Color.RED;
            getInnerNephew(node).color = Color.BLACK;
            // rotate sibling opposite to child
            rightRotate(sibling);
            doubleBlackCase3(node);
        } else {
            sibling.color = Color.RED;
            getInnerNephew(node).color = Color.BLACK;
            // rotate sibling opposite to child
            leftRotate(sibling);
            doubleBlackCase3(node);
        }
    }

    /**
     * sibling is black, and it's outer child is red
     *
     *         P
     *      /    \
     *    N       S
     *          /   \
     *      I.N.     O.N.
     *
     */
    public static void doubleBlackCase3(Node node) {
        Node parent = node.parent;
        Node sibling = getSibling(node); // could be left or right

        if (isLeftChild(node)) {
            getOuterNephew(node).color = Color.BLACK;
            exchangeColors(parent, sibling);
            leftRotate(parent);
            // TODO: remove double black from node
            node.color = Color.BLACK;
        } else {
            getOuterNephew(node).color = Color.BLACK;
            exchangeColors(parent, sibling);
            rightRotate(parent);
            // TODO: remove double black from node
            node.color = Color.BLACK;
        }
    }

    /**
     * sibling is red
     *
     *
     *         P
     *      /    \
     *    N       S
     *          /   \
     *      I.N.     O.N.
     *
     *
     */
    public static void doubleBlackCase4(Node node) {
        Node parent = node.parent;
        Node sibling = getSibling(node); // could be left or right

        if (isLeftChild(node)) {
            exchangeColors(parent, sibling);
            leftRotate(parent);
            // TODO: go to case1 (with which node?) -- fix this?
            node = node.parent;
            node.color = Color.DOUBLE_BLACK;
            node.left = nullNode;
            doubleBlackCase1(node);
        } else {
            exchangeColors(parent, sibling);
            rightRotate(parent);
            // TODO: go to case1 (with which node?) -- fix this?
            node = node.parent;
            node.color = Color.DOUBLE_BLACK;
            node.right = nullNode;
            doubleBlackCase1(node);
        }
    }
}
