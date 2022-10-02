package com.example.foo;

import java.util.*;

class Node {
    private int key;
    private int weight;
    private Node next;

    public Node(int key, int weight) {
        this.key = key;
        this.weight = weight;
    }

    public int getKey() {
        return key;
    }

    public int getWeight() {
        return weight;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        next = node;
    }
}

class Graph {
    private Map<Integer, Node> map;

    public Graph() {
        this.map = new HashMap<>();
    }

    public void addEdge(int i, int j) {
        Node newNode = new Node(j, 0);
        Node curr = map.get(i);

        if (curr == null) {
            map.put(i, newNode);
            return;
        }

        Node prev = null;
        while (curr != null) {
            prev = curr;
            if (curr.getKey() == j) return; // edge exists
            curr = curr.getNext();
        }

        prev.setNext(newNode);
    }

    public void BFS(int i) {
        Map<Integer, Boolean> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            System.out.print(idx + " -> ");
            visited.put(idx, true);
            Node curr = map.get(idx);

            while (curr != null) {
                int k = curr.getKey();
                if (visited.get(k) == null)
                    queue.offer(k);
                curr = curr.getNext();
            }
        }
    }

    public void DFS(int i) {
        Map<Integer, Boolean> visited = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(i);

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            System.out.print(idx + " -> ");
            visited.put(idx, true);
            Node curr = map.get(idx);

            while (curr != null) {
                int k = curr.getKey();
                if (visited.get(k) == null)
                    stack.push(k);
                curr = curr.getNext();
            }
        }
    }
}

public class GraphAdjacencyListExample {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

        graph.addEdge(7, 1); // Add loop

        graph.BFS(1);
        System.out.println();
        graph.DFS(1);
    }
}

/* OUTPUT

1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> // BFS
1 -> 4 -> 5 -> 6 -> 7 -> 3 -> 2 -> // DFS

*/
