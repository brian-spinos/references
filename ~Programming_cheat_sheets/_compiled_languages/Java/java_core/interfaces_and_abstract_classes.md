# Interfaces and Abstract classes

```java
// Interfaces and Abstract classes

public class Main{
  public static void main(String[] args){
      Person b = new Person("Brian");
      
      // Inteligence is abstract, cannot be instantiated
      // Inteligence inteligence = new Inteligence("Brian");

      b.getName(); // Brian
      b.myNormalMethod();
      b.studyInterf();
      b.goToClassInterf();
      b.barAbst();
      b.fooAbst();
  }
}

// Interface
interface Student {
    public void studyInterf();
    public void goToClassInterf();
}

// Abstract class
abstract class Inteligence {
  private String name;

  public Inteligence(String name){
    this.name = name;
  }

  public void getName(){
    System.out.println(this.name);
  }

  public void fooAbst(){
    System.out.println("foo - method implemented in abstract class");
  }
  
  // abstract methods cannot have a body
  public abstract void barAbst();
}

// Class
class Person extends Inteligence implements Student {
  // private String name;

  public Person(String name){
    // we need to instantiate the parent class too ???
    super(name);

    // this.name = name;
  }

  public void myNormalMethod(){
    System.out.println("myNormalMethod called!");
  }

  public void studyInterf(){
    System.out.println("studyInterf called!");
  }

  public void goToClassInterf(){
    System.out.println("goToClassInterf called!");
  }

  public void barAbst(){
    System.out.println("barAbst - method implemented in concrete class");
  }

  // fooAbst already defined in abstract class
}
```
