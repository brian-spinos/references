# Collections

```java
import java.util.*;

class Main {
  public static void main(String[] args) {

    // Hash: order of elements is NOT kept!

    // set: no duplicate items!

    // tree: ordered items!

    // map: key value pair

    //
    // List
    // (ArrayList, LinkedList, ...)
    //

    List<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);

    System.out.println(list); // [10, 20, 30]

    Iterator it = list.iterator();
    while (it.hasNext()) {
        Integer num = (Integer)it.next();
        System.out.println(num);
    }


    //
    // Set
    // (TreeSet, HashSet, MapSet, ...)
    //

    Set<Integer> set = new HashSet<>();
      set.add(40);
      set.add(40); // Duplicates are NOT added...
      set.add(50);
      set.add(60);
      set.add(null); // null IS allowed!
      set.remove(50); // removing elements
     
      System.out.println(set); // [null, 40, 60]


    //
    // Queue
    // (LinkedList, ...)
    //

    Queue<Integer> queue = new LinkedList<>();
    queue.add(70);
    queue.add(80);
    queue.add(90);
 
    // Display contents of the queue.
    System.out.println("Elements of queue: " + queue); // [70, 80, 90]
 
    // To remove the head of queue.
    int h = queue.remove(); // 70
    System.out.println("removed element: " + h); // 70
    System.out.println(queue); // [80, 90]
 
    // To view the head of queue
    int head = queue.peek(); // 80
    System.out.println("head of queue: " + head); // 80
 
    int size = queue.size(); // 2
    System.out.println("Size of queue: " + size); // 2

    //
    // Map
    // (HashMap, TreeMap, Properties, ...)
    //

    Map<String, String> map = new HashMap<String, String>();
    map.put("name", "Brian");
    map.put("age", "29");
    map.put("address", "123 Foobar St");
    map.put("foo", "bar");
    map.remove("foo"); // removing item

    String name = map.get("name"); // No need to downcast
    System.out.println(name); // Brian
   
    Set<String> keys = map.keySet();
    for(String str : keys) {
      System.out.print(str);
      System.out.print(":");
      System.out.println(map.get(str));
    }

  }
  
}
```
