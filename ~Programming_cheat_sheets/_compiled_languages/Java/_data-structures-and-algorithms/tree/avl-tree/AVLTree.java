package com.example;

class Node {
    private int key;
    private int height;
    private Node left;
    private Node right;

    Node(int key) {
        this.key = key;
        height = 1;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
}

class AVLTree {
    private Node root;

    private int getHeight(Node n) {
        if (n == null) return 0;
        return n.getHeight();
    }

    private void setHeight(Node node) {
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
    }

    private int getBalance(Node n) {
        if (n == null) return 0;
        return getHeight(n.getRight()) - getHeight(n.getLeft());
    }

    private Node rightRotate(Node a) {
        Node b = a.getLeft();
        Node c = b.getRight();
        b.setRight(a);
        a.setLeft(c);

        setHeight(a);
        setHeight(b);

        return b;
    }

    private Node leftRotate(Node a) {
        Node b = a.getRight();
        Node c = b.getLeft();
        b.setLeft(a);
        a.setRight(c);

        setHeight(a);
        setHeight(b);

        return b;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return (new Node(key));

        if (key < node.getKey())
            node.setLeft(insert(node.getLeft(), key));
        else if (key > node.getKey())
            node.setRight(insert(node.getRight(), key));
        else
            return node; // no dups

        return insertFixUp(node, key);
    }

    private Node insertFixUp(Node node, int key) {
        setHeight(node);
        int balance = getBalance(node);

        if (balance < -1 && key < node.getLeft().getKey()) {
            return rightRotate(node); // LL
        } else if (balance > 1 && key > node.getRight().getKey()) {
            return leftRotate(node); // RR
        } else if (balance < -1 && key > node.getLeft().getKey()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node); // LR
        } else if (balance > 1 && key < node.getRight().getKey()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node); // RL
        }

        return node;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.getLeft());
        System.out.println(node.getKey());
        inOrder(node.getRight());
    }

    public void print() {
        printRecursive(root, "", true);
    }

    private void printRecursive(Node node, String indent, boolean isLeft) {
        if (node == null) return;
        System.out.print(indent);
        if (isLeft) {
            System.out.print("|---");
            indent += "    ";
        } else {
            System.out.print("|---");
            indent += "|   ";
        }

        String heightLabel = " (H" + node.getHeight() + ")";
        System.out.println(node.getKey() + heightLabel);

        printRecursive(node.getRight(), indent, false);
        printRecursive(node.getLeft(), indent, true);
    }
}

class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }

        tree.print();
        tree.inOrder();
    }
}

/* OUTPUT

|---3 (H4)
    |---7 (H3)
    |   |---8 (H2)
    |   |   |---9 (H1)
    |   |---5 (H2)
    |       |---6 (H1)
    |       |---4 (H1)
    |---1 (H2)
        |---2 (H1)
        |---0 (H1)
0
1
2
3
4
5
6
7
8
9

*/
