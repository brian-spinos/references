# HashMap

```java
import java.util.*;

class Main{
    public static void main(String[] args){
        HashMap<String, String> hash = new HashMap<String, String>();  
            
        hash.put("name", "brian");  
        hash.put("age","29");  
        hash.put("phone","555-555-5555");  
        hash.put("address","3456 Main St");
        
        for(Map.Entry h : hash.entrySet()){  
            System.out.println(h.getKey() + ": " + h.getValue());  
        }  
    }
}

/*
Output:

address: 3456 Main St
phone: 555-555-5555
name: brian
age: 29
*/
```
