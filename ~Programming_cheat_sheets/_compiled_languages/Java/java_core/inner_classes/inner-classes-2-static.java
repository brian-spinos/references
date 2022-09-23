
//
// Static inner classes
//

class Foo {
  static class Goo {
    Goo(){
      System.out.println("new Goo!!!");
    }

    void goobar(){
      System.out.println("Foo.Goo.goobar");
    }
  }

  void foobar(){
    System.out.println("foo.foobar");
  }
}

class Main {
  public static void main(String[] args) {
    System.out.println("\n\n\n");

    Foo foo = new Foo();
    foo.foobar();

    Foo.Goo goo = new Foo.Goo();
    goo.goobar();
  }
}

/* OUTPUT

foo.foobar
new Goo!!!
Foo.Goo.goobar

*/
