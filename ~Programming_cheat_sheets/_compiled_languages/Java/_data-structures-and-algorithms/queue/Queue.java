package com.example;

class Node {
    private int key;
    private Node next;

    public Node(int key) {
        this.key = key;
        next = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class Queue {
    private Node head;
    private Node tail;

    public Queue() {
    }

    public void enqueue(int key) {
        Node node = new Node(key);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        tail.setNext(node);
        tail = node;
    }

    public Node dequeue() throws Exception {
        if (head == null) {
            throw new Exception("INFO::1001 Queue is empty");
        }

        Node node = head;
        head = node.getNext();

        if (head == null) {
            tail = null;
        }
        return node;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int peek() throws Exception {
        if (head == null) {
            throw new Exception("INFO::1002 Queue is empty");
        }

        return head.getKey();
    }

    public void print() {
        Node currNode = head;

        while (currNode != null) {
            System.out.print(currNode.getKey() + " -> ");
            currNode = currNode.getNext();
        }
        System.out.println("");
    }

}

public class QueueExample {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print();

        try {
            queue.dequeue();
            queue.print();

            queue.dequeue();
            queue.print();

            queue.dequeue();
            queue.print();

            // queue.dequeue(); //  Queue is empty
            System.out.println("queue.isEmpty() = " + queue.isEmpty());
            // System.out.println(queue.peek()); //  Queue is empty
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

/* OUTPUT

1 -> 2 -> 3 -> 
2 -> 3 -> 
3 -> 

 */
