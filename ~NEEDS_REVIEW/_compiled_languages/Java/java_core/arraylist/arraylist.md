# ArrayList

```java
import java.util.*;

public class Main{
    public static void main(String args[]){
       
	    ArrayList<String> array = new ArrayList<String>();

	    array.add("One");
	    array.add("Two");
	    array.add("Three");
	    array.add("Four");

	    System.out.println("array: " + array);
        
        // Adding extra elements
	    array.add(0, "0");
	    array.add(1, "1");
	    array.add(2, "2");
	    array.add(3, "3");
	    
	    System.out.println("array: " + array);
        
	    array.remove("Four");

	    System.out.println("array: " + array);
    
        // Remove element in the 6th index
	    array.remove(6);

	    System.out.println("array: " + array);
   }
}

/*
Output:

array: [One, Two, Three, Four]
array: [0, 1, 2, 3, One, Two, Three, Four]
array: [0, 1, 2, 3, One, Two, Three]
array: [0, 1, 2, 3, One, Two]
*/
```
