/**
 * Take away, the type of the object does not change, but it can act as its superclass type
 * and when `Bar b = getBar(foo)` the return is still Foo, but it needs to be assigned to a Bar variable,
 * to honor the method signature
 */

class Bar {
  String data;

  Bar(String data){
    this.data = data;
  }
}

class Foo extends Bar{
  String x;

  Foo(String data, String x){
    super(data);
    this.x = x;
  }
}

class Main {
  public static void main(String[] args) {
    System.out.println("\n\n");

    Foo foo = new Foo("data-val", "x-val");
    Bar bar = new Bar("data-val");

    Foo fooReturn = method1(foo);
    Bar barReturn = method2(bar);

    System.out.println("Got fooReturn: " + fooReturn.getClass().getName());
    System.out.println("Got barReturn: " + barReturn.getClass().getName());

    System.out.println("=================");

    // Foo fooReturn2 = method1(bar); // Error
    // (Bar is not a Foo, but a Foo is a Bar)

    Bar barReturn2 = method2(foo); // Foo
    System.out.println("Got barReturn2: " + barReturn2.getClass().getName()); // Foo

    // Error, method2 needs to be assigned to a Bar, even though it is a Foo
    // Foo FooReturn3 = method2(foo); 
    // System.out.println("Got FooReturn3: " + FooReturn3.getClass().getName()); // Foo
  }

  public static Foo method1(Foo foo){
    System.out.println("[m]Got Foo foo: " + foo.getClass().getName());
    return foo;
  }

  /**
   * if we pass Foo, it will be Foo inside the method
   * when returning it is still a Foo, inside the method and outside!
   */
  public static Bar method2(Bar bar){
    System.out.println("[m]Got Bar bar: " + bar.getClass().getName()); // Foo
    return bar; // Foo
  }
}

/* OUTPUT

[m]Got Foo foo: Foo
[m]Got Bar bar: Bar
Got fooReturn: Foo
Got barReturn: Bar
=================
[m]Got Bar bar: Foo
Got barReturn2: Foo

*/
