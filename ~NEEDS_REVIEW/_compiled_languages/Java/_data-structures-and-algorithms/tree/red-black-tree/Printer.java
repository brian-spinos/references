
class Printer {
  public void print(Node root){
    Node n0 = getN(root);
    Node n1 = getL(root);
    Node n2 = getR(root);
    Node n3 = getL(n1);
    Node n4 = getR(n1);
    Node n5 = getL(n2);
    Node n6 = getR(n2);
    System.out.println("=================================");
    System.out.printf("      %s       %n", n0);
    System.out.printf("  %d       %d   %n", n1, n2);
    System.out.printf("%d   %d   %d   %d %n", n3, n4, n5, n6);
    System.out.println("=================================");
  }

  private String getN(Node node){
    if (node == null) {
      return "null";
    }
    return node.toString();
  }

  private String getL(Node node){
    if (node == null) {
      return "null";
    }
    if (node.getLeft() == null) {
      return "null";
    }

    return node.getLeft();
  }

  private String getR(Node node){
    if (node == null) {
      return "null";
    }
    if (node.getRight() == null) {
      return "null";
    }

    return node.getRight();
  }
}
