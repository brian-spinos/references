# Exceptions


```java
class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    
    try{
      foo();
    }catch(Exception e){
      e.printStackTrace();
      // System.out.println(e); // java.lang.Exception: errrr
      // System.out.println(e.getMessage()); // errrr
    }
    
  }
  
  public static void foo() throws Exception {
    System.out.println("foo!");
    throw new Exception("errrr");
  }
}

/** 
 * OUTPUT:
 *
 * Hello world!
 * foo!
 * ERROR: java.lang.Exception: errrr
 * 
 */
```

```java

class Main{  
    
  public static class Foo{
      public int x = 0;
  }
    
  public static void main(String args[]){  
      
    Foo f = new Foo();
      
    try{
        throw new Exception("\n==> BAD, BAD, BAD <==\n");
        
        // This also throws an exception
        // int x = 10 / 0;
    }
    catch(Exception e){
        System.out.println("============= ERROR");
        e.printStackTrace();
        
        // System.out.println(e.getMessage());
    }
    finally{
      // Do some clean-up!    
      System.out.println("============= finally will always run!");
    }
  }  
}  

```
