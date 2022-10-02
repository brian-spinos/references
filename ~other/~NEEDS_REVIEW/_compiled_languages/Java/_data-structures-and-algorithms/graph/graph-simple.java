package com.example;

import java.util.*;

class Node {
    String data;

    Node(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return data.equals(node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                '}';
    }
}

/**
 * Undirected Graph
 */
class Graph {
    private Map<Node, List<Node>> map = new HashMap<>();

    // If you want to add but not connect
    public void addNode(String a) {
        map.putIfAbsent(new Node(a), new ArrayList<>());
    }

    public void connect(String a, String b) {
        map.putIfAbsent(new Node(a), new ArrayList<>()); // in case it is empty
        List<Node> list = map.get(new Node(a));
        if (!list.contains(new Node(b))) {
            list.add(new Node(b));
        }

        map.putIfAbsent(new Node(b), new ArrayList<>()); // in case it is empty
        List<Node> list2 = map.get(new Node(b));
        if (!list2.contains(new Node(a))) {
            list2.add(new Node(a));
        }
    }

    boolean isConnected(String a, String b) {
        List<Node> list = map.get(new Node(a));
        if (list==null) return false;
        return list.contains(new Node(b));
    }

    void BFS(String a) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visitedNodeSet = new LinkedHashSet<>();
        queue.offer(new Node(a));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            visitedNodeSet.add(currentNode);
            System.out.println("visited: " + currentNode);
            for (Node nn : map.get(currentNode)) { // <================= PAY ATTENTION
                if (!visitedNodeSet.contains(nn)) {
                    queue.offer(nn);
                }
            }
        }

        System.out.println("visitedNodeSet: " + visitedNodeSet);
    }

    void DFS(String a) {
        Stack<Node> stack = new Stack<>();
        Set<Node> visitedNodeSet = new LinkedHashSet<>();
        stack.push(new Node(a));

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            visitedNodeSet.add(currentNode);
            System.out.println("visited: " + currentNode);

            for (Node c : map.get(currentNode)) {
                if (!visitedNodeSet.contains(c)) {
                    stack.push(c);
                }
            }
        }

        System.out.println("visitedNodeSet: " + visitedNodeSet);
    }
}

public class Foo {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.connect("A", "A1");
        graph.connect("A", "A2");
        graph.connect("A", "A3");
        graph.connect("A", "A4");
        graph.connect("A", "A5");

        graph.connect("A", "B");
        graph.connect("B", "C");
        graph.connect("C", "D");
        graph.connect("D", "E");
        graph.connect("E", "F");
        graph.connect("F", "G");
        graph.connect("G", "H");
        graph.connect("H", "I");
        graph.connect("I", "J");

        graph.addNode("Z-NO_CONNECTIONS"); // node with no connections

        //=======================================================================
        System.out.println("res: " + graph.isConnected("A", "B")); // true
        System.out.println("res: " + graph.isConnected("A", "C")); // false (Not directly connected)
        System.out.println("res: " + graph.isConnected("A", "NON-EXISTENT")); // false
        System.out.println("res: " + graph.isConnected("NON-EXISTENT-1", "NON-EXISTENT-2")); // false
        System.out.println("res: " + graph.isConnected("Z-NO_CONNECTIONS", "NON-EXISTENT")); // false
        System.out.println("res: " + graph.isConnected("Z-NO_CONNECTIONS", "A")); // false
        //=======================================================================
        graph.DFS("A");
        // visitedNodeSet: [Node{data='A'}, Node{data='B'}, Node{data='C'}, Node{data='D'}, Node{data='E'}, Node{data='F'}, Node{data='G'}, Node{data='H'}, Node{data='I'}, Node{data='J'}, Node{data='A5'}, Node{data='A4'}, Node{data='A3'}, Node{data='A2'}, Node{data='A1'}]
        //=======================================================================
        graph.BFS("A");
        // visitedNodeSet: [Node{data='A'}, Node{data='A1'}, Node{data='A2'}, Node{data='A3'}, Node{data='A4'}, Node{data='A5'}, Node{data='B'}, Node{data='C'}, Node{data='D'}, Node{data='E'}, Node{data='F'}, Node{data='G'}, Node{data='H'}, Node{data='I'}, Node{data='J'}]
        //=======================================================================
    }
}
