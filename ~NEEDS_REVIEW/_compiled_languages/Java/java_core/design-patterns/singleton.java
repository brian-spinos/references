class Foo {
  private static Foo instance = null;

  private Foo(){}

  static Foo getInstance(){
    if(instance == null){
      System.out.println("NEW instance...");
      instance = new Foo();
      return instance;
    }

    System.out.println("EXISTING instance...");
    return instance;
  }

  int work(){
    System.out.println("work...\n\n");
    return 123;
  }
}


class Main {
  public static void main(String[] args) {
    System.out.println("\n\n");

    Foo foo = Foo.getInstance();
    foo.work();

    Foo foo2 = Foo.getInstance();
    foo2.work();

    Foo foo3 = Foo.getInstance();
    foo3.work();
  }
}

/* OUTPUT:

NEW instance...
work...


EXISTING instance...
work...


EXISTING instance...
work...

*/
