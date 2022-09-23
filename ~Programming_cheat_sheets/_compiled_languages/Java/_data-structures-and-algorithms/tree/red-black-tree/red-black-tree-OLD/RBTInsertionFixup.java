// TODO: needs testing

class RBTInsertionFixup {
    public static void insertFixup(Node node) {
        if (isRoot(node)) {
            node.color = Color.BLACK;
            return;
        }

        Node uncle = getUncle(node);

        if (isRed(uncle)) {
            handleRedUncleCase(node);
        } else {
            if (isLineCase(node)) {
                handleLineCase(node);
            } else {
                handleTriangleCase(node);
            }
        }
    }

    private static void handleRedUncleCase(Node node) {
        Node uncle = getUncle(node);
        Node parent = node.parent;
        Node grandParent = parent.parent;

        reColor(parent);
        reColor(grandParent);
        reColor(uncle);
        insertFixup(grandParent); // recursive call
    }

    private static boolean isRoot(Node node) {
        return node.parent == null; // TODO: test this
    }

    private static boolean isLeftChild(Node node) {
        return node.parent.left == node;
    }

    // TODO: watch for null
    private static Node getUncle(Node node) {
        Node parent = node.parent;
        if (isLeftChild(parent)) {
            return parent.parent.right;
        } else {
            return parent.parent.left;
        }
    }

    private static boolean isRed(Node node) {
        return node.color == Color.RED;
    }

    private static void reColor(Node node) {
        if (isRed(node)) {
            node.color = Color.BLACK;
        } else {
            node.color = Color.RED;
        }
    }

    private static boolean isLineCase(Node node) {
        if (isLeftChild(node)) {
            return isLeftChild(node.parent);
        } else {
            return !isLeftChild(node.parent);
        }
    }

    private static void handleTriangleCase(Node node) {
        Node parent = node.parent;
        if (isLeftChild(node)) {
            rightRotate(parent);
            handleLineCase(parent);
        } else {
            leftRotate(parent);
            handleLineCase(parent);
        }
    }

    private static void handleLineCase(Node node) {
        Node parent = node.parent;
        Node grandParent = parent.parent;

        if (isLeftChild(node)) {
            rightRotate(grandParent);
        } else {
            leftRotate(grandParent);
        }

        reColor(parent);
        reColor(grandParent);
        insertFixup(parent); // recursive call
    }

    private static Node leftRotate(Node a) {
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

    private static Node rightRotate(Node a) {
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
}
