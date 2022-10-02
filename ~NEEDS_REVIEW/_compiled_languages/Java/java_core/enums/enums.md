# Enum with a contructor

- https://www.ntu.edu.sg/home/ehchua/programming/java/JavaEnum.html
- Enums can include other component of a traditional class, such as constructors, member variables and methods.


```java
enum TrafficLight{

    // Named constants
    RED(30, "A"),
    AMBER(10, "B"),
    GREEN(30, "C");  
    
    // Private variable
    private final int seconds;
    private final String letter;

    
    // Constructor
    TrafficLight(int seconds, String letter){     
        this.seconds = seconds;
        this.letter = letter;
    }

    //
    // Getters
    //
    
    int getSeconds(){
        return this.seconds;
    }
    
    String getLetter(){
        return this.letter;
    }
}
 
public class Main{
    public static void main(String[] args){
        for(TrafficLight light : TrafficLight.values()){
            System.out.printf("%s: %d %s\n", light, light.getSeconds(), light.getLetter());
        }
        
        
        System.out.println("================");
        
        System.out.println(TrafficLight.RED);
        System.out.println(TrafficLight.RED.getSeconds());
        System.out.println(TrafficLight.RED.getLetter());
    }
}


/* 

Output:

RED: 30 A
AMBER: 10 B
GREEN: 30 C
================
RED
30
A

*/
```
