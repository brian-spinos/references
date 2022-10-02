# Join threads

```java

// javac Main.java; java Main;

import java.util.Random;

public class Main{


    //
    // MyThread
    //
    public static class MyThread extends Thread{

        @Override
        public void run(){
            for(int i = 1; i <= 5; i++){
                try{
                    int r = new Random().nextInt(1000);
                    int timmer = r;

                    // String msg1 = "--> Start: " + Thread.currentThread().getName() + i;
                    // System.out.println(msg1);


                    //
                    // Just to make sure thread `B`will run faster.
                    //
                    if( Thread.currentThread().getName().equals("B") ){
                        timmer = 10;
                    }



                    if(Thread.currentThread().getName().equals("A") ){
                        System.out.println("|");
                    }

                    if(Thread.currentThread().getName().equals("B") ){
                        System.out.println("     |");
                    }

                    if(Thread.currentThread().getName().equals("C") ){
                        System.out.println("          |");
                    }

                    if(Thread.currentThread().getName().equals("D") ){
                        System.out.println("               |");
                    }

                    if(Thread.currentThread().getName().equals("E") ){
                        System.out.println("                    |");
                    }

                    if(Thread.currentThread().getName().equals("F") ){
                        System.out.println("                         |");
                    }

                    // Emulate some expensive job
                    Thread.sleep(timmer);


                    // String msg2 = "--> End: " + Thread.currentThread().getName() + i + " (" + timmer + " ms)";
                    // System.out.println(msg2);
                }
                catch(Exception ex){
                    System.out.println("ERROR: " + ex);
                }
            }
        }
    }

    //
    // Main method
    //
    public static void main (String[] args){

        // Creating threads
        MyThread ta = new MyThread();
        MyThread tb = new MyThread();
        MyThread tc = new MyThread();
        MyThread td = new MyThread();
        MyThread te = new MyThread();
        MyThread tf = new MyThread();

        // Naming threads
        ta.setName("A");
        tb.setName("B");
        tc.setName("C");
        td.setName("D");
        te.setName("E");
        tf.setName("F");

        // Starting threads
        String msg5 =
        "=========================== [Main thread] Starting All threads!";
        System.out.println(msg5);

        ta.start();
        tb.start();
        tc.start();
        td.start();
        te.start();
        tf.start();


        //
        // The method `ta.join()` means:
        // - the current thread will pause,
        //   and wait for ta to finish,
        //   then the current thread will resume!
        //


        try{ // Because someThread.join() raises an InterruptedException

            String ms3 =
            "=========================== [Main thread] " +
            "Now [Main thread] waits for `B` to finish, while other threads can run freely.\n" +
            "A    B    C    D    E    F";
            System.out.println(ms3);


            // ta.join();
            tb.join();
            // tc.join();
            // td.join();
            // te.join();
            // tf.join();

            String msg4 =
            "=========================== [Main thread] \n" +
            "=========================== " +
            "So there should be no `B` below: \n" +
            "A    B    C    D    E    F";
            System.out.println(msg4);
        }
        catch(InterruptedException ex){
            System.out.println("Error: " + ex);
        }


        //
        // Threads might still be running here...
        //


    }
}
```



```
Output (example):

=========================== [Main thread] Starting All threads!
=========================== [Main thread] Now [Main thread] waits for `B` to finish, while other threads can run freely.
A    B    C    D    E    F
                    |
|
                         |
     |
               |
          |
     |
     |
     |
     |
=========================== [Main thread]
=========================== So there should be no `B` below:
A    B    C    D    E    F
|
          |
                         |
          |
                    |
                         |
               |
|
                    |
|
          |
                         |
|
                         |
                    |
          |
               |
                    |
               |
               |

```
