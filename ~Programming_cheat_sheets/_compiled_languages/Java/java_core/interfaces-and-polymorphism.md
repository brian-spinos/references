```java
// Interfaces and polymorphism

interface Jumper {
  public void jump();
}

class Foo implements Jumper {
  public void jump(){
    System.out.println("foo jumping...");
  }
}

class Bar implements Jumper {
  public void jump(){
    System.out.println("bar jumping...");
  }
}

class Executor {
  static void execute(Jumper j){
    j.jump();
  }
}

class Main {
  public static void main(String[] args) {
    Executor.execute(new Foo()); // foo jumping...
    Executor.execute(new Bar()); // bar jumping...
  }
}
```
