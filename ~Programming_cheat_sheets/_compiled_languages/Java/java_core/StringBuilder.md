# StringBuilder

- It is a wrapper class for operations on a string.

https://www.javatpoint.com/StringBuilder-class

```java
class Main{  
  public static void main(String args[]){  
      
    StringBuilder sb = new StringBuilder("Hello ");
    
    sb.append("World");
    sb.append("!");
    sb.insert(5, " big");
    System.out.println(sb); // Hello big World!
    
    sb.replace(6, 9, "small");
    sb.delete(1, 4); // delete index 1 through (4 - 1)
    System.out.println(sb); // Ho small World! 
    
    sb.reverse();
    System.out.println(sb); // !dlroW llams oH
    
    // sb.capacity();
    // sb.ensureCapacity();
    // sb.charAt();
    // sb.length();
    // sb.substring();
  }  
}  
```
