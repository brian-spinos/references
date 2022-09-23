# Collections

```java
import java.util.*;

//
// Java Collections
//
class Main {
  public static void main(String[] args) {

    //
    // List
    //
    System.out.println("\n\n============== List");


    // ArrayList
    System.out.println("\n\n======= ArrayList");
    List<String> arrList = new ArrayList<String>();
    arrList.add("foo");
    arrList.add("bar");
    arrList.add(null);
    for(String item : arrList){
      System.out.println(item);
    }


    // LinkedList
    System.out.println("\n\n======= LinkedList");
    List<String> liList = new LinkedList<String>();
    liList.add("foo");
    liList.add("bar");
    liList.add(null);
    for(String item : liList){
      System.out.println(item);
    }


    //
    // Queue
    //
    System.out.println("\n\n============== Queue");

    // PriorityQueue
    System.out.println("\n\n======= PriorityQueue");
    Queue<String> pQueue = new PriorityQueue<>();
    pQueue.add("foo");
    pQueue.add("bar");
    // pQueue.add(null);
    for(String item : pQueue){
      System.out.println(item);
    }

    // LinkedList
    System.out.println("\n\n======= LinkedList*");

    // ArrayDeque
    System.out.println("\n\n======= ArrayDeque");
    Queue<String> aDeque = new ArrayDeque<>();
    aDeque.add("foo");
    aDeque.add("bar");
    // aDeque.add(null);
    for(String item : aDeque){
      System.out.println(item);
    }


    //
    // Set
    //
    System.out.println("\n\n============== Set");


    // HashSet
    System.out.println("\n\n======= HashSet");
    Set<String> hSet = new HashSet<>();
    hSet.add("foo");
    hSet.add("bar");
    hSet.add(null);
    for(String item : hSet){
      System.out.println(item);
    }


    // TreeSet
    System.out.println("\n\n======= TreeSet");
    Set<String> tSet = new HashSet<>();
    tSet.add("foo");
    tSet.add("bar");
    tSet.add(null);
    for(String item : tSet){
      System.out.println(item);
    }

    //
    // Map
    //
    System.out.println("\n\n============== Map");


    // HashMap
    System.out.println("\n\n======= HashMap");
    Map<String, String> hMap = new HashMap<>();
    hMap.put("brian", "spinos");
    hMap.put("erich", "spinos");
    hMap.put(null, null);
    for(String key : hMap.keySet()){
      System.out.println(key + " = " + hMap.get(key));
    }


    // TreeMap
    System.out.println("\n\n======= TreeMap");
    Map<String, String> tMap = new TreeMap<>();
    tMap.put("brian", "spinos");
    tMap.put("erich", "spinos");
    // tMap.put(null, null);
    for(String key : tMap.keySet()){
      System.out.println(key + " : " + tMap.get(key));
    }
    
  }
}
```

###### Output

```

============== List


======= ArrayList
foo
bar
null


======= LinkedList
foo
bar
null


============== Queue


======= PriorityQueue
bar
foo


======= LinkedList*


======= ArrayDeque
foo
bar


============== Set


======= HashSet
null
bar
foo


======= TreeSet
null
bar
foo


============== Map


======= HashMap
null = null
erich = spinos
brian = spinos


======= TreeMap
brian : spinos
erich : spinos
```
