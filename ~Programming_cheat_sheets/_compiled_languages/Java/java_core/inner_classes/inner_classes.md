# Inner classes

```java
class Main {
  
    // Inner class (in case we dont use it anywhere else...)
    public class Inner{
        public void print(){
            System.out.println("Inner here!");
        }
    }
  
    public static void main(String[] args){
      
        // We need to instantiate the outer class first
        Main m = new Main();
      
        // m.new Inner()
        Main.Inner i = m.new Inner();
    
        i.print(); // "Inner here!"
    }
}
```
