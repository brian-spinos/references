# Java Packages


```java
// file location: ./Main.java


/**
 * Import classes from the `./foo` folder,
 * that use `package foo;`
 * here, we could have used `import foo.Bar;`
 */
import foo.*;

public class Main{
    public static void main(String[] args){
        System.out.println("Hello!");

        Bar b = new Bar();
        b.init();
    }
}
```

```java
// file location: ./foo/Bar.java

package foo;

public class Bar{
    public void init(){
        System.out.println("This is Bar!");
    }
}
```
