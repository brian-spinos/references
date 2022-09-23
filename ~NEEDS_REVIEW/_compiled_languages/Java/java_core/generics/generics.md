# Generics

```java
public class Main {
 
    public static void main(String[] args){
         
        // Create Foo object with String as type parameter
        Foo<String> foo_str = new Foo<String>("Hello");
        foo_str.printType();  // Type: java.lang.String

        // Create Foo object with Boolean as type parameter
        Foo<Boolean> foo_bool = new Foo<Boolean>(Boolean.TRUE);
        foo_bool.printType();  // Type: java.lang.Boolean
    }
}
 

class Foo<T> {

    private T obj = null;
     

    public Foo(T obj){
        this.obj = obj;
    }
     
    public T getObj(){
        return this.obj;
    }

    public void setObj(T obj){
        this.obj = obj;
    }
     
    public void printType(){
        System.out.println("Type: " + obj.getClass().getName());
    }
}

```
