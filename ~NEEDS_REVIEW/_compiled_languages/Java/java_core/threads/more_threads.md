# More threads

```java
/**
 * Main
 */
public class Main {

    public static void main(String args[]) {

        System.out.println("Creating, Starting, Running, Working, Exiting");


        Foo R1 = new Foo("T1");
        R1.start();

        Foo R2 = new Foo("T2");
        R2.start();

        Foo R3 = new Foo("T3");
        R3.start();
    }
}


/**
 * Foo
 */
class Foo implements Runnable {

    private Thread t;
    private String threadName;

    Foo(String name) {
        threadName = name;
        System.out.println("===============================================================[C] " + threadName);
    }

    public void run() {
        System.out.println("======================================[R] " + threadName);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("====================[W] " + threadName + ": task #" + i);
                // Let the thread sleep for a while.


                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println(">.......>>>>>>>>>>[I] T " + threadName + " interrupted.");
        }
        System.out.println("========[E] T " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("================================================[S] " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
```

###### Output

```
Creating, Starting, Running, Working, Exiting
===============================================================[C] T1
================================================[S] T1
===============================================================[C] T2
================================================[S] T2
===============================================================[C] T3
================================================[S] T3
======================================[R] T1
======================================[R] T2
====================[W] T1: task #0
======================================[R] T3
====================[W] T3: task #0
====================[W] T2: task #0
====================[W] T1: task #1
====================[W] T3: task #1
====================[W] T2: task #1
====================[W] T1: task #2
====================[W] T3: task #2
====================[W] T2: task #2
====================[W] T1: task #3
====================[W] T3: task #3
====================[W] T2: task #3
====================[W] T1: task #4
====================[W] T3: task #4
====================[W] T2: task #4
========[E] T T1 exiting.
========[E] T T2 exiting.
========[E] T T3 exiting.
```
