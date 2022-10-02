# Custom annotations

```java
import java.lang.annotation.*;  
import java.lang.reflect.*;  

//
// Custom annotations  
//

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
@interface MyAnnotation{  
    int value();  
}  
  
// Applying annotation 

class Foo{ 
    
    @MyAnnotation(value=10) 
    public void bar(){
        System.out.println("hello annotation");
    }  
}  
  
// Accessing annotation  

class Main{  
    
    public static void main(String args[]) throws Exception {  
        Foo foo = new Foo();  
        Method m = foo.getClass().getMethod("bar");  
  
        MyAnnotation a = m.getAnnotation(MyAnnotation.class);  
        System.out.println("value is: " + a.value()); // value is: 10
    }
    
} 
```
