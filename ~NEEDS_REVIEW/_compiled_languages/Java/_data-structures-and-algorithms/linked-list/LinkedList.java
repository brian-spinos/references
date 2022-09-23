/**
 * Singly-linked-list
 */
class Node {
    int key;
    String value;
    Node next;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}


class LinkedList {
    Node root;
    Node tail;

    public void add(int key, String value) {
        if (root == null) {
            root = new Node(key, value);
            tail = root;
            return;
        }

        tail.setNext(new Node(key, value));
        tail = tail.getNext();
    }

    public void print() {
        Node currentNode = root;

        while (currentNode != null) {
            System.out.println("key: " + currentNode.getKey() + ", value: " + currentNode.getValue());
            currentNode = currentNode.getNext();
        }
    }
}

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        linkedList.add(1, "brian");
        linkedList.add(2, "ana");
        linkedList.add(3, "erich");
        linkedList.add(4, "bia");
        linkedList.add(5, "sandra");
        linkedList.add(6, "rick");

        linkedList.print();
    }
}


/* OUTPUT

key: 1, value: brian
key: 2, value: ana
key: 3, value: erich
key: 4, value: bia
key: 5, value: sandra
key: 6, value: rick

*/
