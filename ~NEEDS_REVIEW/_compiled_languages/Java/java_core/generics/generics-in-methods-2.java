//
// Generic method
//

class Foo<T> {

  /**
   * T2, T3 will be infered by the passed in argument
   */
  <T2, T3> T bar(T t, T2 t2, T3 t3){
    System.out.println("t class: " + t.getClass());
    System.out.println("t: " + t);
    System.out.println("t2 class: " + t2.getClass());
    System.out.println("t2: " + t2);
    System.out.println("t3 class: " + t3.getClass());
    System.out.println("t3: " + t2);
    return t;
  }
}

class Main {
  public static void main(String[] args) {
    System.out.println("\n\n");

    Foo<String> foo = new Foo<>();

    foo.bar("AAA", 123, true);
    foo.<Boolean, Integer>bar("AAA", true, 123);

    // 123 is still an Integer
    foo.<Number, Boolean>bar("AAA", 123, true); 
  }
}
