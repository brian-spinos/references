# Anonymous Inner Class

```java
class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    
    Foo f = new Foo(){
      void sayHi(){
        System.out.println("test 2");
      }
      
    };
    
    f.sayHi();
  }
}

class Foo {
  void sayHi(){
    System.out.println("test 1");
  }
}
```

###### Output
```
Hello world!
test 2
```
