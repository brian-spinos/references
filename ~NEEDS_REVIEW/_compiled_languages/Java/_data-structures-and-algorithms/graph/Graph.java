package com.example;

import java.util.*;

class Node {
    private String data;

    public Node(String data) {
        this.data = data;
    }

    /**
     * WE NEED THIS:  we need to compare by data,
     * not address, and hashcode for comparison
     */
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

class Graph {
    private Map<Node, List<Node>> map = new HashMap<>();

    // If you want to add but not connect
    public void addNode(String a) {
        map.putIfAbsent(new Node(a), new ArrayList<>());
    }

    // Undirected graph
    public void connect(String a, String b) {
        map.putIfAbsent(new Node(a), new ArrayList<>());
        map.get(new Node(a)).add(new Node(b));

        map.putIfAbsent(new Node(b), new ArrayList<>());
        map.get(new Node(b)).add(new Node(a));
    }

    public boolean isConnected(String a, String b) {
        List<Node> list = map.get(new Node(a));
        if (list == null) return false; // if node does not exist, it will return a null list
        for (Node n : list) {
            if (n.equals(new Node(b))) return true;
        }
        return false;
    }

    /**
     * In graphs, you don't need to search for a node, since it is constant time to find a node in the HashMap
     * you want to traverse
     * <p>
     * - there could be loops, so we need a 'visited' set
     */
    public void DFS(String a) { // traverse from a starting point - check if two nodes are reachable?
        Node startingNode = new Node(a);
        List<Node> list = map.get(startingNode);
        if (list == null) return; // startingNode does not exist

        Stack<Node> stack = new Stack<>();
        Set<Node> visitedSet = new LinkedHashSet<>(); // LinkedHashSet to keep order of insertion, that is it!
        stack.push(startingNode); // seed push
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            visitedSet.add(currentNode);
            System.out.println("VISITED: " + currentNode);
            List<Node> list2 = map.get(currentNode); // get children
            for (Node n2 : list2) {
                if (!visitedSet.contains(n2)) {
                    stack.push(n2);
                }
            }
        }

        System.out.println("visitedSet: " + visitedSet);
    }

    public void BFS(String a) { // traverse from a starting point - check if two nodes are reachable?
        Node startingNode = new Node(a);
        List<Node> list = map.get(startingNode);
        if (list == null) return; // startingNode does not exist

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visitedSet = new LinkedHashSet<>(); // LinkedHashSet to keep order of insertion, that is it!
        queue.offer(startingNode); // seed push
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.println("VISITED: " + currentNode);

            visitedSet.add(currentNode);
            List<Node> children = map.get(currentNode); // get children
            for (Node c : children) {
                if (!visitedSet.contains(c)) {
                    queue.offer(c);
                }
            }
        }

        System.out.println("visitedSet: " + visitedSet);
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
        // visitedSet: [Node{data='A'}, Node{data='B'}, Node{data='C'}, Node{data='D'}, Node{data='E'}, Node{data='F'}, Node{data='G'}, Node{data='H'}, Node{data='I'}, Node{data='J'}, Node{data='A5'}, Node{data='A4'}, Node{data='A3'}, Node{data='A2'}, Node{data='A1'}]
        //=======================================================================
        graph.BFS("A");
        // visitedSet: [Node{data='A'}, Node{data='A1'}, Node{data='A2'}, Node{data='A3'}, Node{data='A4'}, Node{data='A5'}, Node{data='B'}, Node{data='C'}, Node{data='D'}, Node{data='E'}, Node{data='F'}, Node{data='G'}, Node{data='H'}, Node{data='I'}, Node{data='J'}]
        //=======================================================================
    }
}

