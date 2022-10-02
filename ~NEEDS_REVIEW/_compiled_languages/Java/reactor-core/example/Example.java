package com.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Example {
  public static void main(String[] args) {
    
    // both a Flux and Mono are implementations of the Reactive Streams Publisher interface. Both classes are compliant with the specification, and we could use this interface in their place:
    //Publisher<String> just = Mono.just("foo");

    //----------------------------------------------- https://www.baeldung.com/reactor-core
    Flux.just("aaa", "bbb", "ccc")
            .map(String::toUpperCase)
            .doOnSubscribe(s->{
              System.out.println("S: " + s.toString()); // I think we can cancel it...
            })
            .doOnError(e -> {
              System.out.println("E: " + e);
            })
            .doOnNext(n -> {
              System.out.println("N: " + n);
              try {
                Thread.sleep(3000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            })
            .doOnComplete(() -> {
              System.out.println("completed");
            }).subscribeOn(Schedulers.parallel())
            .subscribe();


    //-----------------------------------------------
    Flux.just("aaa2", "bbb2", "ccc2")
            .map(String::toUpperCase)
            .doOnSubscribe(s->{
              System.out.println("S: " + s.toString()); // I think we can cancel it...
            })
            .doOnError(e -> {
              System.out.println("E: " + e);
            })
            .doOnNext(n -> {
              System.out.println("N: " + n);
            })
            .doOnComplete(() -> {
              System.out.println("completed");
            })
            .subscribeOn(Schedulers.parallel())
            .subscribe();


    //-----------------------------------------------



    Mono.just("hello")
            .map(String::toUpperCase)
            .doOnSubscribe(s->{
              System.out.println("M_S: " + s.toString()); // I think we can cancel it...
            })
            .doOnError(e -> {
              System.out.println("M_E: " + e);
            })
            .doOnNext(n -> {
              System.out.println("M_N: " + n);
            })
            .doOnSuccess(x -> {
              System.out.println("M_x: " + x);
            })
//            .doOnComplete(() -> {
//              System.out.println("completed");
//            })
            .subscribeOn(Schedulers.parallel())
            .subscribe();
    //-----------------------------------------------
    //-----------------------------------------------


    try {
      Thread.sleep(20000); // we need to wait for other "parallel" threads to finish, or else,
      // our program will just stop before the "parallel" threads complete
      System.out.println("DONE!");

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
