
//
//  Anonymous Classes
//

class Foo {
  void foobar(){
    System.out.println("foo.foobar");
  }

  void foobarOriginal(){
    System.out.println("foo.foobar - ORIGINAL");
  }
}

class Main {
  public static void main(String[] args) {
    System.out.println("\n\n\n");

    Foo foo = new Foo(){
      @Override
      void foobar(){
        System.out.println("foo.foobar - OVERRIDE");
      }
    };

    foo.foobar(); // foo.foobar - OVERRIDE

    foo.foobarOriginal(); // foo.foobar - ORIGINAL
    
  }
}

/* OUTPUT

foo.foobar - OVERRIDE
foo.foobar - ORIGINAL

*/
