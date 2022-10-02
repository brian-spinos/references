import java.lang.FunctionalInterface;


@FunctionalInterface
interface Handler {
  void handle(String s);
}

class Main {
  public static void main(String[] args) {
    Handler h1 = data -> System.out.println("h1 here: " + data);
    Handler h2 = data -> System.out.println("h2 here: " + data);

    h1.handle("AAA"); // h1 here: AAA
    h2.handle("BBB"); // h2 here: BBB
  }
}
