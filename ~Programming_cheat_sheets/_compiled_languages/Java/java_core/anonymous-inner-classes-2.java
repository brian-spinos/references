
//
//  Anonymous Inner Classes
//

class Foo {
  void foobar(){
    System.out.println("foo.foobar - ORIGINAL");
  }

  void foobarOriginal(){
    System.out.println("foo.foobarOriginal - ORIGINAL");
  }
}

class Bar {
  Foo foo = new Foo(){
    @Override
    void foobar(){
      System.out.println("bar.foo.foobar - OVERRIDE");
    }
  };

  void barBaz(){
    System.out.println("bar.barbaz");
  }
}

class Main {
  public static void main(String[] args) {
    System.out.println("\n\n\n");

    Bar bar = new Bar();

    bar.foo.foobar(); // bar.foo.foobar - OVERRIDE
    bar.foo.foobarOriginal(); // foo.foobarOriginal - ORIGINAL
  }
}

/* OUTPUT

bar.foo.foobar - OVERRIDE
foo.foobarOriginal - ORIGINAL

*/
