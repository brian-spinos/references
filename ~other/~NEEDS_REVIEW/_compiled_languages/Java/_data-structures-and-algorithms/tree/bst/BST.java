// status: uso only the deletion code from here, not sure about the rest


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
    Node r;

    public void insert(int key){
        Node newNode = new Node(key);
        if (r == null){
            r = newNode;
            return;
        }

        Node currentNode = r;
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

        if (currentNode == null){
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
        queue.offer(r);
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

        stack.push(r);
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
        if (r == null) return null; // not found

        Node currentNode = r;

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

    /**
     * Gotcha: we needs the parents of the node to deleted and the parent of the substitute
     */
    void delete(int k){
        if (r==null) return;

        Node c1 = r;
        Node p1 = null;

        while(c1!=null){
            if (c1.key == k) break;
            p1 = c1; // if not, set to parent
            if (k>c1.key) {
                c1 = c1.right;
            }else {
                c1 = c1.left;
            }
        }
        if (c1==null) return; // not found

        assert p1 != null; // could be root? TODO: could be root?

        if (c1.left == null && c1.right == null){    // has NONE
            if (c1==p1.right) p1.right = null;
            else p1.left = null;
        }else if (c1.left == null){                  // has R
            if (c1==p1.right) p1.right = c1.right;
            else p1.left = c1.right;
        }else if (c1.right == null){                 // has L
            if (c1==p1.right) p1.right = c1.left;
            else p1.left = c1.left;
        }else{                                       // has R L
            Node p2 = getSuccessorParent(c1);
            Node c2 = p2.left == null ? p2 : p2.left;
            c1.key = c2.key;

            if (c2.right == null){ // c2 does NOT have a left child at this point!
                p2.left = null;
            }else{
                p2.left = c2.right;
            }
        }
    }

    /**
     * case 0: no children
     * case 1: one child
     * case 2: 2 children <--------
     */
    Node getSuccessorParent(Node n){
        if(n.right.left == null) return n; // the node to delete could be the parent of the successor!
        Node c = n.right; // it is garanteed to have a right node!
        Node p2 = c;
        while(c.left !=null){
            p2=c;
            c=c.left;
        }
        return p2;
    }

    void printPathAndBack(Node c, int k){
        if (c==null) return;

        System.out.println(c); // down

        if (k>c.key)
            printPathAndBack(c.right, k);
        else
            printPathAndBack(c.left, k);

        System.out.println(c); // up
    }

   //===============

    /**
     * currentNode is just a starting point, to continue and find another node!
     *
     * the RETURN -> what will it bring to the parent node!
     *
     * t.r = delete(t.r, 150);
     */
    public Node delete(Node c, int key){
        if (c == null) return null; // base case  (stop when the "starting point" (Node c) is null)

        /**
         * Recursion (3 branches, 3 possibilities, 3 directions)
         */
        else if(key > c.key)
            c.right = delete(c.right, key);
        else if(key < c.key)
            c.left = delete(c.left, key);
        else{

            // At this point: c == node to be deleted!

            // case 1: no children
            if (c.left == null && c.right == null) return null;

            // case 2: one child
            else if (c.left == null) return c.right;

            // case 2: one child
            else if (c.right == null) return c.left;

            else{
                // case 3: 2 children
                Node min = findMin(c.right);
                c.key = min.key;
                c.right = delete(c.right, min.key); // now we delete min!
                return c;
            }
        }
        return c;
    }

    private Node findMin(Node currentNode) {
        while(currentNode.left != null){
            currentNode = currentNode.right;
        }
        return currentNode;
    }


}
