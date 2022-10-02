# Threads 

```java
/**
 * There are two ways to create a thread:
 * - By extending Thread class
 * - By implementing Runnable interface.
 */
class Main{  
  
  // Create thread by `Runnable` interface
  public static class MyThread implements Runnable{  
    @Override
    public void run(){  
      System.out.println("MyThread is running...");  
    }  
  } 
  
  // Create thread by extending `Thread`
  public static class MyOtherThread extends Thread{  
    @Override
    public void run(){  
      System.out.println("MyOtherThread is running...");  
    }  
  } 


  public static void main(String args[]){  
      
    MyThread t1 = new MyThread();  
    Thread th1 = new Thread(t1);  
    th1.start();  
    
    MyOtherThread t2 = new MyOtherThread();  
    Thread th2 = new Thread(t2);  
    th2.start();  
    
    MyOtherThread t3 = new MyOtherThread();  
    Thread th3 = new Thread(t3);  
    th3.start();
  }  
}  
```
