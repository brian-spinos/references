package com.example;

import rx.Observable;
import rx.Single;
import rx.Subscriber;

public class Example {
  public static void main(String[] args) {

    // The flatMap can be used to flatten Observables whenever we end up with nested Observables.

    // https://www.baeldung.com/rx-java

    Observable.just(1, 2, 3, 4)
            .map(n -> n * 2)
            .subscribe(
                    n -> {
                      System.out.println("N: " + n);

                    },  //OnNext
                    e -> {
                      System.out.println("E: " + e);
                    }, //OnError
                    () -> {
                      System.out.println("Completed ");
                    } //OnCompleted
            );


    Single.just("hello").map(n -> {
//      return n.toUpperCase();
      throw new RuntimeException("aaaEX");
    })
//            .doOnError(e -> {
//              System.out.println("SINGLE E: " + e); // does not prevent exception thrown
//            }).doOnSuccess(s -> {
//      System.out.println("SINGLE S: " + s);
//    }).doOnSubscribe(() -> {
//      System.out.println("SINGLE Subscribe: ");
//    }).subscribe();

            .subscribe(
                    s -> {
                      System.out.println("SINGLE S: " + s);
                    },
                    e -> {
                      System.out.println("SINGLE E: " + e);

                    });
//            .subscribe(new Subscriber<String>() {
//              @Override
//              public void onCompleted() {
//                System.out.println("SC");
//              }
//
//              @Override
//              public void onError(Throwable ex) {
//                System.out.println("SE: " + ex);
//
//              }
//
//              @Override
//              public void onNext(String s) {
//                System.out.println("SN: " + s);
//
//              }
//            });


  }
}
