```java
/**
 * Virtual method invocation
 * At compile time, the compiler used talk() in Human to validate this statement. 
 * At run time, however, the JVM invokes talk() in the Person class.
 */
class Main {
  public static void main(String[] args) {
    Human h1 = new Human();
    h1.talk(); // Human talking...
    Human h2 = new Person();
    h2.talk(); // Person talking...
    
    // We can use polymorphism with this!
    MyPrinter.communicate(h1); // Human talking...
    MyPrinter.communicate(h2); // Person talking...
  }
}

class Human {
  void talk(){
    System.out.println("Human talking...");
  }
}

class Person extends Human {
  void talk(){
    System.out.println("Person talking...");
  }
}

class MyPrinter {
  static void communicate(Human h){
    h.talk();
  }
}

```
