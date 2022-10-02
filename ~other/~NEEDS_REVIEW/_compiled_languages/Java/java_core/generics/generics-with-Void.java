//
// Generics with Void
//

class Foo<T> {
  T bar(T baz){
    System.out.println("test #1"); // null -> Void
    return baz;
  }

  <T1, T2> T2 bar2(T1 x, T2 y){
    System.out.println("test #2"); // null -> Void
    return y;
  }
}


class Main {
  public static void main(String[] args) {
    System.out.println("\n\n");

    Foo<Void> foo = new Foo<>();

    foo.bar(null);
    foo.<Integer, Void>bar2(123, null);
    foo.bar2(123, null); // no need for the `<Integer, Void>`
  }
}


/* OUTPUT


test #1
test #2
test #2
*/
