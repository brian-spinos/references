package com.aexp.paymentnetwork.nrdscheduler;


import java.util.Arrays;
import java.util.Random;

public class BrianThread {
  public static void main(String[] args) {

    MyThread[] threadArray = {
            new MyThread("A", 1, false),
            new MyThread("B", 2, false),
            new MyThread("C", 3, true),
            new MyThread("D", 4, false),
            new MyThread("E", 5, false),
            new MyThread("F", 6, false)
    };

    MainThread currentThread = new MainThread("M", 0, threadArray);
    currentThread.start();
  }
}


/**
 *
 */
class Constants {
  public static String LABELS = "M    A    B    C    D    E    F";
  public static String LINE = "=================================== ";
  public static String INVISIBLE_LINE = "                                    ";
}


/**
 *
 */
class MainThread {
  private String name;
  private int position;
  private MyThread[] threadArray;
  private int distance;
  private int sleepTime;

  public MainThread(String name, int position, MyThread[] threadArray) {
    this.name = name;
    this.position = position;
    this.threadArray = threadArray;
    distance = 30;
    sleepTime = 50;
  }

  private String repeat(int times) {
    return new String(new char[times]).replace("\0", "     ");
  }

  private void startOtherThreads() {
    String msg5 =
            Constants.LINE + "[Main thread] Starting other threads (**ALL** threads run together, including the main thread)";
    System.out.println(msg5);

    Arrays.asList(threadArray).forEach(t -> {
      t.start(); // its start, not run... "run" runs right away
    });
  }

  private void joinAThread() {
    String ms3 =
            Constants.LINE + "[Joining thread 'C'] Main thread stops, 'C' thread behaves like main thread, then when 'C' thread is done, main thread resumes.";
    System.out.println(ms3);

    int threadIndex = 2; // Thread 'C'

    try {
      threadArray[threadIndex].join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    String msg6 =
            Constants.LINE + "[Main thread] ONLY\n" + Constants.LABELS;
    System.out.println(msg6);


    for (int i = 1; i <= distance; i++) {
      System.out.println(repeat(position) + "|");

      if (i == 10) {
        startOtherThreads();
      }

      if (i == 15) {
        joinAThread();
      }

      try {
        // Emulate some expensive job
        Thread.sleep(sleepTime);
      } catch (Exception ex) {
        System.out.println("ERROR: " + ex);
      }
    }
  }
}

/**
 *
 */
class MyThread extends Thread {
  private String name;
  private int position;
  private boolean isSlowest;

  public MyThread(String name, int position, boolean isSlowest) {
    this.name = name;
    this.position = position;
    this.isSlowest = isSlowest;
    this.setName(name);
  }

  private String repeat(int times) {
    return new String(new char[times]).replace("\0", "     ");
  }

  @Override
  public void run() {
    int distance = 5;
    int sleepTime; // miliseconds
    int minSpeedForNormal = 1;

    if (isSlowest) {
      distance = 30;
      sleepTime = 50;
    } else {
      sleepTime = minSpeedForNormal + new Random().nextInt(5);
    }

    for (int i = 1; i <= distance; i++) {
      System.out.println(repeat(position) + "|");

      try {
        // Emulate some expensive job
        Thread.sleep(sleepTime);
      } catch (Exception ex) {
        System.out.println("ERROR: " + ex);
      }
    }
  }
}


/* OUTPUT

=================================== [Main thread] ONLY
M    A    B    C    D    E    F
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
=================================== [Main thread] Starting other threads (**ALL** threads run together, including the main thread)
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
=================================== [Joining thread 'C'] Main thread stops, 'C' thread behaves like main thread, then when 'C' thread is done, main thread resumes.
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

*/
