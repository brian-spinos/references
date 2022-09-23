# Upcasting and downcasting


```java
public class Main{
  
  public static void main(String[] args){
    System.out.println("Hello!");
    
    System.out.println("============================!");
    
    Parent p = new Child();
    
    p.foo(); // child
    p.bar(); // parent
    //p.baz(); // error
    
    // We can only downcast, because it was originally pointing to a Child
    // even though the reference was a Parent
    ((Child)p).baz(); // child
    
    System.out.println("============================!");
    

  }
}



// Parent
class Parent{
  public void foo(){
    System.out.println("parent foo");
  }
  
  public void bar(){
    System.out.println("parent bar");
  }
}

// Child
class Child extends Parent{
  
  public void baz(){
    System.out.println("child baz");
  }
  
  public void foo(){
    System.out.println("child foo");
  }
}
```
