import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int key;

    Node left, right;

    Node(int key){
        this.key = key;
    }

    @Override
    public String toString(){
        return "K: " + key;
    }
}

class Tree{
    Node root;

    void insert(int key){
        Node newNode = new Node(key);

        if (root == null){
            root = newNode;
            return;
        }

        Node currentNode = root;
        Node leaf = null;

        while(currentNode !=null){
            leaf = currentNode;

            if (key > currentNode.key){
                currentNode = currentNode.right;
            }
            else if (key < currentNode.key){
                currentNode = currentNode.left;
            }
            else {
                return; // no dups
            }
        }

        if (key > leaf.key){
            leaf.right = newNode;
        }
        else if (key < leaf.key){
            leaf.left = newNode;
        }
    }

    Node insert(Node currentNode, int key){
        Node newNode = new Node(key);

        if (root == null)
            return newNode;

        if (key > currentNode.key)
            currentNode.right = insert(currentNode.right, key);
        else if (key < currentNode.key)
            currentNode.left = insert(currentNode.left, key);
        else
            return currentNode; // no dups

        return currentNode;
    }

    Node leftRotate(Node a){
        Node b = a.right;
        Node c = b.left;

        b.left = a;
        a.right = c;

        return b;
    }

    Node rightRotate(Node a){
        Node b = a.left;
        Node c = b.right;

        b.right = a;
        a.left = c;

        return b;
    }

    Node DFS(int key){
        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();

            if (node.key == key) return node;
            if (node.left != null) stack.push(node.left);
            if (node.right!= null) stack.push(node.right);
        }

        return null;
    }

    Node BFS(int key){
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()){
            Node node = queue.remove();

            if (node.key == key) return node;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        return null;
    }

    void inOrder(Node currentNode){
        if (currentNode == null) return;
        inOrder(currentNode.left);
        System.out.println(currentNode);
        inOrder(currentNode.right);
    }
}




class Main{
    public static void main(String[] args){
        System.out.println("aaa");

        Tree tree = new Tree();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        tree.inOrder(tree.root);
    }
}
