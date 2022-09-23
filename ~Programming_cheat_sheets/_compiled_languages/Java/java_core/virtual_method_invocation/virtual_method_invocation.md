# Virtual method invocation


```java
// Virtual method invocation

// parent reference, but uses child method


class Parent{
  public void parentMethod(){
    System.out.println("parentMethod");
  }
  
  public void foo(){
    System.out.println("foo parent");
  }
}

class Child extends Parent{
  
  public void childMethod(){
    System.out.println("childMethod");
  }
  
  @Override
  public void foo(){
    System.out.println("foo child");
  }
}


class Main {
  public static void main(String[] args) {
    
    Parent p = new Child();
    
    p.parentMethod(); //=> "parentMethod"
    
    // p.childMethod(); error
      
    // It should have called the Parent's foo method,
    // because it is a Parent reference,
    // But it called the Child's foo method
    p.foo(); //=> "foo child"
    
  }
}
```
