```java
// Anonymous classes can be derived from Interfaces, Abstract classes, and concrete classes!

interface FooI {
  String bar();
}

abstract class BarA {
  abstract String bar();
}

class BazC {
  String bar(){
    return "BazC.bar default string";
  }
}


class Main {
  public static void main(String[] args) {
    System.out.println("anonymous classes!");


    FooI fooI = new FooI(){
      @Override
      public String bar(){
        return "FooI.bar overwriten";
      }
    };

    System.out.println(fooI.bar());

    BarA barA = new BarA(){
      @Override
      public String bar(){
        return "BarA.bar overwriten";
      }
    };

    System.out.println(barA.bar());

    BazC bazC = new BazC(){
      @Override
      public String bar(){
        System.out.println(baz());
        return "BazC.bar overwriten";
      }

      //@Override
      // you can add new methods, but only use internally...
      public String baz(){
        System.out.println("inside baz");
        return "BazC.baz - new method!";
      }
    };

    System.out.println(bazC.bar());
    // System.out.println(bazC.baz()); // error
  }
}
```


###### Output
```
anonymous classes!
FooI.bar overwriten
BarA.bar overwriten
inside baz
BazC.baz - new method!
BazC.bar overwriten
```
