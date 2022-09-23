import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

class Node {
    int key;
    Node left, right;

    public Node(int key){
        this.key = key;
    }

    @Override
    public String toString(){
        return "k:" + key;
    }
}

class Tree{
    Node root;

    public void insert(int key){
        Node newNode = new Node(key);
        if (root == null){
            root = newNode;
            return;
        }

        Node currentNode = root;
        Node leaf = null;

        while(currentNode != null){
            leaf = currentNode;

            if (key > currentNode.key)
                currentNode = currentNode.right;
            else if (key < currentNode.key)
                currentNode = currentNode.left;
            else
                return; // no dups
        }

        if (key > leaf.key)
            leaf.right = newNode;
        if (key < leaf.key)
            leaf.left = newNode;
    }

    public Node insert(Node currentNode, int key){
        Node newNode = new Node(key);

        if (root == null){
            return newNode;
        }

        if (key > currentNode.key)
            currentNode.right = insert(currentNode.right, key);
        else if (key < currentNode.key)
            currentNode.left = insert(currentNode.left, key);
        else
            return currentNode;

        //...

        return currentNode;
    }

    public Node BFS(int key){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if (node.key == key) return node;
            if (node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return null; // none found
    }

    public Node DFS(int key){
        Stack<Node> stack = new Stack<>();

        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            if (node.key == key) return node;
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return null; // none found
    }

    private Node leftRotate(Node a){
        Node b = a.right;
        Node c = b.left;

        b.left = a;
        a.right = c;

        return b;
    }

    private Node rightRotate(Node a){
        Node b = a.left;
        Node c = b.right;

        b.right = a;
        a.left = c;

        return b;
    }

    public void inOrder(Node currentNode){
        if (currentNode == null) return; // no left
        inOrder(currentNode.left);
        System.out.println(currentNode);
        inOrder(currentNode.right);
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

    /**
     * TODO: Need to check this one!
     */
    public Node find(Node currentNode, int key){
        if (currentNode == null) return null; // not found
        if (key == currentNode.key) return currentNode; // base case

        if (key > currentNode.key)
            return find(currentNode.right, key);
        else
            return find(currentNode.left, key);
    }

    public void delete(int key){
        //...
    }

    public Node delete(Node currentNode, int key){
        if (root == null) return null;

        else if(key < currentNode.key) currentNode.left = delete(currentNode.left, key);
        else if(key > currentNode.key) currentNode.right = delete(currentNode.right, key);
        else{
            if (currentNode.left == null && currentNode.right == null){
                // case 1: no children
                currentNode = null;
            }
            else if (currentNode.left == null){
                // case 2: one child
                currentNode = currentNode.right;

            }
            else if (currentNode.right == null){
                // case 2: one child
                currentNode = currentNode.left;
            }
            else{
                // case 3: 2 children
                Node temp = findMin(currentNode.right);
                currentNode.key = temp.key;
                currentNode.right = delete(currentNode.right, key);
            }
        }
        return currentNode;
    }

    private Node findMin(Node currentNode) {
        while(currentNode.left != null){
            currentNode = currentNode.right;
        }
        return currentNode;
    }
}

public class AvlTreeExample {
    public static void main(String[] args) {
        Tree t = new Tree();
        t.insert(8);
        t.insert(6);
        t.insert(2);
        t.insert(5);
        t.insert(7);
        t.insert(1);
        t.insert(3);
        t.insert(9);
        t.insert(10);
        t.insert(4);

        t.inOrder(t.root);

        System.out.println("found: " + t.find(t.root, 5));
        System.out.println("found: " + t.find(5));

        System.out.println("found: " + t.find(t.root, 50));
        System.out.println("found: " + t.find(50));

        System.out.println("========== Deletion");
        t.root = t.delete(t.root, 1);
        t.root = t.delete(t.root, 2);
        t.root = t.delete(t.root, 3);
        t.root = t.delete(t.root, 4);

        t.inOrder(t.root);
    }
}

/*
========== OUTPUT:
k:1
k:2
k:3
k:4
k:5
k:6
k:7
k:8
k:9
k:10
found: k:5
found: k:5
found: null
found: null
========== Deletion
k:5
k:6
k:7
k:8
k:9
k:10
*/
