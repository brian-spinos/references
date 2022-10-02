// Reverse a linked list

class Node {
  int data;
  Node next;

  public Node(int data){
    this.data = data;
  }
}

class Main {
  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;

    Node current = n1;
    while(current != null){
      System.out.println(current.data);
      current = current.next;
    }
    
    System.out.println("========== REVERSED:");
    reverse(n1);

    current = n5;
    while(current != null){
      System.out.println(current.data);
      current = current.next;
    }
  }
  
  /**
   * reversing a linked list.
   * This works with 0 to N nodes!
   
   * basically, you need 3 pointers, the middle pointer
   * points to the current node, and you need to make 
   * the middle node point to the previous node.
   * there is a pointer setup before the loop,
   * and at the end of each iteration, we want to
   * always have a pointer to the previous and next
   * nodes.
   */
  public static void reverse(Node head){
    // setup for 1st iteration
    Node prevNode = null;
    Node currentNode = head;
    Node nextNode = null;

    // traverse the linked list, modifying pointers
    while(currentNode != null){
      // prevNode = null
      // currentNode = currentNode;
      nextNode = currentNode.next;

      // modifying pointers
      currentNode.next = prevNode;
      
      // setup for next iteration
      prevNode = currentNode;
      currentNode = nextNode;
    }
  }
}
