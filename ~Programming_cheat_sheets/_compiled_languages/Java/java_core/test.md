# Test

```java
class Main{ // Because of name of file

    //--------------------------------------- Interface
    public interface Bar{
        public void funcA();
        public void funcB();
        public void funcC();
    }
    
    //--------------------------------------- Abstract class
    public abstract class Baz{
    
        public String xyz;
        
        // The 'constructor' is just to initialize fields
        public Baz(String x){
            System.out.println(x);
            
            this.xyz = x;
        }
        
        public void func1(String x){
            System.out.println(x);
        }
        
        // You need to have at least one abstract method
        public abstract int funcAbs(String x);
    }
    
    //--------------------------------------- Base class
    public static class Foo{
        String name;
        int age;
        
        public Foo(String name){
            this.name = name;
        }
        
        public void func1(){
            System.out.println("This is func1");
        }
        
        public void func3(){
            System.out.println("This is func3");
        }
    }
    
    //--------------------------------------- Derived class
    public static class FooBar extends Foo implements Bar{
        String name;
        int age;
        
        public FooBar(String name){
            super(name); // you need to call the parent constructor!
            this.name = name;
        }
        
        public void func1(){
            System.out.println("This is func1");
        }
        
        // For Baz abstract class
        public void funcAbs(){
            System.out.println("This is funcAbs");
        }
        
        // For Bar interface
        public void funcA(){
            System.out.println("funcA");
        };
        public void funcB(){
            System.out.println("funcB");
        };
        public void funcC(){
            System.out.println("funcC");
        };
        
    }
    
    //--------------------------------------- main function
    public static void main(String[] args){
        System.out.println("Hello");
        
        Foo foo = new Foo("foo");
        
        System.out.println(foo.name);
        
        
        FooBar foobar = new FooBar("FooBar");
        
        foobar.funcAbs();
    }
    
    //--------------------------------------- 
    
}

```
