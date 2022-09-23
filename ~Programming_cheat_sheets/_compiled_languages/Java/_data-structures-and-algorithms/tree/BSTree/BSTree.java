package com.example;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

class Node {
    private int key;
    private Node left;
    private Node right;

    public Node(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
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

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", left=" + (left == null ? "null" : left.getKey()) +
                ", right=" + (right == null ? "null" : right.getKey()) +
                '}';
    }
}

class BSTree {
    private Node root;

    public BSTree() {
    }

    public void insert(int key) throws Exception {
        Node node = new Node(key);
        if (root == null) {
            root = node;
            return;
        }

        Node currNode = root;
        Node parent = null;

        while (currNode != null) {
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
        } else {
            parent.setLeft(node);
        }
    }

    public Node find(int key) {
        Node currNode = root;

        while (currNode != null) {
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
    }

    public void inOrder() {
        inOrder(root);
        System.out.println("");
    }

    public void inOrder(Node currNode) { // recursive
        if (currNode == null) return;
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
            if (n.getLeft() != null) queue.add(n.getLeft());
            if (n.getRight() != null) queue.add(n.getRight());
        }
        System.out.println("");
    }

    public void DFS() { // stack
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.print(n.getKey() + ", ");
            if (n.getLeft() != null) stack.push(n.getLeft());
            if (n.getRight() != null) stack.push(n.getRight());
        }
        System.out.println("");
    }
}

public class BSTreeExample {
    public static void main(String[] args) {
        BSTree bsTree = new BSTree();

        try {
            bsTree.insert(4);
            bsTree.insert(5);
            bsTree.insert(2);
            bsTree.insert(3);
            bsTree.insert(1);

            System.out.println("bsTree.find(5) = " + bsTree.find(5));
            System.out.println("bsTree.find(100) = " + bsTree.find(100));

            System.out.println("\ninOrder:");
            bsTree.inOrder();

            System.out.println("\nBFS:");
            bsTree.BFS();

            System.out.println("\nDFS:");
            bsTree.DFS();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

/* OUTPUT

bsTree.find(5) = Node{key=5, left=null, right=null}
bsTree.find(100) = null

inOrder:
1, 2, 3, 4, 5,

BFS:
4, 2, 5, 1, 3,

DFS:
4, 5, 2, 3, 1,

*/
