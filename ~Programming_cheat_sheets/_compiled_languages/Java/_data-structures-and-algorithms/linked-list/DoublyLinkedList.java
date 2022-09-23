package com.example;

class Node {
    private int key;
    private Node prev;
    private Node next;

    public Node(int key) {
        this.key = key;
        this.prev = null;
        this.next = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
    }

    public void addToBack(int key) {
        Node node = new Node(key);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        tail.setNext(node);
        node.setPrev(tail);
        tail = node;
    }

    public void addToFront(int key) {
        Node node = new Node(key);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        head.setPrev(node);
        node.setNext(head);
        head = node;
    }

    public Node removeFromFront() throws Exception {
        if (head == null) {
            throw new Exception("LL is empty");
        }

        Node node = head;

        if (node.getNext() == null) { // 1 node in LL
            head = null;
            tail = null;
            return node;
        }

        head = node.getNext();
        head.setPrev(null);
        return node;
    }

    public Node removeFromBack() throws Exception {
        if (head == null) {
            throw new Exception("LL is empty");
        }

        Node node = tail;

        if (node.getPrev() == null) { // 1 node in LL
            head = null;
            tail = null;
            return node;
        }

        tail = node.getPrev();
        tail.setNext(null);
        return node;
    }

    public void print() {
        Node currNode = head;

        while (currNode != null) {
            System.out.print(currNode.getKey() + " <-> ");
            currNode = currNode.getNext();
        }
        System.out.println("");
    }

    public void print2() { // backwards
        Node currNode = tail;

        while (currNode != null) {
            System.out.print(currNode.getKey() + " <-> ");
            currNode = currNode.getPrev();
        }
        System.out.println("");
    }

}

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        linkedList.addToBack(4);
        linkedList.addToBack(5);
        linkedList.addToBack(6);

        linkedList.addToFront(3);
        linkedList.addToFront(2);
        linkedList.addToFront(1);

        linkedList.print();
        linkedList.print2();
    }
}

/* OUTPUT

1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6 <->
6 <-> 5 <-> 4 <-> 3 <-> 2 <-> 1 <->

 */
