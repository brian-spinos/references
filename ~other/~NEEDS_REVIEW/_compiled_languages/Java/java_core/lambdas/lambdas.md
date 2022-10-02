# Lambda Expressions

```java
public class Main {
    
    // We need an interface for the lambda
    interface IGreeter {
        String greet(String message);
    }
   
    public static void main(String args[]){
        IGreeter g = (msg) -> {
            return msg + "!!!";
        };
        
        System.out.println(g.greet("Hello")); // Hello!!!
    }
}
```
