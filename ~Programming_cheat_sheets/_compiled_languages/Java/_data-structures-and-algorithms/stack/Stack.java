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

class Stack {
    private Node top;

    public Stack() {
    }

    public void push(int key) {
        Node node = new Node(key);
        if (top == null) {
            top = node;
            return;
        }

        node.setNext(top);
        top = node;
    }

    public Node pop() throws Exception {
        if (top == null) {
            throw new Exception("INFO::1002 Stack is empty");
        }

        Node node = top;
        top = top.getNext();
        return node;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Node peek() throws Exception {
        if (top == null) {
            throw new Exception("INFO::1001 Stack is empty");
        }
        return top;
    }

    public void print() {
        Node currNode = top;
        while (currNode != null) {
            System.out.print(currNode.getKey() + " -> ");
            currNode = currNode.getNext();
        }
        System.out.println("");
    }

}

public class StackExample {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
        System.out.println("stack.isEmpty() = " + stack.isEmpty());


        try {
            stack.pop();
            stack.print();

            System.out.println("stack.peek() = " + stack.peek().getKey());

            stack.pop();
            stack.print();

            stack.pop();
            stack.print();

            System.out.println("stack.isEmpty() = " + stack.isEmpty());

            //stack.peek(); //  Stack is empty
            //stack.pop(); //  Stack is empty
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
